package org.polaris2023.wildwind.wilderness.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.datagen.lang.WildernessEnUsLanguageProvider;
import org.polaris2023.wildwind.wilderness.datagen.lang.WildernessZhCnLanguageProvider;
import org.polaris2023.wildwind.wilderness.datagen.tag.WildernessBiomeTagsProvider;
import org.polaris2023.wildwind.wilderness.datagen.tag.WildernessBlockTagsProvider;
import org.polaris2023.wildwind.wilderness.datagen.tag.WildernessEntityTypeTagsProvider;
import org.polaris2023.wildwind.wilderness.datagen.tag.WildernessItemTagsProvider;

/**
 * 荒野更新的数据生成入口。
 */
@EventBusSubscriber(modid = WildWindWilderness.MOD_ID)
public class WildernessDatagen {

    @SubscribeEvent
    private static void gatherClient(GatherDataEvent.Client event) {
        event.createProvider(WildernessModelProvider::new);
        event.createProvider(WildernessEnUsLanguageProvider::new);
        event.createProvider(WildernessZhCnLanguageProvider::new);
    }

    @SubscribeEvent
    private static void gatherServer(GatherDataEvent.Server event) {
        event.createProvider(WildernessBlockTagsProvider::new);
        event.createProvider(WildernessItemTagsProvider::new);
        event.createProvider(WildernessEntityTypeTagsProvider::new);
        event.createProvider(WildernessBiomeTagsProvider::new);
        event.createProvider(WildernessLootProvider::new);
        event.createProvider(WildernessDatapackProvider::new);
        event.createProvider(WildernessRecipeProvider.Runner::new);
    }

    private WildernessDatagen() {
    }
}
