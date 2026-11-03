package org.polaris2023.wildwind.core.client;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.component.ArrowComponent;
import org.polaris2023.wildwind.core.registry.ModDataComponents;

/**
 * 矢术提示框：把箭羽 / 箭杆 / 箭头的效果写进物品说明。
 */
@EventBusSubscriber(modid = WildWindCore.MOD_ID, value = Dist.CLIENT)
public final class CoreClientTooltips {

    @SubscribeEvent
    static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        ArrowComponent component = stack.get(ModDataComponents.ARROW_COMPONENT.get());
        if (component == null) {
            return;
        }

        String description = component.getFullDescription();
        if (description.isEmpty()) {
            return;
        }

        for (String line : description.split("\n")) {
            event.getToolTip().add(Component.literal(line).withStyle(ChatFormatting.GRAY));
        }
    }

    private CoreClientTooltips() {
    }
}
