package org.polaris2023.wildwind.wilderness.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

import javax.annotation.Nullable;

/**
 * 模组自定义的实体生成位置类型常量喵~
 */
public interface ModSpawnPlacementTypes {
	/**
	 * 允许实体生成在水中地面上的生成类型喵~
	 */
	SpawnPlacementType IN_WATER_GROUND = new SpawnPlacementType() {
		/**
		 * 判断指定位置是否适合作为水中地面生成点喵~
		 *
		 * @param level 当前世界读取器喵~
		 * @param pos 待检测的位置喵~
		 * @param entityType 待生成的实体类型喵~
		 * @return 可生成时返回 true 喵~
		 */
		@Override
		public boolean isSpawnPositionOk(LevelReader level, BlockPos pos, @Nullable EntityType<?> entityType) {
			if (entityType == null) return false;
			if (!level.getWorldBorder().isWithinBounds(pos)) return false;
			BlockPos above = pos.above();
			BlockPos below = pos.below();
			BlockState blockstate = level.getBlockState(below);

			return blockstate.isValidSpawn(level, below, entityType)
					&& this.isValidEmptySpawnBlock(level, pos, entityType)
					&& this.isValidEmptySpawnBlock(level, above, entityType);
		}

		private boolean isValidEmptySpawnBlock(LevelReader level, BlockPos pos, EntityType<?> entityType) {
			BlockState blockState = level.getBlockState(pos);
			return !blockState.isSignalSource()
					&& blockState.getFluidState().is(Fluids.WATER)
					&& !blockState.is(BlockTags.PREVENT_MOB_SPAWNING_INSIDE)
					&& !entityType.isBlockDangerous(blockState);
		}
	};
}
