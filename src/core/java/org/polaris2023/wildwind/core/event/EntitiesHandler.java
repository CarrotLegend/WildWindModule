package org.polaris2023.wildwind.core.event;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.entity.monster.Spiderling;
import org.polaris2023.wildwind.core.registry.ModEntities;

/**
 * 核心模块的实体属性与生成位置注册。
 */
@EventBusSubscriber(modid = WildWindCore.MOD_ID)
public final class EntitiesHandler {

    @SubscribeEvent
    static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SPIDERLING.get(), Spiderling.createAttributes().build());
    }

    @SubscribeEvent
    static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.SPIDERLING.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    private EntitiesHandler() {
    }
}
