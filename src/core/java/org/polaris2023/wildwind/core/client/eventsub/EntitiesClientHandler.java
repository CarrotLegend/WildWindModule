package org.polaris2023.wildwind.core.client.eventsub;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.client.entity.renderer.SpiderlingRenderer;
import org.polaris2023.wildwind.core.registry.ModEntities;

/**
 * 核心模块的实体渲染器注册。
 */
@EventBusSubscriber(modid = WildWindCore.MOD_ID, value = Dist.CLIENT)
public final class EntitiesClientHandler {

    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.SPIDERLING.get(), SpiderlingRenderer::new);
    }

    private EntitiesClientHandler() {
    }
}
