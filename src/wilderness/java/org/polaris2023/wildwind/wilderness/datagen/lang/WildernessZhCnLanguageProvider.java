package org.polaris2023.wildwind.wilderness.datagen.lang;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.block.WildernessBlocks;
import org.polaris2023.wildwind.wilderness.registry.ModEntities;
import org.polaris2023.wildwind.wilderness.registry.ModItems;

/**
 * 荒野更新的简体中文语言文件。
 */
public class WildernessZhCnLanguageProvider extends LanguageProvider {

    public WildernessZhCnLanguageProvider(PackOutput output) {
        super(output, WildWindWilderness.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        addBlock(WildernessBlocks.SOUL_JACK_O_LANTERN, "§4灵魂南瓜灯");
        addBlock(WildernessBlocks.SILT, "泥沼块");

        // 创造栏标题
        add("tab.ww_wilderness.name", "荒野更新");

        addItem(ModItems.MUDCRAB_SPAWN_EGG, "泥沼蟹刷怪蛋");
        addItem(ModItems.MUDCRAB_BUCKET, "泥沼蟹桶");
        addItem(ModItems.CRAB_CLAW, "蟹钳");
        addItem(ModItems.OMNI_CLAW, "万用蟹钳");
        addItem(ModItems.PIRANHA_SPAWN_EGG, "恐鱼刷怪蛋");
        addItem(ModItems.PIRANHA, "生恐鱼");
        addItem(ModItems.COOKED_PIRANHA, "熟恐鱼");
        addItem(ModItems.PIRANHA_BUCKET, "恐鱼桶");
        addItem(ModItems.FANG, "尖牙");
        addItem(ModItems.SPIDER_EGG, "蜘蛛卵");

        addEntityType(ModEntities.MUDCRAB, "泥沼蟹");
        addEntityType(ModEntities.PIRANHA, "恐鱼");

        // 属性
        add("attribute.name.ww_wilderness.extra_item_pickup_range", "额外物品拾取距离");

        // 延展（reach）效果与药水
        add("effect.ww_wilderness.reach", "延展");
        add("item.minecraft.potion.effect.reach", "延展药水");
        add("item.minecraft.splash_potion.effect.reach", "喷溅型延展药水");
        add("item.minecraft.lingering_potion.effect.reach", "滞留型延展药水");
        add("item.minecraft.tipped_arrow.effect.reach", "延展之箭");
        add("item.minecraft.potion.effect.long_reach", "延展药水");
        add("item.minecraft.splash_potion.effect.long_reach", "喷溅型延展药水");
        add("item.minecraft.lingering_potion.effect.long_reach", "滞留型延展药水");
        add("item.minecraft.tipped_arrow.effect.long_reach", "延展之箭");

        // 恐鱼音效字幕
        add("subtitles.entity.piranha.attack", "恐鱼：袭击");
        add("subtitles.entity.piranha.death", "恐鱼：死亡");
        add("subtitles.entity.piranha.flop", "恐鱼：拍打");
    }
}
