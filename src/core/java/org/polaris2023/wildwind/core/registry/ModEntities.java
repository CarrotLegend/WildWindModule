package org.polaris2023.wildwind.core.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.entity.monster.Spiderling;

/**
 * 核心模块的实体类型注册。
 */
public final class ModEntities {
    public static final DeferredRegister.Entities ENTITY_TYPES =
            DeferredRegister.createEntities(WildWindCore.MOD_ID);

    /** 幼蛛实体类型 */
    public static final DeferredHolder<EntityType<?>, EntityType<Spiderling>> SPIDERLING =
            ENTITY_TYPES.registerEntityType("spiderling", Spiderling::new, MobCategory.MONSTER,
                    builder -> builder.sized(0.55F, 0.35F).eyeHeight(0.15F)
                            .passengerAttachments(0.765F)
                            .clientTrackingRange(8)
                            .notInPeaceful()
            );

    public static void register(IEventBus modBus) {
        ENTITY_TYPES.register(modBus);
    }

    private ModEntities() {
    }
}
