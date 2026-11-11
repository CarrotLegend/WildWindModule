package org.polaris2023.wildwind.hfas.datagen.provider;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.polaris2023.wildwind.hfas.HFASMod;
import org.polaris2023.wildwind.hfas.block.ModBlocks;
import org.polaris2023.wildwind.hfas.block.ModWoodFamilies;
import org.polaris2023.wildwind.hfas.block.WoodFamily;
import org.polaris2023.wildwind.hfas.registry.ModItems;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * 模型生成器
 * 用于生成方块和物品的模型文件
 *
 * @author baka4n
 * @since 2026/04/15
 */
public class ModModelProvider extends ModelProvider {

    /**
     * 底面旋转 180 度的砂岩模型模板
     */
    private static final ModelTemplate ROTATED_TOP_SANDSTONE = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(HFASMod.MOD_ID, "block/rotated_top_sandstone")),
            Optional.empty(),
            TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.BOTTOM
    );

    private void registerSandstoneVariants(BlockModelGenerators models, Block base, Block stairs, Block slab, Block wall,
                                           Block smooth, Block smoothStairs, Block smoothSlab, Block cut, Block cutSlab) {
        Material top = TextureMapping.getBlockTexture(base, "_top");
        TextureMapping ordinary = sandstoneTextures(TextureMapping.getBlockTexture(base), top,
                TextureMapping.getBlockTexture(base, "_bottom"));
        createSandstoneStairs(models, stairs, ordinary);
        createSandstoneSlab(models, slab, base, ordinary);
        createSandstoneWall(models, wall, ordinary);

        TextureMapping smoothTextures = sandstoneTextures(top, top, top);
        createSandstoneStairs(models, smoothStairs, smoothTextures);
        createSandstoneSlab(models, smoothSlab, smooth, smoothTextures);
        createSandstoneSlab(models, cutSlab, cut, sandstoneTextures(TextureMapping.getBlockTexture(cut), top, top));
    }

    private TextureMapping sandstoneTextures(Material side, Material top, Material bottom) {
        return new TextureMapping().put(TextureSlot.SIDE, side).put(TextureSlot.TOP, top).put(TextureSlot.BOTTOM, bottom);
    }

    private void createSandstoneStairs(BlockModelGenerators models, Block stairs, TextureMapping textures) {
        Identifier inner = ModelTemplates.STAIRS_INNER.create(stairs, textures, models.modelOutput);
        Identifier straight = ModelTemplates.STAIRS_STRAIGHT.create(stairs, textures, models.modelOutput);
        Identifier outer = ModelTemplates.STAIRS_OUTER.create(stairs, textures, models.modelOutput);
        models.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs,
                BlockModelGenerators.plainVariant(inner), BlockModelGenerators.plainVariant(straight),
                BlockModelGenerators.plainVariant(outer)));
        models.registerSimpleItemModel(stairs.asItem(), straight);
    }

    private void createSandstoneSlab(BlockModelGenerators models, Block slab, Block base, TextureMapping textures) {
        Identifier bottom = ModelTemplates.SLAB_BOTTOM.create(slab, textures, models.modelOutput);
        Identifier top = ModelTemplates.SLAB_TOP.create(slab, textures, models.modelOutput);
        models.blockStateOutput.accept(BlockModelGenerators.createSlab(slab,
                BlockModelGenerators.plainVariant(bottom), BlockModelGenerators.plainVariant(top),
                BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(base))));
        models.registerSimpleItemModel(slab.asItem(), bottom);
    }

    private void createSandstoneWall(BlockModelGenerators models, Block wall, TextureMapping textures) {
        Identifier post = sandstoneWallTemplate("post").create(wall, textures, models.modelOutput);
        Identifier side = sandstoneWallTemplate("side").create(wall, textures, models.modelOutput);
        Identifier tall = sandstoneWallTemplate("side_tall").create(wall, textures, models.modelOutput);
        Identifier inventory = sandstoneWallTemplate("inventory").create(wall, textures, models.modelOutput);
        models.blockStateOutput.accept(BlockModelGenerators.createWall(wall,
                BlockModelGenerators.plainVariant(post), BlockModelGenerators.plainVariant(side),
                BlockModelGenerators.plainVariant(tall)));
        models.registerSimpleItemModel(wall.asItem(), inventory);
    }

    private ModelTemplate sandstoneWallTemplate(String part) {
        return new ModelTemplate(Optional.of(HFASMod.id("block/sandstone_wall_" + part + "_template")),
                Optional.of("_" + part), TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.BOTTOM);
    }

    public ModModelProvider(PackOutput output) {
        super(output, HFASMod.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        // ==================== 三种木材套件 ====================
        for (WoodFamily family : ModWoodFamilies.ALL) {
            registerWoodModels(blockModels, family);
        }

        // 树苗 + 花盆：createPlant 一次生成树苗交叉模型与花盆模型
        blockModels.createPlant(ModBlocks.BLAZE_SAPLING.get(), ModBlocks.POTTED_BLAZE_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createPlant(ModBlocks.SOUL_SAPLING.get(), ModBlocks.POTTED_SOUL_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        // ==================== 其他方块 ====================
        // 焦灰草方块 - 使用简单的方块模型
        createSimpleBlock(blockModels, ModBlocks.SCORCHED_GRASS_BLOCK.get());

        // 焦土 - 使用简单的方块模型
        createSimpleBlock(blockModels, ModBlocks.SCORCHED_DIRT.get());

        // 焦灰草丛 - 使用交叉模型（类似草丛）
        createCrossBlock(blockModels, ModBlocks.SCORCHED_GRASS.get());

        // 焦灰枝条 - 使用交叉模型
        createCrossBlock(blockModels, ModBlocks.SCORCHED_TWIG.get());

        // 仙人球 - 使用交叉模型
        createCrossBlock(blockModels, ModBlocks.TINY_CACTUS.get());

        // 制箭台 - 使用简单方块模型
        createSimpleBlock(blockModels, ModBlocks.FLETCHING_TABLE.get());

        // 流沙 - 使用简单方块模型
        createSimpleBlock(blockModels, ModBlocks.QUICKSAND.get());

        // 可疑红沙 - 基于原版可疑沙：四个模型
        blockModels.createBrushableBlock(ModBlocks.SUSPICIOUS_RED.get());

        // 灵魂南瓜灯 - 正面 soul_jack_o_lantern，剩下使用原版南瓜贴图
        blockModels.createPumpkinVariant(ModBlocks.SOUL_JACK_O_LANTERN.get(), TextureMapping.column(Blocks.PUMPKIN));

        // ==================== 砂岩 / 沙 / 花套件 ====================
        // 石英砂岩 / 灵魂砂岩 - 侧面 + 顶底三面贴图
        createSandstone(blockModels, ModBlocks.QUARTZ_SANDSTONE.get());
        createSandstone(blockModels, ModBlocks.SOUL_SANDSTONE.get());

        // 雕纹 / 切制 / 硝化砂岩 - 侧面自身，顶底用共享贴图
        Material quartzTop = TextureMapping.getBlockTexture(ModBlocks.QUARTZ_SANDSTONE.get(), "_top");
        Material soulTop = TextureMapping.getBlockTexture(ModBlocks.SOUL_SANDSTONE.get(), "_top");
        createRotatedTopSandstone(blockModels, ModBlocks.CHISELED_QUARTZ_SANDSTONE.get(), quartzTop);
        createRotatedTopSandstone(blockModels, ModBlocks.CUT_QUARTZ_SANDSTONE.get(), quartzTop);
        createRotatedTopSandstone(blockModels, ModBlocks.NITRIFIED_QUARTZ_SANDSTONE.get(), quartzTop);
        createRotatedTopSandstone(blockModels, ModBlocks.CHISELED_SOUL_SANDSTONE.get(), soulTop);
        createRotatedTopSandstone(blockModels, ModBlocks.CUT_SOUL_SANDSTONE.get(), soulTop);
        createRotatedTopSandstone(blockModels, ModBlocks.NITRIFIED_SOUL_SANDSTONE.get(), soulTop);
        createVanillaTopBottomSandstone(blockModels, ModBlocks.NITRIFIED_RED_SANDSTONE.get(), Blocks.RED_SANDSTONE);
        createVanillaTopBottomSandstone(blockModels, ModBlocks.NITRIFIED_SANDSTONE.get(), Blocks.SANDSTONE);

        // 平滑砂岩 - 六面均为对应砂岩的顶面贴图，底面旋转 180 度
        createSmoothSandstone(blockModels, ModBlocks.SMOOTH_QUARTZ_SANDSTONE.get(), quartzTop);
        createSmoothSandstone(blockModels, ModBlocks.SMOOTH_SOUL_SANDSTONE.get(), soulTop);

        registerSandstoneVariants(blockModels, ModBlocks.QUARTZ_SANDSTONE.get(), ModBlocks.QUARTZ_SANDSTONE_STAIRS.get(),
                ModBlocks.QUARTZ_SANDSTONE_SLAB.get(), ModBlocks.QUARTZ_SANDSTONE_WALL.get(),
                ModBlocks.SMOOTH_QUARTZ_SANDSTONE.get(), ModBlocks.SMOOTH_QUARTZ_SANDSTONE_STAIRS.get(), ModBlocks.SMOOTH_QUARTZ_SANDSTONE_SLAB.get(),
                ModBlocks.CUT_QUARTZ_SANDSTONE.get(), ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB.get());
        registerSandstoneVariants(blockModels, ModBlocks.SOUL_SANDSTONE.get(), ModBlocks.SOUL_SANDSTONE_STAIRS.get(),
                ModBlocks.SOUL_SANDSTONE_SLAB.get(), ModBlocks.SOUL_SANDSTONE_WALL.get(),
                ModBlocks.SMOOTH_SOUL_SANDSTONE.get(), ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get(), ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(),
                ModBlocks.CUT_SOUL_SANDSTONE.get(), ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get());

        // 石英沙 / 流沙
        createSimpleBlock(blockModels, ModBlocks.QUARTZ_SAND.get());
        createSimpleBlock(blockModels, ModBlocks.QUARTZ_QUICKSAND.get());
        createSimpleBlock(blockModels, ModBlocks.SOUL_QUICKSAND.get());

        // 玫瑰（单格花）
        blockModels.createCrossBlockWithDefaultItem(ModBlocks.ROSE.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        // 灵魂玫瑰（单格花）
        blockModels.createCrossBlockWithDefaultItem(ModBlocks.SOUL_ROSE.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        // 灵魂玫瑰丛（双层花，纹理 soul_rose_bush_top / soul_rose_bush_bottom）
        blockModels.createDoublePlantWithDefaultItem(ModBlocks.SOUL_ROSE_BUSH.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        // 可疑的石英沙 / 灵魂沙
        blockModels.createBrushableBlock(ModBlocks.SUSPICIOUS_QUARTZ_SAND.get());
        blockModels.createBrushableBlock(ModBlocks.SUSPICIOUS_SOUL_SAND.get());

        // ==================== 独立物品 ====================
        simpleItem(itemModels, ModItems.CRAB_CLAW);

        // 万用蟹钳 - 使用手写 3D 模型（resources 下的 models/item/omni_claw.json），仅生成物品模型定义
        itemModels.itemModelOutput.accept(
                ModItems.OMNI_CLAW.get(),
                ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(ModItems.OMNI_CLAW.get()))
        );
        simpleItem(itemModels, ModItems.MUDCRAB_BUCKET);
        simpleItem(itemModels, ModItems.MUDCRAB_SPAWN_EGG);

        // ==================== 食人鱼 ====================
        registerPiranhaModels(itemModels);
    }

    /**
     * 注册一种木材的方块模型（含树叶与树苗）
     */
    private void registerWoodModels(BlockModelGenerators blockModels, WoodFamily family) {
        // 原木（自动生成物品模型）
        blockModels.woodProvider(family.log().get()).logWithHorizontal(family.log().get()).wood(family.wood().get());

        // 去皮原木（自动生成物品模型）
        blockModels.woodProvider(family.strippedLog().get())
                .logWithHorizontal(family.strippedLog().get())
                .wood(family.strippedWood().get());

        // 木质方块套件（包括木板，自动生成物品模型）
        blockModels.family(family.planks().get())
                .stairs(family.stairs().get())
                .slab(family.slab().get())
                .fence(family.fence().get())
                .fenceGate(family.fenceGate().get())
                .pressurePlate(family.pressurePlate().get())
                .button(family.button().get());

        // 门 / 活板门（自动生成物品模型）
        blockModels.createDoor(family.door().get());
        blockModels.createTrapdoor(family.trapdoor().get());

        // 树叶（物品图标使用常数染色），杜鹃木沿用原版，没有自成套的树叶
        if (family.hasLeaves()) {
            blockModels.createTintedLeaves(family.leaves().get(), TexturedModel.LEAVES, family.leavesTint());
        }
    }

    /**
     * 创建简单方块模型（六面相同贴图）
     */
    private void createSimpleBlock(BlockModelGenerators blockModels, Block block) {
        blockModels.createTrivialCube(block);
    }

    /**
     * 创建砂岩模型（侧面 + 顶面 + 底面，纹理命名 xxx / xxx_top / xxx_bottom）
     */
    private void createSandstone(BlockModelGenerators blockModels, Block block) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, "_bottom"))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block));
        MultiVariant model = BlockModelGenerators.plainVariant(
                ModelTemplates.CUBE_BOTTOM_TOP.create(block, mapping, blockModels.modelOutput));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, model));
        blockModels.registerSimpleItemModel(block.asItem(), ModelLocationUtils.getModelLocation(block));
    }

    /**
     * 侧面自身，顶底共用同一贴图，底面旋转 180 度
     */
    private void createRotatedTopSandstone(BlockModelGenerators blockModels, Block block, Material top) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block))
                .put(TextureSlot.TOP, top)
                .put(TextureSlot.BOTTOM, top);
        Identifier model = ROTATED_TOP_SANDSTONE.create(block, mapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(model)));
        blockModels.registerSimpleItemModel(block.asItem(), model);
    }

    /**
     * 六面均为同一砂岩顶面贴图，底面旋转 180 度（平滑砂岩）
     */
    private void createSmoothSandstone(BlockModelGenerators blockModels, Block block, Material top) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.SIDE, top)
                .put(TextureSlot.TOP, top)
                .put(TextureSlot.BOTTOM, top);
        Identifier model = ROTATED_TOP_SANDSTONE.create(block, mapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(model)));
        blockModels.registerSimpleItemModel(block.asItem(), model);
    }

    /**
     * 侧面自身，顶底使用指定原版砂岩的顶底贴图
     */
    private void createVanillaTopBottomSandstone(BlockModelGenerators blockModels, Block block, Block vanilla) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(vanilla, "_top"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(vanilla, "_bottom"));
        Identifier model = ModelTemplates.CUBE_BOTTOM_TOP.create(block, mapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(model)));
        blockModels.registerSimpleItemModel(block.asItem(), model);
    }

    /**
     * 创建交叉模型（用于植物类方块）
     */
    private void createCrossBlock(BlockModelGenerators blockModels, Block block) {
        blockModels.createCrossBlock(block, BlockModelGenerators.PlantType.NOT_TINTED);
    }

    private void simpleItem(ItemModelGenerators itemModels, Supplier<? extends Item> item){
        itemModels.generateFlatItem(item.get(), ModelTemplates.FLAT_ITEM);
    }

    private void registerPiranhaModels(ItemModelGenerators itemModels) {
        simpleItem(itemModels, ModItems.PIRANHA);
        simpleItem(itemModels, ModItems.COOKED_PIRANHA);
        simpleItem(itemModels, ModItems.PIRANHA_BUCKET);
        simpleItem(itemModels, ModItems.PIRANHA_SPAWN_EGG);
        simpleItem(itemModels, ModItems.SPIDERLING_SPAWN_EGG);
        simpleItem(itemModels, ModItems.FANG);
    }
}
