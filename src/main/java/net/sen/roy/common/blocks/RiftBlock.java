package net.sen.roy.common.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.sen.roy.common.blocks.entities.RiftBlockEntity;
import net.sen.roy.common.registries.RootsOfYggdrasilBlockEntities;
import org.jetbrains.annotations.Nullable;

public class RiftBlock extends BaseEntityBlock {
    public static final MapCodec<RiftBlock> CODEC = simpleCodec(RiftBlock::new);

    public RiftBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return RootsOfYggdrasilBlockEntities.RIFT_BLOCK_ENTITY.get().create(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, RootsOfYggdrasilBlockEntities.RIFT_BLOCK_ENTITY.get(), level.isClientSide ? null : RiftBlockEntity::serverTick);
    }
}
