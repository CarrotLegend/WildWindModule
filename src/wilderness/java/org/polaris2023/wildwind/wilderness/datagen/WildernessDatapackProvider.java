package org.polaris2023.wildwind.wilderness.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.entity.animal.MudcrabVariant;
import org.polaris2023.wildwind.wilderness.registry.DatadrivenRegistryKey;
import org.polaris2023.wildwind.wilderness.worldgen.WildernessBiomeModifiers;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * 荒野更新的数据包内建注册：实体生成 + 泥沼蟹变种。
 */
public class WildernessDatapackProvider extends DatapackBuiltinEntriesProvider {

    public WildernessDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, new RegistrySetBuilder()
                        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, WildernessBiomeModifiers::bootstrap)
                        .add(DatadrivenRegistryKey.MUDCRAB_VARIANT, MudcrabVariant::bootstrapDatagen),
                Set.of(WildWindWilderness.MOD_ID, "minecraft"));
    }
}
