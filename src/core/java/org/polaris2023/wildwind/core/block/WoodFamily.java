package org.polaris2023.wildwind.core.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 一种木材（方块家族）的聚合视图。
 * <p>
 * 方块注册仍由 {@link ModBlocks} 以显式字段完成，这里只做聚合，
 * 让模型 / 战利品 / 标签 / 数据地图 / 物品栏等 provider 只需遍历一次，
 * 不用在每个文件里重复抄 13~15 个参数。
 *
 * @param name      木材名（blaze / soul / azalea），用于拼接翻译与自建标签
 * @param leavesTint 树叶物品图标的常数染色（无树叶时忽略）
 */
public record WoodFamily(
        String name,
        int leavesTint,
        DeferredBlock<RotatedPillarBlock> log,
        DeferredBlock<RotatedPillarBlock> wood,
        DeferredBlock<RotatedPillarBlock> strippedLog,
        DeferredBlock<RotatedPillarBlock> strippedWood,
        DeferredBlock<Block> planks,
        DeferredBlock<StairBlock> stairs,
        DeferredBlock<SlabBlock> slab,
        DeferredBlock<FenceBlock> fence,
        DeferredBlock<FenceGateBlock> fenceGate,
        DeferredBlock<DoorBlock> door,
        DeferredBlock<TrapDoorBlock> trapdoor,
        DeferredBlock<PressurePlateBlock> pressurePlate,
        DeferredBlock<ButtonBlock> button,
        @Nullable DeferredBlock<LeavesBlock> leaves,
        @Nullable DeferredBlock<SaplingBlock> sapling
) {

    /**
     * 建筑套件方块（不含树叶 / 树苗），顺序即物品栏与标签的常规顺序。
     */
    public List<DeferredBlock<? extends Block>> buildingBlocks() {
        return List.of(log, wood, strippedLog, strippedWood, planks,
                stairs, slab, fence, fenceGate, door, trapdoor, pressurePlate, button);
    }

    /**
     * 是否带树叶与树苗（杜鹃木沿用原版，没有自成套的树叶）。
     */
    public boolean hasLeaves() {
        return leaves != null && sapling != null;
    }
}
