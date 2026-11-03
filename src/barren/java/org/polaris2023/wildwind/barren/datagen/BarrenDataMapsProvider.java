package org.polaris2023.wildwind.barren.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Strippable;
import org.polaris2023.wildwind.barren.block.BarrenBlocks;
import org.polaris2023.wildwind.barren.block.BarrenWoodFamilies;
import org.polaris2023.wildwind.core.block.WoodFamily;

import java.util.concurrent.CompletableFuture;

/**
 * 瘠土更新的数据映射：去皮与燃料。
 */
public class BarrenDataMapsProvider extends DataMapProvider {

    public BarrenDataMapsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        builder(NeoForgeDataMaps.STRIPPABLES)
                .add(BarrenBlocks.BLAZE_LOG.getKey(), new Strippable(BarrenBlocks.STRIPPED_BLAZE_LOG.get()), false)
                .add(BarrenBlocks.BLAZE_WOOD.getKey(), new Strippable(BarrenBlocks.STRIPPED_BLAZE_WOOD.get()), false)
                .add(BarrenBlocks.SOUL_LOG.getKey(), new Strippable(BarrenBlocks.STRIPPED_SOUL_LOG.get()), false)
                .add(BarrenBlocks.SOUL_WOOD.getKey(), new Strippable(BarrenBlocks.STRIPPED_SOUL_WOOD.get()), false);

        var fuels = builder(NeoForgeDataMaps.FURNACE_FUELS);
        for (WoodFamily family : BarrenWoodFamilies.ALL) {
            fuels.add(itemKey(family.log().get()), new FurnaceFuel(300), false);
            fuels.add(itemKey(family.wood().get()), new FurnaceFuel(300), false);
            fuels.add(itemKey(family.strippedLog().get()), new FurnaceFuel(300), false);
            fuels.add(itemKey(family.strippedWood().get()), new FurnaceFuel(300), false);
            fuels.add(itemKey(family.planks().get()), new FurnaceFuel(300), false);
            fuels.add(itemKey(family.stairs().get()), new FurnaceFuel(300), false);
            fuels.add(itemKey(family.slab().get()), new FurnaceFuel(150), false);
            fuels.add(itemKey(family.fence().get()), new FurnaceFuel(300), false);
            fuels.add(itemKey(family.fenceGate().get()), new FurnaceFuel(300), false);
            fuels.add(itemKey(family.pressurePlate().get()), new FurnaceFuel(300), false);
            fuels.add(itemKey(family.button().get()), new FurnaceFuel(100), false);
            if (family.sapling() != null) {
                fuels.add(itemKey(family.sapling().get()), new FurnaceFuel(100), false);
            }
        }
    }

    private ResourceKey<Item> itemKey(Block block) {
        Item item = block.asItem();
        return BuiltInRegistries.ITEM.getResourceKey(item)
                .orElseThrow(() -> new IllegalStateException("未注册的物品: " + item));
    }
}
