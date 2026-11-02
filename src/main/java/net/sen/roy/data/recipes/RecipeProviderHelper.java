package net.sen.roy.data.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public abstract class RecipeProviderHelper extends RecipeProvider {
    public RecipeProviderHelper(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        super.buildRecipes(recipeOutput);

        riftConversionRecipes(recipeOutput);
    }

    protected abstract void riftConversionRecipes(RecipeOutput recipeOutput);
}
