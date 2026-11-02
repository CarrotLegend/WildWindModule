package org.polaris2023.wildwind.barren.groups;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.polaris2023.wildwind.barren.WildWindBarren;
import org.polaris2023.wildwind.barren.block.BarrenBlocks;
import org.polaris2023.wildwind.barren.block.BarrenWoodFamilies;
import org.polaris2023.wildwind.core.block.WoodFamily;

/**
 * 瘠土更新的原版物品栏排序（模组自己的页由 {@link org.polaris2023.wildwind.barren.registry.ModCreativeTabs} 注册）。
 */
@EventBusSubscriber(modid = WildWindBarren.MOD_ID)
public final class BarrenCreativeTabs {

    private static final CreativeModeTab.TabVisibility VISIBILITY = CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;

    @SubscribeEvent
    public static void buildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            appendWoodSet(event, new ItemStack(Blocks.BAMBOO_BUTTON), BarrenWoodFamilies.BLAZE);
            appendWoodSet(event, BarrenBlocks.BLAZE_BUTTON.toStack(), BarrenWoodFamilies.SOUL);
        }

        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            ItemStack blazeLogStack = BarrenBlocks.BLAZE_LOG.toStack();
            event.insertAfter(new ItemStack(Items.CHERRY_LOG), blazeLogStack, VISIBILITY);
            event.insertAfter(blazeLogStack, BarrenBlocks.SOUL_LOG.toStack(), VISIBILITY);
            event.insertAfter(BarrenBlocks.SOUL_LOG.toStack(), BarrenBlocks.BLAZE_LEAVES.toStack(), VISIBILITY);
            event.insertAfter(BarrenBlocks.BLAZE_LEAVES.toStack(), BarrenBlocks.SOUL_LEAVES.toStack(), VISIBILITY);
            event.insertAfter(BarrenBlocks.SOUL_LEAVES.toStack(), BarrenBlocks.BLAZE_SAPLING.toStack(), VISIBILITY);
            event.insertAfter(BarrenBlocks.BLAZE_SAPLING.toStack(), BarrenBlocks.SOUL_SAPLING.toStack(), VISIBILITY);
            event.insertAfter(BarrenBlocks.SOUL_SAPLING.toStack(), BarrenBlocks.QUICKSAND.toStack(), VISIBILITY);
            event.insertAfter(BarrenBlocks.QUICKSAND.toStack(), BarrenBlocks.QUARTZ_SAND.toStack(), VISIBILITY);
            event.insertAfter(BarrenBlocks.QUARTZ_SAND.toStack(), BarrenBlocks.TINY_CACTUS.toStack(), VISIBILITY);
        }
    }

    private static void appendWoodSet(BuildCreativeModeTabContentsEvent event, ItemStack anchor, WoodFamily family) {
        ItemStack previous = anchor;
        for (DeferredBlock<? extends Block> block : family.buildingBlocks()) {
            ItemStack stack = block.toStack();
            event.insertAfter(previous, stack, VISIBILITY);
            previous = stack;
        }
    }

    private BarrenCreativeTabs() {
    }
}
