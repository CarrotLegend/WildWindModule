package org.polaris2023.wildwind.wilderness.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.entity.animal.Mudcrab;
import org.polaris2023.wildwind.wilderness.entity.animal.Piranha;


public class ModEntities {
    public static final DeferredRegister.Entities ENTITY_TYPES =
            DeferredRegister.createEntities(WildWindWilderness.MOD_ID);

    private ModEntities() {
    }

    //泥沼蟹实体类型
    public static final DeferredHolder<EntityType<?>, EntityType<Mudcrab>> MUDCRAB =
            ENTITY_TYPES.registerEntityType("mudcrab", Mudcrab::new, MobCategory.WATER_CREATURE,
                    builder -> builder.sized(0.5F, 0.55F).clientTrackingRange(8)
            );

    //恐鱼实体类型
    public static final DeferredHolder<EntityType<?>, EntityType<Piranha>> PIRANHA =
            ENTITY_TYPES.registerEntityType("piranha", Piranha::new, MobCategory.WATER_AMBIENT,
                    builder -> builder.sized(0.7F, 0.4F).eyeHeight(0.26F)
            );

    /**
     * 向模组事件总线注册实体类型喵~
     *
     * @see org.polaris2023.wildwind.wilderness.client.eventsub.EntitiesClientHandler 客户端渲染绑定
     * @see org.polaris2023.wildwind.wilderness.event.EntitiesHandler 实体服务端信息（attribute 等绑定）
     *
     * @param modBus 模组事件总线喵~
     */
    public static void register(IEventBus modBus) {
        ENTITY_TYPES.register(modBus);
    }

}
