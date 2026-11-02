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
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack slotItem = slot.getItem();
            itemStack = slotItem.copy();

            // 输出槽 (index 3) -> 背包
            if (index == FletchingTableBlockEntity.Slots.SLOT_RESULT.slot) {
                if (!this.moveItemStackTo(slotItem, INV_START, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onTake(player, slotItem);
            }
            // 方块自己的槽 (index 0-3) -> 背包
            else if (index < INV_START) {
                if (!this.moveItemStackTo(slotItem, INV_START, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            // 玩家物品栏
            else {
                // 尝试放入输入槽（0-2），放不下再留在背包
                if (!this.moveItemStackTo(slotItem, 0, FletchingTableBlockEntity.Slots.SLOT_RESULT.slot, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (slotItem.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.FLETCHING_TABLE.get());
    }

}
