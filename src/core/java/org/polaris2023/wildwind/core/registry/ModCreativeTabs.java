package org.polaris2023.wildwind.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.block.ModBlocks;
import org.polaris2023.wildwind.core.block.ModWoodFamilies;

/**
 * 模组专用创造模式物品栏。
 * <p>
 * 只放核心内容；荒野 / 瘠土更新各自把自己的条目追加进原版物品栏。
 */
public final class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WildWindCore.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WILD_WIND =
            CREATIVE_TABS.register("wild_wind", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.ww_core.name"))
                    .icon(() -> ModBlocks.AZALEA_LOG.toStack())
                    .displayItems((params, output) -> addStacks(output))
                    .build()
            );

    public static void register(IEventBus modBus) {
        CREATIVE_TABS.register(modBus);
    }

    private static void addStacks(CreativeModeTab.Output output) {
        ModWoodFamilies.AZALEA.buildingBlocks().forEach(output::accept);

        output.accept(ModBlocks.ROSE.get());
        output.accept(ModBlocks.SOUL_ROSE.get());
        output.accept(ModBlocks.SOUL_ROSE_BUSH.get());
        output.accept(ModBlocks.FLETCHING_TABLE.get());
        output.accept(ModBlocks.WITHER_ROSE_BUSH.get());
        output.accept(ModBlocks.KILN.get());

        output.accept(ModItems.SPIDERLING_SPAWN_EGG);
    }

    private ModCreativeTabs() {
    }
}
