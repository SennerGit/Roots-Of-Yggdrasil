package net.sen.roy.data.models;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.sen.roy.common.registries.RootsOfYggdrasilBlocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RootsOfYggdrasilBlockState extends BlockStateHelper {
    public RootsOfYggdrasilBlockState(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, exFileHelper);
    }

    @Override
    protected List<? extends DeferredBlock<? extends Block>> registerCubesBlocksWithItems() {
        List<DeferredBlock<? extends Block>> deferredBlocks = new ArrayList<>();
        deferredBlocks.add(RootsOfYggdrasilBlocks.RIFT_BLOCK);
        return deferredBlocks;
    }
}
