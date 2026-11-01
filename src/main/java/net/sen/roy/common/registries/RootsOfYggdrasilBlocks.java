package net.sen.roy.common.registries;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sen.roy.RootsOfYggdrasil;

import java.util.Locale;
import java.util.function.Supplier;

public class RootsOfYggdrasilBlocks {
    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RootsOfYggdrasil.MODID);
    private static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(RootsOfYggdrasil.MODID);

    private static DeferredBlock<Block> createBlock(String name) {
        return createBlock(name, () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    }

    private static DeferredBlock<Block> createBlock(String name, BlockBehaviour.Properties properties) {
        return createBlock(name, () -> new Block(properties));
    }

    private static <T extends Block> DeferredBlock<T> createBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name.toLowerCase(Locale.ROOT), block);
        createBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> createBlockOnly(String name, DeferredBlock<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name.toLowerCase(Locale.ROOT), block);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<Item> createBlockItem(String name, DeferredBlock<T> block) {
        return BLOCK_ITEMS.register(name.toLowerCase(Locale.ROOT), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }
}
