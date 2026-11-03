package org.polaris2023.wildwind.wilderness.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoods {
    public static final FoodProperties PIRANHA = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.1F)
            .build();

    public static final Consumable PIRANHA_CONSUMABLE = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 600), 0.8f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.UNLUCK, 600), 0.5f))
            .build();

    public static final FoodProperties COOKED_PIRANHA = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.8F)
            .build();

    public static final Consumable COOKED_PIRANHA_CONSUMABLE = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.UNLUCK, 300), 0.3f))
            .build();

    private ModFoods() {
    }
}
