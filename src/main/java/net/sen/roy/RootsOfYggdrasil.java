package net.sen.roy;

import net.neoforged.api.distmarker.Dist;
import net.sen.roy.client.RootsOfYggdrasilClient;
import net.sen.roy.config.RootsOfYggdrasilConfig;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(RootsOfYggdrasil.MODID)
public class RootsOfYggdrasil {
    public static final String MODID = "roy";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RootsOfYggdrasil(IEventBus eventBus, ModContainer container, Dist dist) {
        //Client-Only Setup
        if (dist.isClient()) {
            RootsOfYggdrasilClient.RootsOfYggdrasilClientRegistry(eventBus);
        }

        //Registries

        //Setup
        eventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        //Config
        container.registerConfig(ModConfig.Type.COMMON, RootsOfYggdrasilConfig.COMMON_SPEC);
        container.registerConfig(ModConfig.Type.SERVER, RootsOfYggdrasilConfig.SERVER_SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, RootsOfYggdrasilConfig.CLIENT_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        preInit(event);
        init(event);
        postInit(event);
    }

    private void preInit(FMLCommonSetupEvent event) {
    }

    private void init(FMLCommonSetupEvent event) {
    }

    private void postInit(FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
