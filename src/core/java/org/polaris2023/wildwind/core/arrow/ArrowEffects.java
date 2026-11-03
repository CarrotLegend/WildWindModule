package org.polaris2023.wildwind.core.arrow;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import org.jspecify.annotations.Nullable;
import org.polaris2023.wildwind.core.component.ArrowComponent;
import org.polaris2023.wildwind.core.registry.ModDataComponents;

/**
 * 矢术（箭羽 / 箭杆 / 箭头）的实际玩法效果。
 * <p>
 * 组件随箭矢实体的 {@code pickupItemStack} 一起存读档，所以飞行中的箭随时能把
 * 自己的三个部件读回来，不需要额外的 attachment 或同步字段。
 * <p>
 * 所有效果都只在服务端执行，客户端靠 {@code sendParticles} 与爆炸包同步。
 *
 * @author baka4n
 */
public final class ArrowEffects {

    /** 风弹爆炸半径（与原版风弹的冲击范围接近，但不破坏方块） */
    private static final float WIND_BURST_RADIUS = 1.2F;
    /** 火药箭头的爆炸半径 */
    private static final float GUNPOWDER_RADIUS = 1.0F;
    /** 致燃时长（tick），烈焰粉 / 岩浆膏共用 */
    private static final int IGNITE_TICKS = 100;
    /** 紫颂果的随机传送半径 */
    private static final double CHORUS_RADIUS = 8.0;

    /**
     * 读取箭矢携带的部件数据；普通原版箭（没有组件）返回 null，走原版逻辑。
     */
    public static @Nullable ArrowComponent componentOf(AbstractArrow arrow) {
        ItemStack stack = arrow.getPickupItemStackOrigin();
        return stack.get(ModDataComponents.ARROW_COMPONENT.get());
    }

    /**
     * 飞行途中的持续效果：烈焰棒箭杆的火尾粒子。
     */
    public static void tick(AbstractArrow arrow, ArrowComponent component) {
        if (!(arrow.level() instanceof ServerLevel server)) {
            return;
        }
        // 只有飞行中的箭才留火尾（落地后速度归零）
        if (component.shaft().hasFireTrail() && arrow.getDeltaMovement().lengthSqr() > 1.0E-6) {
            server.sendParticles(ParticleTypes.FLAME,
                    arrow.getX(), arrow.getY(), arrow.getZ(),
                    2, 0.05, 0.05, 0.05, 0.01);
        }
    }

    /**
     * 命中实体时的箭头效果。
     */
    public static void onHitEntity(AbstractArrow arrow, ArrowComponent component, Entity target) {
        if (arrow.level().isClientSide()) {
            return;
        }
        switch (component.head()) {
            case GUNPOWDER -> explode(arrow, GUNPOWDER_RADIUS, Level.ExplosionInteraction.NONE);
            case WIND_CHARGE -> windBurst(arrow);
            case SLIME_BALL -> bounce(arrow, target);
            case BLAZE_POWDER -> target.igniteForTicks(IGNITE_TICKS);
            case MAGMA_CREAM -> {
                target.igniteForTicks(IGNITE_TICKS);
                bounce(arrow, target);
            }
            case CHORUS_FRUIT -> teleportRandomly(target);
            case ENDER_EYE -> swapWithOwner(arrow, target);
            default -> {
            }
        }
    }

    /**
     * 命中方块时的箭头 / 箭杆效果。
     */
    public static void onHitBlock(AbstractArrow arrow, ArrowComponent component, BlockHitResult hit) {
        if (arrow.level().isClientSide()) {
            return;
        }
        switch (component.head()) {
            case GUNPOWDER -> explode(arrow, GUNPOWDER_RADIUS, Level.ExplosionInteraction.NONE);
            case WIND_CHARGE -> windBurst(arrow);
            case COAL -> placeTorch(arrow, hit);
            case AMETHYST_SHARD -> breakSoftBlocks(arrow, hit);
            default -> {
            }
        }
        // 烈焰棒箭杆：落点生成火焰
        if (component.shaft().hasFireTrail()) {
            placeFire(arrow, hit);
        }
        // 旋风棒箭杆：落点风弹爆炸
        if (component.shaft().hasNoGravity()) {
            windBurst(arrow);
        }
    }

    private static void explode(AbstractArrow arrow, float radius, Level.ExplosionInteraction interaction) {
        arrow.level().explode(arrow, arrow.getX(), arrow.getY(), arrow.getZ(), radius, interaction);
    }

    /**
     * 风弹爆炸：不破坏方块，但会击退周围实体。
     */
    private static void windBurst(AbstractArrow arrow) {
        if (arrow.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.GUST_EMITTER_LARGE,
                    arrow.getX(), arrow.getY(), arrow.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
            server.sendParticles(ParticleTypes.EXPLOSION,
                    arrow.getX(), arrow.getY(), arrow.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
        }
        // 与原版风弹一致：只击退实体，不破坏、也不触发方块
        explode(arrow, WIND_BURST_RADIUS, Level.ExplosionInteraction.NONE);
    }

    /**
     * 弹力：把命中的实体朝远离箭矢的方向弹开。
     */
    private static void bounce(AbstractArrow arrow, Entity target) {
        Vec3 away = new Vec3(arrow.getX() - target.getX(), 0.0, arrow.getZ() - target.getZ());
        if (away.lengthSqr() < 1.0E-4) {
            Vec3 motion = arrow.getDeltaMovement();
            away = new Vec3(-motion.x, 0.0, -motion.z);
        }
        if (away.lengthSqr() < 1.0E-4) {
            away = new Vec3(0.0, 0.0, 1.0);
        }
        Vec3 push = away.normalize().scale(0.6).add(0.0, 0.4, 0.0);
        target.push(push);
    }

    /**
     * 紫颂果：目标在半径内随机传送。
     */
    private static void teleportRandomly(Entity target) {
        double x = target.getX() + (target.getRandom().nextDouble() - 0.5) * 2.0 * CHORUS_RADIUS;
        double y = target.getY() + (target.getRandom().nextInt(16) - 8);
        double z = target.getZ() + (target.getRandom().nextDouble() - 0.5) * 2.0 * CHORUS_RADIUS;
        if (target instanceof LivingEntity living) {
            living.randomTeleport(x, y, z, true);
        } else {
            target.teleportTo(x, y, z);
        }
    }

    /**
     * 末影之眼：射手与目标交换位置。
     */
    private static void swapWithOwner(AbstractArrow arrow, Entity target) {
        Entity owner = arrow.getOwner();
        if (owner == null || owner == target) {
            return;
        }
        Vec3 ownerPosition = owner.position();
        Vec3 targetPosition = target.position();
        owner.teleportTo(targetPosition.x, targetPosition.y, targetPosition.z);
        target.teleportTo(ownerPosition.x, ownerPosition.y, ownerPosition.z);
    }

    /**
     * 煤炭：命中面外侧放置火把。
     */
    private static void placeTorch(AbstractArrow arrow, BlockHitResult hit) {
        Level level = arrow.level();
        Direction direction = hit.getDirection();
        BlockPos pos = hit.getBlockPos().relative(direction);
        if (!level.getBlockState(pos).canBeReplaced()) {
            return;
        }

        BlockState torch;
        if (direction == Direction.UP) {
            torch = Blocks.TORCH.defaultBlockState();
        } else if (direction.getAxis().isHorizontal()) {
            torch = Blocks.WALL_TORCH.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, direction);
        } else {
            return;
        }

        if (!torch.canSurvive(level, pos)) {
            return;
        }
        level.setBlockAndUpdate(pos, torch);
    }

    /**
     * 烈焰棒箭杆：落点生成火焰。
     */
    private static void placeFire(AbstractArrow arrow, BlockHitResult hit) {
        Level level = arrow.level();
        BlockPos pos = hit.getBlockPos().relative(hit.getDirection());
        if (!level.getBlockState(pos).isAir()) {
            return;
        }

        BlockState fire = BaseFireBlock.getState(level, pos);
        if (!fire.canSurvive(level, pos)) {
            return;
        }
        level.setBlockAndUpdate(pos, fire);
    }

    /**
     * 紫水晶碎片：破坏命中位置附近的植物与玻璃类方块。
     */
    private static void breakSoftBlocks(AbstractArrow arrow, BlockHitResult hit) {
        Level level = arrow.level();
        BlockPos center = hit.getBlockPos().relative(hit.getDirection());
        BlockPos.betweenClosed(center.offset(-1, -1, -1), center.offset(1, 1, 1)).forEach(pos -> {
            BlockState state = level.getBlockState(pos);
            if (isSoftBlock(state)) {
                level.destroyBlock(pos.immutable(), false, arrow);
            }
        });
    }

    /**
     * 植物（{@link VegetationBlock}）与玻璃类（NeoForge 的 {@code c:glass_blocks} / {@code c:glass_panes} 标签）。
     */
    private static boolean isSoftBlock(BlockState state) {
        return state.getBlock() instanceof VegetationBlock
                || state.is(Tags.Blocks.GLASS_BLOCKS)
                || state.is(Tags.Blocks.GLASS_PANES);
    }

    private ArrowEffects() {
    }
}
