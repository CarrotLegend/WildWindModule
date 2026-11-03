package org.polaris2023.wildwind.core.registry;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.component.ArrowComponent;

/**
 * 核心模块的数据组件注册。
 */
public final class ModDataComponents {
    public static final DeferredRegister.DataComponents REGISTER =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, WildWindCore.MOD_ID);

    /** 箭矢组件 - 存储箭羽、箭杆、箭头类型 */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ArrowComponent>> ARROW_COMPONENT =
            REGISTER.registerComponentType("arrow_component", builder ->
                    builder.persistent(ArrowComponent.CODEC).networkSynchronized(ArrowComponent.STREAM_CODEC)
            );

    public static void register(IEventBus modBus) {
        REGISTER.register(modBus);
    }

    private ModDataComponents() {
    }
}
