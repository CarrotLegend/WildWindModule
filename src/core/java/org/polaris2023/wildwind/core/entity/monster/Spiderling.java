package org.polaris2023.wildwind.core.entity.monster;

import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.object.PlayState;
import com.geckolib.constant.DefaultAnimations;
import com.geckolib.util.GeckoLibUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.level.Level;
import org.polaris2023.wildwind.core.entity.WindupAttackMob;
import org.polaris2023.wildwind.core.entity.ai.goal.ChargingMeleeAttackGoal;
import org.polaris2023.wildwind.core.registry.ModSoundEvents;

/** 幼蛛；继承原版蜘蛛的攀爬与目标选择，攻击改为蓄力扑击喵~ */
public class Spiderling extends Spider implements GeoEntity, WindupAttackMob {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private int preparingToAttack;

    public Spiderling(EntityType<? extends Spider> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Spider.createAttributes()
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.removeAllGoals(goal -> goal instanceof MeleeAttackGoal);
        this.goalSelector.addGoal(4, new ChargingMeleeAttackGoal<>(this, 1.0, true));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("Move", 2,
                state -> state.isMoving() ? state.setAndContinue(DefaultAnimations.WALK) : PlayState.STOP));
        controllers.add(new AnimationController<>("Attack", state -> PlayState.STOP)
                .triggerableAnim("attack", DefaultAnimations.ATTACK_SWING)
        );
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Override
    protected boolean isImmobile() {
        return !this.isPreparingToAttack() && super.isImmobile();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSoundEvents.SPIDERLING_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSoundEvents.SPIDERLING_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.SPIDERLING_DEATH.get();
    }

    @Override
    public boolean onClimbable() {
        return !this.isPreparingToAttack() && super.onClimbable();
    }

    @Override
    public void setPreparingToAttackTick(int tick) {
        this.preparingToAttack = tick;
    }

    @Override
    public int getPreparingToAttackTick() {
        return this.preparingToAttack;
    }

    @Override
    public int getPreparationToAttackDuration() {
        return 13;
    }

    @Override
    public boolean prepareAttack(LivingEntity entity) {
        boolean result = WindupAttackMob.super.prepareAttack(entity);
        if (result) this.triggerAnim("Attack", "attack");
        return result;
    }

    @Override
    public void onAttackDone(LivingEntity target) {
        this.addDeltaMovement(target.getPosition(1.0f)
                .subtract(this.getPosition(1.0f))
                .normalize()
                .scale(0.7)
                .add(0.0, 0.45, 0.0));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level() instanceof ServerLevel serverLevel && this.isAlive()) {
            this.processPreparingAttack(serverLevel, this);
        }
    }
}
