package org.polaris2023.wildwind.wilderness.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.network.payload.ServerBoundSelectOmniClawItemPayload;

/**
 * 荒野更新的网络负载注册入口
 */
@EventBusSubscriber(modid = WildWindWilderness.MOD_ID)
public class WildernessNetwork {

	@SubscribeEvent
	static void register(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar("1");
		registrar.commonToServer(
				ServerBoundSelectOmniClawItemPayload.TYPE,
				ServerBoundSelectOmniClawItemPayload.STREAM_CODEC,
				ServerBoundSelectOmniClawItemPayload::handle
		);
	}

	private WildernessNetwork() {
	}
}