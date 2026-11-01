package net.sen.roy.data.models;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.sen.roy.RootsOfYggdrasil;

public abstract class ItemModelHelper extends ItemModelProvider {
    public ItemModelHelper(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RootsOfYggdrasil.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    }
}
