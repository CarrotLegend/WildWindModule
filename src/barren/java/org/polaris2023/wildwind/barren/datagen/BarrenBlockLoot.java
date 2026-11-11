package org.polaris2023.wildwind.barren.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;
import org.polaris2023.wildwind.barren.block.BarrenBlocks;
import org.polaris2023.wildwind.barren.block.BarrenWoodFamilies;
import org.polaris2023.wildwind.core.block.WoodFamily;

import java.util.Set;

/**
 * 瘠土更新的方块战利品表。
 */
public class BarrenBlockLoot extends BlockLootSubProvider {

    public BarrenBlockLoot(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected @NonNull Iterable<Block> getKnownBlocks() {
        return BarrenBlocks.BLOCKS.getEntries().stream().map(h -> (Block) h.value()).toList();
    }

    @Override
    protected void generate() {
        for (WoodFamily family : BarrenWoodFamilies.ALL) {
            generateWood(family);
        }

        dropPottedContents(BarrenBlocks.POTTED_BLAZE_SAPLING.get());
        dropPottedContents(BarrenBlocks.POTTED_SOUL_SAPLING.get());

        dropSelf(BarrenBlocks.SCORCHED_GRASS_BLOCK.get());
        dropSelf(BarrenBlocks.SCORCHED_DIRT.get());
        dropSelf(BarrenBlocks.SCORCHED_GRASS.get());
        dropSelf(BarrenBlocks.SCORCHED_TWIG.get());
        dropSelf(BarrenBlocks.TINY_CACTUS.get());
        dropSelf(BarrenBlocks.QUICKSAND.get());
        dropSelf(BarrenBlocks.RED_QUICKSAND.get());
        add(BarrenBlocks.SUSPICIOUS_RED.get(), createSilkTouchOnlyTable(BarrenBlocks.SUSPICIOUS_RED.get()));

        dropSelf(BarrenBlocks.CHISELED_QUARTZ_SANDSTONE.get());
        dropSelf(BarrenBlocks.CHISELED_SOUL_SANDSTONE.get());
        dropSelf(BarrenBlocks.CUT_QUARTZ_SANDSTONE.get());
        dropSelf(BarrenBlocks.CUT_SOUL_SANDSTONE.get());
        dropSelf(BarrenBlocks.NITRIFIED_QUARTZ_SANDSTONE.get());
        dropSelf(BarrenBlocks.NITRIFIED_RED_SANDSTONE.get());
        dropSelf(BarrenBlocks.NITRIFIED_SANDSTONE.get());
        dropSelf(BarrenBlocks.NITRIFIED_SOUL_SANDSTONE.get());
        dropSelf(BarrenBlocks.QUARTZ_SANDSTONE.get());
        dropSelf(BarrenBlocks.SOUL_SANDSTONE.get());
        dropSelf(BarrenBlocks.SMOOTH_QUARTZ_SANDSTONE.get());
        dropSelf(BarrenBlocks.SMOOTH_SOUL_SANDSTONE.get());
        dropSelf(BarrenBlocks.QUARTZ_SAND.get());
        dropSelf(BarrenBlocks.QUARTZ_QUICKSAND.get());
        dropSelf(BarrenBlocks.SOUL_QUICKSAND.get());
        dropSelf(BarrenBlocks.ASH_BLOCK.get());
        add(BarrenBlocks.SUSPICIOUS_QUARTZ_SAND.get(), createSilkTouchOnlyTable(BarrenBlocks.SUSPICIOUS_QUARTZ_SAND.get()));
        add(BarrenBlocks.SUSPICIOUS_SOUL_SAND.get(), createSilkTouchOnlyTable(BarrenBlocks.SUSPICIOUS_SOUL_SAND.get()));
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

        if (family.hasLeaves()) {
            Block leaves = family.leaves().get();
            add(leaves, createLeavesDrops(leaves, family.sapling().get(), 0.05F));
            dropSelf(family.sapling().get());
        }
    }
}
