package net.sen.roy.common.registries;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sen.roy.RootsOfYggdrasil;

import java.util.Locale;
import java.util.function.Supplier;

public class RootsOfYggdrasilItems {
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RootsOfYggdrasil.MODID);

    private static DeferredItem<Item> createItem(String name) {
        return createItem(name, new Item.Properties());
    }

    private static DeferredItem<Item> createItem(String name, Item.Properties properties) {
        return createItem(name, () -> new Item(properties));
    }

    private static <T extends Item> DeferredItem<T> createItem(String name, Supplier<T> item) {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
