package org.polaris2023.wildwind.wilderness.entity.animal;

import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.object.PlayState;
import com.geckolib.animation.state.AnimationTest;
import com.geckolib.constant.DefaultAnimations;
import com.geckolib.util.GeckoLibUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.fish.AbstractFish;
import net.minecraft.world.entity.animal.fish.AbstractSchoolingFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.polaris2023.wildwind.core.entity.WindupAttackMob;
import org.polaris2023.wildwind.core.entity.ai.goal.AlertOthersNearestAttackableTargetGoal;
import org.polaris2023.wildwind.core.entity.ai.goal.ChargingMeleeAttackGoal;
import org.polaris2023.wildwind.wilderness.registry.ModItems;
import org.polaris2023.wildwind.wilderness.registry.ModSoundEvents;

import javax.annotation.Nullable;

public class Piranha extends AbstractSchoolingFish implements WindupAttackMob, GeoEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private int preparingToAttack;

    public Piranha(EntityType<? extends AbstractSchoolingFish> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractFish.createAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.95)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.ATTACK_DAMAGE, 3.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PiranhaAttackGoal(this, 2.0f, false));
        super.registerGoals();
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(1, new AlertOthersNearestAttackableTargetGoal<>(this, Mob.class, true,
                (entity, level) -> !(entity instanceof Piranha) && (entity.getHealth() <= entity.getMaxHealth() * 0.25))
                .overrideFindEntityFollowDistance(15.0)
        );
        this.targetSelector.addGoal(1, new AlertOthersNearestAttackableTargetGoal<>(this, Player.class, true,
                (entity, level) -> entity.getHealth() <= entity.getMaxHealth() * 0.25)
                .overrideFindEntityFollowDistance(15.0)
        );
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return ModSoundEvents.PIRANHA_FLOP.get();
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return ModSoundEvents.PIRANHA_DEATH.get();
    }

    protected SoundEvent getAttackSound() {
        return ModSoundEvents.PIRANHA_ATTACK.get();
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack((ItemLike) ModItems.PIRANHA_BUCKET);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("Move", 3, this::moveAnimController));
        controllers.add(new AnimationController<>("Attack", state -> PlayState.STOP)
                .triggerableAnim("attack", DefaultAnimations.ATTACK_SWING)
        );
    }

    protected PlayState moveAnimController(final AnimationTest<Piranha> state) {
        return state.setAndContinue(this.isInWater() ? DefaultAnimations.SWIM : DefaultAnimations.IDLE);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Override
    public void setPreparingToAttackTick(int tick) {
        this.preparingToAttack = tick;
    }

    @Override
    public int getPreparingToAttackTick() {
        return this.preparingToAttack;
    }

    @Override
    public int getPreparationToAttackDuration() {
        return 6;
    }

    @Override
    public boolean prepareAttack(LivingEntity entity) {
        boolean result = WindupAttackMob.super.prepareAttack(entity);
        if (result) {
            this.triggerAnim("Attack", "attack");
            this.makeSound(this.getAttackSound());
        }
        return result;
    }

    @Override
    public boolean canContinuePreparingAttack() {
        return this.isInWater();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide() && this.isAlive()) {
            processPreparingAttack((ServerLevel) this.level(), this);
        }
    }

    protected static class PiranhaAttackGoal extends ChargingMeleeAttackGoal<Piranha> {
        public PiranhaAttackGoal(Piranha mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
            super(mob, speedModifier, followingTargetEvenIfNotSeen);
        }

        @Override
        public boolean canUse() {
            return this.mob.isInWater() && super.canUse();
        }
    }
}