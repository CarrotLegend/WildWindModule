package org.polaris2023.wildwind.barren.datagen.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris2023.wildwind.barren.WildWindBarren;
import org.polaris2023.wildwind.barren.block.BarrenBlocks;

/**
 * 瘠土更新的英文语言文件。
 */
public class BarrenEnUsLanguageProvider extends LanguageProvider {

    public BarrenEnUsLanguageProvider(PackOutput output) {
        super(output, WildWindBarren.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // 创造栏标题
        add("tab.ww_barren.name", "Barren Update");

        // 焚烬木套件
        addBlock(BarrenBlocks.BLAZE_LOG, "Blaze Log");
        addBlock(BarrenBlocks.BLAZE_WOOD, "Blaze Wood");
        addBlock(BarrenBlocks.STRIPPED_BLAZE_LOG, "Stripped Blaze Log");
        addBlock(BarrenBlocks.STRIPPED_BLAZE_WOOD, "Stripped Blaze Wood");
        addBlock(BarrenBlocks.BLAZE_PLANKS, "Blaze Planks");
        addBlock(BarrenBlocks.BLAZE_STAIRS, "Blaze Stairs");
        addBlock(BarrenBlocks.BLAZE_SLAB, "Blaze Slab");
        addBlock(BarrenBlocks.BLAZE_FENCE, "Blaze Fence");
        addBlock(BarrenBlocks.BLAZE_FENCE_GATE, "Blaze Fence Gate");
        addBlock(BarrenBlocks.BLAZE_DOOR, "Blaze Door");
        addBlock(BarrenBlocks.BLAZE_TRAPDOOR, "Blaze Trapdoor");
        addBlock(BarrenBlocks.BLAZE_PRESSURE_PLATE, "Blaze Pressure Plate");
        addBlock(BarrenBlocks.BLAZE_BUTTON, "Blaze Button");
        addBlock(BarrenBlocks.BLAZE_LEAVES, "Blaze Leaves");
        addBlock(BarrenBlocks.BLAZE_SAPLING, "Blaze Sapling");
        addBlock(BarrenBlocks.POTTED_BLAZE_SAPLING, "Potted Blaze Sapling");

        // 灵焰木套件
        addBlock(BarrenBlocks.SOUL_LOG, "Soul Log");
        addBlock(BarrenBlocks.SOUL_WOOD, "Soul Wood");
        addBlock(BarrenBlocks.STRIPPED_SOUL_LOG, "Stripped Soul Log");
        addBlock(BarrenBlocks.STRIPPED_SOUL_WOOD, "Stripped Soul Wood");
        addBlock(BarrenBlocks.SOUL_PLANKS, "Soul Planks");
        addBlock(BarrenBlocks.SOUL_STAIRS, "Soul Stairs");
        addBlock(BarrenBlocks.SOUL_SLAB, "Soul Slab");
        addBlock(BarrenBlocks.SOUL_FENCE, "Soul Fence");
        addBlock(BarrenBlocks.SOUL_FENCE_GATE, "Soul Fence Gate");
        addBlock(BarrenBlocks.SOUL_DOOR, "Soul Door");
        addBlock(BarrenBlocks.SOUL_TRAPDOOR, "Soul Trapdoor");
        addBlock(BarrenBlocks.SOUL_PRESSURE_PLATE, "Soul Pressure Plate");
        addBlock(BarrenBlocks.SOUL_BUTTON, "Soul Button");
        addBlock(BarrenBlocks.SOUL_LEAVES, "Soul Leaves");
        addBlock(BarrenBlocks.SOUL_SAPLING, "Soul Sapling");
        addBlock(BarrenBlocks.POTTED_SOUL_SAPLING, "Potted Soul Sapling");

        // 焦土 / 瘠地
        addBlock(BarrenBlocks.SCORCHED_GRASS_BLOCK, "Scorched Grass Block");
        addBlock(BarrenBlocks.SCORCHED_DIRT, "Scorched Dirt");
        addBlock(BarrenBlocks.SCORCHED_GRASS, "Scorched Grass");
        addBlock(BarrenBlocks.SCORCHED_TWIG, "Scorched Twig");
        addBlock(BarrenBlocks.TINY_CACTUS, "Tiny Cactus");
        addBlock(BarrenBlocks.QUICKSAND, "§4Quicksand");
        addBlock(BarrenBlocks.RED_QUICKSAND, "§4Red Quicksand");
        addBlock(BarrenBlocks.SUSPICIOUS_RED, "§4Suspicious Red Sand");

        // 砂岩 / 沙
        addBlock(BarrenBlocks.CHISELED_QUARTZ_SANDSTONE, "Chiseled Quartz Sandstone");
        addBlock(BarrenBlocks.CHISELED_SOUL_SANDSTONE, "Chiseled Soul Sandstone");
        addBlock(BarrenBlocks.CUT_QUARTZ_SANDSTONE, "Cut Quartz Sandstone");
        addBlock(BarrenBlocks.CUT_SOUL_SANDSTONE, "Cut Soul Sandstone");
        addBlock(BarrenBlocks.NITRIFIED_QUARTZ_SANDSTONE, "Nitrified Quartz Sandstone");
        addBlock(BarrenBlocks.NITRIFIED_RED_SANDSTONE, "Nitrified Red Sandstone");
        addBlock(BarrenBlocks.NITRIFIED_SANDSTONE, "Nitrified Sandstone");
        addBlock(BarrenBlocks.NITRIFIED_SOUL_SANDSTONE, "Nitrified Soul Sandstone");
        addBlock(BarrenBlocks.QUARTZ_SANDSTONE, "Quartz Sandstone");
        addBlock(BarrenBlocks.SOUL_SANDSTONE, "Soul Sandstone");
        addBlock(BarrenBlocks.SMOOTH_QUARTZ_SANDSTONE, "Smooth Quartz Sandstone");
        addBlock(BarrenBlocks.SMOOTH_SOUL_SANDSTONE, "Smooth Soul Sandstone");
        addBlock(BarrenBlocks.QUARTZ_SAND, "Quartz Sand");
        addBlock(BarrenBlocks.QUARTZ_QUICKSAND, "Quartz Quicksand");
        addBlock(BarrenBlocks.SOUL_QUICKSAND, "Soul Quicksand");
        addBlock(BarrenBlocks.SUSPICIOUS_QUARTZ_SAND, "Suspicious Quartz Sand");
        addBlock(BarrenBlocks.SUSPICIOUS_SOUL_SAND, "Suspicious Soul Sand");
    }
}
