package net.sen.roy.common.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.sen.roy.common.registries.RootsOfYggdrasilRecipes;

public record RiftConversionRecipe (Ingredient input, ItemStack result, int time) implements Recipe<SingleRecipeInput> {
    @Override
    public boolean matches(SingleRecipeInput singleRecipeInput, Level level) {
        return input.test(singleRecipeInput.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput singleRecipeInput, HolderLookup.Provider provider) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return result.copy();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RootsOfYggdrasilRecipes.RIFT_CONVERSION_RECIPE.serializer().get();
    }

    @Override
    public RecipeType<?> getType() {
        return RootsOfYggdrasilRecipes.RIFT_CONVERSION_RECIPE.type().get();
    }

    public static class Serializer implements RecipeSerializer<RiftConversionRecipe> {
        private static final MapCodec<RiftConversionRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("input").forGetter(RiftConversionRecipe::input),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(RiftConversionRecipe::result),
                Codec.INT.optionalFieldOf("time", 200).forGetter(RiftConversionRecipe::time)
        ).apply(i, RiftConversionRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, RiftConversionRecipe> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, RiftConversionRecipe::input,
                ItemStack.STREAM_CODEC, RiftConversionRecipe::result,
                ByteBufCodecs.VAR_INT, RiftConversionRecipe::time,
                RiftConversionRecipe::new
        );

        @Override
        public MapCodec<RiftConversionRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, RiftConversionRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
