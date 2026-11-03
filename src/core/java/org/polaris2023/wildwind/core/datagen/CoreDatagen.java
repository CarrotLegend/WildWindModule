package org.polaris2023.wildwind.core.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.datagen.provider.ModDataMapsProvider;
import org.polaris2023.wildwind.core.datagen.provider.ModDatapackProvider;
import org.polaris2023.wildwind.core.datagen.provider.ModLootProvider;
import org.polaris2023.wildwind.core.datagen.provider.ModModelProvider;
import org.polaris2023.wildwind.core.datagen.provider.ModRecipeProvider;
import org.polaris2023.wildwind.core.datagen.provider.lang.EnUsLanguageProvider;
import org.polaris2023.wildwind.core.datagen.provider.lang.ZhCnLanguageProvider;
import org.polaris2023.wildwind.core.datagen.provider.tag.ModBlockTagsProvider;
import org.polaris2023.wildwind.core.datagen.provider.tag.ModEntityTypeTagsProvider;
import org.polaris2023.wildwind.core.datagen.provider.tag.ModItemTagsProvider;

/**
 * 核心模块的数据生成入口。
 */
@EventBusSubscriber(modid = WildWindCore.MOD_ID)
public class CoreDatagen {

    @SubscribeEvent
    private static void gatherClient(GatherDataEvent.Client event) {
        event.createProvider(ModModelProvider::new);
        event.createProvider(EnUsLanguageProvider::new);
        event.createProvider(ZhCnLanguageProvider::new);
    }

    @SubscribeEvent
    private static void gatherServer(GatherDataEvent.Server event) {
        event.createProvider(ModBlockTagsProvider::new);
        event.createProvider(ModItemTagsProvider::new);
        event.createProvider(ModEntityTypeTagsProvider::new);
        event.createProvider(ModLootProvider::new);
        event.createProvider(ModDatapackProvider::new);
        event.createProvider(ModDataMapsProvider::new);
        event.createProvider(ModRecipeProvider.Runner::new);
    }

    private CoreDatagen() {
    }
}
