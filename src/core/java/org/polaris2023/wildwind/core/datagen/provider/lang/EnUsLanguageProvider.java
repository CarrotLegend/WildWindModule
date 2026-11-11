package org.polaris2023.wildwind.core.datagen.provider.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.block.ModBlocks;
import org.polaris2023.wildwind.core.registry.ModEntities;
import org.polaris2023.wildwind.core.registry.ModItems;

/**
 * 核心模块的英文语言文件。
 */
public class EnUsLanguageProvider extends LanguageProvider {

    public EnUsLanguageProvider(PackOutput output) {
        super(output, WildWindCore.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // 杜鹃木套件
        addBlock(ModBlocks.AZALEA_LOG, "Azalea Log");
        addBlock(ModBlocks.AZALEA_WOOD, "Azalea Wood");
        addBlock(ModBlocks.STRIPPED_AZALEA_LOG, "Stripped Azalea Log");
        addBlock(ModBlocks.STRIPPED_AZALEA_WOOD, "Stripped Azalea Wood");
        addBlock(ModBlocks.AZALEA_PLANKS, "Azalea Planks");
        addBlock(ModBlocks.AZALEA_STAIRS, "Azalea Stairs");
        addBlock(ModBlocks.AZALEA_SLAB, "Azalea Slab");
        addBlock(ModBlocks.AZALEA_FENCE, "Azalea Fence");
        addBlock(ModBlocks.AZALEA_FENCE_GATE, "Azalea Fence Gate");
        addBlock(ModBlocks.AZALEA_DOOR, "Azalea Door");
        addBlock(ModBlocks.AZALEA_TRAPDOOR, "Azalea Trapdoor");
        addBlock(ModBlocks.AZALEA_PRESSURE_PLATE, "Azalea Pressure Plate");
        addBlock(ModBlocks.AZALEA_BUTTON, "Azalea Button");

        // 其他方块
        addBlock(ModBlocks.FLETCHING_TABLE, "Fletching Table");
        addBlock(ModBlocks.ROSE, "Rose");
        addBlock(ModBlocks.SOUL_ROSE, "Soul Rose");
        addBlock(ModBlocks.SOUL_ROSE_BUSH, "Soul Rose Bush");
        addBlock(ModBlocks.WITHER_ROSE_BUSH, "Wither Rose Bush");
        addBlock(ModBlocks.KILN, "Kiln");

        // GUI
        add("container.fletching_table", "Arrow Crafting");
        add("tab.ww_core.name", "Wild Wind");

        // 幼蛛
        addItem(ModItems.SPIDERLING_SPAWN_EGG, "Spiderling Spawn Egg");
        addEntityType(ModEntities.SPIDERLING, "Spiderling");

        // 幼蛛音效字幕
        add("subtitles.entity.spiderling.ambient", "Spiderling hisses");
        add("subtitles.entity.spiderling.hurt", "Spiderling hurts");
        add("subtitles.entity.spiderling.death", "Spiderling dies");
    }
}
