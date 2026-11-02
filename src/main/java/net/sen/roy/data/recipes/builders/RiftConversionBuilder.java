package net.sen.roy.data.recipes.builders;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.sen.roy.RootsOfYggdrasil;
import net.sen.roy.common.recipes.RiftConversionRecipe;

public class RiftConversionBuilder {
    private final Ingredient input;
    private final ItemStack result;
    private int time = 200;

    private RiftConversionBuilder(Ingredient input, ItemStack result) {
        this.input = input;
        this.result = result;
    }

    public static RiftConversionBuilder convert(ItemLike input, ItemLike result) {
        return new RiftConversionBuilder(Ingredient.of(input), new ItemStack(result));
    }

    public static RiftConversionBuilder convert(TagKey<Item> input, ItemLike result) {
        return new RiftConversionBuilder(Ingredient.of(input), new ItemStack(result));
    }

    public RiftConversionBuilder count(int count) {
        result.setCount(count);
        return this;
    }

    public RiftConversionBuilder time(int ticks) {
        this.time = ticks;
        return this;
    }

    public void save(RecipeOutput output, String name) {
        output.accept(
                ResourceLocation.fromNamespaceAndPath(RootsOfYggdrasil.MODID, "rift_conversion/" + name),
                new RiftConversionRecipe(input, result, time),
                null
        );
    }

    public void save(RecipeOutput output) {
        save(output, BuiltInRegistries.ITEM.getKey(result.getItem()).getPath());
    }
}
