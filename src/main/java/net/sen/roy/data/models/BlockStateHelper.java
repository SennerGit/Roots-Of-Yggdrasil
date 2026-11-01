package net.sen.roy.data.models;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.sen.roy.RootsOfYggdrasil;

public abstract class BlockStateHelper extends BlockStateProvider {
    public BlockStateHelper(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RootsOfYggdrasil.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    }
}
