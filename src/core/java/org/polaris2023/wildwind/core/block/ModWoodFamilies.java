package org.polaris2023.wildwind.core.block;

import java.util.List;

/**
 * 核心模块的木材家族清单。
 */
public final class ModWoodFamilies {

    /** 杜鹃木（azalea）：沿用原版树叶 / 树苗，没有自成套的树叶 */
    public static final WoodFamily AZALEA = new WoodFamily(
            "azalea",
            0,
            ModBlocks.AZALEA_LOG,
            ModBlocks.AZALEA_WOOD,
            ModBlocks.STRIPPED_AZALEA_LOG,
            ModBlocks.STRIPPED_AZALEA_WOOD,
            ModBlocks.AZALEA_PLANKS,
            ModBlocks.AZALEA_STAIRS,
            ModBlocks.AZALEA_SLAB,
            ModBlocks.AZALEA_FENCE,
            ModBlocks.AZALEA_FENCE_GATE,
            ModBlocks.AZALEA_DOOR,
            ModBlocks.AZALEA_TRAPDOOR,
            ModBlocks.AZALEA_PRESSURE_PLATE,
            ModBlocks.AZALEA_BUTTON,
            null,
            null
    );

    public static final List<WoodFamily> ALL = List.of(AZALEA);

    private ModWoodFamilies() {
    }
}
