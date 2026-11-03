package org.polaris2023.wildwind.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AzaleaBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wildwind.core.worldgen.ModTreeGrowers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin 注入 AzaleaBlock 以替换杜鹃树生成
 * 使用杜鹃木原木替代橡木原木
 *
 * @author baka4n
 * @since 2026/04/15
 */
@Mixin(AzaleaBlock.class)
public class AzaleaBlockMixin {

    /**
     * 拦截 performBonemeal 方法
     * 使用我们的 TreeGrower 替代原版
     */
    @Inject(
            method = "performBonemeal",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onPerformBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state, CallbackInfo ci) {
        // 使用我们的杜鹃树生长器（使用杜鹃木原木）
        ModTreeGrowers.AZALEA.growTree(level, level.getChunkSource().getGenerator(), pos, state, random);
        ci.cancel();
    }
}
