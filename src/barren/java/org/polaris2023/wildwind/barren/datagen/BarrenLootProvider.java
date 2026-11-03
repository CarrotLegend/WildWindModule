package org.polaris2023.wildwind.barren.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * 瘠土更新的战利品表入口（只有方块战利品）。
 */
public class BarrenLootProvider extends LootTableProvider {

    public BarrenLootProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(
                output,
                Set.of(),
                List.of(new SubProviderEntry(BarrenBlockLoot::new, LootContextParamSets.BLOCK)),
                lookupProvider
        );
    }
}
