package org.polaris2023.wildwind.core.menu;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.core.WildWindCore;

/**
 * 菜单类型注册类
 *
 * @author baka4n
 * @since 2026/04/15
 */
public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(BuiltInRegistries.MENU, WildWindCore.MOD_ID);

    // 制箭台菜单
    public static final DeferredHolder<MenuType<?>, MenuType<FletchingTableMenu>> FLETCHING_TABLE =
            MENUS.register(
                    "fletching_table",
                    () -> new MenuType<>(FletchingTableMenu::new, FeatureFlags.DEFAULT_FLAGS)
            );

    public static void register(IEventBus bus) {
        MENUS.register(bus);
    }
}
