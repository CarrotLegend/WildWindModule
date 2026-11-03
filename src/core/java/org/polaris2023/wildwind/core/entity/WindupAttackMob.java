package org.polaris2023.wildwind.core.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

public interface WindupAttackMob {
    void setTarget(LivingEntity entity);

    /**
     * 设置蓄力攻击完成倒计时(t)
     * @param tick 完成倒计时(t)
     */
    void setPreparingToAttackTick(int tick);

    default boolean isPreparingToAttack() {
        return this.getPreparingToAttackTick() > 0;
    }

    int getPreparingToAttackTick();

    /**
     * 获取蓄力攻击所需要的总时间(t)
     * @return 蓄力攻击完成需要的总时间(t)
     */
    int getPreparationToAttackDuration();

    /**
     * 进入攻击蓄力状态并准备对目标发动攻击喵~
     *
     * @param entity 攻击目标喵~
     * @return 成功进入蓄力状态时返回 true 喵~
     */
    default boolean prepareAttack(LivingEntity entity) {
        this.setTarget(entity);
        if (entity == null || !entity.isAlive()) return false;

        this.setPreparingToAttackTick(getPreparationToAttackDuration());
        return true;
    }

    default boolean canContinuePreparingAttack() {
        return true;
    }

    /**
     * 处理攻击蓄力
     */
    default void processPreparingAttack(ServerLevel level, Mob mob) {
        int preparingToAttackTick = this.getPreparingToAttackTick();
        if (this.isPreparingToAttack()) {
            if (!this.canContinuePreparingAttack()) {
                this.setPreparingToAttackTick(-1);
                return;
            }

            LivingEntity target = mob.getTarget();
            if (target != null && target.isAlive()) {
                mob.lookAt(target, 15.0f, 15.0f);
            }

            preparingToAttackTick -= 1;
            this.setPreparingToAttackTick(preparingToAttackTick);
            if (preparingToAttackTick <= 0) {
                this.setPreparingToAttackTick(-1);

                if (target != null && target.isAlive() && mob.isWithinMeleeAttackRange(target) && mob.getSensing().hasLineOfSight(target)) {
                    mob.doHurtTarget(level, target);
                    this.onAttackDone(target);
                }
            }
        }
    }

    default void onAttackDone(LivingEntity target) {
    }
}
