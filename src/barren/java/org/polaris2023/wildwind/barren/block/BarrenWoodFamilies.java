package org.polaris2023.wildwind.barren.block;

import org.polaris2023.wildwind.core.block.WoodFamily;

import java.util.List;

/**
 * 瘠土更新的木材家族清单（焚烬木 / 灵焰木）。
 */
public final class BarrenWoodFamilies {

    public static final WoodFamily BLAZE = new WoodFamily(
            "blaze",
            0xFF8000,
            BarrenBlocks.BLAZE_LOG,
            BarrenBlocks.BLAZE_WOOD,
            BarrenBlocks.STRIPPED_BLAZE_LOG,
            BarrenBlocks.STRIPPED_BLAZE_WOOD,
            BarrenBlocks.BLAZE_PLANKS,
            BarrenBlocks.BLAZE_STAIRS,
            BarrenBlocks.BLAZE_SLAB,
            BarrenBlocks.BLAZE_FENCE,
            BarrenBlocks.BLAZE_FENCE_GATE,
            BarrenBlocks.BLAZE_DOOR,
            BarrenBlocks.BLAZE_TRAPDOOR,
            BarrenBlocks.BLAZE_PRESSURE_PLATE,
            BarrenBlocks.BLAZE_BUTTON,
            BarrenBlocks.BLAZE_LEAVES,
            BarrenBlocks.BLAZE_SAPLING
    );

    public static final WoodFamily SOUL = new WoodFamily(
            "soul",
            0x00FFFF,
            BarrenBlocks.SOUL_LOG,
            BarrenBlocks.SOUL_WOOD,
            BarrenBlocks.STRIPPED_SOUL_LOG,
            BarrenBlocks.STRIPPED_SOUL_WOOD,
            BarrenBlocks.SOUL_PLANKS,
            BarrenBlocks.SOUL_STAIRS,
            BarrenBlocks.SOUL_SLAB,
            BarrenBlocks.SOUL_FENCE,
            BarrenBlocks.SOUL_FENCE_GATE,
            BarrenBlocks.SOUL_DOOR,
            BarrenBlocks.SOUL_TRAPDOOR,
            BarrenBlocks.SOUL_PRESSURE_PLATE,
            BarrenBlocks.SOUL_BUTTON,
            BarrenBlocks.SOUL_LEAVES,
            BarrenBlocks.SOUL_SAPLING
    );

    public static final List<WoodFamily> ALL = List.of(BLAZE, SOUL);

    private BarrenWoodFamilies() {
    }
}
