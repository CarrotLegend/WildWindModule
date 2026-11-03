package org.polaris2023.wildwind.core.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.block.ModBlocks;

/**
 * 方块实体注册类
 *
 * @author baka4n
 * @since 2026/04/15
 */
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, WildWindCore.MOD_ID);

    // 制箭台方块实体
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FletchingTableBlockEntity>> FLETCHING_TABLE =
            BLOCK_ENTITIES.register(
                    "fletching_table",
                    () -> new BlockEntityType<>(
                            FletchingTableBlockEntity::new,
                            ModBlocks.FLETCHING_TABLE.get()
                    )
            );

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
