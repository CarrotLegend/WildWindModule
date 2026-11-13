package org.polaris2023.wildwind.barren;

import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.polaris2023.wildwind.barren.block.BarrenBlocks;
import org.polaris2023.wildwind.barren.registry.ModCreativeTabs;
import org.polaris2023.wildwind.barren.registry.ModItems;

/**
 * 原野之风：瘠土更新（Wild Wind: Barren Update）
 * <p>
 * 砂岩 / 沙 / 流沙 / 焦土 / 下界木质套件。
 */
@Mod(WildWindBarren.MOD_ID)
public class WildWindBarren {
    public static final String MOD_ID = "ww_barren";

    public WildWindBarren(IEventBus bus) {
        BarrenBlocks.register(bus);
        ModCreativeTabs.register(bus);
        ModItems.register(bus);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
