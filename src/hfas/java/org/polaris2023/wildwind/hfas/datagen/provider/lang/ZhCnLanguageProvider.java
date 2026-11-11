package org.polaris2023.wildwind.hfas.datagen.provider.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris2023.wildwind.hfas.HFASMod;
import org.polaris2023.wildwind.hfas.block.ModBlocks;
import org.polaris2023.wildwind.hfas.registry.ModItems;

/**
 * 中文语言文件生成器
 *
 * @author baka4n
 * @since 2026/04/15
 */
public class ZhCnLanguageProvider extends LanguageProvider {

    public ZhCnLanguageProvider(PackOutput output) {
        super(output, HFASMod.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        // 焚烬木套件
        addBlock(ModBlocks.BLAZE_LOG, "焚烬木原木");
        addBlock(ModBlocks.BLAZE_WOOD, "焚烬木");
        addBlock(ModBlocks.STRIPPED_BLAZE_LOG, "去皮焚烬木原木");
        addBlock(ModBlocks.STRIPPED_BLAZE_WOOD, "去皮焚烬木");
        addBlock(ModBlocks.BLAZE_PLANKS, "焚烬木木板");
        addBlock(ModBlocks.BLAZE_STAIRS, "焚烬木楼梯");
        addBlock(ModBlocks.BLAZE_SLAB, "焚烬木台阶");
        addBlock(ModBlocks.BLAZE_FENCE, "焚烬木栅栏");
        addBlock(ModBlocks.BLAZE_FENCE_GATE, "焚烬木栅栏门");
        addBlock(ModBlocks.BLAZE_DOOR, "焚烬木门");
        addBlock(ModBlocks.BLAZE_TRAPDOOR, "焚烬木活板门");
        addBlock(ModBlocks.BLAZE_PRESSURE_PLATE, "焚烬木压力板");
        addBlock(ModBlocks.BLAZE_BUTTON, "焚烬木按钮");
        addBlock(ModBlocks.BLAZE_LEAVES, "焚烬木树叶");
        addBlock(ModBlocks.BLAZE_SAPLING, "焚烬木树苗");
        addBlock(ModBlocks.POTTED_BLAZE_SAPLING, "焚烬木树苗盆栽");

        // 灵焰木套件
        addBlock(ModBlocks.SOUL_LOG, "灵焰木原木");
        addBlock(ModBlocks.SOUL_WOOD, "灵焰木");
        addBlock(ModBlocks.STRIPPED_SOUL_LOG, "去皮灵焰木原木");
        addBlock(ModBlocks.STRIPPED_SOUL_WOOD, "去皮灵焰木");
        addBlock(ModBlocks.SOUL_PLANKS, "灵焰木木板");
        addBlock(ModBlocks.SOUL_STAIRS, "灵焰木楼梯");
        addBlock(ModBlocks.SOUL_SLAB, "灵焰木台阶");
        addBlock(ModBlocks.SOUL_FENCE, "灵焰木栅栏");
        addBlock(ModBlocks.SOUL_FENCE_GATE, "灵焰木栅栏门");
        addBlock(ModBlocks.SOUL_DOOR, "灵焰木门");
        addBlock(ModBlocks.SOUL_TRAPDOOR, "灵焰木活板门");
        addBlock(ModBlocks.SOUL_PRESSURE_PLATE, "灵焰木压力板");
        addBlock(ModBlocks.SOUL_BUTTON, "灵焰木按钮");
        addBlock(ModBlocks.SOUL_LEAVES, "灵焰木树叶");
        addBlock(ModBlocks.SOUL_SAPLING, "灵焰木树苗");
        addBlock(ModBlocks.POTTED_SOUL_SAPLING, "灵焰木树苗盆栽");

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
        addBlock(ModBlocks.SCORCHED_GRASS_BLOCK, "焦灰草方块");
        addBlock(ModBlocks.SCORCHED_DIRT, "焦土");
        addBlock(ModBlocks.SCORCHED_GRASS, "焦灰草丛");
        addBlock(ModBlocks.SCORCHED_TWIG, "焦灰枝条");
        addBlock(ModBlocks.TINY_CACTUS, "仙人球");
        addBlock(ModBlocks.FLETCHING_TABLE, "制箭台");
        addBlock(ModBlocks.QUICKSAND, "§4流沙");
        addBlock(ModBlocks.SUSPICIOUS_RED, "§4可疑的红沙");
        addBlock(ModBlocks.SOUL_JACK_O_LANTERN, "§4灵魂南瓜灯");

        // 砂岩 / 沙 / 花套件
        addBlock(ModBlocks.CHISELED_QUARTZ_SANDSTONE, "雕纹石英砂岩");
        addBlock(ModBlocks.CHISELED_SOUL_SANDSTONE, "雕纹灵魂砂岩");
        addBlock(ModBlocks.CUT_QUARTZ_SANDSTONE, "切制石英砂岩");
        addBlock(ModBlocks.CUT_SOUL_SANDSTONE, "切制灵魂砂岩");
        addBlock(ModBlocks.NITRIFIED_QUARTZ_SANDSTONE, "硝化石英砂岩");
        addBlock(ModBlocks.NITRIFIED_RED_SANDSTONE, "硝化红砂岩");
        addBlock(ModBlocks.NITRIFIED_SANDSTONE, "硝化砂岩");
        addBlock(ModBlocks.NITRIFIED_SOUL_SANDSTONE, "硝化灵魂砂岩");
        addBlock(ModBlocks.QUARTZ_SANDSTONE, "石英砂岩");
        addBlock(ModBlocks.SOUL_SANDSTONE, "灵魂砂岩");
        addBlock(ModBlocks.SMOOTH_QUARTZ_SANDSTONE, "平滑石英砂岩");
        addBlock(ModBlocks.SMOOTH_SOUL_SANDSTONE, "平滑灵魂砂岩");
        addBlock(ModBlocks.QUARTZ_SANDSTONE_STAIRS, "石英砂岩楼梯");
        addBlock(ModBlocks.QUARTZ_SANDSTONE_SLAB, "石英砂岩台阶");
        addBlock(ModBlocks.QUARTZ_SANDSTONE_WALL, "石英砂岩墙");
        addBlock(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_STAIRS, "平滑石英砂岩楼梯");
        addBlock(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_SLAB, "平滑石英砂岩台阶");
        addBlock(ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB, "切制石英砂岩台阶");
        addBlock(ModBlocks.SOUL_SANDSTONE_STAIRS, "灵魂砂岩楼梯");
        addBlock(ModBlocks.SOUL_SANDSTONE_SLAB, "灵魂砂岩台阶");
        addBlock(ModBlocks.SOUL_SANDSTONE_WALL, "灵魂砂岩墙");
        addBlock(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS, "平滑灵魂砂岩楼梯");
        addBlock(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB, "平滑灵魂砂岩台阶");
        addBlock(ModBlocks.CUT_SOUL_SANDSTONE_SLAB, "切制灵魂砂岩台阶");
        addBlock(ModBlocks.QUARTZ_SAND, "石英沙子");
        addBlock(ModBlocks.QUARTZ_QUICKSAND, "石英流沙");
        addBlock(ModBlocks.SOUL_QUICKSAND, "灵魂流沙");
        addBlock(ModBlocks.ROSE, "玫瑰");
        addBlock(ModBlocks.SOUL_ROSE, "灵魂玫瑰");
        addBlock(ModBlocks.SOUL_ROSE_BUSH, "灵魂玫瑰丛");
        addBlock(ModBlocks.SUSPICIOUS_QUARTZ_SAND, "可疑的石英沙");
        addBlock(ModBlocks.SUSPICIOUS_SOUL_SAND, "可疑的灵魂沙");

        // GUI
        add("container.fletching_table", "制箭台");
        // 属性
        add("attribute.name.ww_hfas.extra_item_pickup_range", "额外物品拾取距离");

        // 延展（reach）效果与药水
        add("effect.ww_hfas.reach", "延展");
        add("item.minecraft.potion.effect.reach", "延展药水");
        add("item.minecraft.splash_potion.effect.reach", "喷溅型延展药水");
        add("item.minecraft.lingering_potion.effect.reach", "滞留型延展药水");
        add("item.minecraft.tipped_arrow.effect.reach", "延展之箭");
        add("item.minecraft.potion.effect.long_reach", "延展药水");
        add("item.minecraft.splash_potion.effect.long_reach", "喷溅型延展药水");
        add("item.minecraft.lingering_potion.effect.long_reach", "滞留型延展药水");
        add("item.minecraft.tipped_arrow.effect.long_reach", "延展之箭");

        // 食人鱼音效字幕
        add("subtitles.entity.piranha.attack", "食人鱼：袭击");
        add("subtitles.entity.piranha.death", "食人鱼：死亡");
        add("subtitles.entity.piranha.flop", "食人鱼：拍打");

        // 幼蛛音效字幕
        add("subtitles.entity.spiderling.ambient", "幼蛛：嘶嘶");
        add("subtitles.entity.spiderling.hurt", "幼蛛：受伤");
        add("subtitles.entity.spiderling.death", "幼蛛：死亡");

        //创造模式物品栏
        add("mod.wild_wind.name", "原野之风");

        ZhEnCombineLangProvider.addLang(this, "zh_cn");

    }


}
