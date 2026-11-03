package org.polaris2023.wildwind.barren.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.polaris2023.wildwind.barren.WildWindBarren;
import org.polaris2023.wildwind.barren.block.BarrenBlocks;
import org.polaris2023.wildwind.barren.block.BarrenWoodFamilies;
import org.polaris2023.wildwind.core.block.WoodFamily;

import java.util.Optional;

/**
 * 瘠土更新的模型生成器：木材套件 / 砂岩 / 沙 / 焦土。
 */
public class BarrenModelProvider extends ModelProvider {

    /** 底面旋转 180 度的砂岩模型模板 */
    private static final ModelTemplate ROTATED_TOP_SANDSTONE = new ModelTemplate(
            Optional.of(WildWindBarren.id("block/rotated_top_sandstone")),
            Optional.empty(),
            TextureSlot.SIDE, TextureSlot.TOP, TextureSlot.BOTTOM
    );

    public BarrenModelProvider(PackOutput output) {
        super(output, WildWindBarren.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, net.minecraft.client.data.models.ItemModelGenerators itemModels) {
        // 木材套件
        for (WoodFamily family : BarrenWoodFamilies.ALL) {
            registerWoodModels(blockModels, family);
        }

        // 树苗 + 花盆
        blockModels.createPlant(BarrenBlocks.BLAZE_SAPLING.get(), BarrenBlocks.POTTED_BLAZE_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createPlant(BarrenBlocks.SOUL_SAPLING.get(), BarrenBlocks.POTTED_SOUL_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        // 焦土
        createGrassBlock(blockModels, BarrenBlocks.SCORCHED_GRASS_BLOCK.get());
        blockModels.createTrivialCube(BarrenBlocks.SCORCHED_DIRT.get());
        blockModels.createCrossBlock(BarrenBlocks.SCORCHED_GRASS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(BarrenBlocks.SCORCHED_TWIG.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(BarrenBlocks.TINY_CACTUS.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        // 流沙 / 可疑沙
        blockModels.createTrivialCube(BarrenBlocks.QUICKSAND.get());
        blockModels.createTrivialCube(BarrenBlocks.RED_QUICKSAND.get());
        blockModels.createBrushableBlock(BarrenBlocks.SUSPICIOUS_RED.get());

        // 砂岩
        createSandstone(blockModels, BarrenBlocks.QUARTZ_SANDSTONE.get());
        createSandstone(blockModels, BarrenBlocks.SOUL_SANDSTONE.get());

        Material quartzTop = TextureMapping.getBlockTexture(BarrenBlocks.QUARTZ_SANDSTONE.get(), "_top");
        Material soulTop = TextureMapping.getBlockTexture(BarrenBlocks.SOUL_SANDSTONE.get(), "_top");
        createRotatedTopSandstone(blockModels, BarrenBlocks.CHISELED_QUARTZ_SANDSTONE.get(), quartzTop);
        createRotatedTopSandstone(blockModels, BarrenBlocks.CUT_QUARTZ_SANDSTONE.get(), quartzTop);
        createRotatedTopSandstone(blockModels, BarrenBlocks.NITRIFIED_QUARTZ_SANDSTONE.get(), quartzTop);
        createRotatedTopSandstone(blockModels, BarrenBlocks.CHISELED_SOUL_SANDSTONE.get(), soulTop);
        createRotatedTopSandstone(blockModels, BarrenBlocks.CUT_SOUL_SANDSTONE.get(), soulTop);
        createRotatedTopSandstone(blockModels, BarrenBlocks.NITRIFIED_SOUL_SANDSTONE.get(), soulTop);
        createVanillaTopBottomSandstone(blockModels, BarrenBlocks.NITRIFIED_RED_SANDSTONE.get(), Blocks.RED_SANDSTONE);
        createVanillaTopBottomSandstone(blockModels, BarrenBlocks.NITRIFIED_SANDSTONE.get(), Blocks.SANDSTONE);

        createSmoothSandstone(blockModels, BarrenBlocks.SMOOTH_QUARTZ_SANDSTONE.get(), quartzTop);
        createSmoothSandstone(blockModels, BarrenBlocks.SMOOTH_SOUL_SANDSTONE.get(), soulTop);

        blockModels.createTrivialCube(BarrenBlocks.QUARTZ_SAND.get());
        blockModels.createTrivialCube(BarrenBlocks.QUARTZ_QUICKSAND.get());
        blockModels.createTrivialCube(BarrenBlocks.SOUL_QUICKSAND.get());

        blockModels.createBrushableBlock(BarrenBlocks.SUSPICIOUS_QUARTZ_SAND.get());
        blockModels.createBrushableBlock(BarrenBlocks.SUSPICIOUS_SOUL_SAND.get());
    }

    private void registerWoodModels(BlockModelGenerators blockModels, WoodFamily family) {
        blockModels.woodProvider(family.log().get()).logWithHorizontal(family.log().get()).wood(family.wood().get());
        blockModels.woodProvider(family.strippedLog().get())
                .logWithHorizontal(family.strippedLog().get())
                .wood(family.strippedWood().get());
        blockModels.family(family.planks().get())
                .stairs(family.stairs().get())
                .slab(family.slab().get())
                .fence(family.fence().get())
                .fenceGate(family.fenceGate().get())
                .pressurePlate(family.pressurePlate().get())
                .button(family.button().get());
        blockModels.createDoor(family.door().get());
        blockModels.createTrapdoor(family.trapdoor().get());
        if (family.hasLeaves()) {
            blockModels.createTintedLeaves(family.leaves().get(), TexturedModel.LEAVES, family.leavesTint());
        }
    }

    /**
     * 草方块式模型：侧面 scorched_grass_block_side，顶面 _top，底面使用焦土贴图。
     */
    private void createGrassBlock(BlockModelGenerators blockModels, Block block) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(BarrenBlocks.SCORCHED_DIRT.get()));
        Identifier model = ModelTemplates.CUBE_BOTTOM_TOP.create(block, mapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(model)));
        blockModels.registerSimpleItemModel(block.asItem(), model);
    }

    private void createSandstone(BlockModelGenerators blockModels, Block block) {        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, "_bottom"))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block));
        MultiVariant model = BlockModelGenerators.plainVariant(
                ModelTemplates.CUBE_BOTTOM_TOP.create(block, mapping, blockModels.modelOutput));
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, model));
        blockModels.registerSimpleItemModel(block.asItem(), ModelLocationUtils.getModelLocation(block));
    }

    private void createRotatedTopSandstone(BlockModelGenerators blockModels, Block block, Material top) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block))
                .put(TextureSlot.TOP, top)
                .put(TextureSlot.BOTTOM, top);
        Identifier model = ROTATED_TOP_SANDSTONE.create(block, mapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(model)));
        blockModels.registerSimpleItemModel(block.asItem(), model);
    }

    private void createSmoothSandstone(BlockModelGenerators blockModels, Block block, Material top) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.SIDE, top)
                .put(TextureSlot.TOP, top)
                .put(TextureSlot.BOTTOM, top);
        Identifier model = ROTATED_TOP_SANDSTONE.create(block, mapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(model)));
        blockModels.registerSimpleItemModel(block.asItem(), model);
    }

    private void createVanillaTopBottomSandstone(BlockModelGenerators blockModels, Block block, Block vanilla) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(vanilla, "_top"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(vanilla, "_bottom"));
        Identifier model = ModelTemplates.CUBE_BOTTOM_TOP.create(block, mapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(model)));
        blockModels.registerSimpleItemModel(block.asItem(), model);
    }
}
