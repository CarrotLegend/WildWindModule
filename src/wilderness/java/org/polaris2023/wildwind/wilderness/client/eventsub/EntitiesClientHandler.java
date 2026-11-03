package org.polaris2023.wildwind.wilderness.client.eventsub;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.client.entity.renderer.MudcrabRenderer;
import org.polaris2023.wildwind.wilderness.client.entity.renderer.PiranhaRenderer;
import org.polaris2023.wildwind.wilderness.registry.ModEntities;

/**
 * 荒野更新的实体渲染器注册。
 */
@EventBusSubscriber(modid = WildWindWilderness.MOD_ID, value = Dist.CLIENT)
public class EntitiesClientHandler {

    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.PIRANHA.get(), PiranhaRenderer::new);
        event.registerEntityRenderer(ModEntities.MUDCRAB.get(), MudcrabRenderer::new);
    }

    private EntitiesClientHandler() {
    }
}
