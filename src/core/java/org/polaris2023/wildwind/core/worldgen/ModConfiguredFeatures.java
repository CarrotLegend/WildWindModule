package org.polaris2023.wildwind.core.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.block.ModBlocks;

import java.util.Optional;

/**
 * 核心模块的配置特征：杜鹃树（杜鹃木原木 + 原版杜鹃树叶）。
 */
public final class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> AZALEA_TREE_KEY = ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            WildWindCore.id("azalea_tree")
    );

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        BlockStateProvider belowTrunkProvider = TreeConfiguration.defaultPlaceBelowTreeTrunkProvider(biomes);

        context.register(AZALEA_TREE_KEY, new ConfiguredFeature<>(Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.AZALEA_LOG.get()),
                        new BendingTrunkPlacer(4, 2, 0, 3, UniformInt.of(1, 2)),
                        new WeightedStateProvider(
                                WeightedList.<BlockState>builder()
                                        .add(Blocks.AZALEA_LEAVES.defaultBlockState(), 3)
                                        .add(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)
                        ),
                        new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50),
                        Optional.empty(),
                        new TwoLayersFeatureSize(1, 0, 1),
                        SimpleStateProvider.simple(Blocks.ROOTED_DIRT)
                ).build()));
    }

    private ModConfiguredFeatures() {
    }
}
