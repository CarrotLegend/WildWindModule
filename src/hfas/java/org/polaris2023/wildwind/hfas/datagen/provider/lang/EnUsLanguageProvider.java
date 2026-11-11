package org.polaris2023.wildwind.hfas.datagen.provider.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris2023.wildwind.hfas.HFASMod;
import org.polaris2023.wildwind.hfas.block.ModBlocks;

/**
 * 英语语言文件生成器
 *
 * @author baka4n
 * @since 2026/04/15
 */
public class EnUsLanguageProvider extends LanguageProvider {

    public EnUsLanguageProvider(PackOutput output) {
        super(output, HFASMod.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // 焚烬木套件
        addBlock(ModBlocks.BLAZE_LOG, "Blaze Log");
        addBlock(ModBlocks.BLAZE_WOOD, "Blaze Wood");
        addBlock(ModBlocks.STRIPPED_BLAZE_LOG, "Stripped Blaze Log");
        addBlock(ModBlocks.STRIPPED_BLAZE_WOOD, "Stripped Blaze Wood");
        addBlock(ModBlocks.BLAZE_PLANKS, "Blaze Planks");
        addBlock(ModBlocks.BLAZE_STAIRS, "Blaze Stairs");
        addBlock(ModBlocks.BLAZE_SLAB, "Blaze Slab");
        addBlock(ModBlocks.BLAZE_FENCE, "Blaze Fence");
        addBlock(ModBlocks.BLAZE_FENCE_GATE, "Blaze Fence Gate");
        addBlock(ModBlocks.BLAZE_DOOR, "Blaze Door");
        addBlock(ModBlocks.BLAZE_TRAPDOOR, "Blaze Trapdoor");
        addBlock(ModBlocks.BLAZE_PRESSURE_PLATE, "Blaze Pressure Plate");
        addBlock(ModBlocks.BLAZE_BUTTON, "Blaze Button");
        addBlock(ModBlocks.BLAZE_LEAVES, "Blaze Leaves");
        addBlock(ModBlocks.BLAZE_SAPLING, "Blaze Sapling");
        addBlock(ModBlocks.POTTED_BLAZE_SAPLING, "Potted Blaze Sapling");

        // 灵焰木套件
        addBlock(ModBlocks.SOUL_LOG, "Soul Log");
        addBlock(ModBlocks.SOUL_WOOD, "Soul Wood");
        addBlock(ModBlocks.STRIPPED_SOUL_LOG, "Stripped Soul Log");
        addBlock(ModBlocks.STRIPPED_SOUL_WOOD, "Stripped Soul Wood");
        addBlock(ModBlocks.SOUL_PLANKS, "Soul Planks");
        addBlock(ModBlocks.SOUL_STAIRS, "Soul Stairs");
        addBlock(ModBlocks.SOUL_SLAB, "Soul Slab");
        addBlock(ModBlocks.SOUL_FENCE, "Soul Fence");
        addBlock(ModBlocks.SOUL_FENCE_GATE, "Soul Fence Gate");
        addBlock(ModBlocks.SOUL_DOOR, "Soul Door");
        addBlock(ModBlocks.SOUL_TRAPDOOR, "Soul Trapdoor");
        addBlock(ModBlocks.SOUL_PRESSURE_PLATE, "Soul Pressure Plate");
        addBlock(ModBlocks.SOUL_BUTTON, "Soul Button");
        addBlock(ModBlocks.SOUL_LEAVES, "Soul Leaves");
        addBlock(ModBlocks.SOUL_SAPLING, "Soul Sapling");
        addBlock(ModBlocks.POTTED_SOUL_SAPLING, "Potted Soul Sapling");

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
        addBlock(ModBlocks.SCORCHED_GRASS_BLOCK, "Scorched Grass Block");
        addBlock(ModBlocks.SCORCHED_DIRT, "Scorched Dirt");
        addBlock(ModBlocks.SCORCHED_GRASS, "Scorched Grass");
        addBlock(ModBlocks.SCORCHED_TWIG, "Scorched Twig");
        addBlock(ModBlocks.TINY_CACTUS, "Tiny Cactus");
        addBlock(ModBlocks.FLETCHING_TABLE, "Fletching Table");
        addBlock(ModBlocks.QUICKSAND, "§4Quicksand");
        addBlock(ModBlocks.SUSPICIOUS_RED, "§4Suspicious Red Sand");
        addBlock(ModBlocks.SOUL_JACK_O_LANTERN, "§4Soul Jack o'Lantern");

        // 砂岩 / 沙 / 花套件
        addBlock(ModBlocks.CHISELED_QUARTZ_SANDSTONE, "Chiseled Quartz Sandstone");
        addBlock(ModBlocks.CHISELED_SOUL_SANDSTONE, "Chiseled Soul Sandstone");
        addBlock(ModBlocks.CUT_QUARTZ_SANDSTONE, "Cut Quartz Sandstone");
        addBlock(ModBlocks.CUT_SOUL_SANDSTONE, "Cut Soul Sandstone");
        addBlock(ModBlocks.NITRIFIED_QUARTZ_SANDSTONE, "Nitrified Quartz Sandstone");
        addBlock(ModBlocks.NITRIFIED_RED_SANDSTONE, "Nitrified Red Sandstone");
        addBlock(ModBlocks.NITRIFIED_SANDSTONE, "Nitrified Sandstone");
        addBlock(ModBlocks.NITRIFIED_SOUL_SANDSTONE, "Nitrified Soul Sandstone");
        addBlock(ModBlocks.QUARTZ_SANDSTONE, "Quartz Sandstone");
        addBlock(ModBlocks.SOUL_SANDSTONE, "Soul Sandstone");
        addBlock(ModBlocks.SMOOTH_QUARTZ_SANDSTONE, "Smooth Quartz Sandstone");
        addBlock(ModBlocks.SMOOTH_SOUL_SANDSTONE, "Smooth Soul Sandstone");
        addBlock(ModBlocks.QUARTZ_SANDSTONE_STAIRS, "Quartz Sandstone Stairs");
        addBlock(ModBlocks.QUARTZ_SANDSTONE_SLAB, "Quartz Sandstone Slab");
        addBlock(ModBlocks.QUARTZ_SANDSTONE_WALL, "Quartz Sandstone Wall");
        addBlock(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_STAIRS, "Smooth Quartz Sandstone Stairs");
        addBlock(ModBlocks.SMOOTH_QUARTZ_SANDSTONE_SLAB, "Smooth Quartz Sandstone Slab");
        addBlock(ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB, "Cut Quartz Sandstone Slab");
        addBlock(ModBlocks.SOUL_SANDSTONE_STAIRS, "Soul Sandstone Stairs");
        addBlock(ModBlocks.SOUL_SANDSTONE_SLAB, "Soul Sandstone Slab");
        addBlock(ModBlocks.SOUL_SANDSTONE_WALL, "Soul Sandstone Wall");
        addBlock(ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS, "Smooth Soul Sandstone Stairs");
        addBlock(ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB, "Smooth Soul Sandstone Slab");
        addBlock(ModBlocks.CUT_SOUL_SANDSTONE_SLAB, "Cut Soul Sandstone Slab");
        addBlock(ModBlocks.QUARTZ_SAND, "Quartz Sand");
        addBlock(ModBlocks.QUARTZ_QUICKSAND, "Quartz Quicksand");
        addBlock(ModBlocks.SOUL_QUICKSAND, "Soul Quicksand");
        addBlock(ModBlocks.ROSE, "Rose");
        addBlock(ModBlocks.SOUL_ROSE, "Soul Rose");
        addBlock(ModBlocks.SOUL_ROSE_BUSH, "Soul Rose Bush");
        addBlock(ModBlocks.SUSPICIOUS_QUARTZ_SAND, "Suspicious Quartz Sand");
        addBlock(ModBlocks.SUSPICIOUS_SOUL_SAND, "Suspicious Soul Sand");

        // GUI
        add("container.fletching_table", "Fletching Table");
        // 属性
        add("attribute.name.ww_hfas.extra_item_pickup_range", "Extra Item Pickup Range");

        // Reach effect & potions
        add("effect.ww_hfas.reach", "Reach");
        add("item.minecraft.potion.effect.reach", "Potion of Reach");
        add("item.minecraft.splash_potion.effect.reach", "Splash Potion of Reach");
        add("item.minecraft.lingering_potion.effect.reach", "Lingering Potion of Reach");
        add("item.minecraft.tipped_arrow.effect.reach", "Arrow of Reach");
        add("item.minecraft.potion.effect.long_reach", "Potion of Reach");
        add("item.minecraft.splash_potion.effect.long_reach", "Splash Potion of Reach");
        add("item.minecraft.lingering_potion.effect.long_reach", "Lingering Potion of Reach");
        add("item.minecraft.tipped_arrow.effect.long_reach", "Arrow of Reach");

        // Piranha sound subtitles
        add("subtitles.entity.piranha.attack", "Piranha attacks");
        add("subtitles.entity.piranha.death", "Piranha dies");
        add("subtitles.entity.piranha.flop", "Piranha flops");

        // Spiderling sound subtitles
        add("subtitles.entity.spiderling.ambient", "Spiderling hisses");
        add("subtitles.entity.spiderling.hurt", "Spiderling hurts");
        add("subtitles.entity.spiderling.death", "Spiderling dies");

        //创造模式物品栏
        add("mod.wild_wind.name", "Wild Wind");

        ZhEnCombineLangProvider.addLang(this, "en_us");
    }
}
