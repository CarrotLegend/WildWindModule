package org.polaris2023.wildwind.hfas.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.hfas.HFASMod;
import org.polaris2023.wildwind.hfas.worldgen.ModTreeGrowers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 方块注册类
 * 使用NeoForge 26.1的DeferredRegister方式注册方块
 * 注意：方块和物品分开注册，避免延迟注册bug
 *
 * @author baka4n
 * @since 2026/04/15
 */
//TODO 考虑迁移至registry目录下，或将registry目录下的注册条目分散到各目录下
public class ModBlocks {
    // 方块注册器
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(HFASMod.MOD_ID);
    // 物品注册器
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HFASMod.MOD_ID);

    // ==================== 方块类型定义 ====================

    // 焚烬木方块类型
    public static final BlockSetType BLAZE_BLOCK_SET = BlockSetType.register(new BlockSetType("blaze"));
    // 焚烬木木材类型
    public static final WoodType BLAZE_WOOD_TYPE = WoodType.register(new WoodType("blaze", BLAZE_BLOCK_SET));

    // 灵焰木方块类型
    public static final BlockSetType SOUL_BLOCK_SET = BlockSetType.register(new BlockSetType("soul"));
    // 灵焰木木材类型
    public static final WoodType SOUL_WOOD_TYPE = WoodType.register(new WoodType("soul", SOUL_BLOCK_SET));

    // 杜鹃木方块类型
    public static final BlockSetType AZALEA_BLOCK_SET = BlockSetType.register(new BlockSetType("azalea"));
    // 杜鹃木木材类型
    public static final WoodType AZALEA_WOOD_TYPE = WoodType.register(new WoodType("azalea", AZALEA_BLOCK_SET));

    // ==================== 焚烬木套件 (blaze_wood) ====================

    // 焚烬木原木
    public static final DeferredBlock<RotatedPillarBlock> BLAZE_LOG = BLOCKS.registerBlock(
            "blaze_log",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 焚烬木
    public static final DeferredBlock<RotatedPillarBlock> BLAZE_WOOD = BLOCKS.registerBlock(
            "blaze_wood",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 去皮焚烬木原木
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BLAZE_LOG = BLOCKS.registerBlock(
            "stripped_blaze_log",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 去皮焚烬木
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_BLAZE_WOOD = BLOCKS.registerBlock(
            "stripped_blaze_wood",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 焚烬木木板
    public static final DeferredBlock<Block> BLAZE_PLANKS = BLOCKS.registerBlock(
            "blaze_planks",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 焚烬木楼梯
    public static final DeferredBlock<StairBlock> BLAZE_STAIRS = BLOCKS.registerBlock(
            "blaze_stairs",
            p -> new StairBlock(BLAZE_PLANKS.get().defaultBlockState(), p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 焚烬木台阶
    public static final DeferredBlock<SlabBlock> BLAZE_SLAB = BLOCKS.registerBlock(
            "blaze_slab",
            p -> new SlabBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 焚烬木栅栏
    public static final DeferredBlock<FenceBlock> BLAZE_FENCE = BLOCKS.registerBlock(
            "blaze_fence",
            p -> new FenceBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 焚烬木栅栏门
    public static final DeferredBlock<FenceGateBlock> BLAZE_FENCE_GATE = BLOCKS.registerBlock(
            "blaze_fence_gate",
            p -> new FenceGateBlock(BLAZE_WOOD_TYPE, p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 焚烬木门
    public static final DeferredBlock<DoorBlock> BLAZE_DOOR = BLOCKS.registerBlock(
            "blaze_door",
            p -> new DoorBlock(BLAZE_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 焚烬木活板门
    public static final DeferredBlock<TrapDoorBlock> BLAZE_TRAPDOOR = BLOCKS.registerBlock(
            "blaze_trapdoor",
            p -> new TrapDoorBlock(BLAZE_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 焚烬木压力板
    public static final DeferredBlock<PressurePlateBlock> BLAZE_PRESSURE_PLATE = BLOCKS.registerBlock(
            "blaze_pressure_plate",
            p -> new PressurePlateBlock(BLAZE_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.5F)
                    .sound(SoundType.WOOD))
    );

    // 焚烬木按钮
    public static final DeferredBlock<ButtonBlock> BLAZE_BUTTON = BLOCKS.registerBlock(
            "blaze_button",
            p -> new ButtonBlock(BLAZE_BLOCK_SET, 30, p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.5F)
                    .sound(SoundType.WOOD))
    );

    // 焚烬木树叶
    public static final DeferredBlock<LeavesBlock> BLAZE_LEAVES = BLOCKS.registerBlock(
            "blaze_leaves",
            p -> new TintedParticleLeavesBlock(0.01F,p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.2F)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .randomTicks())
    );

    // 焚烬木树苗
    public static final DeferredBlock<SaplingBlock> BLAZE_SAPLING = BLOCKS.registerBlock(
            "blaze_sapling",
            p -> new SaplingBlock(ModTreeGrowers.BLAZE, p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .randomTicks())
    );

    // 焚烬木树苗花盆（原版构造器，会自动把树苗登记进原版花盆的映射表）
    @SuppressWarnings("deprecation")
    public static final DeferredBlock<FlowerPotBlock> POTTED_BLAZE_SAPLING = BLOCKS.registerBlock(
            "potted_blaze_sapling",
            p -> new FlowerPotBlock(BLAZE_SAPLING.get(), p
                    .instabreak()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY))
    );

    // ==================== 灵焰木套件 (soul_wood) ====================

    // 灵焰木原木
    public static final DeferredBlock<RotatedPillarBlock> SOUL_LOG = BLOCKS.registerBlock(
            "soul_log",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 灵焰木
    public static final DeferredBlock<RotatedPillarBlock> SOUL_WOOD = BLOCKS.registerBlock(
            "soul_wood",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 去皮灵焰木原木
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SOUL_LOG = BLOCKS.registerBlock(
            "stripped_soul_log",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 去皮灵焰木
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SOUL_WOOD = BLOCKS.registerBlock(
            "stripped_soul_wood",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 灵焰木木板
    public static final DeferredBlock<Block> SOUL_PLANKS = BLOCKS.registerBlock(
            "soul_planks",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 灵焰木楼梯
    public static final DeferredBlock<StairBlock> SOUL_STAIRS = BLOCKS.registerBlock(
            "soul_stairs",
            p -> new StairBlock(SOUL_PLANKS.get().defaultBlockState(), p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 灵焰木台阶
    public static final DeferredBlock<SlabBlock> SOUL_SLAB = BLOCKS.registerBlock(
            "soul_slab",
            p -> new SlabBlock(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 灵焰木栅栏
    public static final DeferredBlock<FenceBlock> SOUL_FENCE = BLOCKS.registerBlock(
            "soul_fence",
            p -> new FenceBlock(p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 灵焰木栅栏门
    public static final DeferredBlock<FenceGateBlock> SOUL_FENCE_GATE = BLOCKS.registerBlock(
            "soul_fence_gate",
            p -> new FenceGateBlock(SOUL_WOOD_TYPE, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 灵焰木门
    public static final DeferredBlock<DoorBlock> SOUL_DOOR = BLOCKS.registerBlock(
            "soul_door",
            p -> new DoorBlock(SOUL_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 灵焰木活板门
    public static final DeferredBlock<TrapDoorBlock> SOUL_TRAPDOOR = BLOCKS.registerBlock(
            "soul_trapdoor",
            p -> new TrapDoorBlock(SOUL_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 灵焰木压力板
    public static final DeferredBlock<PressurePlateBlock> SOUL_PRESSURE_PLATE = BLOCKS.registerBlock(
            "soul_pressure_plate",
            p -> new PressurePlateBlock(SOUL_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(0.5F)
                    .sound(SoundType.WOOD))
    );

    // 灵焰木按钮
    public static final DeferredBlock<ButtonBlock> SOUL_BUTTON = BLOCKS.registerBlock(
            "soul_button",
            p -> new ButtonBlock(SOUL_BLOCK_SET, 30, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(0.5F)
                    .sound(SoundType.WOOD))
    );

    // 灵焰木树叶
    public static final DeferredBlock<LeavesBlock> SOUL_LEAVES = BLOCKS.registerBlock(
            "soul_leaves",
            p -> new TintedParticleLeavesBlock(0.01F, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .strength(0.2F)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .randomTicks())
    );

    // 灵焰木树苗
    public static final DeferredBlock<SaplingBlock> SOUL_SAPLING = BLOCKS.registerBlock(
            "soul_sapling",
            p -> new SaplingBlock(ModTreeGrowers.SOUL, p
                    .mapColor(MapColor.COLOR_CYAN)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .randomTicks())
    );

    // 灵焰木树苗花盆（原版构造器，会自动把树苗登记进原版花盆的映射表）
    @SuppressWarnings("deprecation")
    public static final DeferredBlock<FlowerPotBlock> POTTED_SOUL_SAPLING = BLOCKS.registerBlock(
            "potted_soul_sapling",
            p -> new FlowerPotBlock(SOUL_SAPLING.get(), p
                    .instabreak()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY))
    );

    // ==================== 杜鹃木套件 (azalea_wood) ====================

    // 杜鹃木原木
    public static final DeferredBlock<RotatedPillarBlock> AZALEA_LOG = BLOCKS.registerBlock(
            "azalea_log",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 杜鹃木
    public static final DeferredBlock<RotatedPillarBlock> AZALEA_WOOD = BLOCKS.registerBlock(
            "azalea_wood",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 去皮杜鹃木原木
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AZALEA_LOG = BLOCKS.registerBlock(
            "stripped_azalea_log",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 去皮杜鹃木
    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AZALEA_WOOD = BLOCKS.registerBlock(
            "stripped_azalea_wood",
            p -> new RotatedPillarBlock(p
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 杜鹃木木板
    public static final DeferredBlock<Block> AZALEA_PLANKS = BLOCKS.registerBlock(
            "azalea_planks",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 杜鹃木楼梯
    public static final DeferredBlock<StairBlock> AZALEA_STAIRS = BLOCKS.registerBlock(
            "azalea_stairs",
            p -> new StairBlock(AZALEA_PLANKS.get().defaultBlockState(), p
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 杜鹃木台阶
    public static final DeferredBlock<SlabBlock> AZALEA_SLAB = BLOCKS.registerBlock(
            "azalea_slab",
            p -> new SlabBlock(p
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 杜鹃木栅栏
    public static final DeferredBlock<FenceBlock> AZALEA_FENCE = BLOCKS.registerBlock(
            "azalea_fence",
            p -> new FenceBlock(p
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 杜鹃木栅栏门
    public static final DeferredBlock<FenceGateBlock> AZALEA_FENCE_GATE = BLOCKS.registerBlock(
            "azalea_fence_gate",
            p -> new FenceGateBlock(AZALEA_WOOD_TYPE, p
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 杜鹃木门
    public static final DeferredBlock<DoorBlock> AZALEA_DOOR = BLOCKS.registerBlock(
            "azalea_door",
            p -> new DoorBlock(AZALEA_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 杜鹃木活板门
    public static final DeferredBlock<TrapDoorBlock> AZALEA_TRAPDOOR = BLOCKS.registerBlock(
            "azalea_trapdoor",
            p -> new TrapDoorBlock(AZALEA_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(2.0F)
                    .sound(SoundType.WOOD))
    );

    // 杜鹃木压力板
    public static final DeferredBlock<PressurePlateBlock> AZALEA_PRESSURE_PLATE = BLOCKS.registerBlock(
            "azalea_pressure_plate",
            p -> new PressurePlateBlock(AZALEA_BLOCK_SET, p
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(0.5F)
                    .sound(SoundType.WOOD))
    );

    // 杜鹃木按钮
    public static final DeferredBlock<ButtonBlock> AZALEA_BUTTON = BLOCKS.registerBlock(
            "azalea_button",
            p -> new ButtonBlock(AZALEA_BLOCK_SET, 30, p
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(0.5F)
                    .sound(SoundType.WOOD))
    );

    // ==================== 其他方块 ====================

    // 焦灰草方块 (scorched_grass_block)
    public static final DeferredBlock<Block> SCORCHED_GRASS_BLOCK = BLOCKS.registerBlock(
            "scorched_grass_block",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(0.6F)
                    .sound(SoundType.GRASS)
                    .randomTicks())
    );

    // 焦土 (scorched_dirt)
    public static final DeferredBlock<Block> SCORCHED_DIRT = BLOCKS.registerBlock(
            "scorched_dirt",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(0.5F)
                    .sound(SoundType.GRAVEL))
    );

    // 焦灰草丛 (scorched_grass)
    public static final DeferredBlock<Block> SCORCHED_GRASS = BLOCKS.registerBlock(
            "scorched_grass",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BLACK)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY))
    );

    // 焦灰枝条 (scorched_twig)
    public static final DeferredBlock<Block> SCORCHED_TWIG = BLOCKS.registerBlock(
            "scorched_twig",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BLACK)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY))
    );

    // 仙人球 (tiny_cactus)
    public static final DeferredBlock<Block> TINY_CACTUS = BLOCKS.registerBlock(
            "tiny_cactus",
            p -> new Block(p
                    .mapColor(MapColor.PLANT)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.WOOL)
                    .pushReaction(PushReaction.DESTROY))
    );

    // 制箭台 (fletching_table) - 使用自定义方块类支持GUI
    public static final DeferredBlock<Block> FLETCHING_TABLE = BLOCKS.registerBlock(
            "fletching_table",
            p -> new FletchingTableBlock(p
                    .mapColor(MapColor.WOOD)
                    .strength(2.5F)
                    .sound(SoundType.WOOD))
    );

    // 流沙 (quicksand)
    public static final DeferredBlock<Block> QUICKSAND = BLOCKS.registerBlock(
            "quicksand",
            p -> new Block(p
                    .mapColor(MapColor.SAND)
                    .strength(0.5F)
                    .sound(SoundType.SAND))

    );

    // 可疑的红沙 (suspicious_red_sand)
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
    // 灵魂南瓜灯 (soul_jack_o_lantern)
    public static final DeferredBlock<Block> SOUL_JACK_O_LANTERN = BLOCKS.registerBlock(
            "soul_jack_o_lantern",
            p -> new CarvedPumpkinBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .lightLevel(state -> 10))
    );

    // ==================== 砂岩 / 沙 / 花套件 ====================

    // 雕纹石英砂岩
    public static final DeferredBlock<Block> CHISELED_QUARTZ_SANDSTONE = BLOCKS.registerBlock(
            "chiseled_quartz_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    // 雕纹灵魂砂岩
    public static final DeferredBlock<Block> CHISELED_SOUL_SANDSTONE = BLOCKS.registerBlock(
            "chiseled_soul_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    // 切制石英砂岩
    public static final DeferredBlock<Block> CUT_QUARTZ_SANDSTONE = BLOCKS.registerBlock(
            "cut_quartz_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    // 切制灵魂砂岩
    public static final DeferredBlock<Block> CUT_SOUL_SANDSTONE = BLOCKS.registerBlock(
            "cut_soul_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    // 硝化石英砂岩
    public static final DeferredBlock<Block> NITRIFIED_QUARTZ_SANDSTONE = BLOCKS.registerBlock(
            "nitrified_quartz_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    // 硝化红色砂岩
    public static final DeferredBlock<Block> NITRIFIED_RED_SANDSTONE = BLOCKS.registerBlock(
            "nitrified_red_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    // 硝化砂岩
    public static final DeferredBlock<Block> NITRIFIED_SANDSTONE = BLOCKS.registerBlock(
            "nitrified_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.SAND)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    // 硝化灵魂砂岩
    public static final DeferredBlock<Block> NITRIFIED_SOUL_SANDSTONE = BLOCKS.registerBlock(
            "nitrified_soul_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    // 石英砂岩
    public static final DeferredBlock<Block> QUARTZ_SANDSTONE = BLOCKS.registerBlock(
            "quartz_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    // 灵魂砂岩
    public static final DeferredBlock<Block> SOUL_SANDSTONE = BLOCKS.registerBlock(
            "soul_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    // 平滑砂岩系列 - 六面均为对应砂岩的顶面贴图

    // 平滑石英砂岩
    public static final DeferredBlock<Block> SMOOTH_QUARTZ_SANDSTONE = BLOCKS.registerBlock(
            "smooth_quartz_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    // 平滑灵魂砂岩
    public static final DeferredBlock<Block> SMOOTH_SOUL_SANDSTONE = BLOCKS.registerBlock(
            "smooth_soul_sandstone",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.8F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops())
    );

    public static final DeferredBlock<StairBlock> QUARTZ_SANDSTONE_STAIRS = BLOCKS.registerBlock(
            "quartz_sandstone_stairs", p -> new StairBlock(QUARTZ_SANDSTONE.get().defaultBlockState(), p),
            () -> BlockBehaviour.Properties.ofLegacyCopy(QUARTZ_SANDSTONE.get()));
    public static final DeferredBlock<SlabBlock> QUARTZ_SANDSTONE_SLAB = BLOCKS.registerBlock(
            "quartz_sandstone_slab", SlabBlock::new,
            () -> BlockBehaviour.Properties.ofLegacyCopy(QUARTZ_SANDSTONE.get()));
    public static final DeferredBlock<WallBlock> QUARTZ_SANDSTONE_WALL = BLOCKS.registerBlock(
            "quartz_sandstone_wall", WallBlock::new,
            () -> BlockBehaviour.Properties.ofLegacyCopy(QUARTZ_SANDSTONE.get()));
    public static final DeferredBlock<StairBlock> SMOOTH_QUARTZ_SANDSTONE_STAIRS = BLOCKS.registerBlock(
            "smooth_quartz_sandstone_stairs", p -> new StairBlock(SMOOTH_QUARTZ_SANDSTONE.get().defaultBlockState(), p),
            () -> BlockBehaviour.Properties.ofLegacyCopy(SMOOTH_QUARTZ_SANDSTONE.get()));
    public static final DeferredBlock<SlabBlock> SMOOTH_QUARTZ_SANDSTONE_SLAB = BLOCKS.registerBlock(
            "smooth_quartz_sandstone_slab", SlabBlock::new,
            () -> BlockBehaviour.Properties.ofLegacyCopy(SMOOTH_QUARTZ_SANDSTONE.get()));
    public static final DeferredBlock<SlabBlock> CUT_QUARTZ_SANDSTONE_SLAB = BLOCKS.registerBlock(
            "cut_quartz_sandstone_slab", SlabBlock::new,
            () -> BlockBehaviour.Properties.ofLegacyCopy(CUT_QUARTZ_SANDSTONE.get()));
    public static final DeferredBlock<StairBlock> SOUL_SANDSTONE_STAIRS = BLOCKS.registerBlock(
            "soul_sandstone_stairs", p -> new StairBlock(SOUL_SANDSTONE.get().defaultBlockState(), p),
            () -> BlockBehaviour.Properties.ofLegacyCopy(SOUL_SANDSTONE.get()));
    public static final DeferredBlock<SlabBlock> SOUL_SANDSTONE_SLAB = BLOCKS.registerBlock(
            "soul_sandstone_slab", SlabBlock::new,
            () -> BlockBehaviour.Properties.ofLegacyCopy(SOUL_SANDSTONE.get()));
    public static final DeferredBlock<WallBlock> SOUL_SANDSTONE_WALL = BLOCKS.registerBlock(
            "soul_sandstone_wall", WallBlock::new,
            () -> BlockBehaviour.Properties.ofLegacyCopy(SOUL_SANDSTONE.get()));
    public static final DeferredBlock<StairBlock> SMOOTH_SOUL_SANDSTONE_STAIRS = BLOCKS.registerBlock(
            "smooth_soul_sandstone_stairs", p -> new StairBlock(SMOOTH_SOUL_SANDSTONE.get().defaultBlockState(), p),
            () -> BlockBehaviour.Properties.ofLegacyCopy(SMOOTH_SOUL_SANDSTONE.get()));
    public static final DeferredBlock<SlabBlock> SMOOTH_SOUL_SANDSTONE_SLAB = BLOCKS.registerBlock(
            "smooth_soul_sandstone_slab", SlabBlock::new,
            () -> BlockBehaviour.Properties.ofLegacyCopy(SMOOTH_SOUL_SANDSTONE.get()));
    public static final DeferredBlock<SlabBlock> CUT_SOUL_SANDSTONE_SLAB = BLOCKS.registerBlock(
            "cut_soul_sandstone_slab", SlabBlock::new,
            () -> BlockBehaviour.Properties.ofLegacyCopy(CUT_SOUL_SANDSTONE.get()));

    // 石英沙子
    public static final DeferredBlock<SandBlock> QUARTZ_SAND = BLOCKS.registerBlock(
            "quartz_sand",
            p -> new SandBlock(new ColorRGBA(0xFFDBD3C0), p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.5F)
                    .sound(SoundType.SAND))
    );

    // 石英流沙
    public static final DeferredBlock<Block> QUARTZ_QUICKSAND = BLOCKS.registerBlock(
            "quartz_quicksand",
            p -> new Block(p
                    .mapColor(MapColor.QUARTZ)
                    .strength(0.5F)
                    .sound(SoundType.SAND))
    );

    // 灵魂流沙
    public static final DeferredBlock<Block> SOUL_QUICKSAND = BLOCKS.registerBlock(
            "soul_quicksand",
            p -> new Block(p
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(0.5F)
                    .sound(SoundType.SAND))
    );

    // 玫瑰
    public static final DeferredBlock<FlowerBlock> ROSE = BLOCKS.registerBlock(
            "rose",
            p -> new FlowerBlock(SuspiciousStewEffects.EMPTY, p
                    .mapColor(MapColor.PLANT)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY))
    );

    // 灵魂玫瑰（单格花）
    public static final DeferredBlock<FlowerBlock> SOUL_ROSE = BLOCKS.registerBlock(
            "soul_rose",
            p -> new FlowerBlock(SuspiciousStewEffects.EMPTY, p
                    .mapColor(MapColor.PLANT)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY))
    );

    // 灵魂玫瑰丛（双层高花）
    public static final DeferredBlock<DoublePlantBlock> SOUL_ROSE_BUSH = BLOCKS.registerBlock(
            "soul_rose_bush",
            p -> new DoublePlantBlock(p
                    .mapColor(MapColor.PLANT)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY))
    );

    // 可疑的石英沙子
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

    // 可疑的灵魂沙子
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


    public static void blockItems() {
        // 焚烬木套件物品
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

        // 灵焰木套件物品
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

        // 杜鹃木套件物品
        ITEMS.registerSimpleBlockItem(AZALEA_LOG);
        ITEMS.registerSimpleBlockItem(AZALEA_WOOD);
        ITEMS.registerSimpleBlockItem(STRIPPED_AZALEA_LOG);
        ITEMS.registerSimpleBlockItem(STRIPPED_AZALEA_WOOD);
        ITEMS.registerSimpleBlockItem(AZALEA_PLANKS);
        ITEMS.registerSimpleBlockItem(AZALEA_STAIRS);
        ITEMS.registerSimpleBlockItem(AZALEA_SLAB);
        ITEMS.registerSimpleBlockItem(AZALEA_FENCE);
        ITEMS.registerSimpleBlockItem(AZALEA_FENCE_GATE);
        ITEMS.registerSimpleBlockItem(AZALEA_DOOR);
        ITEMS.registerSimpleBlockItem(AZALEA_TRAPDOOR);
        ITEMS.registerSimpleBlockItem(AZALEA_PRESSURE_PLATE);
        ITEMS.registerSimpleBlockItem(AZALEA_BUTTON);

        // 其他方块物品
        ITEMS.registerSimpleBlockItem(SCORCHED_GRASS_BLOCK);
        ITEMS.registerSimpleBlockItem(SCORCHED_DIRT);
        ITEMS.registerSimpleBlockItem(SCORCHED_GRASS);
        ITEMS.registerSimpleBlockItem(SCORCHED_TWIG);
        ITEMS.registerSimpleBlockItem(TINY_CACTUS);
        ITEMS.registerSimpleBlockItem(QUICKSAND);
        ITEMS.registerSimpleBlockItem(SUSPICIOUS_RED);
        ITEMS.registerSimpleBlockItem(SOUL_JACK_O_LANTERN);

        // 砂岩 / 沙 / 花套件
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
        ITEMS.registerSimpleBlockItem(QUARTZ_SANDSTONE_STAIRS);
        ITEMS.registerSimpleBlockItem(QUARTZ_SANDSTONE_SLAB);
        ITEMS.registerSimpleBlockItem(QUARTZ_SANDSTONE_WALL);
        ITEMS.registerSimpleBlockItem(SMOOTH_QUARTZ_SANDSTONE_STAIRS);
        ITEMS.registerSimpleBlockItem(SMOOTH_QUARTZ_SANDSTONE_SLAB);
        ITEMS.registerSimpleBlockItem(CUT_QUARTZ_SANDSTONE_SLAB);
        ITEMS.registerSimpleBlockItem(SOUL_SANDSTONE_STAIRS);
        ITEMS.registerSimpleBlockItem(SOUL_SANDSTONE_SLAB);
        ITEMS.registerSimpleBlockItem(SOUL_SANDSTONE_WALL);
        ITEMS.registerSimpleBlockItem(SMOOTH_SOUL_SANDSTONE_STAIRS);
        ITEMS.registerSimpleBlockItem(SMOOTH_SOUL_SANDSTONE_SLAB);
        ITEMS.registerSimpleBlockItem(CUT_SOUL_SANDSTONE_SLAB);
        ITEMS.registerSimpleBlockItem(QUARTZ_SAND);
        ITEMS.registerSimpleBlockItem(QUARTZ_QUICKSAND);
        ITEMS.registerSimpleBlockItem(SOUL_QUICKSAND);
        ITEMS.registerSimpleBlockItem(ROSE);
        ITEMS.registerSimpleBlockItem(SOUL_ROSE);
        ITEMS.registerSimpleBlockItem(SOUL_ROSE_BUSH);
        ITEMS.registerSimpleBlockItem(SUSPICIOUS_QUARTZ_SAND);
        ITEMS.registerSimpleBlockItem(SUSPICIOUS_SOUL_SAND);
    }

    /**
     * 注册方块和物品到事件总线
     * 必须在模组构造函数中调用
     */
    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        blockItems();
        ITEMS.register(bus);
    }
}
