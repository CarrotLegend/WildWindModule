package org.polaris2023.wildwind.barren.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.polaris2023.wildwind.barren.WildWindBarren;
import org.polaris2023.wildwind.barren.worldgen.BarrenBiomeModifiers;
import org.polaris2023.wildwind.barren.worldgen.BarrenConfiguredFeatures;
import org.polaris2023.wildwind.barren.worldgen.BarrenPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * 瘠土更新的数据包内建注册：树木特征与群系修改器。
 */
public class BarrenDatapackProvider extends DatapackBuiltinEntriesProvider {

    public BarrenDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, new RegistrySetBuilder()
                        .add(Registries.CONFIGURED_FEATURE, BarrenConfiguredFeatures::bootstrap)
                        .add(Registries.PLACED_FEATURE, BarrenPlacedFeatures::bootstrap)
                        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, BarrenBiomeModifiers::bootstrap),
                Set.of(WildWindBarren.MOD_ID, "minecraft"));
    }
}
