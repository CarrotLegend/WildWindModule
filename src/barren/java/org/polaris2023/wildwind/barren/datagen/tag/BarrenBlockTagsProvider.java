package org.polaris2023.wildwind.barren.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.polaris2023.wildwind.barren.WildWindBarren;
import org.polaris2023.wildwind.barren.block.BarrenBlocks;
import org.polaris2023.wildwind.barren.block.BarrenWoodFamilies;
import org.polaris2023.wildwind.core.block.WoodFamily;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * 瘠土更新的方块标签生成器。
 */
public class BarrenBlockTagsProvider extends BlockTagsProvider {

    public BarrenBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, WildWindBarren.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (WoodFamily family : BarrenWoodFamilies.ALL) {
            registerWoodTags(family);
        }

        addToTag(BlockTags.DIRT, BarrenBlocks.SCORCHED_GRASS_BLOCK.get(), BarrenBlocks.SCORCHED_DIRT.get());
        addToTag(BlockTags.MINEABLE_WITH_SHOVEL, BarrenBlocks.SUSPICIOUS_RED.get());

        addToTag(BlockTags.MINEABLE_WITH_PICKAXE,
                BarrenBlocks.CHISELED_QUARTZ_SANDSTONE.get(),
                BarrenBlocks.CHISELED_SOUL_SANDSTONE.get(),
                BarrenBlocks.CUT_QUARTZ_SANDSTONE.get(),
                BarrenBlocks.CUT_SOUL_SANDSTONE.get(),
                BarrenBlocks.NITRIFIED_QUARTZ_SANDSTONE.get(),
                BarrenBlocks.NITRIFIED_RED_SANDSTONE.get(),
                BarrenBlocks.NITRIFIED_SANDSTONE.get(),
                BarrenBlocks.NITRIFIED_SOUL_SANDSTONE.get(),
                BarrenBlocks.QUARTZ_SANDSTONE.get(),
                BarrenBlocks.SOUL_SANDSTONE.get(),
                BarrenBlocks.SMOOTH_QUARTZ_SANDSTONE.get(),
                BarrenBlocks.SMOOTH_SOUL_SANDSTONE.get());

        addToTag(BlockTags.MINEABLE_WITH_SHOVEL,
                BarrenBlocks.QUARTZ_SAND.get(),
                BarrenBlocks.QUICKSAND.get(),
                BarrenBlocks.RED_QUICKSAND.get(),
                BarrenBlocks.QUARTZ_QUICKSAND.get(),
                BarrenBlocks.SOUL_QUICKSAND.get(),
                BarrenBlocks.SUSPICIOUS_QUARTZ_SAND.get(),
                BarrenBlocks.SUSPICIOUS_SOUL_SAND.get());
    }

    private void registerWoodTags(WoodFamily family) {
        Block log = family.log().get();
        Block wood = family.wood().get();
        Block strippedLog = family.strippedLog().get();
        Block strippedWood = family.strippedWood().get();
        Block planks = family.planks().get();
        Block stairs = family.stairs().get();
        Block slab = family.slab().get();
        Block fence = family.fence().get();
        Block fenceGate = family.fenceGate().get();
        Block door = family.door().get();
        Block trapdoor = family.trapdoor().get();
        Block pressurePlate = family.pressurePlate().get();
        Block button = family.button().get();

        addToTag(BlockTags.LOGS, log, wood, strippedLog, strippedWood);
        addToTag(BlockItemTags.LOGS_THAT_BURN.block(), log, wood, strippedLog, strippedWood);
        addToTag(Tags.Blocks.STRIPPED_LOGS, strippedLog);
        addToTag(Tags.Blocks.STRIPPED_WOODS, strippedWood);

        addToTag(BlockTags.PLANKS, planks);
        addToTag(BlockTags.WOODEN_STAIRS, stairs);
        addToTag(BlockTags.WOODEN_SLABS, slab);
        addToTag(BlockTags.WOODEN_FENCES, fence);
        addToTag(Tags.Blocks.FENCES_WOODEN, fence);
        addToTag(BlockTags.FENCES, fence);
        addToTag(Tags.Blocks.FENCE_GATES_WOODEN, fenceGate);
        addToTag(BlockTags.FENCE_GATES, fenceGate);
        addToTag(BlockTags.WOODEN_DOORS, door);
        addToTag(BlockTags.DOORS, door);
        addToTag(BlockTags.TRAPDOORS, trapdoor);
        addToTag(BlockTags.WOODEN_TRAPDOORS, trapdoor);
        addToTag(BlockTags.WOODEN_PRESSURE_PLATES, pressurePlate);
        addToTag(BlockTags.WOODEN_BUTTONS, button);

        if (family.leaves() != null) {
            addToTag(BlockTags.LEAVES, family.leaves().get());
        }
        if (family.sapling() != null) {
            addToTag(BlockItemTags.SAPLINGS.block(), family.sapling().get());
        }

        addToTag(BlockTags.MINEABLE_WITH_AXE, log, wood, strippedLog, strippedWood, planks, stairs, slab, fence, fenceGate, door, trapdoor, pressurePlate, button);
    }

    public TagAppender<Block> addToTag(TagKey<Block> tag, Block... blocks) {
        if (blocks.length == 0) return tag(tag);
        return tag(tag).addAll(Arrays.stream(blocks).map(BarrenBlockTagsProvider::blockKey));
    }

    private static ResourceKey<Block> blockKey(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block)
                .orElseThrow(() -> new IllegalStateException("未注册的方块: " + block));
    }
}
