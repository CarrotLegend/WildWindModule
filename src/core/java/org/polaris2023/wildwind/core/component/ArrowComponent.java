package org.polaris2023.wildwind.core.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import org.polaris2023.wildwind.core.arrow.ArrowFletching;
import org.polaris2023.wildwind.core.arrow.ArrowHead;
import org.polaris2023.wildwind.core.arrow.ArrowShaft;
import org.polaris2023.wildwind.core.registry.ModDataComponents;

/**
 * 箭矢组件数据
 * 存储箭羽、箭杆、箭头类型
 *
 * @param fletching 箭羽类型
 * @param shaft     箭杆类型
 * @param head      箭头类型
 * @author baka4n
 * @since 2026/04/15
 */
public record ArrowComponent(ArrowFletching fletching, ArrowShaft shaft, ArrowHead head) {

    public static final Codec<ArrowComponent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.xmap(ArrowFletching::fromName, ArrowFletching::getName).fieldOf("fletching").forGetter(ArrowComponent::fletching),
                    Codec.STRING.xmap(ArrowShaft::fromName, ArrowShaft::getName).fieldOf("shaft").forGetter(ArrowComponent::shaft),
                    Codec.STRING.xmap(ArrowHead::fromName, ArrowHead::getName).fieldOf("head").forGetter(ArrowComponent::head)
            ).apply(instance, ArrowComponent::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, ArrowComponent> STREAM_CODEC = StreamCodec.of(
            (buf, component) -> {
                buf.writeUtf(component.fletching().getName());
                buf.writeUtf(component.shaft().getName());
                buf.writeUtf(component.head().getName());
            },
            buf -> new ArrowComponent(
                    ArrowFletching.fromName(buf.readUtf()),
                    ArrowShaft.fromName(buf.readUtf()),
                    ArrowHead.fromName(buf.readUtf())
            )
    );

    /**
     * 默认组件（普通箭矢）
     */
    public static final ArrowComponent DEFAULT = new ArrowComponent(
            ArrowFletching.FEATHER,
            ArrowShaft.STICK,
            ArrowHead.FLINT
    );

    /**
     * 创建带组件的箭矢物品
     */
    public ItemStack createArrowStack(int count) {
        ItemStack stack = new ItemStack(net.minecraft.world.item.Items.ARROW, count);
        stack.set(ModDataComponents.ARROW_COMPONENT.get(), this);
        return stack;
    }

    /**
     * 从物品获取箭矢组件
     */
    public static ArrowComponent fromStack(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.ARROW_COMPONENT.get(), DEFAULT);
    }

    /**
     * 获取完整描述
     */
    public String getFullDescription() {
        StringBuilder sb = new StringBuilder();
        if (fletching != ArrowFletching.FEATHER) {
            sb.append("箭羽: ").append(fletching.getDescription()).append("\n");
        }
        if (shaft != ArrowShaft.STICK) {
            sb.append("箭杆: ").append(shaft.getDescription()).append("\n");
        }
        if (head != ArrowHead.FLINT) {
            sb.append("箭头: ").append(head.getDescription());
        }
        return sb.toString().trim();
    }

    /**
     * 是否不可回收
     */
    public boolean isNotRecoverable() {
        return shaft.hasFireTrail() || shaft.hasNoGravity() || head.isNotRecoverable();
    }

    /**
     * 获取飞行速度倍率
     */
    public float getSpeedMultiplier() {
        return fletching.getSpeedMultiplier();
    }

    /**
     * 获取重力倍率
     */
    public float getGravityMultiplier() {
        return shaft.getGravityMultiplier();
    }

    /**
     * 获取伤害加成
     */
    public float getDamageBonus() {
        return shaft.getDamageBonus();
    }

    /**
     * 获取不精确度
     */
    public float getInaccuracy() {
        return shaft.getInaccuracy();
    }
}
