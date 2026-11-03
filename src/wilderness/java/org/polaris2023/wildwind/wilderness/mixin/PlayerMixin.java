package org.polaris2023.wildwind.wilderness.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import org.polaris2023.wildwind.wilderness.registry.ModAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 让玩家的物品拾取判定范围受额外拾取距离属性加成的混入类喵~
 */
@Mixin(Player.class)
public abstract class PlayerMixin {

	@Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Lists;newArrayList()Ljava/util/ArrayList;"))
	private void reachMobEffectTouchItem(CallbackInfo ci, @Local(ordinal = 0) AABB aabb) {
		Player self = (Player) (Object) this;

		AttributeMap attributes = self.getAttributes();
		if (attributes.hasAttribute(ModAttributes.EXTRA_ITEM_PICKUP_RANGE)) {
			double extraItemPickupRange = attributes.getValue(ModAttributes.EXTRA_ITEM_PICKUP_RANGE);
			if (extraItemPickupRange != 0.0) {
				AABB itemPickupAABB = aabb.inflate(extraItemPickupRange);
				for (Entity itemEntity : self.level().getEntitiesOfClass(ItemEntity.class, itemPickupAABB)) {
					itemEntity.playerTouch(self);
				}
			}
		}
	}
}