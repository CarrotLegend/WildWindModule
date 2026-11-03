package org.polaris2023.wildwind.core.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.polaris2023.wildwind.core.WildWindCore;

import java.util.Set;

/**
 * 核心模块的生物群系修改器：繁茂洞穴的杜鹃树（移除原版、加入杜鹃木版）。
 */
public final class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_AZALEA_TREE = createKey("add_azalea_tree");
    public static final ResourceKey<BiomeModifier> REMOVE_VANILLA_AZALEA_TREE = createKey("remove_vanilla_azalea_tree");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(REMOVE_VANILLA_AZALEA_TREE, new BiomeModifiers.RemoveFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.LUSH_CAVES)),
                HolderSet.direct(placedFeatures.getOrThrow(CavePlacements.ROOTED_AZALEA_TREE)),
                Set.of(GenerationStep.Decoration.VEGETAL_DECORATION)
        ));

        context.register(ADD_AZALEA_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.LUSH_CAVES)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AZALEA_TREE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }

    private static ResourceKey<BiomeModifier> createKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, WildWindCore.id(name));
    }

    private ModBiomeModifiers() {
    }
}
