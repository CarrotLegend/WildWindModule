package org.polaris2023.wildwind.core.datagen.provider.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.block.ModBlocks;
import org.polaris2023.wildwind.core.registry.ModEntities;
import org.polaris2023.wildwind.core.registry.ModItems;

/**
 * 核心模块的简体中文语言文件。
 */
public class ZhCnLanguageProvider extends LanguageProvider {

    public ZhCnLanguageProvider(PackOutput output) {
        super(output, WildWindCore.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        // 杜鹃木套件
        addBlock(ModBlocks.AZALEA_LOG, "杜鹃木原木");
        addBlock(ModBlocks.AZALEA_WOOD, "杜鹃木");
        addBlock(ModBlocks.STRIPPED_AZALEA_LOG, "去皮杜鹃木原木");
        addBlock(ModBlocks.STRIPPED_AZALEA_WOOD, "去皮杜鹃木");
        addBlock(ModBlocks.AZALEA_PLANKS, "杜鹃木木板");
        addBlock(ModBlocks.AZALEA_STAIRS, "杜鹃木楼梯");
        addBlock(ModBlocks.AZALEA_SLAB, "杜鹃木台阶");
        addBlock(ModBlocks.AZALEA_FENCE, "杜鹃木栅栏");
        addBlock(ModBlocks.AZALEA_FENCE_GATE, "杜鹃木栅栏门");
        addBlock(ModBlocks.AZALEA_DOOR, "杜鹃木门");
        addBlock(ModBlocks.AZALEA_TRAPDOOR, "杜鹃木活板门");
        addBlock(ModBlocks.AZALEA_PRESSURE_PLATE, "杜鹃木压力板");
        addBlock(ModBlocks.AZALEA_BUTTON, "杜鹃木按钮");

        // 其他方块
        addBlock(ModBlocks.FLETCHING_TABLE, "制箭台");
        addBlock(ModBlocks.ROSE, "玫瑰");
        addBlock(ModBlocks.SOUL_ROSE, "灵魂玫瑰");
        addBlock(ModBlocks.SOUL_ROSE_BUSH, "灵魂玫瑰丛");

        // GUI
        add("container.fletching_table", "制作箭");
        add("tab.ww_core.name", "原野之风");

        // 幼蛛
        addItem(ModItems.SPIDERLING_SPAWN_EGG, "幼蛛刷怪蛋");
        addEntityType(ModEntities.SPIDERLING, "幼蛛");

        // 幼蛛音效字幕
        add("subtitles.entity.spiderling.ambient", "幼蛛：嘶嘶");
        add("subtitles.entity.spiderling.hurt", "幼蛛：受伤");
        add("subtitles.entity.spiderling.death", "幼蛛：死亡");
    }
}
