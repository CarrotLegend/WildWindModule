package org.polaris2023.wildwind.barren.mixin;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wildwind.barren.block.BarrenBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 允许自定义的可疑沙使用原版 BrushableBlockEntity。
 * 原版 {@link BlockEntityType#isValid(BlockState)} 只认注册时列出的方块，
 * 这里对 BRUSHABLE_BLOCK 额外放行瘠土更新的可疑沙。
 */
@Mixin(BlockEntityType.class)
public abstract class BlockEntityTypeMixin {

    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void ww_barren$allowSuspiciousSand(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this == BlockEntityTypes.BRUSHABLE_BLOCK
                && (state.is(BarrenBlocks.SUSPICIOUS_RED.get())
                || state.is(BarrenBlocks.SUSPICIOUS_QUARTZ_SAND.get())
                || state.is(BarrenBlocks.SUSPICIOUS_SOUL_SAND.get()))) {
            cir.setReturnValue(true);
        }
    }
}
