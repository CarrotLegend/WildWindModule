package org.polaris2023.wildwind.core.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.client.screen.FletchingTableScreen;
import org.polaris2023.wildwind.core.menu.ModMenus;

/**
 * 核心模块的客户端界面注册。
 */
@EventBusSubscriber(modid = WildWindCore.MOD_ID, value = Dist.CLIENT)
public final class CoreClientScreens {

    @SubscribeEvent
    static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.FLETCHING_TABLE.get(), FletchingTableScreen::new);
    }

    private CoreClientScreens() {
    }
}
