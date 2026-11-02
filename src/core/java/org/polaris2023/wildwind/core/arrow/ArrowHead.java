package org.polaris2023.wildwind.core.arrow;

import net.minecraft.world.item.Items;

/**
 * 箭头类型枚举
 * 影响命中效果
 *
 * @author baka4n
 * @since 2026/04/15
 */
public enum ArrowHead {
    /**
     * 燧石 - 无效果
     */
    FLINT("flint", Items.FLINT, false, "无效果"),

    /**
     * 火药 - 爆炸，伤害不破坏地形
     */
    GUNPOWDER("gunpowder", Items.GUNPOWDER, false, "爆炸，伤害不破坏地形"),

    /**
     * 粘液球 - 命中产生弹力
     */
    SLIME_BALL("slime_ball", Items.SLIME_BALL, false, "命中产生弹力"),

    /**
     * 烈焰粉 - 命中致燃实体
     */
    BLAZE_POWDER("blaze_powder", Items.BLAZE_POWDER, false, "命中致燃实体"),

    /**
     * 岩浆膏 - 致燃+弹力
     */
    MAGMA_CREAM("magma_cream", Items.MAGMA_CREAM, false, "致燃+弹力"),

    /**
     * 风弹 - 风弹爆炸，不可回收
     */
    WIND_CHARGE("wind_charge", Items.WIND_CHARGE, true, "风弹爆炸，不可回收"),

    /**
     * 煤炭 - 命中方块放置火把，不可回收
     */
    COAL("coal", Items.COAL, true, "命中方块放置火把，不可回收"),

    /**
     * 紫颂果 - 目标随机传送，不可回收
     */
    CHORUS_FRUIT("chorus_fruit", Items.CHORUS_FRUIT, true, "目标随机传送，不可回收"),

    /**
     * 末影之眼 - 与目标交换位置，不可回收
     */
    ENDER_EYE("ender_eye", Items.ENDER_EYE, true, "与目标交换位置，不可回收"),

    /**
     * 紫水晶 - 破坏植物/玻璃类方块
     */
    AMETHYST_SHARD("amethyst_shard", Items.AMETHYST_SHARD, false, "破坏植物/玻璃类方块"),

    /**
     * 海晶碎片 - 无视流体重力
     */
    PRISMARINE_SHARD("prismarine_shard", Items.PRISMARINE_SHARD, false, "无视流体重力");

    private final String name;
    private final net.minecraft.world.level.ItemLike item;
    private final boolean notRecoverable;
    private final String description;

    ArrowHead(String name, net.minecraft.world.level.ItemLike item, boolean notRecoverable, String description) {
        this.name = name;
        this.item = item;
        this.notRecoverable = notRecoverable;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public net.minecraft.world.level.ItemLike getItem() {
        return item;
    }

    public boolean isNotRecoverable() {
        return notRecoverable;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据物品获取对应的箭头类型
     */
    public static ArrowHead fromItem(net.minecraft.world.item.Item item) {
        for (ArrowHead head : values()) {
            if (head.getItem().asItem() == item) {
                return head;
            }
        }
        return null;
    }

    /**
     * 根据序列化用的 name（小写）反查类型。
     */
    public static ArrowHead fromName(String name) {
        for (ArrowHead head : values()) {
            if (head.getName().equals(name)) {
                return head;
            }
        }
        throw new IllegalArgumentException("Unknown arrow head: " + name);
    }
}
