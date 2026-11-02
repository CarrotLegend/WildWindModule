package org.polaris2023.wildwind.core.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import org.polaris2023.wildwind.core.arrow.ArrowFletching;
import org.polaris2023.wildwind.core.arrow.ArrowHead;
import org.polaris2023.wildwind.core.arrow.ArrowShaft;
import org.polaris2023.wildwind.core.component.ArrowComponent;

/**
 * 制箭台方块实体
 *
 * @author baka4n
 * @since 2026/04/15
 */
public class FletchingTableBlockEntity extends BlockEntity implements Container {
    public enum Slots {
        SLOT_FLETCHING(0), // 箭羽槽
        SLOT_SHAFT(1),     // 箭杆槽
        SLOT_HEAD(2),      // 箭头槽
        SLOT_RESULT(3)     // 输出槽
        ;

        public final int slot;

        Slots(int slot) {
            this.slot = slot;
        }

        public Slot toSlot(Container c, int x, int z) {
            return new Slot(c, slot, x, z);
        }

        public ItemStack remove(Container c, int size) {
            return c.removeItem(slot, size);
        }
    }

    public static final int CONTAINER_SIZE = Slots.values().length;

    /** 每次组装产出的箭矢数量（对齐原版燧石箭 4 支） */
    public static final int ARROWS_PER_CRAFT = 4;

    private final NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);

    public FletchingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLETCHING_TABLE.get(), pos, state);

    }

    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return slot >= 0 && slot < items.size() ? items.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack removed = ContainerHelper.removeItem(items, slot, amount);
        if (!removed.isEmpty() && slot != Slots.SLOT_RESULT.slot) {
            updateResult();
            setChanged();
        }
        return removed;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack removed = ContainerHelper.takeItem(items, slot);
        if (!removed.isEmpty() && slot != Slots.SLOT_RESULT.slot) {
            updateResult();
        }
        return removed;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (slot >= 0 && slot < items.size()) {
            items.set(slot, stack);
            if (stack.getCount() > getMaxStackSize()) {
                stack.setCount(getMaxStackSize());
            }
            if (slot != Slots.SLOT_RESULT.slot) {
                updateResult();
            }
            setChanged();
        }
    }

    /**
     * 根据三个输入槽重新计算输出槽。
     */
    private void updateResult() {
        ArrowComponent component = resolveArrow();
        items.set(Slots.SLOT_RESULT.slot,
                component == null ? ItemStack.EMPTY : component.createArrowStack(ARROWS_PER_CRAFT));
    }

    /**
     * 把三个输入槽解析成箭矢组件，任一格为空或不是合法材料时返回 null。
     */
    private @Nullable ArrowComponent resolveArrow() {
        ItemStack fletchingStack = getItem(Slots.SLOT_FLETCHING.slot);
        ItemStack shaftStack = getItem(Slots.SLOT_SHAFT.slot);
        ItemStack headStack = getItem(Slots.SLOT_HEAD.slot);
        if (fletchingStack.isEmpty() || shaftStack.isEmpty() || headStack.isEmpty()) {
            return null;
        }

        ArrowFletching fletching = ArrowFletching.fromItem(fletchingStack.getItem());
        ArrowShaft shaft = ArrowShaft.fromItem(shaftStack.getItem());
        ArrowHead head = ArrowHead.fromItem(headStack.getItem());
        if (fletching == null || shaft == null || head == null) {
            return null;
        }
        return new ArrowComponent(fletching, shaft, head);
    }

    /**
     * 取走产物后消耗每样材料各 1 个，并重算下一次的产物。
     */
    public void consumeInputs() {
        ContainerHelper.removeItem(items, Slots.SLOT_FLETCHING.slot, 1);
        ContainerHelper.removeItem(items, Slots.SLOT_SHAFT.slot, 1);
        ContainerHelper.removeItem(items, Slots.SLOT_HEAD.slot, 1);
        updateResult();
        setChanged();
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public boolean stillValid(Player player) {
        if (level == null) {
            return false;
        }
        if (level.getBlockEntity(worldPosition) != this) {
            return false;
        }
        return player.distanceToSqr(worldPosition.getX() + 0.5, worldPosition.getY() + 0.5, worldPosition.getZ() + 0.5) <= 64.0;
    }

    @Override
    public void clearContent() {
        items.clear();
        setChanged();
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items);
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, items);
    }
}
