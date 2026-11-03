package org.polaris2023.wildwind.wilderness.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.polaris2023.wildwind.wilderness.component.OmniClawTools;
import org.polaris2023.wildwind.wilderness.item.OmniClawItem;
import org.polaris2023.wildwind.wilderness.registry.ModDataComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * 为物品栈中的万用蟹钳代理工具行为提供支持的混入类喵~
 * <p>
 * 26.2 迁移说明：hurtAndBreak 内的 shrink 调用点已下沉到私有方法 applyDamage，
 * 因此第二个注入目标改为 applyDamage。
 */
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

	/**
	 * 让万用蟹钳沿用当前选中工具的耐久判定喵~
	 *
	 * @param original 原始方法调用喵~
	 * @return 是否可损坏喵~
	 */
	@WrapMethod(method = "isDamageableItem")
	public boolean omniClawIsDamageableItem(Operation<Boolean> original) {
		ItemStack tool = OmniClawItem.getLastSelectedTool((ItemStack) (Object) this);
		if (!tool.isEmpty()) return tool.isDamageableItem();

		return original.call();
	}

	@WrapOperation(method = "applyDamage(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
	private void omniClawShrinkTool(ItemStack instance, int decrement, Operation<Void> original, @Local LocalRef<Item> item) {
		ItemStack tool = OmniClawItem.getLastSelectedTool(instance);
		if (tool.isEmpty()) {
			original.call(instance, decrement);
			return;
		}

		item.set(tool.getItem());
		tool.shrink(decrement);
		OmniClawTools tools = instance.get(ModDataComponents.OMNI_CLAW_TOOLS.get());
		instance.set(ModDataComponents.OMNI_CLAW_TOOLS.get(), tools.toMutable().scrollSelectToNoEmptyItem().toImmutable());
	}
}