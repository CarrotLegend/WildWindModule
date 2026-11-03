package org.polaris2023.wildwind.wilderness.client;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.client.tooltip.ClientOmniClawTooltip;
import org.polaris2023.wildwind.wilderness.component.OmniClawTools;
import org.polaris2023.wildwind.wilderness.network.payload.ServerBoundSelectOmniClawItemPayload;
import org.polaris2023.wildwind.wilderness.registry.ModDataComponents;

import java.util.List;

/**
 * 客户端侧钩子与界面注册入口喵~
 * <p>
 * 26.2 迁移说明：PacketDistributor.sendToServer 迁移至 ClientPacketDistributor；
 * AbstractContainerScreen#getSlotUnderMouse 废弃，改用 getHoveredSlot()。
 */
@EventBusSubscriber(modid = WildWindWilderness.MOD_ID, value = Dist.CLIENT)
public class ModClientHooks {

	/**
	 * 注册全能蟹钳的客户端提示组件工厂喵~
	 *
	 * @param event 客户端提示组件注册事件喵~
	 */
	@SubscribeEvent
	static void onRegisterClientTooltipComponent(RegisterClientTooltipComponentFactoriesEvent event) {
		event.register(OmniClawTools.class, ClientOmniClawTooltip::new);
	}

	/**
	 * 处理容器界面中的滚轮选中工具切换并发包同步喵~
	 *
	 * @param event 滚轮前事件喵~
	 */
	@SubscribeEvent
	static void onScreenPreMouseScroll(ScreenEvent.MouseScrolled.Pre event) {
		if (!(event.getScreen() instanceof AbstractContainerScreen<?> screen)) return;
		Slot slot = screen.getHoveredSlot();

		if (slot == null) return;
		ItemStack hoverItem = slot.getItem();

		if (!hoverItem.has(ModDataComponents.OMNI_CLAW_TOOLS.get())) return;
		OmniClawTools tools = hoverItem.get(ModDataComponents.OMNI_CLAW_TOOLS.get());

		if (tools.getNonEmptyItemCount() < 2) return;
		double y = event.getScrollDeltaY();
		int index = tools.getSelectedToolIndex();
		int delta = (int) Math.signum(y == 0 ? event.getScrollDeltaX() : -y);
		List<ItemStack> toolStacks = tools.getTools();

		int start = index;
		int size = toolStacks.size();
		do {
			index = (index + delta + size) % size;

			ItemStack stack = toolStacks.get(index);
			if (stack != null && !stack.isEmpty()) {
				break;
			}
		} while (index != start);

		ClientPacketDistributor.sendToServer(new ServerBoundSelectOmniClawItemPayload(slot.index, index));
		event.setCanceled(true);
	}

	private ModClientHooks() {
	}
}