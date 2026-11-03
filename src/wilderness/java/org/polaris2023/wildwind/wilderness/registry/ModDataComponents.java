package org.polaris2023.wildwind.wilderness.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.component.OmniClawTools;
import org.polaris2023.wildwind.wilderness.entity.animal.MudcrabVariant;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents REGISTER = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, WildWindWilderness.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Holder<MudcrabVariant>>> MUDCRAB_VARIANT =
            REGISTER.registerComponentType("mubcrab_variant", builder ->
                    builder.persistent(MudcrabVariant.CODEC).networkSynchronized(MudcrabVariant.STREAM_CODEC)
            );

    /**
     * 万用蟹钳工具集合数据组件喵~
     */
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<OmniClawTools>> OMNI_CLAW_TOOLS =
            REGISTER.registerComponentType("omni_claw_tools", builder ->
                    builder.persistent(OmniClawTools.CODEC).networkSynchronized(OmniClawTools.STREAM_CODEC)
            );

    public static void register(IEventBus modBus) {
        REGISTER.register(modBus);
    }
}
