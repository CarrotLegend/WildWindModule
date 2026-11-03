package org.polaris2023.wildwind.wilderness.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.tag.ModBiomeTags;

import java.util.concurrent.CompletableFuture;

/**
 * 荒野更新的群系标签生成器。
 */
public class WildernessBiomeTagsProvider extends BiomeTagsProvider {

    public WildernessBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, WildWindWilderness.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModBiomeTags.EntityGen.MUDCRABS_WARM)
                .add(Biomes.DESERT)
                .add(Biomes.WARM_OCEAN)
                .addTag(BiomeTags.IS_JUNGLE)
                .addTag(BiomeTags.IS_SAVANNA)
                .addTag(BiomeTags.IS_NETHER)
                .addTag(BiomeTags.IS_BADLANDS)
                .add(Biomes.MANGROVE_SWAMP);
        tag(ModBiomeTags.EntityGen.MUDCRABS_COLD)
                .add(Biomes.SNOWY_PLAINS)
                .add(Biomes.ICE_SPIKES)
                .add(Biomes.FROZEN_PEAKS)
                .add(Biomes.JAGGED_PEAKS)
                .add(Biomes.SNOWY_SLOPES)
                .add(Biomes.FROZEN_OCEAN)
                .add(Biomes.DEEP_FROZEN_OCEAN)
                .add(Biomes.GROVE)
                .add(Biomes.DEEP_DARK)
                .add(Biomes.FROZEN_RIVER)
                .add(Biomes.SNOWY_TAIGA)
                .add(Biomes.SNOWY_BEACH)
                .addTag(BiomeTags.IS_END);
        tag(ModBiomeTags.EntityGen.PIRANHA_SPAWNS)
                .add(Biomes.JUNGLE)
                .addTag(Tags.Biomes.IS_SWAMP);
    }
}
