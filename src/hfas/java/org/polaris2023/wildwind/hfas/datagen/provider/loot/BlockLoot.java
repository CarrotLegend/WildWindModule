package org.polaris2023.wildwind.hfas.datagen.provider.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;
import org.polaris2023.wildwind.hfas.block.ModBlocks;
import org.polaris2023.wildwind.hfas.block.ModWoodFamilies;
import org.polaris2023.wildwind.hfas.block.WoodFamily;

import java.util.Set;

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
        // 三种木材质统一按家族生成
        for (WoodFamily family : ModWoodFamilies.ALL) {
            generateWood(family);
        }

        // 树苗花盆：掉落花盆 + 里面的树苗（与原版一致）
        dropPottedContents(ModBlocks.POTTED_BLAZE_SAPLING.get());
        dropPottedContents(ModBlocks.POTTED_SOUL_SAPLING.get());

        // 其他方块
        dropSelf(ModBlocks.SCORCHED_GRASS_BLOCK.get());
        dropSelf(ModBlocks.SCORCHED_DIRT.get());
        dropSelf(ModBlocks.SCORCHED_GRASS.get());
        dropSelf(ModBlocks.SCORCHED_TWIG.get());
        dropSelf(ModBlocks.TINY_CACTUS.get());
        dropOther(ModBlocks.FLETCHING_TABLE.get(), Items.FLETCHING_TABLE);
        dropSelf(ModBlocks.QUICKSAND.get());

        // 可疑红沙
        add(ModBlocks.SUSPICIOUS_RED.get(), createSilkTouchOnlyTable(ModBlocks.SUSPICIOUS_RED.get()));
        dropSelf(ModBlocks.SOUL_JACK_O_LANTERN.get());

        // 砂岩 / 沙 / 花套件
        dropSelf(ModBlocks.CHISELED_QUARTZ_SANDSTONE.get());
        dropSelf(ModBlocks.CHISELED_SOUL_SANDSTONE.get());
        dropSelf(ModBlocks.CUT_QUARTZ_SANDSTONE.get());
        dropSelf(ModBlocks.CUT_SOUL_SANDSTONE.get());
        dropSelf(ModBlocks.NITRIFIED_QUARTZ_SANDSTONE.get());
        dropSelf(ModBlocks.NITRIFIED_RED_SANDSTONE.get());
        dropSelf(ModBlocks.NITRIFIED_SANDSTONE.get());
        dropSelf(ModBlocks.NITRIFIED_SOUL_SANDSTONE.get());
        dropSelf(ModBlocks.QUARTZ_SANDSTONE.get());
        dropSelf(ModBlocks.SOUL_SANDSTONE.get());
        dropSelf(ModBlocks.SMOOTH_QUARTZ_SANDSTONE.get());
        dropSelf(ModBlocks.SMOOTH_SOUL_SANDSTONE.get());
        dropSelf(ModBlocks.QUARTZ_SANDSTONE_STAIRS.get());
        add(ModBlocks.QUARTZ_SANDSTONE_SLAB.get(), this::createSlabItemTable);
        dropSelf(ModBlocks.QUARTZ_SANDSTONE_WALL.get());
        dropSelf(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_STAIRS.get());
        add(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_SLAB.get(), this::createSlabItemTable);
        add(ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB.get(), this::createSlabItemTable);
        dropSelf(ModBlocks.SOUL_SANDSTONE_STAIRS.get());
        add(ModBlocks.SOUL_SANDSTONE_SLAB.get(), this::createSlabItemTable);
        dropSelf(ModBlocks.SOUL_SANDSTONE_WALL.get());
        dropSelf(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get());
        add(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(), this::createSlabItemTable);
        add(ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get(), this::createSlabItemTable);
        dropSelf(ModBlocks.QUARTZ_SAND.get());
        dropSelf(ModBlocks.QUARTZ_QUICKSAND.get());
        dropSelf(ModBlocks.SOUL_QUICKSAND.get());
        dropSelf(ModBlocks.ROSE.get());
        dropSelf(ModBlocks.SOUL_ROSE.get());
        dropSelf(ModBlocks.SOUL_ROSE_BUSH.get());
        add(ModBlocks.SUSPICIOUS_QUARTZ_SAND.get(), createSilkTouchOnlyTable(ModBlocks.SUSPICIOUS_QUARTZ_SAND.get()));
        add(ModBlocks.SUSPICIOUS_SOUL_SAND.get(), createSilkTouchOnlyTable(ModBlocks.SUSPICIOUS_SOUL_SAND.get()));
    }

    /**
     * 一种木材的战利品表。
     * <p>
     * 只有「门」需要 {@code createDoorTable}（按 half=lower 去重掉落）；
     * 活板门是单方块、可挂在天花板（half=top），必须用 {@code dropSelf}，
     * 否则天花板上的活板门破坏后不掉落。原版 VanillaBlockLoot 亦如此。
     */
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
