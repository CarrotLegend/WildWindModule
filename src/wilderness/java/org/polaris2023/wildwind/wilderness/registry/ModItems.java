package org.polaris2023.wildwind.wilderness.registry;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.component.OmniClawTools;
import org.polaris2023.wildwind.wilderness.item.ModFoods;
import org.polaris2023.wildwind.wilderness.item.OmniClawItem;

import java.util.function.Function;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WildWindWilderness.MOD_ID);

    //泥沼蟹刷怪蛋
    public static final DeferredItem<SpawnEggItem> MUDCRAB_SPAWN_EGG = registerMobEgg("mudcrab_spawn_egg",ModEntities.MUDCRAB);

    //泥沼蟹桶
    public static final DeferredItem<MobBucketItem> MUDCRAB_BUCKET = register("mudcrab_bucket",p -> new MobBucketItem(ModEntities.MUDCRAB.get(),Fluids.WATER,SoundEvents.BUCKET_EMPTY,p));

    //蟹钳
    public static final DeferredItem<Item> CRAB_CLAW = register("crab_claw");

    //食人鱼
    public static final DeferredItem<Item> PIRANHA = register("piranha", p -> new Item(p.food(ModFoods.PIRANHA, ModFoods.PIRANHA_CONSUMABLE)));
    public static final DeferredItem<Item> COOKED_PIRANHA = register("cooked_piranha", p -> new Item(p.food(ModFoods.COOKED_PIRANHA, ModFoods.COOKED_PIRANHA_CONSUMABLE)));
    public static final DeferredItem<SpawnEggItem> PIRANHA_SPAWN_EGG = registerMobEgg("piranha_spawn_egg", ModEntities.PIRANHA);

    //食人鱼桶
    public static final DeferredItem<MobBucketItem> PIRANHA_BUCKET = register("piranha_bucket", p -> new MobBucketItem(ModEntities.PIRANHA.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, p));

    //尖牙（食人鱼掉落物）
    public static final DeferredItem<Item> FANG = register("fang");


    //万用蟹钳
    public static final DeferredItem<OmniClawItem> OMNI_CLAW =
            register("omni_claw", p -> new OmniClawItem(
                    p.stacksTo(1)
                            .attributes(OmniClawItem.createAttributes())
                            .component(ModDataComponents.OMNI_CLAW_TOOLS.get(), OmniClawTools.EMPTY)
            ));

    public static void register(IEventBus modBus) {
        ITEMS.register(modBus);
    }


    public static DeferredItem<SpawnEggItem> registerMobEgg(String id, Holder<EntityType<?>> type){
        return ITEMS.registerItem(id, p -> new SpawnEggItem(p.spawnEgg(type.value())));
    }

    public static DeferredItem<Item> register(String id){
        return ITEMS.registerItem(id, Item::new);
    }

    public static <T extends Item> DeferredItem<T> register(String id, Function<Item.Properties,T> builder){
        return ITEMS.registerItem(id, builder);
    }

    public static <T extends Item> DeferredItem<T> register(String id, Function<Item.Properties,T> builder, Function<Item.Properties,Item.Properties> property){
        return ITEMS.registerItem(id, p -> builder.apply(property.apply(p)));
    }
}
