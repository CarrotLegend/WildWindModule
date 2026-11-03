package org.polaris2023.wildwind.wilderness.datagen.tag;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.block.WildernessBlocks;
import org.polaris2023.wildwind.wilderness.tag.ModBlockTags;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * 荒野更新的方块标签生成器。
 */
public class WildernessBlockTagsProvider extends BlockTagsProvider {

    public WildernessBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, WildWindWilderness.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addToTag(BlockTags.MINEABLE_WITH_AXE, WildernessBlocks.SOUL_JACK_O_LANTERN.get());
        addToTag(BlockTags.PIGLIN_REPELLENTS, WildernessBlocks.SOUL_JACK_O_LANTERN.get());

        addToTag(ModBlockTags.EntityAbout.MUDCRAB_SPAWNABLE_ON, Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS);

        addToTag(ModBlockTags.EntityAbout.MUDCRAB_SPAWNABLE_IN_WATER_GROUND, Blocks.CLAY, Blocks.MUD, Blocks.DIRT, Blocks.MUDDY_MANGROVE_ROOTS)
                .addTag(BlockTags.SAND)
                .addTag(Tags.Blocks.GRAVELS);

        addToTag(ModBlockTags.EntityAbout.MUDCRAB_PREFERRED_WANDER_BLOCKS, Blocks.MANGROVE_LOG, Blocks.MANGROVE_ROOTS, Blocks.MUDDY_MANGROVE_ROOTS);
    }

    @SuppressWarnings("all")
    public TagAppender<Block> addToTag(TagKey<Block> tag, Block... items) {
        if (items.length == 0) return tag(tag);
        return tag(tag).addAll(Arrays.stream(items).map(Block::builtInRegistryHolder).map(Holder.Reference::getKey));
    }
}
