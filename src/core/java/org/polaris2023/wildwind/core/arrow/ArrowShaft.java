package org.polaris2023.wildwind.core.arrow;

import net.minecraft.world.item.Items;

/**
 * 箭杆类型枚举
 * 影响箭矢轨迹
 *
 * @author baka4n
 * @since 2026/04/15
 */
public enum ArrowShaft {
    /**
     * 木棍 - 无效果
     */
    STICK("stick", Items.STICK, 1.0F, 0.0F, 0.0F, false, false, "无效果"),

    /**
     * 烈焰棒 - 路径产生火焰粒子，落点生成火焰，不可回收
     */
    BLAZE_ROD("blaze_rod", Items.BLAZE_ROD, 1.0F, 0.0F, 0.0F, true, false, "路径产生火焰粒子，落点生成火焰，不可回收"),

    /**
     * 旋风棒 - 非流体环境无重力，落点风弹爆炸，不可回收
     */
    BREEZE_ROD("breeze_rod", Items.BREEZE_ROD, 1.0F, 0.0F, 0.0F, false, true, "非流体环境无重力，落点风弹爆炸，不可回收"),

    /**
     * 骨头 - 下坠快，+1伤害，随机偏移大
     */
    BONE("bone", Items.BONE, 1.35F, 1.0F, 0.15F, false, false, "下坠快，+1伤害，随机偏移大"),

    /**
     * 竹子 - 下坠慢，穿透1个目标，准度高
     */
    BAMBOO("bamboo", Items.BAMBOO, 0.7F, 0.0F, 0.0F, false, false, "下坠慢，穿透1个目标，准度高");

    private final String name;
    private final net.minecraft.world.level.ItemLike item;
    private final float gravityMultiplier;
    private final float damageBonus;
    private final float inaccuracy;
    private final boolean fireTrail;
    private final boolean noGravity;
    private final String description;

    ArrowShaft(String name, net.minecraft.world.level.ItemLike item,
               float gravityMultiplier, float damageBonus, float inaccuracy,
               boolean fireTrail, boolean noGravity, String description) {
        this.name = name;
        this.item = item;
        this.gravityMultiplier = gravityMultiplier;
        this.damageBonus = damageBonus;
        this.inaccuracy = inaccuracy;
        this.fireTrail = fireTrail;
        this.noGravity = noGravity;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public net.minecraft.world.level.ItemLike getItem() {
        return item;
    }

    public float getGravityMultiplier() {
        return gravityMultiplier;
    }

    public float getDamageBonus() {
        return damageBonus;
    }

    public float getInaccuracy() {
        return inaccuracy;
    }

    public boolean hasFireTrail() {
        return fireTrail;
    }

    public boolean hasNoGravity() {
        return noGravity;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据物品获取对应的箭杆类型
     */
    public static ArrowShaft fromItem(net.minecraft.world.item.Item item) {
        for (ArrowShaft shaft : values()) {
            if (shaft.getItem().asItem() == item) {
                return shaft;
            }
        }
        return null;
    }

    /**
     * 根据序列化用的 name（小写）反查类型。
     */
    public static ArrowShaft fromName(String name) {
        // 旧存档把旋风棒箭杆写作 wind_charge，读取时迁到新常量，避免加载崩溃
        String normalized = "wind_charge".equals(name) ? BREEZE_ROD.getName() : name;
        for (ArrowShaft shaft : values()) {
            if (shaft.getName().equals(normalized)) {
                return shaft;
            }
        }
        throw new IllegalArgumentException("Unknown arrow shaft: " + name);
    }
}
