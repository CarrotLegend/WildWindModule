package org.polaris2023.wildwind.barren.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.barren.WildWindBarren;
import org.polaris2023.wildwind.barren.worldgen.BarrenTreeGrowers;

/**
 * 瘠土更新的方块注册：砂岩 / 沙 / 流沙 / 焦土 / 下界木质套件。
 */
public final class BarrenBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(WildWindBarren.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WildWindBarren.MOD_ID);

    // ==================== 木质类型 ====================

    public static final BlockSetType BLAZE_BLOCK_SET = BlockSetType.register(new BlockSetType("blaze"));
    public static final WoodType BLAZE_WOOD_TYPE = WoodType.register(new WoodType("blaze", BLAZE_BLOCK_SET));

    public static final BlockSetType SOUL_BLOCK_SET = BlockSetType.register(new BlockSetType("soul"));
    public static final WoodType SOUL_WOOD_TYPE = WoodType.register(new WoodType("soul", SOUL_BLOCK_SET));

    // ==================== 焚烬木套件 (blaze_wood) ====================

    public static final DeferredBlock<RotatedPillarBlock> BLAZE_LOG = BLOCKS.registerBlock(
            "blaze_log",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<RotatedPillarBlock> BLAZE_WOOD = BLOCKS.registerBlock(
            "blaze_wood",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BLAZE_LOG = BLOCKS.registerBlock(
            "stripped_blaze_log",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BLAZE_WOOD = BLOCKS.registerBlock(
            "stripped_blaze_wood",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<Block> BLAZE_PLANKS = BLOCKS.registerBlock(
            "blaze_planks",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<StairBlock> BLAZE_STAIRS = BLOCKS.registerBlock(
            "blaze_stairs",
            p -> new StairBlock(BLAZE_PLANKS.get().defaultBlockState(), p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<SlabBlock> BLAZE_SLAB = BLOCKS.registerBlock(
            "blaze_slab",
            p -> new SlabBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<FenceBlock> BLAZE_FENCE = BLOCKS.registerBlock(
            "blaze_fence",
            p -> new FenceBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<FenceGateBlock> BLAZE_FENCE_GATE = BLOCKS.registerBlock(
            "blaze_fence_gate",
            p -> new FenceGateBlock(BLAZE_WOOD_TYPE, p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<DoorBlock> BLAZE_DOOR = BLOCKS.registerBlock(
            "blaze_door",
            p -> new DoorBlock(BLAZE_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<TrapDoorBlock> BLAZE_TRAPDOOR = BLOCKS.registerBlock(
            "blaze_trapdoor",
            p -> new TrapDoorBlock(BLAZE_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<PressurePlateBlock> BLAZE_PRESSURE_PLATE = BLOCKS.registerBlock(
            "blaze_pressure_plate",
            p -> new PressurePlateBlock(BLAZE_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.5F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<ButtonBlock> BLAZE_BUTTON = BLOCKS.registerBlock(
            "blaze_button",
            p -> new ButtonBlock(BLAZE_BLOCK_SET, 30, p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.5F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<LeavesBlock> BLAZE_LEAVES = BLOCKS.registerBlock(
            "blaze_leaves",
            p -> new TintedParticleLeavesBlock(0.01F, p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.2F)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .randomTicks())
    );

    public static final DeferredBlock<SaplingBlock> BLAZE_SAPLING = BLOCKS.registerBlock(
            "blaze_sapling",
            p -> new SaplingBlock(BarrenTreeGrowers.BLAZE, p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .randomTicks())
    );

    public static final DeferredBlock<FlowerPotBlock> POTTED_BLAZE_SAPLING = BLOCKS.registerBlock(
            "potted_blaze_sapling",
            p -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    BLAZE_SAPLING,
                    p.instabreak()
                            .noOcclusion()
                            .pushReaction(PushReaction.DESTROY))
    );

    // ==================== 灵焰木套件 (soul_wood) ====================

    public static final DeferredBlock<RotatedPillarBlock> SOUL_LOG = BLOCKS.registerBlock(
            "soul_log",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<RotatedPillarBlock> SOUL_WOOD = BLOCKS.registerBlock(
            "soul_wood",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SOUL_LOG = BLOCKS.registerBlock(
            "stripped_soul_log",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SOUL_WOOD = BLOCKS.registerBlock(
            "stripped_soul_wood",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<Block> SOUL_PLANKS = BLOCKS.registerBlock(
            "soul_planks",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<StairBlock> SOUL_STAIRS = BLOCKS.registerBlock(
            "soul_stairs",
            p -> new StairBlock(SOUL_PLANKS.get().defaultBlockState(), p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<SlabBlock> SOUL_SLAB = BLOCKS.registerBlock(
            "soul_slab",
            p -> new SlabBlock(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<FenceBlock> SOUL_FENCE = BLOCKS.registerBlock(
            "soul_fence",
            p -> new FenceBlock(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<FenceGateBlock> SOUL_FENCE_GATE = BLOCKS.registerBlock(
            "soul_fence_gate",
            p -> new FenceGateBlock(SOUL_WOOD_TYPE, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<DoorBlock> SOUL_DOOR = BLOCKS.registerBlock(
            "soul_door",
            p -> new DoorBlock(SOUL_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<TrapDoorBlock> SOUL_TRAPDOOR = BLOCKS.registerBlock(
            "soul_trapdoor",
            p -> new TrapDoorBlock(SOUL_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<PressurePlateBlock> SOUL_PRESSURE_PLATE = BLOCKS.registerBlock(
            "soul_pressure_plate",
            p -> new PressurePlateBlock(SOUL_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(0.5F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<ButtonBlock> SOUL_BUTTON = BLOCKS.registerBlock(
            "soul_button",
            p -> new ButtonBlock(SOUL_BLOCK_SET, 30, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(0.5F)
                    .sound(SoundType.WOOD))
    );

    public static final DeferredBlock<LeavesBlock> SOUL_LEAVES = BLOCKS.registerBlock(
            "soul_leaves",
            p -> new TintedParticleLeavesBlock(0.01F, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(0.2F)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .randomTicks())
    );

    public static final DeferredBlock<SaplingBlock> SOUL_SAPLING = BLOCKS.registerBlock(
            "soul_sapling",
            p -> new SaplingBlock(BarrenTreeGrowers.SOUL, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .randomTicks())
    );

    public static final DeferredBlock<FlowerPotBlock> POTTED_SOUL_SAPLING = BLOCKS.registerBlock(
            "potted_soul_sapling",
            p -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    SOUL_SAPLING,
                    p.instabreak()
                            .noOcclusion()
                            .pushReaction(PushReaction.DESTROY))
    );

    // ==================== 焦土 / 瘠地装饰 ====================

    /** 焦灰草方块 */
    public static final DeferredBlock<Block> SCORCHED_GRASS_BLOCK = BLOCKS.registerBlock(
            "scorched_grass_block",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(0.6F)
                    .sound(SoundType.GRASS)
                    .randomTicks())
    );

    /** 焦土 */
    public static final DeferredBlock<Block> SCORCHED_DIRT = BLOCKS.registerBlock(
            "scorched_dirt",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(0.5F)
                    .sound(SoundType.GRAVEL))
    );

    /** 焦灰草丛 */
    public static final DeferredBlock<Block> SCORCHED_GRASS = BLOCKS.registerBlock(
            "scorched_grass",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BLACK)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY))
    );

    /** 焦灰枝条 */
    public static final DeferredBlock<Block> SCORCHED_TWIG = BLOCKS.registerBlock(
            "scorched_twig",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BLACK)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY))
    );

    /** 仙人球 */
    public static final DeferredBlock<Block> TINY_CACTUS = BLOCKS.registerBlock(
            "tiny_cactus",
            p -> new Block(p
                    .mapColor(MapColor.PLANT)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.WOOL)
                    .pushReaction(PushReaction.DESTROY))
    );

    /** 流沙 */
    public static final DeferredBlock<Block> QUICKSAND = BLOCKS.registerBlock(
            "quicksand",
            p -> new Block(p
                    .mapColor(MapColor.SAND)
                    .strength(0.5F)
                    .sound(SoundType.SAND))
    );

    /** 红色流沙 */
    public static final DeferredBlock<Block> RED_QUICKSAND = BLOCKS.registerBlock(
            "red_quicksand",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.5F)
                    .sound(SoundType.SAND))
    );

    /** 可疑的红沙 */
    public static final DeferredBlock<Block> SUSPICIOUS_RED = BLOCKS.registerBlock(
            "suspicious_red_sand",
            p -> new BrushableBlock(
                    Blocks.RED_SAND,
                    SoundEvents.BRUSH_SAND,
                    SoundEvents.BRUSH_SAND_COMPLETED, p
                    .mapColor(MapColor.SAND)
                    .strength(0.5F)
                    .sound(SoundType.SAND))
    );

    // ==================== 砂岩 / 沙 ====================

    public static final DeferredBlock<Block> CHISELED_QUARTZ_SANDSTONE = BLOCKS.registerBlock(
            "chiseled_quartz_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final DeferredBlock<Block> CHISELED_SOUL_SANDSTONE = BLOCKS.registerBlock(
            "chiseled_soul_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final DeferredBlock<Block> CUT_QUARTZ_SANDSTONE = BLOCKS.registerBlock(
            "cut_quartz_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final DeferredBlock<Block> CUT_SOUL_SANDSTONE = BLOCKS.registerBlock(
            "cut_soul_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final DeferredBlock<Block> NITRIFIED_QUARTZ_SANDSTONE = BLOCKS.registerBlock(
            "nitrified_quartz_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final DeferredBlock<Block> NITRIFIED_RED_SANDSTONE = BLOCKS.registerBlock(
            "nitrified_red_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final DeferredBlock<Block> NITRIFIED_SANDSTONE = BLOCKS.registerBlock(
            "nitrified_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.SAND)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final DeferredBlock<Block> NITRIFIED_SOUL_SANDSTONE = BLOCKS.registerBlock(
            "nitrified_soul_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final DeferredBlock<Block> QUARTZ_SANDSTONE = BLOCKS.registerBlock(
            "quartz_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final DeferredBlock<Block> SOUL_SANDSTONE = BLOCKS.registerBlock(
            "soul_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final DeferredBlock<Block> SMOOTH_QUARTZ_SANDSTONE = BLOCKS.registerBlock(
            "smooth_quartz_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final DeferredBlock<Block> SMOOTH_SOUL_SANDSTONE = BLOCKS.registerBlock(
            "smooth_soul_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final DeferredBlock<SandBlock> QUARTZ_SAND = BLOCKS.registerBlock(
            "quartz_sand",
            p -> new SandBlock(new ColorRGBA(0xFFDBD3C0), p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.5F)
                    .sound(SoundType.SAND))
    );

    public static final DeferredBlock<Block> QUARTZ_QUICKSAND = BLOCKS.registerBlock(
            "quartz_quicksand",
            p -> new Block(p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.5F)
                    .sound(SoundType.SAND))
    );

    public static final DeferredBlock<Block> SOUL_QUICKSAND = BLOCKS.registerBlock(
            "soul_quicksand",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.5F)
                    .sound(SoundType.SAND))
    );

    public static final DeferredBlock<Block> SUSPICIOUS_QUARTZ_SAND = BLOCKS.registerBlock(
            "suspicious_quartz_sand",
            p -> new BrushableBlock(
                    Blocks.SAND,
                    SoundEvents.BRUSH_SAND,
                    SoundEvents.BRUSH_SAND_COMPLETED, p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.5F)
                    .sound(SoundType.SAND))
    );

    public static final DeferredBlock<Block> SUSPICIOUS_SOUL_SAND = BLOCKS.registerBlock(
            "suspicious_soul_sand",
            p -> new BrushableBlock(
                    Blocks.SOUL_SAND,
                    SoundEvents.BRUSH_SAND,
                    SoundEvents.BRUSH_SAND_COMPLETED, p
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.5F)
                    .sound(SoundType.SOUL_SAND))
    );

    public static final DeferredBlock<Block> ASH_BLOCK = BLOCKS.registerBlock(
            "ash_block",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_GRAY)
                    .strength(0.5F)
                    .sound(SoundType.SAND))
    );

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        blockItems();
        ITEMS.register(bus);
    }

    public static void blockItems() {
        // 焚烬木 / 灵焰木套件
        ITEMS.registerSimpleBlockItem(BLAZE_LOG);
        ITEMS.registerSimpleBlockItem(BLAZE_WOOD);
        ITEMS.registerSimpleBlockItem(STRIPPED_BLAZE_LOG);
        ITEMS.registerSimpleBlockItem(STRIPPED_BLAZE_WOOD);
        ITEMS.registerSimpleBlockItem(BLAZE_PLANKS);
        ITEMS.registerSimpleBlockItem(BLAZE_STAIRS);
        ITEMS.registerSimpleBlockItem(BLAZE_SLAB);
        ITEMS.registerSimpleBlockItem(BLAZE_FENCE);
        ITEMS.registerSimpleBlockItem(BLAZE_FENCE_GATE);
        ITEMS.registerSimpleBlockItem(BLAZE_DOOR);
        ITEMS.registerSimpleBlockItem(BLAZE_TRAPDOOR);
        ITEMS.registerSimpleBlockItem(BLAZE_PRESSURE_PLATE);
        ITEMS.registerSimpleBlockItem(BLAZE_BUTTON);
        ITEMS.registerSimpleBlockItem(BLAZE_LEAVES);
        ITEMS.registerSimpleBlockItem(BLAZE_SAPLING);

        ITEMS.registerSimpleBlockItem(SOUL_LOG);
        ITEMS.registerSimpleBlockItem(SOUL_WOOD);
        ITEMS.registerSimpleBlockItem(STRIPPED_SOUL_LOG);
        ITEMS.registerSimpleBlockItem(STRIPPED_SOUL_WOOD);
        ITEMS.registerSimpleBlockItem(SOUL_PLANKS);
        ITEMS.registerSimpleBlockItem(SOUL_STAIRS);
        ITEMS.registerSimpleBlockItem(SOUL_SLAB);
        ITEMS.registerSimpleBlockItem(SOUL_FENCE);
        ITEMS.registerSimpleBlockItem(SOUL_FENCE_GATE);
        ITEMS.registerSimpleBlockItem(SOUL_DOOR);
        ITEMS.registerSimpleBlockItem(SOUL_TRAPDOOR);
        ITEMS.registerSimpleBlockItem(SOUL_PRESSURE_PLATE);
        ITEMS.registerSimpleBlockItem(SOUL_BUTTON);
        ITEMS.registerSimpleBlockItem(SOUL_LEAVES);
        ITEMS.registerSimpleBlockItem(SOUL_SAPLING);

        // 焦土 / 瘠地
        ITEMS.registerSimpleBlockItem(SCORCHED_GRASS_BLOCK);
        ITEMS.registerSimpleBlockItem(SCORCHED_DIRT);
        ITEMS.registerSimpleBlockItem(SCORCHED_GRASS);
        ITEMS.registerSimpleBlockItem(SCORCHED_TWIG);
        ITEMS.registerSimpleBlockItem(TINY_CACTUS);
        ITEMS.registerSimpleBlockItem(QUICKSAND);
        ITEMS.registerSimpleBlockItem(RED_QUICKSAND);
        ITEMS.registerSimpleBlockItem(SUSPICIOUS_RED);

        // 砂岩 / 沙
        ITEMS.registerSimpleBlockItem(CHISELED_QUARTZ_SANDSTONE);
        ITEMS.registerSimpleBlockItem(CHISELED_SOUL_SANDSTONE);
        ITEMS.registerSimpleBlockItem(CUT_QUARTZ_SANDSTONE);
        ITEMS.registerSimpleBlockItem(CUT_SOUL_SANDSTONE);
        ITEMS.registerSimpleBlockItem(NITRIFIED_QUARTZ_SANDSTONE);
        ITEMS.registerSimpleBlockItem(NITRIFIED_RED_SANDSTONE);
        ITEMS.registerSimpleBlockItem(NITRIFIED_SANDSTONE);
        ITEMS.registerSimpleBlockItem(NITRIFIED_SOUL_SANDSTONE);
        ITEMS.registerSimpleBlockItem(QUARTZ_SANDSTONE);
        ITEMS.registerSimpleBlockItem(SOUL_SANDSTONE);
        ITEMS.registerSimpleBlockItem(SMOOTH_QUARTZ_SANDSTONE);
        ITEMS.registerSimpleBlockItem(SMOOTH_SOUL_SANDSTONE);
        ITEMS.registerSimpleBlockItem(QUARTZ_SAND);
        ITEMS.registerSimpleBlockItem(QUARTZ_QUICKSAND);
        ITEMS.registerSimpleBlockItem(SOUL_QUICKSAND);
        ITEMS.registerSimpleBlockItem(SUSPICIOUS_QUARTZ_SAND);
        ITEMS.registerSimpleBlockItem(SUSPICIOUS_SOUL_SAND);
        ITEMS.registerSimpleBlockItem(ASH_BLOCK);
    }

    private BarrenBlocks() {
    }
}
