package org.polaris2023.wildwind.wilderness.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.block.WildernessBlocks;
import org.polaris2023.wildwind.wilderness.registry.ModItems;

/**
 * 荒野更新的模型生成器。
 */
public class WildernessModelProvider extends ModelProvider {

    public WildernessModelProvider(PackOutput output) {
        super(output, WildWindWilderness.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        // 灵魂南瓜灯 - 正面自定义贴图，其余使用原版南瓜
        blockModels.createPumpkinVariant(WildernessBlocks.SOUL_JACK_O_LANTERN.get(), TextureMapping.column(Blocks.PUMPKIN));

        // 独立物品
        itemModels.generateFlatItem(ModItems.CRAB_CLAW.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MUDCRAB_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MUDCRAB_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PIRANHA.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.COOKED_PIRANHA.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PIRANHA_BUCKET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PIRANHA_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.FANG.get(), ModelTemplates.FLAT_ITEM);

        // 万用蟹钳 - 使用手写 3D 模型
        itemModels.itemModelOutput.accept(
                ModItems.OMNI_CLAW.get(),
                ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(ModItems.OMNI_CLAW.get()))
        );
    }
}
