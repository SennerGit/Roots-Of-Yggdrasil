package net.sen.roy.common.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.sen.roy.common.recipes.RiftConversionRecipe;
import net.sen.roy.common.registries.RootsOfYggdrasilBlockEntities;
import net.sen.roy.common.registries.RootsOfYggdrasilRecipes;

import java.util.*;

public class RiftBlockEntity extends BlockEntity {
    private static final int RADIUS = 3;
    private static final int SCAN_INTERVAL = 20;

    private Map<UUID, Integer> scans = new HashMap<>();

    public RiftBlockEntity(BlockPos pos, BlockState blockState) {
        super(RootsOfYggdrasilBlockEntities.RIFT_BLOCK_ENTITY.get(), pos, blockState);
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, RiftBlockEntity blockEntity) {
        if (!(level instanceof ServerLevel serverLevel)) return;
        if (level.getGameTime() % SCAN_INTERVAL == 0) blockEntity.scan(serverLevel, blockPos);
        blockEntity.countdown(serverLevel, blockPos);
    }

    private Optional<RecipeHolder<RiftConversionRecipe>> findRecipe(Level level, ItemStack stack) {
        return level.getRecipeManager().getRecipeFor(RootsOfYggdrasilRecipes.RIFT_CONVERSION_RECIPE.type().get(), new SingleRecipeInput(stack), level);
    }

    private void scan(Level level, BlockPos blockPos) {
        AABB area = new AABB(blockPos).inflate(RADIUS);

        for (ItemEntity entity : level.getEntitiesOfClass(ItemEntity.class, area)) {
            if (scans.containsKey(entity.getUUID())) continue;

            Optional<RecipeHolder<RiftConversionRecipe>> match = findRecipe(level, entity.getItem());
            if (match.isEmpty()) continue;

            RiftConversionRecipe recipe = match.get().value();
            int timer = Math.max(1, Math.round(recipe.time() * (0.9f + level.random.nextFloat() * 0.2f)));
            scans.put(entity.getUUID(), timer);
            setChanged();
        }
    }

    private void countdown(ServerLevel level, BlockPos blockPos) {
        if (scans.isEmpty()) return;

        Iterator<Map.Entry<UUID, Integer>> iterator = scans.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<UUID, Integer> entry = iterator.next();

            Entity found = level.getEntity(entry.getKey());
            if (!(found instanceof ItemEntity itemEntity) || itemEntity.isRemoved() || !itemEntity.getBoundingBox().intersects(new AABB(blockPos).inflate(RADIUS))) {
                iterator.remove();
                setChanged();
                continue;
            }

            finish(level, blockPos, itemEntity);
            iterator.remove();
            setChanged();
        }
    }

    private void finish(Level level, BlockPos blockPos, ItemEntity itemEntity) {
        ItemStack stack = itemEntity.getItem();
        Optional<RecipeHolder<RiftConversionRecipe>> match = findRecipe(level, stack);
        if (match.isEmpty()) return;

        ItemStack result = match.get().value().assemble(new SingleRecipeInput(stack), level.registryAccess());
        int total = result.getCount() * stack.getCount();
        itemEntity.discard();

        while (total > 0) {
            int amount = Math.min(total, result.getMaxStackSize());
            ItemStack out = result.copyWithCount(amount);
            level.addFreshEntity(new ItemEntity(level, blockPos.getX() + 0.5, blockPos.getY() + 1.2, blockPos.getZ() + 0.5, out));
            total -= amount;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ListTag list = new ListTag();
        scans.forEach((uuid, time) -> {
            CompoundTag entry = new CompoundTag();
            entry.putUUID("Id", uuid);
            entry.putInt("Time", time);
            list.add(entry);
        });
        tag.put("Scans", list);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        scans.clear();
        for (Tag t : tag.getList("Scans", Tag.TAG_COMPOUND)) {
            CompoundTag entry = (CompoundTag) t;
            scans.put(entry.getUUID("Id"), entry.getInt("Time"));
        }
    }
}
