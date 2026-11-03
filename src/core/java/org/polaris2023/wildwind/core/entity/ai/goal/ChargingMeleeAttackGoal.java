package org.polaris2023.wildwind.core.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import org.polaris2023.wildwind.core.entity.WindupAttackMob;

/**
 * 准备近战攻击目标
 * @param <T> 实体类型
 */
public class ChargingMeleeAttackGoal<T extends PathfinderMob & WindupAttackMob> extends MeleeAttackGoal {
    protected final T mob;

	/**
	 * 准备近战攻击目标构造函数
	 * @param mob 生物
	 * @param speedModifier 速度变化
	 * @param followingTargetEvenIfNotSeen 是否在看不到目标时仍然执行
	 */
    public ChargingMeleeAttackGoal(T mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(mob, speedModifier, followingTargetEvenIfNotSeen);
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return !this.mob.isPreparingToAttack()  && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return !this.mob.isPreparingToAttack() && super.canContinueToUse();
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity target) {
        if (!canPerformAttack(target)) return;
        this.resetAttackCooldown();
        this.mob.prepareAttack(target);
    }
}
