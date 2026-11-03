package org.polaris2023.wildwind.wilderness.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.entity.animal.MudcrabVariant;

//内容请注册到mod主类的Datapack注册事件中
public class DatadrivenRegistryKey {
    //---[实体类]---
    //--=[实体变种]=--
    public static final ResourceKey<Registry<MudcrabVariant>> MUDCRAB_VARIANT = createRegistryKey("entity_variant/mudcrab");



    private static <T> ResourceKey<Registry<T>> createRegistryKey(String name) {
        return ResourceKey.createRegistryKey(WildWindWilderness.id(name));
    }
}
