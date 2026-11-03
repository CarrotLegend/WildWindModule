package org.polaris2023.wildwind.wilderness.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;

/**
 * 注册模组属性喵~
 * <p>
 * 26.2 迁移说明：旧仓库通过 LivingEntityMixin 向全体生物基础属性表追加属性，
 * 现改用 NeoForge 的 EntityAttributeModificationEvent 实现同等效果（涵盖玩家）。
 */
@EventBusSubscriber(modid = WildWindWilderness.MOD_ID)
public class ModAttributes {
	/**
	 * 模组属性延迟注册器喵~
	 */
	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, WildWindWilderness.MOD_ID);

	/**
	 * 额外物品拾取距离属性喵~
	 */
	public static final DeferredHolder<Attribute, Attribute> EXTRA_ITEM_PICKUP_RANGE =
			ATTRIBUTES.register("extra_item_pickup_range", () -> new RangedAttribute(
					"attribute.name.ww_wilderness.extra_item_pickup_range",
					0.0,
					0.0,
					10.0
			));

	private ModAttributes() {
	}

	/**
	 * 向模组事件总线注册属性喵~
	 *
	 * @param modBus 模组事件总线喵~
	 */
	public static void register(IEventBus modBus) {
		ATTRIBUTES.register(modBus);
	}

	/**
	 * 向所有生物实体类型追加拾取距离属性（对齐旧 LivingEntityMixin 行为）喵~
	 *
	 * @param event 实体属性修改事件喵~
	 */
	@SubscribeEvent
	static void addAttributesToLivingEntities(EntityAttributeModificationEvent event) {
		event.getTypes().forEach(type -> event.add(type, EXTRA_ITEM_PICKUP_RANGE));
	}
}