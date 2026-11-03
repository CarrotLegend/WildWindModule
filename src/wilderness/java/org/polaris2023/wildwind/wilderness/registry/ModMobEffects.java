package org.polaris2023.wildwind.wilderness.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.registry.ModAttributes;
import org.polaris2023.wildwind.wilderness.effect.WildWindMobEffect;

/**
 * 注册模组状态效果喵~
 */
public class ModMobEffects {
	/**
	 * 状态效果延迟注册器喵~
	 */
	public static final DeferredRegister<MobEffect> MOB_EFFECTS =
			DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, WildWindWilderness.MOD_ID);

	/**
	 * 延展状态效果喵~
	 */
	public static final DeferredHolder<MobEffect, MobEffect> REACH =
			MOB_EFFECTS.register("reach",
					() -> new WildWindMobEffect(MobEffectCategory.BENEFICIAL, 0xFF3F7E8E)
							.addAttributeModifier(
									Attributes.BLOCK_INTERACTION_RANGE,
									WildWindWilderness.id("effect.reach"),
									2.0,
									AttributeModifier.Operation.ADD_VALUE
							)
							.addAttributeModifier(
									Attributes.ENTITY_INTERACTION_RANGE,
									WildWindWilderness.id("effect.reach"),
									2.0,
									AttributeModifier.Operation.ADD_VALUE
							)
							.addAttributeModifier(
									ModAttributes.EXTRA_ITEM_PICKUP_RANGE,
									WildWindWilderness.id("effect.reach"),
									2.0,
									AttributeModifier.Operation.ADD_VALUE
							)
			);

	/**
	 * 向模组事件总线注册状态效果喵~
	 *
	 * @param modBus 模组事件总线喵~
	 */
	public static void register(IEventBus modBus) {
		MOB_EFFECTS.register(modBus);
	}

	private ModMobEffects() {
	}
}