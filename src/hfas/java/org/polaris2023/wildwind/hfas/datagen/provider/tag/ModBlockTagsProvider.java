package org.polaris2023.wildwind.hfas.datagen.provider.tag;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.polaris2023.wildwind.hfas.HFASMod;
import org.polaris2023.wildwind.hfas.block.ModBlocks;
import org.polaris2023.wildwind.hfas.block.ModWoodFamilies;
import org.polaris2023.wildwind.hfas.block.WoodFamily;
import org.polaris2023.wildwind.hfas.tag.ModBlockTags;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * 方块标签生成器
 * 为木质方块添加原版和模组标签
 *
 * @author baka4n
 * @since 2026/04/15
 */
public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, HFASMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // ==================== 三种木材标签 ====================
        for (WoodFamily family : ModWoodFamilies.ALL) {
            registerWoodTags(family);
        }

        // ==================== 其他方块标签 ====================
        // 焦灰草方块 - 泥土类
        addToTag(BlockTags.DIRT, ModBlocks.SCORCHED_GRASS_BLOCK.get(), ModBlocks.SCORCHED_DIRT.get());

        // 制箭台 - 工作站类
        addToTag(BlockTags.MINEABLE_WITH_AXE, ModBlocks.FLETCHING_TABLE.get());

        // 可疑红沙 - 沙子类
        addToTag(BlockTags.MINEABLE_WITH_SHOVEL, ModBlocks.SUSPICIOUS_RED.get());

        // 灵魂南瓜灯 - 灯具类
        addToTag(BlockTags.MINEABLE_WITH_AXE, ModBlocks.SOUL_JACK_O_LANTERN.get());
        addToTag(BlockTags.PIGLIN_REPELLENTS, ModBlocks.SOUL_JACK_O_LANTERN.get());

        // 砂岩 - 砂岩类
        addToTag(BlockTags.MINEABLE_WITH_PICKAXE,
                ModBlocks.CHISELED_QUARTZ_SANDSTONE.get(),
                ModBlocks.CHISELED_SOUL_SANDSTONE.get(),
                ModBlocks.CUT_QUARTZ_SANDSTONE.get(),
                ModBlocks.CUT_SOUL_SANDSTONE.get(),
                ModBlocks.NITRIFIED_QUARTZ_SANDSTONE.get(),
                ModBlocks.NITRIFIED_RED_SANDSTONE.get(),
                ModBlocks.NITRIFIED_SANDSTONE.get(),
                ModBlocks.NITRIFIED_SOUL_SANDSTONE.get(),
                ModBlocks.QUARTZ_SANDSTONE.get(),
                ModBlocks.SOUL_SANDSTONE.get());

        // 平滑砂岩 - 砂岩类
        addToTag(BlockTags.MINEABLE_WITH_PICKAXE,
                ModBlocks.SMOOTH_QUARTZ_SANDSTONE.get(),
                ModBlocks.SMOOTH_SOUL_SANDSTONE.get());

        addToTag(BlockTags.MINEABLE_WITH_PICKAXE,
                ModBlocks.QUARTZ_SANDSTONE_STAIRS.get(),
                ModBlocks.QUARTZ_SANDSTONE_SLAB.get(),
                ModBlocks.QUARTZ_SANDSTONE_WALL.get(),
                ModBlocks.SMOOTH_QUARTZ_SANDSTONE_STAIRS.get(),
                ModBlocks.SMOOTH_QUARTZ_SANDSTONE_SLAB.get(),
                ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB.get(),
                ModBlocks.SOUL_SANDSTONE_STAIRS.get(),
                ModBlocks.SOUL_SANDSTONE_SLAB.get(),
                ModBlocks.SOUL_SANDSTONE_WALL.get(),
                ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get(),
                ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(),
                ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get());
        addToTag(BlockItemTags.STAIRS.block(),
                ModBlocks.QUARTZ_SANDSTONE_STAIRS.get(),
                ModBlocks.SMOOTH_QUARTZ_SANDSTONE_STAIRS.get(),
                ModBlocks.SOUL_SANDSTONE_STAIRS.get(),
                ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get());
        addToTag(BlockItemTags.SLABS.block(),
                ModBlocks.QUARTZ_SANDSTONE_SLAB.get(),
                ModBlocks.SMOOTH_QUARTZ_SANDSTONE_SLAB.get(),
                ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB.get(),
                ModBlocks.SOUL_SANDSTONE_SLAB.get(),
                ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(),
                ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get());
        addToTag(BlockItemTags.WALLS.block(),
                ModBlocks.QUARTZ_SANDSTONE_WALL.get(),
                ModBlocks.SOUL_SANDSTONE_WALL.get());

        // 石英沙 / 流沙 / 可疑沙 - 沙子类
        addToTag(BlockTags.MINEABLE_WITH_SHOVEL,
                ModBlocks.QUARTZ_SAND.get(),
                ModBlocks.QUARTZ_QUICKSAND.get(),
                ModBlocks.SOUL_QUICKSAND.get(),
                ModBlocks.SUSPICIOUS_QUARTZ_SAND.get(),
                ModBlocks.SUSPICIOUS_SOUL_SAND.get());

        // 玫瑰 / 灵魂玫瑰 / 灵魂玫瑰丛 - 花类
        addToTag(BlockTags.SMALL_FLOWERS, ModBlocks.ROSE.get(), ModBlocks.SOUL_ROSE.get());
        addToTag(BlockTags.FLOWERS, ModBlocks.SOUL_ROSE_BUSH.get());


        // ==================== 实体生成有关 ====================

        addToTag(ModBlockTags.EntityAbout.MUDCRAB_SPAWNABLE_ON, Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS);

        addToTag(ModBlockTags.EntityAbout.MUDCRAB_SPAWNABLE_IN_WATER_GROUND, Blocks.CLAY, Blocks.MUD, Blocks.DIRT, Blocks.MUDDY_MANGROVE_ROOTS)
                .addTag(BlockTags.SAND)
                .addTag(Tags.Blocks.GRAVELS);

        addToTag(ModBlockTags.EntityAbout.MUDCRAB_PREFERRED_WANDER_BLOCKS,Blocks.MANGROVE_LOG, Blocks.MANGROVE_ROOTS, Blocks.MUDDY_MANGROVE_ROOTS);
    }

    /**
     * 注册一种木材的方块标签
     */
    private void registerWoodTags(WoodFamily family) {
        Block log = family.log().get();
        Block wood = family.wood().get();
        Block strippedLog = family.strippedLog().get();
        Block strippedWood = family.strippedWood().get();
        Block planks = family.planks().get();
        Block stairs = family.stairs().get();
        Block slab = family.slab().get();
        Block fence = family.fence().get();
        Block fenceGate = family.fenceGate().get();
        Block door = family.door().get();
        Block trapdoor = family.trapdoor().get();
        Block pressurePlate = family.pressurePlate().get();
        Block button = family.button().get();

        // 原木标签 - 用于原木类方块
        addToTag(BlockTags.LOGS, log, wood, strippedLog, strippedWood);
        // 可燃原木（木炭配方等依赖）
        addToTag(BlockItemTags.LOGS_THAT_BURN.block(), log, wood, strippedLog, strippedWood);
        // 去皮原木 / 去皮木（跨模组通用）
        addToTag(Tags.Blocks.STRIPPED_LOGS, strippedLog);
        addToTag(Tags.Blocks.STRIPPED_WOODS, strippedWood);

        // 木板标签
        addToTag(BlockTags.PLANKS, planks);

        // 木质楼梯
        addToTag(BlockTags.WOODEN_STAIRS, stairs);

        // 木质台阶
        addToTag(BlockTags.WOODEN_SLABS, slab);

        // 木质栅栏
        addToTag(BlockTags.WOODEN_FENCES, fence);
        addToTag(Tags.Blocks.FENCES_WOODEN, fence);
        // 栅栏标签（所有栅栏）
        addToTag(BlockTags.FENCES, fence);

        // 木质栅栏门
        addToTag(Tags.Blocks.FENCE_GATES_WOODEN, fenceGate);
        // 栅栏门标签（所有栅栏门）
        addToTag(BlockTags.FENCE_GATES, fenceGate);

        // 木质门
        addToTag(BlockTags.WOODEN_DOORS, door);

        // 门标签（所有门）
        addToTag(BlockTags.DOORS, door);

        // 活板门
        addToTag(BlockTags.TRAPDOORS, trapdoor);

        // 木质活板门
        addToTag(BlockTags.WOODEN_TRAPDOORS, trapdoor);

        // 木质压力板
        addToTag(BlockTags.WOODEN_PRESSURE_PLATES, pressurePlate);

        // 木质按钮
        addToTag(BlockTags.WOODEN_BUTTONS, button);

        // 树叶 / 树苗（杜鹃木沿用原版，没有自成套的树叶）
        if (family.leaves() != null) {
            addToTag(BlockTags.LEAVES, family.leaves().get());
        }
        if (family.sapling() != null) {
            addToTag(BlockItemTags.SAPLINGS.block(), family.sapling().get());
        }

        // 挖掘工具标签
        addToTag(BlockTags.MINEABLE_WITH_AXE, log, wood, strippedLog, strippedWood, planks, stairs, slab, fence, fenceGate, door, trapdoor, pressurePlate, button);

        // 自然原木（用于世界生成相关判断）
        addToTag(BlockTags.OVERWORLD_NATURAL_LOGS, log);
    }

    @SuppressWarnings("all")
    public TagAppender<Block> addToTag(TagKey<Block> tag, Block... items) {
        if (items.length == 0) return tag(tag);
        return tag(tag).addAll(Arrays.stream(items).map(Block::builtInRegistryHolder).map(Holder.Reference::getKey));
    }
}
