package org.polaris2023.wildwind.wilderness.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;

/**
 * 荒野更新的群系标签。
 */
public interface ModBiomeTags {

    interface EntityGen {
        /** 会生成暖色变种泥沼蟹的群系标签 */
        TagKey<Biome> MUDCRABS_WARM = create("entity_spawn/mudcrab/warm");
        /** 会生成冷色变种泥沼蟹的群系标签 */
        TagKey<Biome> MUDCRABS_COLD = create("entity_spawn/mudcrab/cold");
        /** 会生成恐鱼的群系标签 */
        TagKey<Biome> PIRANHA_SPAWNS = create("entity_spawn/piranha");
    }

    static TagKey<Biome> create(String path) {
        return TagKey.create(Registries.BIOME, WildWindWilderness.id(path));
    }
}
