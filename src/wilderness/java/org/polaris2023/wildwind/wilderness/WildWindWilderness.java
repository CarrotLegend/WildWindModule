package org.polaris2023.wildwind.wilderness;

import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import org.polaris2023.wildwind.wilderness.block.WildernessBlocks;
import org.polaris2023.wildwind.wilderness.entity.animal.MudcrabVariant;
import org.polaris2023.wildwind.wilderness.registry.DatadrivenRegistryKey;
import org.polaris2023.wildwind.wilderness.registry.ModAttributes;
import org.polaris2023.wildwind.wilderness.registry.ModCreativeTabs;
import org.polaris2023.wildwind.wilderness.registry.ModDataComponents;
import org.polaris2023.wildwind.wilderness.registry.ModEntities;
import org.polaris2023.wildwind.wilderness.registry.ModEntityDataSerializers;
import org.polaris2023.wildwind.wilderness.registry.ModItems;
import org.polaris2023.wildwind.wilderness.registry.ModMobEffects;
import org.polaris2023.wildwind.wilderness.registry.ModPotions;
import org.polaris2023.wildwind.wilderness.registry.ModSoundEvents;

/**
 * 原野之风：荒野更新（Wild Wind: Wilderness Update）
 * <p>
 * 泥沼蟹 / 恐鱼 / 万用蟹钳 / 延展药水 / 灵魂南瓜灯等荒野内容。
 */
@Mod(WildWindWilderness.MOD_ID)
@EventBusSubscriber(modid = WildWindWilderness.MOD_ID)
public class WildWindWilderness {
    public static final String MOD_ID = "ww_wilderness";

    public WildWindWilderness(IEventBus bus) {
        WildernessBlocks.register(bus);

        ModDataComponents.register(bus);
        ModAttributes.register(bus);
        ModMobEffects.register(bus);
        ModPotions.register(bus);
        ModSoundEvents.register(bus);

        ModEntityDataSerializers.register(bus);
        ModEntities.register(bus);
        ModItems.register(bus);
        ModCreativeTabs.register(bus);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    // 数据驱动项注册
    @SubscribeEvent
    public static void datapackRegistry(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(DatadrivenRegistryKey.MUDCRAB_VARIANT, MudcrabVariant.DIRECT_CODEC, MudcrabVariant.NETWORK_CODEC);
    }
}

