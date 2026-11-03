package org.polaris2023.wildwind.core.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import org.polaris2023.wildwind.core.WildWindCore;

import java.util.List;

/**
 * 核心模块的放置特征：杜鹃树。
 */
public final class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> AZALEA_TREE_PLACED_KEY = ResourceKey.create(
            Registries.PLACED_FEATURE,
            WildWindCore.id("azalea_tree_placed")
    );

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(AZALEA_TREE_PLACED_KEY, new PlacedFeature(
                configuredFeatures.getOrThrow(ModConfiguredFeatures.AZALEA_TREE_KEY),
                List.of(
                        CountPlacement.of(UniformInt.of(1, 2)),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.matchesTag(BlockTags.AIR), 12),
                        RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(-1)),
                        BiomeFilter.biome()
                )
        ));
    }

    private ModPlacedFeatures() {
    }
}
