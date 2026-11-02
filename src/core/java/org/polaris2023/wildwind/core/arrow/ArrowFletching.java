package org.polaris2023.wildwind.core.arrow;

import net.minecraft.world.item.Items;

/**
 * 箭羽类型枚举
 * 影响箭矢飞行速度
 *
 * @author baka4n
 * @since 2026/04/15
 */
public enum ArrowFletching {
    /**
     * 羽毛 - 无效果（基准速度）
     */
    FEATHER("feather", Items.FEATHER, 1.0F, "无效果"),

    /**
     * 幻翼膜 - 飞行速度更快
     */
    PHANTOM_MEMBRANE("phantom_membrane", Items.PHANTOM_MEMBRANE, 1.5F, "飞行速度更快");

    private final String name;
    private final net.minecraft.world.level.ItemLike item;
    private final float speedMultiplier;
    private final String description;

    ArrowFletching(String name, net.minecraft.world.level.ItemLike item, float speedMultiplier, String description) {
        this.name = name;
        this.item = item;
        this.speedMultiplier = speedMultiplier;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public net.minecraft.world.level.ItemLike getItem() {
        return item;
    }

    public float getSpeedMultiplier() {
        return speedMultiplier;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据物品获取对应的箭羽类型
     */
    public static ArrowFletching fromItem(net.minecraft.world.item.Item item) {
        for (ArrowFletching fletching : values()) {
            if (fletching.getItem().asItem() == item) {
                return fletching;
            }
        }
        return null;
    }

    /**
     * 根据序列化用的 name（小写）反查类型，{@code valueOf} 只认枚举常量名，不能拿来做反序列化。
     */
    public static ArrowFletching fromName(String name) {
        for (ArrowFletching fletching : values()) {
            if (fletching.getName().equals(name)) {
                return fletching;
            }
        }
        throw new IllegalArgumentException("Unknown arrow fletching: " + name);
    }
}
