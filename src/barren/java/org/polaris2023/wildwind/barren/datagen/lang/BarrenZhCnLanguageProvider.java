package org.polaris2023.wildwind.barren.datagen.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris2023.wildwind.barren.WildWindBarren;
import org.polaris2023.wildwind.barren.block.BarrenBlocks;
import org.polaris2023.wildwind.barren.registry.ModItems;

/**
 * 瘠土更新的简体中文语言文件。
 */
public class BarrenZhCnLanguageProvider extends LanguageProvider {

    public BarrenZhCnLanguageProvider(PackOutput output) {
        super(output, WildWindBarren.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        // 创造栏标题
        add("tab.ww_barren.name", "瘠土更新");

        // 焚烬木套件
        addBlock(BarrenBlocks.BLAZE_LOG, "焚烬木原木");
        addBlock(BarrenBlocks.BLAZE_WOOD, "焚烬木");
        addBlock(BarrenBlocks.STRIPPED_BLAZE_LOG, "去皮焚烬木原木");
        addBlock(BarrenBlocks.STRIPPED_BLAZE_WOOD, "去皮焚烬木");
        addBlock(BarrenBlocks.BLAZE_PLANKS, "焚烬木木板");
        addBlock(BarrenBlocks.BLAZE_STAIRS, "焚烬木楼梯");
        addBlock(BarrenBlocks.BLAZE_SLAB, "焚烬木台阶");
        addBlock(BarrenBlocks.BLAZE_FENCE, "焚烬木栅栏");
        addBlock(BarrenBlocks.BLAZE_FENCE_GATE, "焚烬木栅栏门");
        addBlock(BarrenBlocks.BLAZE_DOOR, "焚烬木门");
        addBlock(BarrenBlocks.BLAZE_TRAPDOOR, "焚烬木活板门");
        addBlock(BarrenBlocks.BLAZE_PRESSURE_PLATE, "焚烬木压力板");
        addBlock(BarrenBlocks.BLAZE_BUTTON, "焚烬木按钮");
        addBlock(BarrenBlocks.BLAZE_LEAVES, "焚烬木树叶");
        addBlock(BarrenBlocks.BLAZE_SAPLING, "焚烬木树苗");
        addBlock(BarrenBlocks.POTTED_BLAZE_SAPLING, "焚烬木树苗盆栽");

        // 灵焰木套件
        addBlock(BarrenBlocks.SOUL_LOG, "灵焰木原木");
        addBlock(BarrenBlocks.SOUL_WOOD, "灵焰木");
        addBlock(BarrenBlocks.STRIPPED_SOUL_LOG, "去皮灵焰木原木");
        addBlock(BarrenBlocks.STRIPPED_SOUL_WOOD, "去皮灵焰木");
        addBlock(BarrenBlocks.SOUL_PLANKS, "灵焰木木板");
        addBlock(BarrenBlocks.SOUL_STAIRS, "灵焰木楼梯");
        addBlock(BarrenBlocks.SOUL_SLAB, "灵焰木台阶");
        addBlock(BarrenBlocks.SOUL_FENCE, "灵焰木栅栏");
        addBlock(BarrenBlocks.SOUL_FENCE_GATE, "灵焰木栅栏门");
        addBlock(BarrenBlocks.SOUL_DOOR, "灵焰木门");
        addBlock(BarrenBlocks.SOUL_TRAPDOOR, "灵焰木活板门");
        addBlock(BarrenBlocks.SOUL_PRESSURE_PLATE, "灵焰木压力板");
        addBlock(BarrenBlocks.SOUL_BUTTON, "灵焰木按钮");
        addBlock(BarrenBlocks.SOUL_LEAVES, "灵焰木树叶");
        addBlock(BarrenBlocks.SOUL_SAPLING, "灵焰木树苗");
        addBlock(BarrenBlocks.POTTED_SOUL_SAPLING, "灵焰木树苗盆栽");

        // 焦土 / 瘠地
        addBlock(BarrenBlocks.SCORCHED_GRASS_BLOCK, "焦灰草方块");
        addBlock(BarrenBlocks.SCORCHED_DIRT, "焦土");
        addBlock(BarrenBlocks.SCORCHED_GRASS, "焦灰草丛");
        addBlock(BarrenBlocks.SCORCHED_TWIG, "焦灰枝条");
        addBlock(BarrenBlocks.TINY_CACTUS, "仙人球");
        addBlock(BarrenBlocks.QUICKSAND, "§4流沙");
        addBlock(BarrenBlocks.RED_QUICKSAND, "§4红流沙");
        addBlock(BarrenBlocks.SUSPICIOUS_RED, "§4可疑的红沙");
        addBlock(BarrenBlocks.ASH_BLOCK, "灰烬块");

        // 砂岩 / 沙
        addBlock(BarrenBlocks.CHISELED_QUARTZ_SANDSTONE, "雕纹石英砂岩");
        addBlock(BarrenBlocks.CHISELED_SOUL_SANDSTONE, "雕纹灵魂砂岩");
        addBlock(BarrenBlocks.CUT_QUARTZ_SANDSTONE, "切割石英砂岩");
        addBlock(BarrenBlocks.CUT_SOUL_SANDSTONE, "切割灵魂砂岩");
        addBlock(BarrenBlocks.NITRIFIED_QUARTZ_SANDSTONE, "硝化石英砂岩");
        addBlock(BarrenBlocks.NITRIFIED_RED_SANDSTONE, "硝化红砂岩");
        addBlock(BarrenBlocks.NITRIFIED_SANDSTONE, "硝化砂岩");
        addBlock(BarrenBlocks.NITRIFIED_SOUL_SANDSTONE, "硝化灵魂砂岩");
        addBlock(BarrenBlocks.QUARTZ_SANDSTONE, "石英砂岩");
        addBlock(BarrenBlocks.SOUL_SANDSTONE, "灵魂砂岩");
        addBlock(BarrenBlocks.SMOOTH_QUARTZ_SANDSTONE, "平滑石英砂岩");
        addBlock(BarrenBlocks.SMOOTH_SOUL_SANDSTONE, "平滑灵魂砂岩");
        addBlock(BarrenBlocks.QUARTZ_SAND, "石英沙");
        addBlock(BarrenBlocks.QUARTZ_QUICKSAND, "石英流沙");
        addBlock(BarrenBlocks.SOUL_QUICKSAND, "灵魂流沙");
        addBlock(BarrenBlocks.SUSPICIOUS_QUARTZ_SAND, "可疑的珍珠沙");
        addBlock(BarrenBlocks.SUSPICIOUS_SOUL_SAND, "可疑的灵魂沙");

        addItem(ModItems.ASH, "灰烬");
    }
}
