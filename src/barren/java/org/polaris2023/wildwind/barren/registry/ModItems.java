package org.polaris2023.wildwind.barren.registry;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.barren.WildWindBarren;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(WildWindBarren.MOD_ID);

    public static final DeferredItem<Item> ASH =
            ITEMS.registerItem("ash", p -> new Item(p.stacksTo(16)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

    private ModItems() {
    }
}
