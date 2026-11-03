package org.polaris2023.wildwind.wilderness.tag;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;

/**
 * 荒野更新的物品标签。
 */
public interface ModItemTags {

    /** 泥沼蟹食物的物品标签 */
    TagKey<Item> MUDCRAB_FOOD = ItemTags.create(WildWindWilderness.id("mudcrab_food"));
}
