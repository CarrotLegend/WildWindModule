package org.polaris2023.wildwind.wilderness.entity.animal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.TemperatureVariants;
import net.minecraft.world.entity.variant.*;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.registry.DatadrivenRegistryKey;
import org.polaris2023.wildwind.wilderness.tag.ModBiomeTags;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 泥沼蟹变种数据喵~
 */
public record MudcrabVariant(ClientAsset.ResourceTexture assetInfo,
                             SpawnPrioritySelectors spawnConditions) implements PriorityProvider<SpawnContext, SpawnCondition> {
    public static final Codec<MudcrabVariant> DIRECT_CODEC = RecordCodecBuilder.create(
            i -> i.group(
                            ClientAsset.ResourceTexture.DEFAULT_FIELD_CODEC.forGetter(MudcrabVariant::assetInfo),
                            SpawnPrioritySelectors.CODEC.fieldOf("spawn_conditions").forGetter(MudcrabVariant::spawnConditions)
                    )
                    .apply(i, MudcrabVariant::new)
    );
    public static final Codec<MudcrabVariant> NETWORK_CODEC = RecordCodecBuilder.create(
            i -> i.group(ClientAsset.ResourceTexture.DEFAULT_FIELD_CODEC.forGetter(MudcrabVariant::assetInfo)).apply(i, info -> new MudcrabVariant(info, SpawnPrioritySelectors.EMPTY))
    );
    public static final Codec<Holder<MudcrabVariant>> CODEC = RegistryFixedCodec.create(DatadrivenRegistryKey.MUDCRAB_VARIANT);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<MudcrabVariant>> STREAM_CODEC = ByteBufCodecs.holderRegistry(DatadrivenRegistryKey.MUDCRAB_VARIANT);

    @Override
    public List<Selector<SpawnContext, SpawnCondition>> selectors() {
        return spawnConditions.selectors();
    }


    public static final ResourceKey<MudcrabVariant> TEMPERATE = createKey(TemperatureVariants.TEMPERATE);
    public static final ResourceKey<MudcrabVariant> WARM = createKey(TemperatureVariants.WARM);
    public static final ResourceKey<MudcrabVariant> COLD = createKey(TemperatureVariants.COLD);

    private static ResourceKey<MudcrabVariant> createKey(Identifier id) {
        return ResourceKey.create(DatadrivenRegistryKey.MUDCRAB_VARIANT, id);
    }

    public static void bootstrapDatagen(BootstrapContext<MudcrabVariant> registry) {
        registry.register(TEMPERATE, new MudcrabVariant(new ClientAsset.ResourceTexture(WildWindWilderness.id("entity/mudcrab/temperate")), SpawnPrioritySelectors.fallback(0)));
        registry.register(WARM, new MudcrabVariant(new ClientAsset.ResourceTexture(WildWindWilderness.id("entity/mudcrab/warm")), SpawnPrioritySelectors.single(new BiomeCheck(registry.lookup(Registries.BIOME).getOrThrow(ModBiomeTags.EntityGen.MUDCRABS_WARM)), 1)));
        registry.register(COLD, new MudcrabVariant(new ClientAsset.ResourceTexture(WildWindWilderness.id("entity/mudcrab/cold")), SpawnPrioritySelectors.single(new BiomeCheck(registry.lookup(Registries.BIOME).getOrThrow(ModBiomeTags.EntityGen.MUDCRABS_COLD)), 1)));
    }

    public static Holder<MudcrabVariant> lookup(RegistryAccess access, ResourceKey<MudcrabVariant> key){
        return access.getOrThrow(key);
    }

}
