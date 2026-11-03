package org.polaris2023.wildwind.wilderness.effect;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * 用于拓宽 {@link MobEffect} 构造方法的访问权限
 */
public class WildWindMobEffect extends MobEffect {
	/**
	 * 创建一个基础生物效果喵~
	 *
	 * @param category 效果分类喵~
	 * @param color 效果颜色喵~
	 */
	public WildWindMobEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	/**
	 * 创建一个带粒子效果的生物效果喵~
	 *
	 * @param category 效果分类喵~
	 * @param color 效果颜色喵~
	 * @param particle 粒子效果喵~
	 */
	public WildWindMobEffect(MobEffectCategory category, int color, ParticleOptions particle) {
		super(category, color, particle);
	}
}