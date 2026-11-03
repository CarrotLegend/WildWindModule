package org.polaris2023.wildwind.wilderness.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;

/**
 * 荒野更新的方块注册。
 */
public final class WildernessBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(WildWindWilderness.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WildWindWilderness.MOD_ID);

    /** 灵魂南瓜灯 */
    public static final DeferredBlock<Block> SOUL_JACK_O_LANTERN = BLOCKS.registerBlock(
            "soul_jack_o_lantern",
            p -> new CarvedPumpkinBlock(p
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .lightLevel(state -> 10))
    );

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.registerSimpleBlockItem(SOUL_JACK_O_LANTERN);
        ITEMS.register(bus);
    }

    private WildernessBlocks() {
    }
}
