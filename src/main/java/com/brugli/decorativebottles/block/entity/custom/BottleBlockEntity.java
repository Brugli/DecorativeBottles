package com.brugli.decorativebottles.block.entity.custom;

import com.brugli.decorativebottles.block.entity.DecorativeBottlesBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;

public class BottleBlockEntity extends BlockEntity {

    protected NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);

    public BottleBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(DecorativeBottlesBlockEntities.BOTTLE_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public boolean placeBottle(@Nullable Entity p_238285_, ItemStack p_238286_) {
        for(int i = 0; i < this.items.size(); ++i) {
            ItemStack itemstack = this.items.get(i);
            if (itemstack.isEmpty()) {
//                this.scheduleTick();
                this.items.set(i, p_238286_.split(1));
                this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(p_238285_, this.getBlockState()));
                this.markUpdated();

                return true;
            }
        }

        return false;
    }

//    private void scheduleTick() {
//        Block block = this.getBlockState().getBlock();
//        if (block instanceof BottleBlock) {
//            this.level.scheduleTick(this.worldPosition, block, 2);
//        }
//    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public void load(CompoundTag p_155312_) {
        super.load(p_155312_);
        this.items.clear();
        ContainerHelper.loadAllItems(p_155312_, this.items);
    }

    protected void saveAdditional(CompoundTag p_187486_) {
        super.saveAdditional(p_187486_);
        ContainerHelper.saveAllItems(p_187486_, this.items, true);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CompoundTag getUpdateTag() {
        CompoundTag compoundtag = new CompoundTag();
        ContainerHelper.saveAllItems(compoundtag, this.items, true);
        return compoundtag;
    }

}
