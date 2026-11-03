package org.polaris2023.wildwind.barren.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.polaris2023.wildwind.barren.WildWindBarren;
import org.polaris2023.wildwind.barren.block.BarrenWoodFamilies;
import org.polaris2023.wildwind.core.block.WoodFamily;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * 瘠土更新的物品标签生成器。
 */
public class BarrenItemTagsProvider extends ItemTagsProvider {

    public BarrenItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, WildWindBarren.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (WoodFamily family : BarrenWoodFamilies.ALL) {
            registerWoodLogItemTag(family);
            registerWoodItemTags(family);
        }
    }

    private void registerWoodItemTags(WoodFamily family) {
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
        Block leaves = family.leaves() == null ? null : family.leaves().get();
        Block sapling = family.sapling() == null ? null : family.sapling().get();

        addToTag(ItemTags.LOGS, log, wood, strippedLog, strippedWood);
        addToTag(ItemTags.LOGS_THAT_BURN, log, wood, strippedLog, strippedWood);
        addToTag(Tags.Items.STRIPPED_LOGS, strippedLog);
        addToTag(Tags.Items.STRIPPED_WOODS, strippedWood);

        addToTag(ItemTags.PLANKS, planks);
        addToTag(ItemTags.WOODEN_STAIRS, stairs);
        addToTag(ItemTags.WOODEN_SLABS, slab);
        addToTag(ItemTags.WOODEN_FENCES, fence);
        addToTag(Tags.Items.FENCES_WOODEN, fence);
        addToTag(Tags.Items.FENCE_GATES_WOODEN, fenceGate);
        addToTag(ItemTags.FENCE_GATES, fenceGate);
        addToTag(ItemTags.WOODEN_DOORS, door);
        addToTag(BlockItemTags.DOORS.item(), door);
        addToTag(BlockItemTags.TRAPDOORS.item(), trapdoor);
        addToTag(ItemTags.WOODEN_TRAPDOORS, trapdoor);
        addToTag(ItemTags.WOODEN_PRESSURE_PLATES, pressurePlate);
        addToTag(ItemTags.WOODEN_BUTTONS, button);
        addToTag(BlockItemTags.BUTTONS.item(), button);

        if (leaves != null) {
            addToTag(ItemTags.LEAVES, leaves);
        }
        if (sapling != null) {
            addToTag(ItemTags.SAPLINGS, sapling);
        }
    }

    private void registerWoodLogItemTag(WoodFamily family) {
        TagKey<Item> logsTag = TagKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(WildWindBarren.MOD_ID, family.name() + "_logs")
        );
        addToTag(logsTag,
                family.log().get(), family.wood().get(),
                family.strippedLog().get(), family.strippedWood().get());
    }

    public void addToTag(TagKey<Item> tag, Block... blocks) {
        if (blocks.length == 0) return;
        tag(tag).addAll(Arrays.stream(blocks).map(Block::asItem).map(BarrenItemTagsProvider::itemKey));
    }

    public void addToTag(TagKey<Item> tag, Item... items) {
        if (items.length == 0) return;
        tag(tag).addAll(Arrays.stream(items).map(BarrenItemTagsProvider::itemKey));
    }

    private static ResourceKey<Item> itemKey(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item)
                .orElseThrow(() -> new IllegalStateException("未注册的物品: " + item));
    }
}
