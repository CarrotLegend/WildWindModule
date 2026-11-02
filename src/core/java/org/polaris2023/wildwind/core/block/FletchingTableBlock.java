package org.polaris2023.wildwind.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.polaris2023.wildwind.core.block.entity.FletchingTableBlockEntity;
import org.polaris2023.wildwind.core.menu.FletchingTableMenu;

import javax.annotation.Nullable;

/**
 * 制箭台方块 - 带有GUI功能
 *
 * @author baka4n
 * @since 2026/04/15
 */
public class FletchingTableBlock extends Block implements EntityBlock {
    private static final Component CONTAINER_TITLE = Component.translatable("container.fletching_table");

    public FletchingTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        player.openMenu(state.getMenuProvider(level, pos));
        return InteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        // 直接拿方块实体当容器，否则界面里的槽位数组和真实容器对不上
        Container container = level.getBlockEntity(pos) instanceof Container c
                ? c
                : new SimpleContainer(FletchingTableBlockEntity.CONTAINER_SIZE);
        return new SimpleMenuProvider(
                (containerId, playerInventory, player) ->
                        new FletchingTableMenu(containerId, playerInventory, container, ContainerLevelAccess.create(level, pos)),
                CONTAINER_TITLE
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FletchingTableBlockEntity(pos, state);
    }
}
