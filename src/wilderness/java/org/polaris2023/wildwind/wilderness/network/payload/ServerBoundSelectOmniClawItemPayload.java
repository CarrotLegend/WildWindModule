package org.polaris2023.wildwind.wilderness.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.component.OmniClawTools;
import org.polaris2023.wildwind.wilderness.registry.ModDataComponents;

/**
 * 客户端通知服务端切换全能蟹钳选中工具的网络负载喵~
 *
 * @param slot 目标槽位索引喵~
 * @param index 目标工具索引喵~
 */
public record ServerBoundSelectOmniClawItemPayload(int slot, int index) implements CustomPacketPayload {
	/**
	 * 当前负载的类型标识喵~
	 */
	public static final Type<ServerBoundSelectOmniClawItemPayload> TYPE = new Type<>(WildWindWilderness.id("select_omni_claw_item"));
	/**
	 * 当前负载的流编解码器喵~
	 */
	public static final StreamCodec<ByteBuf, ServerBoundSelectOmniClawItemPayload> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.VAR_INT, ServerBoundSelectOmniClawItemPayload::slot,
			ByteBufCodecs.VAR_INT, ServerBoundSelectOmniClawItemPayload::index,
			ServerBoundSelectOmniClawItemPayload::new
	);

	/**
	 * 获取当前网络负载的类型标识喵~
	 *
	 * @return 负载类型喵~
	 */
	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	/**
	 * 在服务端处理全能蟹钳选中工具切换请求喵~
	 *
	 * @param context 负载处理上下文喵~
	 */
	public void handle(IPayloadContext context) {
		Player player = context.player();
		ItemStack stack = player.containerMenu.getSlot(slot).getItem();

		if (!stack.has(ModDataComponents.OMNI_CLAW_TOOLS.get())) {
			return;
		}
		OmniClawTools tools = stack.get(ModDataComponents.OMNI_CLAW_TOOLS.get());

		if (tools.isEmpty()) {
			return;
		}
		OmniClawTools.Mutable mutableTools = tools.toMutable();
		if (this.index >= mutableTools.size() || this.index < 0 || this.index == mutableTools.getSelectedToolIndex()) {
			return;
		}

		if (mutableTools.get(this.index).isEmpty()) {
			return;
		}
		mutableTools.select(this.index);

		stack.set(ModDataComponents.OMNI_CLAW_TOOLS.get(), mutableTools.toImmutable());
	}
}