package org.polaris2023.wildwind.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.polaris2023.wildwind.core.block.ModBlocks;
import org.polaris2023.wildwind.core.config.ModCommonConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * 让原版制箭台物品直接放置成模组制箭台。
 * <p>
 * 26.2 的 {@code BlockItem#place} 不再触发 {@code BlockEvent.EntityPlaceEvent}，
 * 所以不再拦事件，而是在 {@code getPlacementState} 里把 {@code getBlock()} 的返回值换掉：
 * 之后的放置、音效、消耗物品、方块实体创建全部仍走原版流程，只是方块换成了我们的。
 */
@Mixin(BlockItem.class)
public abstract class BlockItemMixin {

    @ModifyExpressionValue(
            method = "getPlacementState",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BlockItem;getBlock()Lnet/minecraft/world/level/block/Block;")
    )
    private Block wildWind$replaceFletchingTable(Block original) {
        if (original == Blocks.FLETCHING_TABLE && ModCommonConfig.shouldReplaceVanillaFletchingTable()) {
            return ModBlocks.FLETCHING_TABLE.get();
        }
        return original;
    }
}
