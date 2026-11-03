package org.polaris2023.wildwind.core.mixin;

import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * 箭矢实体的内部字段 / 私有方法访问器。
 * <p>
 * {@code baseDamage} 是私有字段且没有 getter，{@code setPierceLevel} 是私有方法，
 * 矢术的「伤害加成」与「穿透 1 个目标」都要用到。
 */
@Mixin(AbstractArrow.class)
public interface AbstractArrowAccessor {

    @Accessor("baseDamage")
    double wildwind$getBaseDamage();

    @Invoker("setPierceLevel")
    void wildwind$setPierceLevel(byte pierceLevel);
}
