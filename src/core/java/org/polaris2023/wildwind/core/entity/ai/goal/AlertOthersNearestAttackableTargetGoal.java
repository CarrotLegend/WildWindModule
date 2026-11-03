package org.polaris2023.wildwind.core.entity.ai.goal;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * 警告最近可攻击对象的目标
 * @param <T> 实体类型
 * @author huimanman
 */
public class AlertOthersNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    @Nullable
    private Set<Class<?>> toIgnoreAlert;
    private double overrideFindEntityFollowDistance = -1.0;

    public AlertOthersNearestAttackableTargetGoal(Mob mob, Class<T> targetType, boolean mustSee) {
        super(mob, targetType, mustSee);
    }

    public AlertOthersNearestAttackableTargetGoal(Mob mob, Class<T> targetType, boolean mustSee, TargetingConditions.Selector targetPredicate) {
        super(mob, targetType, mustSee, targetPredicate);
    }

    public AlertOthersNearestAttackableTargetGoal(Mob mob, Class<T> targetType, boolean mustSee, boolean mustReach) {
        super(mob, targetType, mustSee, mustReach);
    }

    public AlertOthersNearestAttackableTargetGoal(Mob mob, Class<T> targetType, int randomInterval, boolean mustSee, boolean mustReach, @Nullable TargetingConditions.Selector targetPredicate) {
        super(mob, targetType, randomInterval, mustSee, mustReach, targetPredicate);
    }

	/**
	 * 覆写跟随距离
	 * @param followDistance 跟随距离
	 * @return 当前目标，用于链式修改
	 */
    public AlertOthersNearestAttackableTargetGoal<T> overrideFindEntityFollowDistance(double followDistance) {
        this.overrideFindEntityFollowDistance = followDistance;
        return this;
    }

    @Override
    public void start() {
        super.start();
        if (this.target != null) this.alertOthers();
    }

    public AlertOthersNearestAttackableTargetGoal<T> ignoreAlertOthers(Class<?>... reinforcementTypes) {
        this.toIgnoreAlert = new ObjectOpenHashSet<>(reinforcementTypes);
        return this;
    }

    protected void alertOthers() {
        double followDistance = super.getFollowDistance();
        AABB alertBox = AABB.unitCubeFromLowerCorner(this.mob.position()).inflate(followDistance, 10.0, followDistance);
        for (Mob mob : this.mob.level().getEntitiesOfClass(this.mob.getClass(), alertBox, EntitySelector.NO_SPECTATORS)) {
            if (this.mob != mob && this.shouldAlert(mob)) {
                if (this.toIgnoreAlert != null && this.toIgnoreAlert.contains(mob.getClass())) continue;
            }

            this.alertOther(mob, this.target);
        }
    }

    protected boolean shouldAlert(Mob other) {
        LivingEntity target = mob.getTarget();
        return target != null
                && (!(this.mob instanceof TamableAnimal selfMob) || (selfMob.getOwner() == ((TamableAnimal) other).getOwner())
                && !this.mob.isAlliedTo(target));
    }

    protected void alertOther(Mob mob, @Nullable LivingEntity target) {
        mob.setTarget(target);
    }

    @Override
    protected double getFollowDistance() {
        return this.overrideFindEntityFollowDistance != -1.0
                ? this.overrideFindEntityFollowDistance
                : super.getFollowDistance();
    }
}