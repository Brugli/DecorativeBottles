package com.brugli.decorativebottles.event;

import com.brugli.decorativebottles.DecorativeBottles;
import com.brugli.decorativebottles.block.DecorativeBottlesBlocks;
import com.brugli.decorativebottles.block.custom.BottleBlock;
import com.brugli.decorativebottles.block.entity.custom.BottleBlockEntity;
import com.brugli.decorativebottles.tag.DecorativeBottlesTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = DecorativeBottles.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeModEvents {

    @SubscribeEvent
    public static InteractionResult clickEvent(final PlayerInteractEvent.RightClickItem event) {
        ItemStack itemStack = event.getItemStack();
        Player player = event.getEntity();
        Level level = event.getLevel();
        if (itemStack != ItemStack.EMPTY && (itemStack.is(DecorativeBottlesTags.Items.POTIONS)) && player.isSteppingCarefully()) {

            HitResult hitResult = Minecraft.getInstance().hitResult;

            if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK) {

                BlockHitResult blockHitResult = (BlockHitResult) Minecraft.getInstance().hitResult;
                BlockPos pos = blockHitResult.getBlockPos();
                BlockState blockState = event.getLevel().getBlockState(pos);
                Direction facing = blockHitResult.getDirection();
                BlockPos relativePos = pos.relative(facing);
                BlockState relativeState = event.getLevel().getBlockState(relativePos);
                BlockPlaceContext blockPlaceContext = new BlockPlaceContext(event.getEntity(), event.getHand(), event.getItemStack(), blockHitResult);

                if (blockState.getBlock() instanceof BottleBlock && blockState.getValue(BottleBlock.BOTTLES) < 4) {
                    event.getLevel().setBlockAndUpdate(pos, Objects.requireNonNull(Objects.requireNonNull(DecorativeBottlesBlocks.GLASS_BOTTLE_BLOCK.get().getStateForPlacement(blockPlaceContext)).setValue(BottleBlock.BOTTLES, blockState.getValue(BottleBlock.BOTTLES) + 1).setValue(BottleBlock.FACING, blockState.getValue(BottleBlock.FACING))));
                    player.swing(player.getUsedItemHand(), true);
                    level.playSound((Player) null, pos, SoundEvents.GLASS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    BlockEntity blockentity = level.getBlockEntity(pos);
                    if (blockentity instanceof BottleBlockEntity bottleBlockEntity) {
                        bottleBlockEntity.placeBottle(player, player.getAbilities().instabuild ? itemStack.copy() : itemStack);
                    }
                    return InteractionResult.SUCCESS;
                }

                if (relativeState.getBlock() instanceof BottleBlock && relativeState.getValue(BottleBlock.BOTTLES) < 4) {
                    event.getLevel().setBlockAndUpdate(relativePos, Objects.requireNonNull(Objects.requireNonNull(DecorativeBottlesBlocks.GLASS_BOTTLE_BLOCK.get().getStateForPlacement(blockPlaceContext)).setValue(BottleBlock.BOTTLES, relativeState.getValue(BottleBlock.BOTTLES) + 1).setValue(BottleBlock.FACING, relativeState.getValue(BottleBlock.FACING))));
                    player.swing(player.getUsedItemHand(), true);
                    level.playSound((Player) null, relativePos, SoundEvents.GLASS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    BlockEntity blockentity = level.getBlockEntity(relativePos);
                    if (blockentity instanceof BottleBlockEntity bottleBlockEntity) {
                        bottleBlockEntity.placeBottle(player, player.getAbilities().instabuild ? itemStack.copy() : itemStack);
                    }
                    return InteractionResult.SUCCESS;
                }

                if (blockState.getBlock().canBeReplaced(blockState, blockPlaceContext) && event.getLevel().getBlockState(pos.below()).isFaceSturdy(event.getLevel(), pos.below(), Direction.UP)) {
                    event.getLevel().setBlockAndUpdate(pos, Objects.requireNonNull(Objects.requireNonNull(DecorativeBottlesBlocks.GLASS_BOTTLE_BLOCK.get().getStateForPlacement(blockPlaceContext)).setValue(BottleBlock.FACING, blockPlaceContext.getHorizontalDirection().getOpposite())));
                    player.swing(player.getUsedItemHand(), true);
                    level.playSound((Player) null, pos, SoundEvents.GLASS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    BlockEntity blockentity = level.getBlockEntity(pos);
                    if (blockentity instanceof BottleBlockEntity bottleBlockEntity) {
                        bottleBlockEntity.placeBottle(player, player.getAbilities().instabuild ? itemStack.copy() : itemStack);
                    }
                    return InteractionResult.SUCCESS;

                } else if (relativeState.getBlock().canBeReplaced(relativeState, blockPlaceContext) && event.getLevel().getBlockState(relativePos.below()).isFaceSturdy(event.getLevel(), relativePos.below(), Direction.UP)) {
                    event.getLevel().setBlockAndUpdate(relativePos, Objects.requireNonNull(Objects.requireNonNull(DecorativeBottlesBlocks.GLASS_BOTTLE_BLOCK.get().getStateForPlacement(blockPlaceContext)).setValue(BottleBlock.FACING, blockPlaceContext.getHorizontalDirection().getOpposite())));
                    player.swing(player.getUsedItemHand(), true);
                    level.playSound((Player) null, relativePos, SoundEvents.GLASS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    BlockEntity blockentity = level.getBlockEntity(relativePos);
                    if (blockentity instanceof BottleBlockEntity bottleBlockEntity) {
                        bottleBlockEntity.placeBottle(player, player.getAbilities().instabuild ? itemStack.copy() : itemStack);
                    }
                    return InteractionResult.SUCCESS;
                }

            }
        }
        return InteractionResult.PASS;
    }
}
