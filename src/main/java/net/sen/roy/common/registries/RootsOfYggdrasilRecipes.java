package net.sen.roy.common.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sen.roy.RootsOfYggdrasil;
import net.sen.roy.common.recipes.RiftConversionRecipe;

import java.util.function.Supplier;

public class RootsOfYggdrasilRecipes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, RootsOfYggdrasil.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, RootsOfYggdrasil.MODID);

    public static final RecipeEntry<RiftConversionRecipe> RIFT_CONVERSION_RECIPE = create("rift_conversion_recipe", RiftConversionRecipe.Serializer::new);

    public record RecipeEntry <R extends Recipe<?>> (DeferredHolder<RecipeType<?>, RecipeType<R>> type, DeferredHolder<RecipeSerializer<?>, RecipeSerializer<R>> serializer) {}

    private static <R extends Recipe<?>> RecipeEntry<R> create(String name, Supplier<? extends RecipeSerializer<R>> serializer) {
        var type = RECIPE_TYPES.register(name, () -> RecipeType.<R>simple(ResourceLocation.fromNamespaceAndPath(RootsOfYggdrasil.MODID, name)));
        var ser = RECIPE_SERIALIZER.<RecipeSerializer<R>>register(name, serializer);
        return new RecipeEntry<>(type, ser);
    }

    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
        RECIPE_SERIALIZER.register(eventBus);
    }
}
