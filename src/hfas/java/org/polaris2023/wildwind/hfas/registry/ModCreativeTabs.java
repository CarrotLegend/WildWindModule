package org.polaris2023.wildwind.hfas.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.hfas.HFASMod;
import org.polaris2023.wildwind.hfas.block.ModBlocks;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HFASMod.MOD_ID);
    /**
     * 模组专用创造模式物品栏喵~
     */
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WILD_WIND =
            CREATIVE_TABS.register("wild_wind", () -> CreativeModeTab.builder()
                    .title(Component.translatable("mod.wild_wind.name"))
                    .icon(() -> ModBlocks.BLAZE_LOG.toStack())
                    .displayItems((params, output) -> addStacks(output))
                    .build()
            );

    /**
     * 向模组事件总线注册创造模式物品栏喵~
     *
     * @param modBus 模组事件总线喵~
     */
    public static void register(IEventBus modBus) {
        CREATIVE_TABS.register(modBus);
    }

    private static void addStacks(CreativeModeTab.Output output){
        // 三种木材的建筑套装（顺序与旧仓库一致）
        addWoodBuildingSet(output,
                ModBlocks.AZALEA_LOG, ModBlocks.AZALEA_WOOD,
                ModBlocks.STRIPPED_AZALEA_LOG, ModBlocks.STRIPPED_AZALEA_WOOD,
                ModBlocks.AZALEA_PLANKS, ModBlocks.AZALEA_STAIRS, ModBlocks.AZALEA_SLAB,
                ModBlocks.AZALEA_FENCE, ModBlocks.AZALEA_FENCE_GATE, ModBlocks.AZALEA_DOOR,
                ModBlocks.AZALEA_TRAPDOOR, ModBlocks.AZALEA_PRESSURE_PLATE, ModBlocks.AZALEA_BUTTON);

        addWoodBuildingSet(output,
                ModBlocks.BLAZE_LOG, ModBlocks.BLAZE_WOOD,
                ModBlocks.STRIPPED_BLAZE_LOG, ModBlocks.STRIPPED_BLAZE_WOOD,
                ModBlocks.BLAZE_PLANKS, ModBlocks.BLAZE_STAIRS, ModBlocks.BLAZE_SLAB,
                ModBlocks.BLAZE_FENCE, ModBlocks.BLAZE_FENCE_GATE, ModBlocks.BLAZE_DOOR,
                ModBlocks.BLAZE_TRAPDOOR, ModBlocks.BLAZE_PRESSURE_PLATE, ModBlocks.BLAZE_BUTTON);

        addWoodBuildingSet(output,
                ModBlocks.SOUL_LOG, ModBlocks.SOUL_WOOD,
                ModBlocks.STRIPPED_SOUL_LOG, ModBlocks.STRIPPED_SOUL_WOOD,
                ModBlocks.SOUL_PLANKS, ModBlocks.SOUL_STAIRS, ModBlocks.SOUL_SLAB,
                ModBlocks.SOUL_FENCE, ModBlocks.SOUL_FENCE_GATE, ModBlocks.SOUL_DOOR,
                ModBlocks.SOUL_TRAPDOOR, ModBlocks.SOUL_PRESSURE_PLATE, ModBlocks.SOUL_BUTTON);

        // 焚烬木/灵焰木的树叶与树苗（杜鹃木无对应物品）
        output.accept(ModBlocks.BLAZE_LEAVES.get());
        output.accept(ModBlocks.SOUL_LEAVES.get());
        output.accept(ModBlocks.BLAZE_SAPLING.get());
        output.accept(ModBlocks.SOUL_SAPLING.get());

        // 石英岩类
        output.accept(ModBlocks.QUARTZ_SANDSTONE.get());
        output.accept(ModBlocks.QUARTZ_SANDSTONE_STAIRS.get());
        output.accept(ModBlocks.QUARTZ_SANDSTONE_SLAB.get());
        output.accept(ModBlocks.QUARTZ_SANDSTONE_WALL.get());
        output.accept(ModBlocks.CHISELED_QUARTZ_SANDSTONE.get());
        output.accept(ModBlocks.SMOOTH_QUARTZ_SANDSTONE.get());
        output.accept(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_STAIRS.get());
        output.accept(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_SLAB.get());
        output.accept(ModBlocks.CUT_QUARTZ_SANDSTONE.get());
        output.accept(ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB.get());
        output.accept(ModBlocks.NITRIFIED_QUARTZ_SANDSTONE.get());

        // 灵魂岩类
        output.accept(ModBlocks.SOUL_SANDSTONE.get());
        output.accept(ModBlocks.SOUL_SANDSTONE_STAIRS.get());
        output.accept(ModBlocks.SOUL_SANDSTONE_SLAB.get());
        output.accept(ModBlocks.SOUL_SANDSTONE_WALL.get());
        output.accept(ModBlocks.CHISELED_SOUL_SANDSTONE.get());
        output.accept(ModBlocks.SMOOTH_SOUL_SANDSTONE.get());
        output.accept(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get());
        output.accept(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get());
        output.accept(ModBlocks.CUT_SOUL_SANDSTONE.get());
        output.accept(ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get());
        output.accept(ModBlocks.NITRIFIED_SOUL_SANDSTONE.get());

        // 硝化岩类
        output.accept(ModBlocks.NITRIFIED_SANDSTONE.get());
        output.accept(ModBlocks.NITRIFIED_RED_SANDSTONE.get());

        // 沙类
        output.accept(ModBlocks.QUARTZ_SAND.get());
        output.accept(ModBlocks.QUICKSAND.get());
        output.accept(ModBlocks.QUARTZ_QUICKSAND.get());
        output.accept(ModBlocks.SOUL_QUICKSAND.get());

        // 可疑沙类
        output.accept(ModBlocks.SUSPICIOUS_RED.get());
        output.accept(ModBlocks.SUSPICIOUS_QUARTZ_SAND.get());
        output.accept(ModBlocks.SUSPICIOUS_SOUL_SAND.get());

        // 花类
        output.accept(ModBlocks.ROSE.get());
        output.accept(ModBlocks.SOUL_ROSE.get());
        output.accept(ModBlocks.SOUL_ROSE_BUSH.get());

        // 灯具类
        output.accept(ModBlocks.SOUL_JACK_O_LANTERN.get());

        output.accept(ModItems.MUDCRAB_BUCKET);
        output.accept(ModItems.MUDCRAB_SPAWN_EGG);
        output.accept(ModItems.CRAB_CLAW);
        output.accept(ModItems.OMNI_CLAW);
        output.accept(ModItems.PIRANHA_BUCKET);
        output.accept(ModItems.PIRANHA);
        output.accept(ModItems.COOKED_PIRANHA);
        output.accept(ModItems.FANG);
        output.accept(ModItems.PIRANHA_SPAWN_EGG);
        output.accept(ModItems.SPIDERLING_SPAWN_EGG);
    }

    private static void addWoodBuildingSet(CreativeModeTab.Output output, DeferredBlock<?>... blocks) {
        for (DeferredBlock<?> block : blocks) {
            output.accept(block.get());
        }
    }
}
