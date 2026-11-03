package org.polaris2023.wildwind.core.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.core.WildWindCore;

/**
 * 核心模块的音效注册。
 */
public final class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, WildWindCore.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> SPIDERLING_AMBIENT = SOUND_EVENTS.register(
            "entity.spiderling.ambient",
            SoundEvent::createVariableRangeEvent
    );

    public static final DeferredHolder<SoundEvent, SoundEvent> SPIDERLING_HURT = SOUND_EVENTS.register(
            "entity.spiderling.hurt",
            SoundEvent::createVariableRangeEvent
    );

    public static final DeferredHolder<SoundEvent, SoundEvent> SPIDERLING_DEATH = SOUND_EVENTS.register(
            "entity.spiderling.death",
            SoundEvent::createVariableRangeEvent
    );

    public static void register(IEventBus modEventBus) {
        SOUND_EVENTS.register(modEventBus);
    }

    private ModSoundEvents() {
    }
}
