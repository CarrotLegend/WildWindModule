package org.polaris2023.wildwind.core.datagen.provider.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;
import org.polaris2023.wildwind.core.block.ModBlocks;
import org.polaris2023.wildwind.core.block.ModWoodFamilies;
import org.polaris2023.wildwind.core.block.WoodFamily;

import java.util.Set;

/**
 * 核心模块的方块战利品表。
 */
public class BlockLoot extends BlockLootSubProvider {

    public BlockLoot(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected @NonNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(h -> (Block) h.value()).toList();
    }

    @Override
    protected void generate() {
        for (WoodFamily family : ModWoodFamilies.ALL) {
            generateWood(family);
        }

        dropSelf(ModBlocks.FLETCHING_TABLE.get());
        dropSelf(ModBlocks.ROSE.get());
        dropSelf(ModBlocks.SOUL_ROSE.get());
        dropSelf(ModBlocks.SOUL_ROSE_BUSH.get());
    }

    private void generateWood(WoodFamily family) {
        dropSelf(family.log().get());
        dropSelf(family.wood().get());
        dropSelf(family.strippedLog().get());
        dropSelf(family.strippedWood().get());
        dropSelf(family.planks().get());
        dropSelf(family.stairs().get());
        dropSelf(family.slab().get());
        dropSelf(family.fence().get());
        dropSelf(family.fenceGate().get());
        dropSelf(family.pressurePlate().get());
        dropSelf(family.button().get());
        dropSelf(family.trapdoor().get());
        add(family.door().get(), createDoorTable(family.door().get()));
    }
}
