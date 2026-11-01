package net.sen.roy.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.sen.roy.RootsOfYggdrasil;

@Mod(value = RootsOfYggdrasil.MODID, dist = Dist.CLIENT)
public class RootsOfYggdrasilClient {
    public static void RootsOfYggdrasilClientRegistry(IEventBus eventBus) {
        eventBus.addListener(RootsOfYggdrasilClient::onClientSetup);
    }

    static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {});
    }
}
