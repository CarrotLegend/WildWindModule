package org.polaris2023.wildwind.wilderness.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.polaris2023.wildwind.wilderness.item.OmniClawItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * 为附魔辅助逻辑补充万用蟹钳工具上下文的混入类喵~
 */
@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

	@ModifyVariable(method = "getRandomItemWith", at = @At(value = "STORE", ordinal = 0))
	private static ItemEnchantments getRandomItemWithUnwarpOmniClaw(ItemEnchantments enchantments, @Local(ordinal = 0) ItemStack stack) {
		ItemStack tool = OmniClawItem.getLastSelectedTool(stack);
		if (!tool.isEmpty()) enchantments = tool.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
		return enchantments;
	}
}