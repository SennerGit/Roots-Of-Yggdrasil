package net.sen.roy.data.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.sen.roy.data.recipes.builders.RiftConversionBuilder;

import java.util.concurrent.CompletableFuture;

public class RootsOfYggdrasilRecipeProvider extends RecipeProviderHelper {
    public RootsOfYggdrasilRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void riftConversionRecipes(RecipeOutput recipeOutput) {
        RiftConversionBuilder.convert(ItemTags.SAPLINGS, Items.STICK).time(200).save(recipeOutput, "any_sapling");
    }
}
