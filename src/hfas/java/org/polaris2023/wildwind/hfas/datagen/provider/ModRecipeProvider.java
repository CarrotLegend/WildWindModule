package org.polaris2023.wildwind.hfas.datagen.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.polaris2023.wildwind.hfas.HFASMod;
import org.polaris2023.wildwind.hfas.arrow.ArrowFletching;
import org.polaris2023.wildwind.hfas.arrow.ArrowHead;
import org.polaris2023.wildwind.hfas.arrow.ArrowShaft;
import org.polaris2023.wildwind.hfas.component.ArrowComponent;
import org.polaris2023.wildwind.hfas.block.ModBlocks;
import org.polaris2023.wildwind.hfas.registry.ModItems;

import java.util.concurrent.CompletableFuture;

/**
 * 箭矢配方数据生成器
 *
 * @author baka4n
 * @since 2026/04/15
 */
public class ModRecipeProvider extends RecipeProvider {


    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        // 生成所有箭矢组合配方
        generateArrowRecipes();
        generateSandstoneRecipes();
        generateSandstoneVariantRecipes(ModBlocks.QUARTZ_SANDSTONE.get(), ModBlocks.QUARTZ_SANDSTONE_STAIRS.get(),
                ModBlocks.QUARTZ_SANDSTONE_SLAB.get(), ModBlocks.QUARTZ_SANDSTONE_WALL.get(), ModBlocks.CHISELED_QUARTZ_SANDSTONE.get(),
                ModBlocks.SMOOTH_QUARTZ_SANDSTONE.get(), ModBlocks.SMOOTH_QUARTZ_SANDSTONE_STAIRS.get(), ModBlocks.SMOOTH_QUARTZ_SANDSTONE_SLAB.get(),
                ModBlocks.CUT_QUARTZ_SANDSTONE.get(), ModBlocks.CUT_QUARTZ_SANDSTONE_SLAB.get());
        generateSandstoneVariantRecipes(ModBlocks.SOUL_SANDSTONE.get(), ModBlocks.SOUL_SANDSTONE_STAIRS.get(),
                ModBlocks.SOUL_SANDSTONE_SLAB.get(), ModBlocks.SOUL_SANDSTONE_WALL.get(), ModBlocks.CHISELED_SOUL_SANDSTONE.get(),
                ModBlocks.SMOOTH_SOUL_SANDSTONE.get(), ModBlocks.SMOOTH_SOUL_SANDSTONE_STAIRS.get(), ModBlocks.SMOOTH_SOUL_SANDSTONE_SLAB.get(),
                ModBlocks.CUT_SOUL_SANDSTONE.get(), ModBlocks.CUT_SOUL_SANDSTONE_SLAB.get());
        // 生食人鱼 -> 熟食人鱼（熔炉）
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.PIRANHA.get()), RecipeCategory.FOOD, CookingBookCategory.FOOD, ModItems.COOKED_PIRANHA.get(), 0.35F, 200)
                .unlockedBy("has_" + ModItems.PIRANHA.getId().getPath(), this.has(ModItems.PIRANHA.get()))
                .save(this.output);
        // 万用蟹钳：铜锭x6 + 蟹钳x3
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.TOOLS, ModItems.OMNI_CLAW.get())
                .define('I', Items.COPPER_INGOT)
                .define('C', ModItems.CRAB_CLAW.get())
                .pattern("ICC")
                .pattern("IIC")
                .pattern("III")
                .unlockedBy("has_" + ModItems.CRAB_CLAW.getId().getPath(), this.has(ModItems.OMNI_CLAW.get()))
                .save(this.output);
    }

    private void generateSandstoneRecipes() {
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_SANDSTONE.get())
                .define('#', ModBlocks.QUARTZ_SAND.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_quartz_sand", this.has(ModBlocks.QUARTZ_SAND.get()))
                .save(this.output);
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOUL_SANDSTONE.get())
                .define('#', Items.SOUL_SAND)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_soul_sand", this.has(Items.SOUL_SAND))
                .save(this.output);
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_QUARTZ_SANDSTONE.get(), 4)
                .define('#', ModBlocks.QUARTZ_SANDSTONE.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_quartz_sandstone", this.has(ModBlocks.QUARTZ_SANDSTONE.get()))
                .save(this.output);
        ShapedRecipeBuilder.shaped(this.items, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SOUL_SANDSTONE.get(), 4)
                .define('#', ModBlocks.SOUL_SANDSTONE.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_soul_sandstone", this.has(ModBlocks.SOUL_SANDSTONE.get()))
                .save(this.output);
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.QUARTZ_SANDSTONE.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_QUARTZ_SANDSTONE.get(), 1)
                .unlockedBy("has_quartz_sandstone", this.has(ModBlocks.QUARTZ_SANDSTONE.get()))
                .save(this.output, HFASMod.MOD_ID + ":chiseled_quartz_sandstone_from_quartz_sandstone_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.QUARTZ_SANDSTONE.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_QUARTZ_SANDSTONE.get(), 1)
                .unlockedBy("has_quartz_sandstone", this.has(ModBlocks.QUARTZ_SANDSTONE.get()))
                .save(this.output, HFASMod.MOD_ID + ":cut_quartz_sandstone_from_quartz_sandstone_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.SOUL_SANDSTONE.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SOUL_SANDSTONE.get(), 1)
                .unlockedBy("has_soul_sandstone", this.has(ModBlocks.SOUL_SANDSTONE.get()))
                .save(this.output, HFASMod.MOD_ID + ":chiseled_soul_sandstone_from_soul_sandstone_stonecutting");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.SOUL_SANDSTONE.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.CUT_SOUL_SANDSTONE.get(), 1)
                .unlockedBy("has_soul_sandstone", this.has(ModBlocks.SOUL_SANDSTONE.get()))
                .save(this.output, HFASMod.MOD_ID + ":cut_soul_sandstone_from_soul_sandstone_stonecutting");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.QUARTZ_SANDSTONE.get()), RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, ModBlocks.SMOOTH_QUARTZ_SANDSTONE.get(), 0.1F, 200)
                .unlockedBy("has_quartz_sandstone", this.has(ModBlocks.QUARTZ_SANDSTONE.get()))
                .save(this.output);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.SOUL_SANDSTONE.get()), RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, ModBlocks.SMOOTH_SOUL_SANDSTONE.get(), 0.1F, 200)
                .unlockedBy("has_soul_sandstone", this.has(ModBlocks.SOUL_SANDSTONE.get()))
                .save(this.output);
    }

    private void generateSandstoneVariantRecipes(Block base, Block stairs, Block slab, Block wall, Block chiseled,
                                                  Block smooth, Block smoothStairs, Block smoothSlab, Block cut, Block cutSlab) {
        RecipeCategory building = RecipeCategory.BUILDING_BLOCKS;
        Ingredient baseIngredient = Ingredient.of(base);
        Ingredient smoothIngredient = Ingredient.of(smooth);
        saveSandstoneRecipe(stairBuilder(stairs, baseIngredient), base);
        saveSandstoneRecipe(slabBuilder(building, slab, baseIngredient), base);
        saveSandstoneRecipe(wallBuilder(RecipeCategory.DECORATIONS, wall, baseIngredient), base);
        saveSandstoneRecipe(stairBuilder(smoothStairs, smoothIngredient), smooth);
        saveSandstoneRecipe(slabBuilder(building, smoothSlab, smoothIngredient), smooth);
        saveSandstoneRecipe(slabBuilder(building, cutSlab, Ingredient.of(cut)), cut);
        saveSandstoneRecipe(chiseledBuilder(building, chiseled, Ingredient.of(slab)), slab);

        sandstoneStonecutting(building, base, stairs, 1);
        sandstoneStonecutting(building, base, slab, 2);
        sandstoneStonecutting(RecipeCategory.DECORATIONS, base, wall, 1);
        sandstoneStonecutting(building, base, cutSlab, 2);
        sandstoneStonecutting(building, cut, cutSlab, 2);
        sandstoneStonecutting(building, smooth, smoothStairs, 1);
        sandstoneStonecutting(building, smooth, smoothSlab, 2);
    }

    private void saveSandstoneRecipe(RecipeBuilder recipe, Block input) {
        recipe.unlockedBy(getHasName(input), this.has(input)).save(this.output);
    }

    private void sandstoneStonecutting(RecipeCategory category, Block input, Block result, int count) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), category, result, count)
                .unlockedBy(getHasName(input), this.has(input))
                .save(this.output, HFASMod.MOD_ID + ":" + getConversionRecipeName(result, input) + "_stonecutting");
    }

    private void generateArrowRecipes() {

    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Wild Wind " + HFASMod.MOD_ID + " Recipe Provider";
        }
    }


}
