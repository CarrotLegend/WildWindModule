package org.polaris2023.wildwind.core.datagen.provider.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import org.polaris2023.wildwind.core.WildWindCore;
import org.polaris2023.wildwind.core.registry.ModEntities;

import java.util.concurrent.CompletableFuture;

/**
 * 核心模块的实体类型标签生成器。
 */
public class ModEntityTypeTagsProvider extends EntityTypeTagsProvider {

    public ModEntityTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, WildWindCore.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(EntityTypeTags.ARTHROPOD)
                .add(ModEntities.SPIDERLING.getKey());
        tag(EntityTypeTags.DISMOUNTS_UNDERWATER)
                .add(ModEntities.SPIDERLING.getKey());
        tag(EntityTypeTags.NO_ANGER_FROM_WIND_CHARGE)
                .add(ModEntities.SPIDERLING.getKey());
    }
}
