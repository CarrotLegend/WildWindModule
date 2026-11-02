package org.polaris2023.wildwind.wilderness.groups;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.block.WildernessBlocks;
import org.polaris2023.wildwind.wilderness.registry.ModItems;

/**
 * 荒野更新的原版物品栏排序（模组自己的页由 {@link org.polaris2023.wildwind.wilderness.registry.ModCreativeTabs} 注册）。
 */
@EventBusSubscriber(modid = WildWindWilderness.MOD_ID)
public final class WildernessCreativeTabs {

    private static final CreativeModeTab.TabVisibility VISIBILITY = CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;

    @SubscribeEvent
    public static void buildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.insertAfter(new ItemStack(Items.JACK_O_LANTERN), WildernessBlocks.SOUL_JACK_O_LANTERN.toStack(), VISIBILITY);
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.insertAfter(new ItemStack(Items.COD_BUCKET), ModItems.MUDCRAB_BUCKET.toStack(), VISIBILITY);
            event.insertAfter(ModItems.MUDCRAB_BUCKET.toStack(), ModItems.PIRANHA_BUCKET.toStack(), VISIBILITY);
            event.insertAfter(ModItems.PIRANHA_BUCKET.toStack(), ModItems.OMNI_CLAW.toStack(), VISIBILITY);
        }

        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.insertAfter(new ItemStack(Items.COOKED_COD), ModItems.PIRANHA.toStack(), VISIBILITY);
            event.insertAfter(ModItems.PIRANHA.toStack(), ModItems.COOKED_PIRANHA.toStack(), VISIBILITY);
        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.insertAfter(new ItemStack(Items.SPIDER_EYE), ModItems.CRAB_CLAW.toStack(), VISIBILITY);
            event.insertAfter(ModItems.CRAB_CLAW.toStack(), ModItems.FANG.toStack(), VISIBILITY);
        }
    }

    private WildernessCreativeTabs() {
    }
}
