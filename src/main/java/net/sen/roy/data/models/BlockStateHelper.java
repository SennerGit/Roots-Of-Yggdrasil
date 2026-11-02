package net.sen.roy.data.models;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.sen.roy.RootsOfYggdrasil;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class BlockStateHelper extends BlockStateProvider {
    public BlockStateHelper(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RootsOfYggdrasil.MODID, exFileHelper);
    }

    protected abstract List<? extends DeferredBlock<? extends Block>> registerCubesBlocksWithItems();

    @Override
    protected void registerStatesAndModels() {
        registerCubesBlocksWithItems().forEach(deferredBlock -> simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get())));
    }
}
