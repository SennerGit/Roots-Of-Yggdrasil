package net.sen.roy.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.sen.roy.RootsOfYggdrasil;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RootsOfYggdrasilRegistriesProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder();

    public RootsOfYggdrasilRegistriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(RootsOfYggdrasil.MODID));
    }
}
