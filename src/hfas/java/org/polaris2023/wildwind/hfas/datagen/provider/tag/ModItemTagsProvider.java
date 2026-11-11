package org.polaris2023.wildwind.hfas.datagen.provider.tag;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.polaris2023.wildwind.hfas.HFASMod;
import org.polaris2023.wildwind.hfas.block.ModBlocks;
import org.polaris2023.wildwind.hfas.block.ModWoodFamilies;
import org.polaris2023.wildwind.hfas.block.WoodFamily;
import org.polaris2023.wildwind.hfas.registry.ModItems;
import org.polaris2023.wildwind.hfas.tag.ModItemTags;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * 物品标签生成器
 * 为木质物品添加原版和模组标签
 * <p>
 * 迁移至26.2时旧有的IntrinsicHolderTagsProvider已被移除。改为父类TagsProvider并由addToTag方法桥接。
 * TODO_FINISHED: 部分item tag key存在缺失问题，已模仿原版行为进行处理，但无法确定这种缺失是否系系统性删除，无法确保对应功能一定可以正常工作。请求测试阶段重点检查。
 *  见[Need Check]注释部分。
 *  ——landis, 2026/9/1
 *
 * @author baka4n
 * @since 2026/04/15
 */
public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, HFASMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // ==================== 三种木材物品标签 ====================
        for (WoodFamily family : ModWoodFamilies.ALL) {
            registerWoodLogItemTag(family);
            registerWoodItemTags(family);
        }

        tag(Tags.Items.ANIMAL_FOODS)
                .addTag(ModItemTags.MUDCRAB_FOOD);
        addToTag(ModItemTags.MUDCRAB_FOOD, Items.SPIDER_EYE);

        // ==================== 食人鱼相关物品标签 ====================
        // 猫、豹猫以生食人鱼为食
        addToTag(ItemTags.CAT_FOOD, ModItems.PIRANHA.get());
        addToTag(ItemTags.OCELOT_FOOD, ModItems.PIRANHA.get());
        // 狼以生/熟食人鱼为食
        addToTag(ItemTags.WOLF_FOOD, ModItems.PIRANHA.get(), ModItems.COOKED_PIRANHA.get());
        // 原版鱼类标签
        addToTag(ItemTags.FISHES, ModItems.PIRANHA.get(), ModItems.COOKED_PIRANHA.get());
        // 熟鱼食物标签
        addToTag(Tags.Items.FOODS_COOKED_FISH, ModItems.COOKED_PIRANHA.get());

        addToTag(BlockItemTags.STAIRS.item(),
                ModBlocks.QUARTZ_SANDSTONE_STAIRS.get(),
                ModBlocks.SMOOTH_QUARTZ_SANDSTONE_STAIRS.get(),
                ModBlocks.SOUL_SANDSTONE_STAIRS.get(),
                ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get());
        addToTag(BlockItemTags.SLABS.item(),
                ModBlocks.QUARTZ_SANDSTONE_SLAB.get(),
                ModBlocks.SMOOTH_QUARTZ_SANDSTONE_SLAB.get(),
                ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB.get(),
                ModBlocks.SOUL_SANDSTONE_SLAB.get(),
                ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(),
                ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get());
        addToTag(BlockItemTags.WALLS.item(),
                ModBlocks.QUARTZ_SANDSTONE_WALL.get(),
                ModBlocks.SOUL_SANDSTONE_WALL.get());
    }

    /**
     * 注册一种木材的物品标签
     */
    private void registerWoodItemTags(WoodFamily family) {
        Block log = family.log().get();
        Block wood = family.wood().get();
        Block strippedLog = family.strippedLog().get();
        Block strippedWood = family.strippedWood().get();
        Block planks = family.planks().get();
        Block stairs = family.stairs().get();
        Block slab = family.slab().get();
        Block fence = family.fence().get();
        Block fenceGate = family.fenceGate().get();
        Block door = family.door().get();
        Block trapdoor = family.trapdoor().get();
        Block pressurePlate = family.pressurePlate().get();
        Block button = family.button().get();
        Block leaves = family.leaves() == null ? null : family.leaves().get();
        Block sapling = family.sapling() == null ? null : family.sapling().get();

        // 原木物品标签
        addToTag(ItemTags.LOGS, log, wood, strippedLog, strippedWood);
        addToTag(ItemTags.LOGS_THAT_BURN, log, wood, strippedLog, strippedWood);
        addToTag(Tags.Items.STRIPPED_LOGS, strippedLog);
        addToTag(Tags.Items.STRIPPED_WOODS, strippedWood);
//        tag(ItemTags.LOGS)
//                .add(log.asItem())
//                .add(wood.asItem())
//                .add(strippedLog.asItem())
//                .add(strippedWood.asItem());

        // 木板物品标签
        addToTag(ItemTags.PLANKS, planks);

        // 木质楼梯物品标签
        addToTag(ItemTags.WOODEN_STAIRS,stairs);

        // 木质台阶物品标签
        addToTag(ItemTags.WOODEN_SLABS, slab);

        // 木质栅栏物品标签
        addToTag(ItemTags.WOODEN_FENCES, fence);
        addToTag(Tags.Items.FENCES_WOODEN, fence);

        // 栅栏物品标签
        // 栅栏tag默认包含"#c:fences/wooden"，弃用
//        tag(Tags.Items.FENCES)
//                .add(fence.asItem());

        // 木质栅栏门物品标签
        addToTag(Tags.Items.FENCE_GATES_WOODEN, fenceGate);

        // 栅栏门物品标签
        addToTag(ItemTags.FENCE_GATES, fenceGate);

        // 木质门物品标签
        addToTag(ItemTags.WOODEN_DOORS,door);

        // 门物品标签
        // bugjump不知道给这玩意更新到哪去了 item没key 难绷
        // [NeedCheck]
        addToTag(BlockItemTags.DOORS.item(), door);

        // 活板门物品标签
        // 续上：我要点名某些明明有包含关系但是就是不引用子tag还要把key删掉的tag
        // [NeedCheck]
        addToTag(BlockItemTags.TRAPDOORS.item(), trapdoor);

        // 木质活板门物品标签
        addToTag(ItemTags.WOODEN_TRAPDOORS, trapdoor);

        // 木质压力板物品标签
        addToTag(ItemTags.WOODEN_PRESSURE_PLATES, pressurePlate);

        // 木质按钮物品标签
        addToTag(ItemTags.WOODEN_BUTTONS, button);

        // 按钮物品标签
        // 不是哥们 你怎么也没了我朝为
        // [NeedCheck]
        addToTag(BlockItemTags.BUTTONS.item(), button);

        // 树叶物品标签
        if (leaves != null) {
            addToTag(ItemTags.LEAVES, leaves);
        }

        // 树苗物品标签
        if (sapling != null) {
            addToTag(ItemTags.SAPLINGS, sapling);
        }
    }

    private void registerWoodLogItemTag(WoodFamily family) {
        TagKey<Item> logsTag = TagKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(HFASMod.MOD_ID, family.name() + "_logs")
        );
        addToTag(logsTag,
                family.log().get(), family.wood().get(),
                family.strippedLog().get(), family.strippedWood().get());
    }

    @SuppressWarnings("all")
    public void addToTag(TagKey<Item> tag, Block... items){
        if(items.length == 0) return;
        tag(tag).addAll(Arrays.stream(items).map(Block::asItem).map(Item::builtInRegistryHolder).map(Holder.Reference::getKey));
    }

    @SuppressWarnings("all")
    public void addToTag(TagKey<Item> tag, Item... items){
        if(items.length == 0) return;
        tag(tag).addAll(Arrays.stream(items).map(Item::builtInRegistryHolder).map(Holder.Reference::getKey));
    }
}
