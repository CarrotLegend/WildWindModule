package org.polaris2023.wildwind.barren.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.polaris2023.wildwind.barren.WildWindBarren;
import org.polaris2023.wildwind.barren.datagen.lang.BarrenEnUsLanguageProvider;
import org.polaris2023.wildwind.barren.datagen.lang.BarrenZhCnLanguageProvider;
import org.polaris2023.wildwind.barren.datagen.tag.BarrenBlockTagsProvider;
import org.polaris2023.wildwind.barren.datagen.tag.BarrenItemTagsProvider;

/**
 * 瘠土更新的数据生成入口。
 */
@EventBusSubscriber(modid = WildWindBarren.MOD_ID)
public class BarrenDatagen {

    @SubscribeEvent
    private static void gatherClient(GatherDataEvent.Client event) {
        event.createProvider(BarrenModelProvider::new);
        event.createProvider(BarrenEnUsLanguageProvider::new);
        event.createProvider(BarrenZhCnLanguageProvider::new);
    }

    @SubscribeEvent
    private static void gatherServer(GatherDataEvent.Server event) {
        event.createProvider(BarrenBlockTagsProvider::new);
        event.createProvider(BarrenItemTagsProvider::new);
        event.createProvider(BarrenLootProvider::new);
        event.createProvider(BarrenDatapackProvider::new);
        event.createProvider(BarrenDataMapsProvider::new);
    }

    private BarrenDatagen() {
    }
}
