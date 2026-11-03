package org.polaris2023.wildwind.core.worldgen;

import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

/**
 * 核心模块的树木生长器：杜鹃树。
 */
public final class ModTreeGrowers {

    public static final TreeGrower AZALEA = new TreeGrower(
            "azalea",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.AZALEA_TREE_KEY),
            Optional.empty()
    );

    private ModTreeGrowers() {
    }
}
