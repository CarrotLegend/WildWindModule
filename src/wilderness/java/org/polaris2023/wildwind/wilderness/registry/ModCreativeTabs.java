package org.polaris2023.wildwind.wilderness.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.block.WildernessBlocks;
import org.polaris2023.wildwind.wilderness.registry.ModItems;

/**
 * 荒野更新自己的创造模式物品栏页。
 */
public final class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WildWindWilderness.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WILDERNESS =
            CREATIVE_TABS.register("wilderness", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.ww_wilderness.name"))
                    .icon(() -> WildernessBlocks.SOUL_JACK_O_LANTERN.toStack())
                    .displayItems((params, output) -> addStacks(output))
                    .build()
            );

    public static void register(IEventBus modBus) {
        CREATIVE_TABS.register(modBus);
    }

    private static void addStacks(CreativeModeTab.Output output) {
        output.accept(WildernessBlocks.SOUL_JACK_O_LANTERN.get());

        output.accept(ModItems.MUDCRAB_SPAWN_EGG);
        output.accept(ModItems.MUDCRAB_BUCKET);
        output.accept(ModItems.PIRANHA_SPAWN_EGG);
        output.accept(ModItems.PIRANHA_BUCKET);
        output.accept(ModItems.PIRANHA);
        output.accept(ModItems.COOKED_PIRANHA);
        output.accept(ModItems.CRAB_CLAW);
        output.accept(ModItems.FANG);
        output.accept(ModItems.OMNI_CLAW);
        output.accept(ModItems.SPIDER_EGG.get());
        output.accept(WildernessBlocks.SILT.get());
    }

    private ModCreativeTabs() {
    }
}
