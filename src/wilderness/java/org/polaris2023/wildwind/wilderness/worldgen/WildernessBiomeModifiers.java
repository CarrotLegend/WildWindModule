package org.polaris2023.wildwind.wilderness.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.registry.ModEntities;
import org.polaris2023.wildwind.wilderness.tag.ModBiomeTags;

/**
 * 荒野更新的生物群系修改器：泥沼蟹 / 恐鱼的生成。
 */
public final class WildernessBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_MUDCRAB_SPAWNS = createKey("add_mudcrab_spawns");
    public static final ResourceKey<BiomeModifier> ADD_PIRANHA_SPAWNS = createKey("add_piranha_spawns");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_MUDCRAB_SPAWNS, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_SWAMP),
                WeightedList.of(new MobSpawnSettings.SpawnerData(ModEntities.MUDCRAB.get(), 1, 2))
        ));

        context.register(ADD_PIRANHA_SPAWNS, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(ModBiomeTags.EntityGen.PIRANHA_SPAWNS),
                WeightedList.<MobSpawnSettings.SpawnerData>builder()
                        .add(new MobSpawnSettings.SpawnerData(ModEntities.PIRANHA.get(), 1, 5), 8)
                        .build()
        ));
    }

    private static ResourceKey<BiomeModifier> createKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, WildWindWilderness.id(name));
    }

    private WildernessBiomeModifiers() {
    }
}
