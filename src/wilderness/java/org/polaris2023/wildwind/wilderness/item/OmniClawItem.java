package org.polaris2023.wildwind.wilderness.item;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.component.OmniClawTools;
import org.polaris2023.wildwind.wilderness.registry.ModAttributes;
import org.polaris2023.wildwind.wilderness.registry.ModDataComponents;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 可收纳多种工具并代理其行为的万用蟹钳物品喵~
 * <p>
 * 26.2 迁移说明：getDamage/setDamage/getMaxDamage/isDamageable/getAllEnchantments
 * 为 {@link IItemExtension} 的默认方法，父类调用需通过 IItemExtension.super；
 * isCorrectToolForDrops/getDestroySpeed/mineBlock/getTooltipImage 已并入原版 Item；
 * HIDE_TOOLTIP/HIDE_ADDITIONAL_TOOLTIP 组件由 TOOLTIP_DISPLAY 取代。
 */
public class OmniClawItem extends Item {
	/**
	 * 额外交互方块距离属性修饰符标识喵~
	 */
	public static final Identifier BLOCK_INTERACTION_RANGE_ID = WildWindWilderness.id("omni_claw_block_interaction_range");
	/**
	 * 额外交互实体距离属性修饰符标识喵~
	 */
	public static final Identifier ENTITY_INTERACTION_RANGE_ID = WildWindWilderness.id("omni_claw_entity_interaction_range");
	/**
	 * 额外拾取物品距离属性修饰符标识喵~
	 */
	public static final Identifier EXTRA_ITEM_PICKUP_RANGE_ID = WildWindWilderness.id("omni_claw_extra_item_pickup_range");

	/**
	 * 创建万用蟹钳物品喵~
	 *
	 * @param properties 物品属性喵~
	 */
	public OmniClawItem(Properties properties) {
		super(properties);
	}

	@Override
	public void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {
		ItemStack stack = itemEntity.getItem();
		OmniClawTools tools = stack.get(ModDataComponents.OMNI_CLAW_TOOLS.get());
		if (tools != null) {
			stack.set(ModDataComponents.OMNI_CLAW_TOOLS.get(), OmniClawTools.EMPTY);
			ItemUtils.onContainerDestroyed(itemEntity, tools.getTools().stream().map(ItemStack::copy));
		}
	}

	/**
	 * 获取内部工具结果，若没有已选工具则返回备用结果喵~
	 *
	 * @param omniStack 万用蟹钳物品栈喵~
	 * @param resultFunction 对内部工具执行的计算喵~
	 * @param or 没有工具时的备用结果提供器喵~
	 * @param <T> 返回值类型喵~
	 * @return 计算结果喵~
	 */
	public static <T> T toolOr(ItemStack omniStack, Function<ItemStack, T> resultFunction, Supplier<T> or) {
		ItemStack tool = getLastSelectedTool(omniStack);
		return tool.isEmpty() ? or.get() : resultFunction.apply(tool);
	}

	/**
	 * 获取最近一次选中的内部工具喵~
	 *
	 * @param omniClawStack 万用蟹钳物品栈喵~
	 * @return 最近一次选中的工具喵~
	 */
	public static ItemStack getLastSelectedTool(ItemStack omniClawStack) {
		if (!omniClawStack.has(ModDataComponents.OMNI_CLAW_TOOLS.get())) return ItemStack.EMPTY;

		OmniClawTools tools = omniClawStack.get(ModDataComponents.OMNI_CLAW_TOOLS.get());
		return tools.getTools().get(tools.getSelectedToolIndex());
	}

	/**
	 * 为指定方块状态选择内部工具喵~
	 *
	 * @param omniClawStack 万用蟹钳物品栈喵~
	 * @param state 方块状态喵~
	 * @return 适合挖掘该方块的工具喵~
	 */
	public static ItemStack chooseToolItemByBlock(ItemStack omniClawStack, BlockState state) {
		return findToolAndSelect(omniClawStack, tools -> tools.getToolFor(state));
	}

	/**
	 * 按能力选择内部工具喵~
	 *
	 * @param omniClawStack 万用蟹钳物品栈喵~
	 * @param ability 目标能力喵~
	 * @return 具备该能力的工具喵~
	 */
	public static ItemStack chooseToolByAbility(ItemStack omniClawStack, ItemAbility ability) {
		return findToolAndSelect(omniClawStack, tools -> tools.getToolByAbility(ability));
	}

	/**
	 * 创建万用蟹钳持有时提供的属性修饰器喵~
	 *
	 * @return 属性修饰器集合喵~
	 */
	public static ItemAttributeModifiers createAttributes() {
		return ItemAttributeModifiers.builder()
				.add(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(BLOCK_INTERACTION_RANGE_ID, 2.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
				.add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ENTITY_INTERACTION_RANGE_ID, 2.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
				.add(ModAttributes.EXTRA_ITEM_PICKUP_RANGE, new AttributeModifier(EXTRA_ITEM_PICKUP_RANGE_ID, 2.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
				.build();
	}

	@Override
	public void setDamage(ItemStack stack, int damage) {
		ItemStack tool = getLastSelectedTool(stack);
		if (tool.isEmpty()) {
			super.setDamage(stack, damage);
			return;
		}

		ItemStack copiedTool = tool.copy();
		copiedTool.setDamageValue(damage);
		OmniClawTools tools = stack.get(ModDataComponents.OMNI_CLAW_TOOLS.get());
		stack.set(ModDataComponents.OMNI_CLAW_TOOLS.get(), tools
				.toMutable()
				.set(copiedTool, tools.indexOf(tool))
				.toImmutable()
		);
	}

	/**
	 * 获取当前选中内部工具的耐久损耗值喵~
	 *
	 * @param omniStack 万用蟹钳物品栈喵~
	 * @return 当前耐久损耗值喵~
	 */
	@Override
	public int getDamage(ItemStack omniStack) {
		return toolOr(omniStack, stack -> stack.getItem().getDamage(stack), () -> super.getDamage(omniStack));
	}

	/**
	 * 获取当前选中内部工具的最大耐久喵~
	 *
	 * @param omniStack 万用蟹钳物品栈喵~
	 * @return 最大耐久值喵~
	 */
	@Override
	public int getMaxDamage(ItemStack omniStack) {
		return toolOr(omniStack, stack -> stack.getItem().getMaxDamage(stack), () -> super.getMaxDamage(omniStack));
	}

	/**
	 * 判断当前选中内部工具是否可损坏喵~
	 *
	 * @param omniStack 万用蟹钳物品栈喵~
	 * @return 可损坏时返回 true 喵~
	 */
	@Override
	public boolean isDamageable(ItemStack omniStack) {
		return toolOr(omniStack, stack -> stack.getItem().isDamageable(stack), () -> super.isDamageable(omniStack));
	}

	/**
	 * 使用当前匹配的内部工具执行方块挖掘耐久消耗喵~
	 *
	 * @param stack 万用蟹钳物品栈喵~
	 * @param level 当前世界喵~
	 * @param state 被挖掘的方块状态喵~
	 * @param pos 方块位置喵~
	 * @param miningEntity 挖掘实体喵~
	 * @return 成功代理内部工具时返回 true 喵~
	 */
	@Override
	public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
		if (!level.isClientSide() && !chooseToolItemByBlock(stack, state).isEmpty()) {
			stack.hurtAndBreak(1, miningEntity, EquipmentSlot.MAINHAND);
			return true;
		}

		return super.mineBlock(stack, level, state, pos, miningEntity);
	}

	/**
	 * 判断当前选中内部工具是否能正确采集该方块掉落物喵~
	 *
	 * @param omniStack 万用蟹钳物品栈喵~
	 * @param state 方块状态喵~
	 * @return 能正确采集时返回 true 喵~
	 */
	@Override
	public boolean isCorrectToolForDrops(ItemStack omniStack, BlockState state) {
		return toolOr(omniStack, stack -> stack.isCorrectToolForDrops(state), () -> super.isCorrectToolForDrops(omniStack, state));
	}

	/**
	 * 获取当前选中内部工具上的全部附魔喵~
	 *
	 * @param omniStack 万用蟹钳物品栈喵~
	 * @param lookup 附魔注册表查询器喵~
	 * @return 附魔集合喵~
	 */
	@Override
	public ItemEnchantments getAllEnchantments(ItemStack omniStack, HolderLookup.RegistryLookup<Enchantment> lookup) {
		return toolOr(omniStack, stack -> stack.getAllEnchantments(lookup), () -> super.getAllEnchantments(omniStack, lookup));
	}

	/**
	 * 获取当前选中内部工具对方块的挖掘速度喵~
	 *
	 * @param omniStack 万用蟹钳物品栈喵~
	 * @param state 方块状态喵~
	 * @return 挖掘速度喵~
	 */
	@Override
	public float getDestroySpeed(ItemStack omniStack, BlockState state) {
		return toolOr(omniStack, stack -> stack.getDestroySpeed(state), () -> super.getDestroySpeed(omniStack, state));
	}

	/**
	 * 代理内部工具执行对方块的右键交互喵~
	 *
	 * @param context 交互上下文喵~
	 * @return 交互结果喵~
	 */
	@Override
	public InteractionResult useOn(UseOnContext context) {
		ItemStack stack = context.getItemInHand();
		if (!stack.has(ModDataComponents.OMNI_CLAW_TOOLS.get())) return super.useOn(context);

		OmniClawTools tools = stack.get(ModDataComponents.OMNI_CLAW_TOOLS.get());

		ItemStack selectedTool = tools.getSelectedTool();
		if (!selectedTool.isEmpty()) {
			InteractionResult result = handleToolUseOn(stack, context, selectedTool, tools);
			if (result.consumesAction()) return result;
		}

		for (ItemStack toolStack : tools.getTools()) {
			if (toolStack.isEmpty()) continue;

			InteractionResult result = handleToolUseOn(stack, context, toolStack, tools);
			if (result.consumesAction()) return result;
		}

		return super.useOn(context);
	}

	/**
	 * 获取万用蟹钳的自定义提示图像组件喵~
	 *
	 * @param stack 万用蟹钳物品栈喵~
	 * @return 提示图像组件喵~
	 */
	@Override
	public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
		TooltipDisplay display = stack.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT);
		return !display.shows(ModDataComponents.OMNI_CLAW_TOOLS.get())
				? Optional.empty()
				: Optional.ofNullable(stack.get(ModDataComponents.OMNI_CLAW_TOOLS.get()));
	}

	/**
	 * 处理万用蟹钳堆叠到其他槽位物品上的右键交换逻辑喵~
	 *
	 * @param stack 万用蟹钳物品栈喵~
	 * @param slot 目标槽位喵~
	 * @param action 点击动作喵~
	 * @param player 玩家喵~
	 * @return 处理成功时返回 true 喵~
	 */
	@Override
	public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player) {
		if (stack.getCount() != 1 || action != ClickAction.SECONDARY) {
			return false;
		}

		return swapItem(stack, slot.getItem(), player, SlotAccess.of(slot::getItem, slot::set));
	}

	/**
	 * 处理其他物品右键堆叠到万用蟹钳上的交换逻辑喵~
	 *
	 * @param stack 万用蟹钳物品栈喵~
	 * @param other 另一物品栈喵~
	 * @param slot 槽位喵~
	 * @param action 点击动作喵~
	 * @param player 玩家喵~
	 * @param access 槽位访问器喵~
	 * @return 处理成功时返回 true 喵~
	 */
	@Override
	public boolean overrideOtherStackedOnMe(
			ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access
	) {
		if (stack.getCount() != 1) return false;
		if (action != ClickAction.SECONDARY || !slot.allowModification(player)) {
			return false;
		}

		return swapItem(stack, other, player, access);
	}

	private static ItemStack findToolAndSelect(ItemStack omniClawStack, Function<OmniClawTools, ItemStack> selectRule) {
		if (!omniClawStack.has(ModDataComponents.OMNI_CLAW_TOOLS.get())) return ItemStack.EMPTY;

		OmniClawTools tools = omniClawStack.get(ModDataComponents.OMNI_CLAW_TOOLS.get());
		ItemStack result = selectRule.apply(tools);
		int lastSelected = tools.indexOf(result);
		if (lastSelected != -1) omniClawStack.update(ModDataComponents.OMNI_CLAW_TOOLS.get(), tools, operator -> operator.withSelectIndex(lastSelected));

		return result;
	}

	private static InteractionResult handleToolUseOn(ItemStack omniClawStack, UseOnContext context, ItemStack toolStack, OmniClawTools tools) {
		ItemStack copiedToolStack = toolStack.copy();
		UseOnContext toolContext = new UseOnContext(
				context.getLevel(),
				context.getPlayer(),
				context.getHand(),
				copiedToolStack,
				context.hitResult
		);

		InteractionResult result = copiedToolStack.useOn(toolContext);
		if (result.consumesAction()) {
			if (!ItemStack.isSameItemSameComponents(toolStack, copiedToolStack)) {
				int toolIndex = tools.indexOf(toolStack);
				omniClawStack.set(ModDataComponents.OMNI_CLAW_TOOLS.get(), tools
						.toMutable()
						.set(copiedToolStack, toolIndex)
						.select(toolIndex)
						.toImmutable()
				);
			}
		}

		return result;
	}

	private boolean swapItem(ItemStack omniClaw, ItemStack other, Player player, SlotAccess access) {
		OmniClawTools tools = omniClaw.get(ModDataComponents.OMNI_CLAW_TOOLS.get());
		if (tools == null) {
			return false;
		}

		OmniClawTools.Mutable mutable = tools.toMutable();
		if (other.isEmpty()) {
			ItemStack output = mutable.removeSelected();
			if (!output.isEmpty()) {
				this.playRemoveOneSound(player);
				access.set(output);
			}
		} else {
			ItemStack output = mutable.insert(other);
			if (!ItemStack.isSameItemSameComponents(other, output)) {
				this.playInsertSound(player);
				access.set(output);
			}
		}

		omniClaw.set(ModDataComponents.OMNI_CLAW_TOOLS.get(), mutable.toImmutable());
		return true;
	}

	private void playRemoveOneSound(Entity entity) {
		entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
	}

	private void playInsertSound(Entity entity) {
		entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
	}
}