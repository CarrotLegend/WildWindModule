package org.polaris2023.wildwind.wilderness.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.registry.ModEntities;

import java.util.concurrent.CompletableFuture;

/**
 * 荒野更新的实体类型标签生成器。
 */
public class WildernessEntityTypeTagsProvider extends EntityTypeTagsProvider {

    public WildernessEntityTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, WildWindWilderness.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EntityTypeTags.AQUATIC)
                .add(ModEntities.MUDCRAB.getKey())
                .add(ModEntities.PIRANHA.getKey());
        tag(EntityTypeTags.CAN_BREATHE_UNDER_WATER)
                .add(ModEntities.MUDCRAB.getKey())
                .add(ModEntities.PIRANHA.getKey());
        tag(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH)
                .add(ModEntities.MUDCRAB.getKey())
                .add(ModEntities.PIRANHA.getKey());
        tag(EntityTypeTags.AXOLOTL_HUNT_TARGETS)
                .add(ModEntities.PIRANHA.getKey());
    }
}
