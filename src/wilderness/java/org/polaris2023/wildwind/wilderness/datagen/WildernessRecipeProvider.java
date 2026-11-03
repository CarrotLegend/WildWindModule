package org.polaris2023.wildwind.wilderness.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import org.polaris2023.wildwind.wilderness.WildWindWilderness;
import org.polaris2023.wildwind.wilderness.registry.ModItems;

import java.util.concurrent.CompletableFuture;

/**
 * 荒野更新的配方生成器。
 */
public class WildernessRecipeProvider extends RecipeProvider {

    protected WildernessRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        // 生恐鱼 -> 熟恐鱼（熔炉）
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

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new WildernessRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Wild Wind " + WildWindWilderness.MOD_ID + " Recipe Provider";
        }
    }
}
