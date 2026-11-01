package net.sen.roy.common.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sen.roy.RootsOfYggdrasil;

import java.util.Locale;
import java.util.function.Supplier;

public class RootsOfYggdrasilCreativeModeTabs {
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RootsOfYggdrasil.MODID);

    private static String generateName(String id) {
        return ("itemgroup." + RootsOfYggdrasil.MODID + "." + id).toLowerCase(Locale.ROOT);
    }

    private static <T extends CreativeModeTab> DeferredHolder<CreativeModeTab, CreativeModeTab> createTab(String id, Supplier<? extends ItemLike> icon, Supplier<? extends ItemLike>... items) {
        return TABS.register(id.toLowerCase(Locale.ROOT), () -> {
            return CreativeModeTab.builder()
                    .icon(() -> new ItemStack(icon.get()))
                    .title(Component.translatable(generateName(id.toLowerCase(Locale.ROOT))))
                    .displayItems(((itemDisplayParameters, output) -> {
                        for (Supplier<? extends ItemLike> itemLike : items) {
                            output.accept(itemLike.get().asItem());
                        }
                    }))
                    .build();
        });
    }

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
