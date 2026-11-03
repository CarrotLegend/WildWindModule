package org.polaris2023.wildwind.wilderness.event;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.fish.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.entity.animal.Mudcrab;
import org.polaris2023.wildwind.wilderness.entity.animal.Piranha;
import org.polaris2023.wildwind.wilderness.registry.ModEntities;

/**
 * 荒野更新的实体属性与生成位置注册。
 */
@EventBusSubscriber(modid = WildWindWilderness.MOD_ID)
public class EntitiesHandler {

    @SubscribeEvent
    static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PIRANHA.get(), Piranha.createAttributes().build());
        event.put(ModEntities.MUDCRAB.get(), Mudcrab.createAttributes().build());
    }

    @SubscribeEvent
    static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.MUDCRAB.get(), Mudcrab.SPAWN_PLACEMENT, Heightmap.Types.OCEAN_FLOOR, Mudcrab::checkMudcrabInWaterGroundSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.MUDCRAB.get(), Mudcrab::checkMudcrabOnGroundSpawnRules);
        event.register(ModEntities.PIRANHA.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    private EntitiesHandler() {
    }
}
