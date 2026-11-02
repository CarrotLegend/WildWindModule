package org.polaris2023.wildwind.barren.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.barren.WildWindBarren;
import org.polaris2023.wildwind.barren.block.BarrenBlocks;
import org.polaris2023.wildwind.barren.block.BarrenWoodFamilies;
import org.polaris2023.wildwind.core.block.WoodFamily;

/**
 * 瘠土更新自己的创造模式物品栏页。
 */
public final class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WildWindBarren.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BARREN =
            CREATIVE_TABS.register("barren", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.ww_barren.name"))
                    .icon(() -> BarrenBlocks.BLAZE_LOG.toStack())
                    .displayItems((params, output) -> addStacks(output))
                    .build()
            );

    public static void register(IEventBus modBus) {
        CREATIVE_TABS.register(modBus);
    }

    private static void addStacks(CreativeModeTab.Output output) {
        // 木材套件
        for (WoodFamily family : BarrenWoodFamilies.ALL) {
            family.buildingBlocks().forEach(output::accept);
            if (family.hasLeaves()) {
                output.accept(family.leaves().get());
                output.accept(family.sapling().get());
            }
        }

        // 焦土
        output.accept(BarrenBlocks.SCORCHED_GRASS_BLOCK.get());
        output.accept(BarrenBlocks.SCORCHED_DIRT.get());
        output.accept(BarrenBlocks.SCORCHED_GRASS.get());
        output.accept(BarrenBlocks.SCORCHED_TWIG.get());
        output.accept(BarrenBlocks.TINY_CACTUS.get());

        // 沙 / 流沙
        output.accept(BarrenBlocks.QUARTZ_SAND.get());
        output.accept(BarrenBlocks.QUICKSAND.get());
        output.accept(BarrenBlocks.QUARTZ_QUICKSAND.get());
        output.accept(BarrenBlocks.SOUL_QUICKSAND.get());

        // 砂岩
        output.accept(BarrenBlocks.QUARTZ_SANDSTONE.get());
        output.accept(BarrenBlocks.CHISELED_QUARTZ_SANDSTONE.get());
        output.accept(BarrenBlocks.CUT_QUARTZ_SANDSTONE.get());
        output.accept(BarrenBlocks.SMOOTH_QUARTZ_SANDSTONE.get());
        output.accept(BarrenBlocks.NITRIFIED_QUARTZ_SANDSTONE.get());
        output.accept(BarrenBlocks.SOUL_SANDSTONE.get());
        output.accept(BarrenBlocks.CHISELED_SOUL_SANDSTONE.get());
        output.accept(BarrenBlocks.CUT_SOUL_SANDSTONE.get());
        output.accept(BarrenBlocks.SMOOTH_SOUL_SANDSTONE.get());
        output.accept(BarrenBlocks.NITRIFIED_SOUL_SANDSTONE.get());
        output.accept(BarrenBlocks.NITRIFIED_SANDSTONE.get());
        output.accept(BarrenBlocks.NITRIFIED_RED_SANDSTONE.get());

        // 可疑沙
        output.accept(BarrenBlocks.SUSPICIOUS_RED.get());
        output.accept(BarrenBlocks.SUSPICIOUS_QUARTZ_SAND.get());
        output.accept(BarrenBlocks.SUSPICIOUS_SOUL_SAND.get());
    }

    private ModCreativeTabs() {
    }
}
