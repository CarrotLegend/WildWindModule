package org.polaris2023.wildwind.wilderness.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;

/**
 * 注册模组药水并处理酿造配方喵~
 */
@EventBusSubscriber(modid = WildWindWilderness.MOD_ID)
public class ModPotions {
	/**
	 * 药水延迟注册器喵~
	 */
	public static final DeferredRegister<Potion> POTIONS =
			DeferredRegister.create(BuiltInRegistries.POTION, WildWindWilderness.MOD_ID);

	/**
	 * 延展药水喵~
	 */
	public static final DeferredHolder<Potion, Potion> REACH =
			POTIONS.register("reach", () -> new Potion("reach", new MobEffectInstance(ModMobEffects.REACH, 3600)));
	/**
	 * 长效延展药水喵~
	 */
	public static final DeferredHolder<Potion, Potion> LONG_REACH =
			POTIONS.register("long_reach", () -> new Potion("long_reach", new MobEffectInstance(ModMobEffects.REACH, 9600)));

	/**
	 * 向模组事件总线注册药水喵~
	 *
	 * @param modBus 模组事件总线喵~
	 */
	public static void register(IEventBus modBus) {
		POTIONS.register(modBus);
	}

	/**
	 * 注册药水酿造配方喵~
	 *
	 * @param event 酿造配方注册事件喵~
	 */
	@SubscribeEvent
	static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
		PotionBrewing.Builder builder = event.getBuilder();

		builder.addMix(
				Potions.AWKWARD,
				ModItems.CRAB_CLAW.get(),
				ModPotions.REACH
		);

		builder.addMix(
				ModPotions.REACH,
				Items.REDSTONE,
				ModPotions.LONG_REACH
		);
	}

	private ModPotions() {
	}
}