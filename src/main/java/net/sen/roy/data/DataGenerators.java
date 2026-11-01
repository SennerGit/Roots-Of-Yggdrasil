package net.sen.roy.data;

import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.util.InclusiveRange;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.sen.roy.data.language.RootsOfYggdrasilLanguageProviderEnUs;
import net.sen.roy.data.models.RootsOfYggdrasilBlockModel;
import net.sen.roy.data.models.RootsOfYggdrasilBlockState;
import net.sen.roy.data.models.RootsOfYggdrasilItemModel;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber()
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();
        PackOutput output = generator.getPackOutput();

        // -------- CLIENT DATA --------
        if (event.includeClient()) {
            generator.addProvider(true, new RootsOfYggdrasilLanguageProviderEnUs(output));
            generator.addProvider(true, new RootsOfYggdrasilBlockModel(output, helper));
            generator.addProvider(true, new RootsOfYggdrasilItemModel(output, helper));
            generator.addProvider(true, new RootsOfYggdrasilBlockState(output, helper));
        }

        // -------- SERVER DATA --------
        if (event.includeServer()) {
            DatapackBuiltinEntriesProvider datapack = new RootsOfYggdrasilRegistriesProvider(output, lookup);

            generator.addProvider(true, datapack);
        }

        // -------- PACK METADATA --------
        generator.addProvider(true, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Roots Of Yggdrasil"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.SERVER_DATA),
                Optional.of(new InclusiveRange<>(0, Integer.MAX_VALUE))
        )));
    }
}
