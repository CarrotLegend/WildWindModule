package org.polaris2023.wildwind.wilderness.datagen.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.block.WildernessBlocks;
import org.polaris2023.wildwind.wilderness.registry.ModEntities;
import org.polaris2023.wildwind.wilderness.registry.ModItems;

/**
 * 荒野更新的英文语言文件。
 */
public class WildernessEnUsLanguageProvider extends LanguageProvider {

    public WildernessEnUsLanguageProvider(PackOutput output) {
        super(output, WildWindWilderness.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addBlock(WildernessBlocks.SOUL_JACK_O_LANTERN, "§4Soul Jack o'Lantern");
        addBlock(WildernessBlocks.SILT, "Silt");

        // 创造栏标题
        add("tab.ww_wilderness.name", "Wilderness Update");

        addItem(ModItems.MUDCRAB_SPAWN_EGG, "Mudcrab Spawn Egg");
        addItem(ModItems.MUDCRAB_BUCKET, "Bucket of Mudcrab");
        addItem(ModItems.CRAB_CLAW, "Crab Claw");
        addItem(ModItems.OMNI_CLAW, "Omni Claw");
        addItem(ModItems.PIRANHA_SPAWN_EGG, "Direfish Spawn Egg");
        addItem(ModItems.PIRANHA, "Raw Direfish");
        addItem(ModItems.COOKED_PIRANHA, "Cooked Direfish");
        addItem(ModItems.PIRANHA_BUCKET, "Bucket of Direfish");
        addItem(ModItems.FANG, "Fang");
        addItem(ModItems.SPIDER_EGG, "Spider Egg");

        addEntityType(ModEntities.MUDCRAB, "Mudcrab");
        addEntityType(ModEntities.PIRANHA, "Direfish");

        // 属性
        add("attribute.name.ww_wilderness.extra_item_pickup_range", "Extra Item Pickup Range");

        // 延展（reach）效果与药水
        add("effect.ww_wilderness.reach", "Reach");
        add("item.minecraft.potion.effect.reach", "Potion of Reach");
        add("item.minecraft.splash_potion.effect.reach", "Splash Potion of Reach");
        add("item.minecraft.lingering_potion.effect.reach", "Lingering Potion of Reach");
        add("item.minecraft.tipped_arrow.effect.reach", "Arrow of Reach");
        add("item.minecraft.potion.effect.long_reach", "Potion of Reach");
        add("item.minecraft.splash_potion.effect.long_reach", "Splash Potion of Reach");
        add("item.minecraft.lingering_potion.effect.long_reach", "Lingering Potion of Reach");
        add("item.minecraft.tipped_arrow.effect.long_reach", "Arrow of Reach");

        // 恐鱼音效字幕
        add("subtitles.entity.piranha.attack", "Direfish attacks");
        add("subtitles.entity.piranha.death", "Direfish dies");
        add("subtitles.entity.piranha.flop", "Direfish flops");
    }
}
