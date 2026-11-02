package net.sen.roy.common.registries;

import com.google.common.base.Preconditions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sen.roy.RootsOfYggdrasil;
import net.sen.roy.common.blocks.entities.RiftBlockEntity;

import java.util.Arrays;
import java.util.Locale;

public class RootsOfYggdrasilBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, RootsOfYggdrasil.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RiftBlockEntity>> RIFT_BLOCK_ENTITY = create("rift_block_entity", RiftBlockEntity::new, RootsOfYggdrasilBlocks.RIFT_BLOCK);

    @SafeVarargs
    private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> create(String name, BlockEntityType.BlockEntitySupplier<T> factory, DeferredBlock<?>... blocks) {
        Preconditions.checkArgument(blocks.length > 0);
        return BLOCK_ENTITIES.register(name.toLowerCase(Locale.ROOT), () ->
                BlockEntityType.Builder.<T>of(
                        factory,
                        Arrays.stream(blocks).map(DeferredBlock::get).toArray(Block[]::new)
                ).build(null)
        );
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
