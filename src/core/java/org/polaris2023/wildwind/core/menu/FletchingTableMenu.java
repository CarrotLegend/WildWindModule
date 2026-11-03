package org.polaris2023.wildwind.core.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.polaris2023.wildwind.core.arrow.ArrowFletching;
import org.polaris2023.wildwind.core.arrow.ArrowHead;
import org.polaris2023.wildwind.core.arrow.ArrowShaft;
import org.polaris2023.wildwind.core.block.ModBlocks;
import org.polaris2023.wildwind.core.block.entity.FletchingTableBlockEntity;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 制箭台菜单
 *
 * @author baka4n
 * @since 2026/04/15
 */
public class FletchingTableMenu extends AbstractContainerMenu {
    /** 方块槽位数（输入 0-2 + 输出 3），玩家背包从这里开始 */
    private static final int INV_START = FletchingTableBlockEntity.CONTAINER_SIZE;

    private final Container container;
    private final ContainerLevelAccess access;

    public FletchingTableMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new SimpleContainer(FletchingTableBlockEntity.CONTAINER_SIZE), ContainerLevelAccess.NULL);
    }

    public FletchingTableMenu(int containerId, Inventory playerInventory, Container container, ContainerLevelAccess access) {
        super(ModMenus.FLETCHING_TABLE.get(), containerId);
        this.container = container;
        this.access = access;

        // 箭羽槽 (slot 0)
        this.addSlot(new FilteredSlot(container, FletchingTableBlockEntity.Slots.SLOT_FLETCHING.slot, 8, 48,
                stack -> ArrowFletching.fromItem(stack.getItem()) != null));
        // 箭杆槽 (slot 1)
        this.addSlot(new FilteredSlot(container, FletchingTableBlockEntity.Slots.SLOT_SHAFT.slot, 26, 48,
                stack -> ArrowShaft.fromItem(stack.getItem()) != null));
        // 箭头槽 (slot 2)
        this.addSlot(new FilteredSlot(container, FletchingTableBlockEntity.Slots.SLOT_HEAD.slot, 44, 48,
                stack -> ArrowHead.fromItem(stack.getItem()) != null));
        // 输出槽 (slot 3)：只能取不能放
        this.addSlot(new ResultSlot(container, FletchingTableBlockEntity.Slots.SLOT_RESULT.slot, 98, 48));

        // 添加玩家物品栏
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        // 添加玩家快捷栏
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    /**
     * 只接受指定材料的输入槽：非法物品直接放不进去。
     */
    private static class FilteredSlot extends Slot {
        private final Predicate<ItemStack> filter;

        FilteredSlot(Container container, int slot, int x, int y, Predicate<ItemStack> filter) {
            super(container, slot, x, y);
            this.filter = filter;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return this.filter.test(stack);
        }
    }

    /**
     * 输出槽 - 不能放入物品，取出时消耗材料
     */
    private class ResultSlot extends Slot {
        public ResultSlot(Container container, int slot, int x, int y) {
            super(container, slot, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }

        /**
         * 产物不可拆分。原版 {@code clicked} 的「右键取半组」、Q 键丢弃、以及与光标同物品合并
         * 都走 {@code tryRemove(amount, ...)}，amount 会小于产物数量；直接放行就会出现
         * 「只拿走一半、材料却按整份扣」的情况，所以这里一律按整份取走，放不下就一个都不取。
         */
        @Override
        public Optional<ItemStack> tryRemove(int amount, int maxAmount, Player player) {
            ItemStack result = this.getItem();
            if (result.isEmpty() || maxAmount < result.getCount()) {
                return Optional.empty();
            }
            return super.tryRemove(result.getCount(), maxAmount, player);
        }

        @Override
        public void onTake(Player player, ItemStack stack) {
            // 取走产物后消耗每样材料各 1 个
            if (FletchingTableMenu.this.container instanceof FletchingTableBlockEntity be) {
                be.consumeInputs();
            }
            super.onTake(player, stack);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot == null || !slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack slotItem = slot.getItem();
        ItemStack moved = slotItem.copy();

        // 输出槽：搬进背包后由 onTake 消费材料并重算下一份产物。
        // 这里绝不能像普通槽位那样把槽置空，否则会把刚重算好的产物清掉，shift 就只能合成一次
        if (index == FletchingTableBlockEntity.Slots.SLOT_RESULT.slot) {
            if (!this.moveItemStackTo(slotItem, INV_START, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotItem);
            return moved;
        }

        if (index < INV_START) {
            // 方块自己的槽 -> 背包
            if (!this.moveItemStackTo(slotItem, INV_START, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else {
            // 玩家物品栏 -> 输入槽（0-2），放不下再留在背包
            if (!this.moveItemStackTo(slotItem, 0, FletchingTableBlockEntity.Slots.SLOT_RESULT.slot, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (slotItem.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return moved;
    }

    /**
     * 关闭界面时把三个输入槽的材料还给玩家（背包放不下就丢在地上），
     * 与原版工作台行为一致，避免材料遗留在方块里。
     */
    @Override
    public void removed(Player player) {
        super.removed(player);
        if (!(this.container instanceof FletchingTableBlockEntity be)) {
            return;
        }

        for (FletchingTableBlockEntity.Slots slot : FletchingTableBlockEntity.Slots.values()) {
            if (slot == FletchingTableBlockEntity.Slots.SLOT_RESULT) {
                continue;
            }
            ItemStack stack = be.getItem(slot.slot);
            if (stack.isEmpty()) {
                continue;
            }
            be.setItem(slot.slot, ItemStack.EMPTY);
            if (!player.getInventory().add(stack)) {
                player.drop(stack, false);
            }
        }
        be.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.FLETCHING_TABLE.get());
    }

}
