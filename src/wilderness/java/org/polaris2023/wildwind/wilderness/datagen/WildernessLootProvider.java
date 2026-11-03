package org.polaris2023.wildwind.wilderness.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jspecify.annotations.NonNull;
import org.polaris2023.wildwind.wilderness.block.WildernessBlocks;
import org.polaris2023.wildwind.wilderness.registry.ModEntities;
import org.polaris2023.wildwind.wilderness.registry.ModItems;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * 荒野更新的战利品表：实体掉落 + 方块掉落。
 */
public class WildernessLootProvider extends LootTableProvider {

    public WildernessLootProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(
                output,
                Set.of(),
                List.of(
                        new SubProviderEntry(WildernessEntityLoot::new, LootContextParamSets.ENTITY),
                        new SubProviderEntry(WildernessBlockLoot::new, LootContextParamSets.BLOCK)
                ),
                lookupProvider
        );
    }

    private static final class WildernessEntityLoot extends EntityLootSubProvider {
        WildernessEntityLoot(HolderLookup.Provider registries) {
            super(FeatureFlags.DEFAULT_FLAGS, registries);
        }

        @Override
        protected Stream<EntityType<?>> getKnownEntityTypes() {
            return ModEntities.ENTITY_TYPES.getEntries().stream().map(DeferredHolder::get);
        }

        @Override
        public void generate() {
            // 泥沼蟹暂无掉落物
            this.add(ModEntities.MUDCRAB.get(), LootTable.lootTable());
            this.add(
                    ModEntities.PIRANHA.get(),
                    LootTable.lootTable()
                            .withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1.0F))
                                            .add(LootItem.lootTableItem(ModItems.PIRANHA.get())
                                                    .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
                            )
                            .withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1.0F))
                                            .add(LootItem.lootTableItem(Items.BONE_MEAL))
                                            .when(LootItemRandomChanceCondition.randomChance(0.05F))
                            )
                            .withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1.0F))
                                            .add(LootItem.lootTableItem(ModItems.FANG.get()))
                                            .when(LootItemRandomChanceCondition.randomChance(0.05F))
                            )
            );
        }
    }

    private static final class WildernessBlockLoot extends net.minecraft.data.loot.BlockLootSubProvider {
        WildernessBlockLoot(HolderLookup.Provider registries) {
            super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
        }

        @Override
        protected @NonNull Iterable<Block> getKnownBlocks() {
            return WildernessBlocks.BLOCKS.getEntries().stream().map(h -> (Block) h.value()).toList();
        }

        @Override
        protected void generate() {
            dropSelf(WildernessBlocks.SOUL_JACK_O_LANTERN.get());
        }
    }
}
