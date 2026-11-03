package org.polaris2023.wildwind.core.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.core.WildWindCore;

/**
 * 核心模块的物品注册。
 */
public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WildWindCore.MOD_ID);

    /** 幼蛛刷怪蛋 */
    public static final DeferredItem<SpawnEggItem> SPIDERLING_SPAWN_EGG =
            ITEMS.registerItem("spiderling_spawn_egg", p -> new SpawnEggItem(p.spawnEgg(ModEntities.SPIDERLING.value())));

    public static void register(IEventBus modBus) {
        ITEMS.register(modBus);
    }

    private ModItems() {
    }
}
