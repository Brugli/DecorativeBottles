package com.brugli.decorativebottles.block.custom;

import com.brugli.decorativebottles.block.entity.custom.BottleBlockEntity;
import com.brugli.decorativebottles.tag.DecorativeBottlesTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.stream.Stream;

public class BottleBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {

    public static final IntegerProperty BOTTLES = IntegerProperty.create("bottles", 1, 4);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

//    public static final EnumProperty<OneBottle> ONE_BOTTLE = EnumProperty.create("one_bottle", OneBottle.class);

    VoxelShape ONE_BOTTLE_AABB = Stream.of(Block.box(5.5, 0, 5.5, 10.5, 4, 10.5),
            Block.box(6.5, 4, 6.5, 9.5, 5, 9.5),
            Block.box(6, 5, 6, 10, 6, 10),
            Block.box(6.5, 6, 6.5, 9.5, 7.5, 9.5)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),

    TWO_BOTTLE_NORTH_SOUTH_AABB = Stream.of(Block.box(2, 0, 4, 7, 4, 9),
            Block.box(3, 4, 5, 6, 5, 8),
            Block.box(2.5, 5, 4.5, 6.5, 6, 8.5),
            Block.box(3, 5.5, 5, 6, 7.5, 8),
            Block.box(9, 0, 7, 14, 4, 12),
            Block.box(10, 4, 8, 13, 5, 11),
            Block.box(9.5, 5, 7.5, 13.5, 6, 11.5),
            Block.box(10, 5.5, 8, 13, 7.5, 11)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),

    TWO_BOTTLE_EAST_WEST_AABB = Stream.of(Block.box(4, 0, 9, 9, 4, 14),
            Block.box(5, 4, 10, 8, 5, 13),
            Block.box(4.5, 5, 9.5, 8.5, 6, 13.5),
            Block.box(5, 5.5, 10, 8, 7.5, 13),
            Block.box(7, 0, 2, 12, 4, 7),
            Block.box(8, 4, 3, 11, 5, 6),
            Block.box(7.5, 5, 2.5, 11.5, 6, 6.5),
            Block.box(8, 5.5, 3, 11, 7.5, 6)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),

    THREE_BOTTLE_NORTH_AABB = Stream.of(Block.box(2, 0, 9, 7, 4, 14),
            Block.box(3, 4, 10, 6, 5, 13),
            Block.box(2.5, 5, 9.5, 6.5, 6, 13.5),
            Block.box(3, 5.5, 10, 6, 7.5, 13),
            Block.box(9, 0, 9, 14, 4, 14),
            Block.box(10, 4, 10, 13, 5, 13),
            Block.box(9.5, 5, 9.5, 13.5, 6, 13.5),
            Block.box(10, 5.5, 10, 13, 7.5, 13),
            Block.box(5.5, 0, 2, 10.5, 4, 7),
            Block.box(6.5, 4, 3, 9.5, 5, 6),
            Block.box(6, 5, 2.5, 10, 6, 6.5),
            Block.box(6.5, 5.5, 3, 9.5, 7.5, 6)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),

    THREE_BOTTLE_EAST_AABB = Stream.of(Block.box(2, 0, 2, 7, 4, 7),
            Block.box(3, 4, 3, 6, 5, 6),
            Block.box(2.5, 5, 2.5, 6.5, 6, 6.5),
            Block.box(3, 5.5, 3, 6, 7.5, 6),
            Block.box(9, 0, 5.5, 14, 4, 10.5),
            Block.box(10, 4, 6.5, 13, 5, 9.5),
            Block.box(9.5, 5, 6, 13.5, 6, 10),
            Block.box(10, 5.5, 6.5, 13, 7.5, 9.5),
            Block.box(2, 0, 9, 7, 4, 14),
            Block.box(3, 4, 10, 6, 5, 13),
            Block.box(2.5, 5, 9.5, 6.5, 6, 13.5),
            Block.box(3, 5.5, 10, 6, 7.5, 13)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),

    THREE_BOTTLE_SOUTH_AABB = Stream.of(Block.box(2, 0, 2, 7, 4, 7),
            Block.box(3, 4, 3, 6, 5, 6),
            Block.box(2.5, 5, 2.5, 6.5, 6, 6.5),
            Block.box(3, 5.5, 3, 6, 7.5, 6),
            Block.box(9, 0, 2, 14, 4, 7),
            Block.box(10, 4, 3, 13, 5, 6),
            Block.box(9.5, 5, 2.5, 13.5, 6, 6.5),
            Block.box(10, 5.5, 3, 13, 7.5, 6),
            Block.box(5.5, 0, 9, 10.5, 4, 14),
            Block.box(6.5, 4, 10, 9.5, 5, 13),
            Block.box(6, 5, 9.5, 10, 6, 13.5),
            Block.box(6.5, 5.5, 10, 9.5, 7.5, 13)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),

    THREE_BOTTLE_WEST_AABB = Stream.of(Block.box(2, 0, 5.5, 7, 4, 10.5),
            Block.box(3, 4, 6.5, 6, 5, 9.5),
            Block.box(2.5, 5, 6, 6.5, 6, 10),
            Block.box(3, 5.5, 6.5, 6, 7.5, 9.5),
            Block.box(9, 0, 9, 14, 4, 14),
            Block.box(10, 4, 10, 13, 5, 13),
            Block.box(9.5, 5, 9.5, 13.5, 6, 13.5),
            Block.box(10, 5.5, 10, 13, 7.5, 13),
            Block.box(9, 0, 2, 14, 4, 7),
            Block.box(10, 4, 3, 13, 5, 6),
            Block.box(9.5, 5, 2.5, 13.5, 6, 6.5),
            Block.box(10, 5.5, 3, 13, 7.5, 6)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get(),

    FOUR_BOTTLE_AABB = Stream.of(Block.box(9, 0, 2, 14, 4, 7),
            Block.box(10, 4, 3, 13, 5, 6),
            Block.box(9.5, 5, 2.5, 13.5, 6, 6.5),
            Block.box(10, 5.5, 3, 13, 7.5, 6),
            Block.box(9, 0, 9, 14, 4, 14),
            Block.box(10, 4, 10, 13, 5, 13),
            Block.box(9.5, 5, 9.5, 13.5, 6, 13.5),
            Block.box(10, 5.5, 10, 13, 7.5, 13),
            Block.box(2, 0, 2, 7, 4, 7),
            Block.box(3, 4, 3, 6, 5, 6),
            Block.box(2.5, 5, 2.5, 6.5, 6, 6.5),
            Block.box(3, 5.5, 3, 6, 7.5, 6),
            Block.box(2, 0, 9, 7, 4, 14),
            Block.box(3, 4, 10, 6, 5, 13),
            Block.box(2.5, 5, 9.5, 6.5, 6, 13.5),
            Block.box(3, 5.5, 10, 6, 7.5, 13)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public BottleBlock(BlockBehaviour.Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(BOTTLES, 1)
                .setValue(WATERLOGGED, Boolean.FALSE)
                .setValue(FACING, Direction.NORTH));
//                .setValue(ONE_BOTTLE, OneBottle.NONE));
    }

    public VoxelShape getShape(BlockState p_56122_, BlockGetter p_56123_, BlockPos p_56124_, CollisionContext p_56125_) {
        if (p_56122_.getValue(BOTTLES) == 1) {
            return ONE_BOTTLE_AABB;

        }
        if (p_56122_.getValue(BOTTLES) == 2) {
            if  (p_56122_.getValue(FACING) == Direction.NORTH || p_56122_.getValue(FACING) == Direction.SOUTH) {
                return TWO_BOTTLE_NORTH_SOUTH_AABB;
            }
            if  (p_56122_.getValue(FACING) == Direction.EAST || p_56122_.getValue(FACING) == Direction.WEST) {
                return TWO_BOTTLE_EAST_WEST_AABB;
            }
        }
        if (p_56122_.getValue(BOTTLES) == 3) {
            if  (p_56122_.getValue(FACING) == Direction.NORTH) {
                return THREE_BOTTLE_NORTH_AABB;
            }
            if  (p_56122_.getValue(FACING) == Direction.EAST) {
                return THREE_BOTTLE_EAST_AABB;
            }
            if  (p_56122_.getValue(FACING) == Direction.SOUTH) {
                return THREE_BOTTLE_SOUTH_AABB;
            }
            if  (p_56122_.getValue(FACING) == Direction.WEST) {
                return THREE_BOTTLE_WEST_AABB;
            }
        }
        return FOUR_BOTTLE_AABB;
    }

    public RenderShape getRenderShape(BlockState p_51307_) {
        return RenderShape.MODEL;
    }

    public void tick(BlockState p_221005_, ServerLevel p_221006_, BlockPos p_221007_, RandomSource p_221008_) {
//        this.updateBottles(p_221006_, p_221007_, p_221005_);
    }

//    public void updateBottles (Level level, BlockPos pos, BlockState state) {
//
//        Item water = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER).getItem();
//        Item mundane = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE).getItem();
//        Item thick = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.THICK).getItem();
//        Item awkward = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD).getItem();
//        ItemStack dragonBreath = Items.DRAGON_BREATH.getDefaultInstance();
//        CompoundTag nightVision = PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.NIGHT_VISION).getTag();
//        CompoundTag longNightVision = PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.LONG_NIGHT_VISION).getTag();
//        Item invisibility = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.INVISIBILITY).getItem();
//        Item leaping = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LEAPING).getItem();
//        Item fireResistance = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.FIRE_RESISTANCE).getItem();
//        Item swiftness = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SWIFTNESS).getItem();
//        Item slowness = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOWNESS).getItem();
//        Item turtleMaster = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.TURTLE_MASTER).getItem();
//        Item waterBreathing = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER_BREATHING).getItem();
//        Item healing = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.HEALING).getItem();
//        Item harming = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.HARMING).getItem();
//        Item poison = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.POISON).getItem();
//        Item regeneration = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.REGENERATION).getItem();
//        Item strength = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH).getItem();
//        Item weakness = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WEAKNESS).getItem();
//        Item luck = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LUCK).getItem();
//        Item slowFalling = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOW_FALLING).getItem();
//
//        OneBottle oneBottle = OneBottle.NONE;
//
//        BlockEntity blockentity = level.getBlockEntity(pos);
//
//        if (blockentity instanceof BottleBlockEntity bottleBlockEntity) {
//
//            ItemStack item0 = bottleBlockEntity.getItems().get(0);
//            ItemStack item1 = bottleBlockEntity.getItems().get(1);
//            ItemStack item2 = bottleBlockEntity.getItems().get(2);
//            ItemStack item3 = bottleBlockEntity.getItems().get(3);
//
//            System.out.println(nightVision);
//            System.out.println(item0.serializeNBT());
//
//            if (item0.is(dragonBreath.getItem())) {
//                oneBottle = OneBottle.DRAGON_BREATH;
//            }
//            if (!(item0.getTag() == null)) {
//                if (item0.getTag().equals(nightVision) || item0.getTag().equals(longNightVision)) {
//                    oneBottle = OneBottle.NIGHT_VISION;
//                }
//            }
////            if (item0.is(invisibility)) {
//////                System.out.println("invisibility");
////                oneBottle = OneBottle.INVISIBILITY;
////            }
////            if (item0.is(leaping)) {
//////                System.out.println("leaping");
////                oneBottle = OneBottle.LEAPING;
////            }
////            if (item0.is(water) || item0.is(mundane) || item0.is(thick) || item0.is(awkward)) {
////                System.out.println("water");
////                oneBottle = OneBottle.WATER;
////            }
//        }
//
//        level.setBlock(pos, state.setValue(BOTTLES,state.getValue(BOTTLES)).setValue(FACING, state.getValue(FACING)).setValue(ONE_BOTTLE, oneBottle), 3);
////        return state.setValue(ONE_BOTTLE, oneBottle);
//    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        BlockEntity blockentity = level.getBlockEntity(blockPos);
        if (player.isSteppingCarefully() && blockentity instanceof BottleBlockEntity bottleBlockEntity && blockState.getValue(BOTTLES) < 4 && itemstack.is(DecorativeBottlesTags.Items.POTIONS)) {
            if (!level.isClientSide && bottleBlockEntity.placeBottle(player, player.getAbilities().instabuild ? itemstack.copy() : itemstack)) {
                BlockPlaceContext blockPlaceContext = new BlockPlaceContext(player, interactionHand, itemstack, blockHitResult);

                level.setBlockAndUpdate(blockPos, this.getStateForPlacement(blockPlaceContext).setValue(BOTTLES,blockState.getValue(BOTTLES) + 1).setValue(FACING, blockState.getValue(FACING)));
                level.playSound((Player) null, blockPos, SoundEvents.GLASS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }
        else {
            return InteractionResult.PASS;
        }

    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        if (blockstate.is(this)) {
            return blockstate.setValue(BOTTLES, Math.min(4, blockstate.getValue(BOTTLES) + 1));
        } else {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return Objects.requireNonNull(super.getStateForPlacement(context)).setValue(WATERLOGGED, flag).setValue(FACING, context.getHorizontalDirection().getOpposite());
        }
    }

    protected boolean mayPlaceOn(BlockState p_56127_, BlockGetter p_56128_, BlockPos p_56129_) {
        return !p_56127_.getCollisionShape(p_56128_, p_56129_).getFaceShape(Direction.UP).isEmpty() || p_56127_.isFaceSturdy(p_56128_, p_56129_, Direction.UP);
    }

    public boolean canSurvive(BlockState p_56109_, LevelReader p_56110_, BlockPos p_56111_) {
        BlockPos blockpos = p_56111_.below();
        return this.mayPlaceOn(p_56110_.getBlockState(blockpos), p_56110_, blockpos);
    }

    @Override
    public BlockState updateShape(BlockState p_56113_, Direction p_56114_, BlockState p_56115_, LevelAccessor p_56116_, BlockPos p_56117_, BlockPos p_56118_) {
        if (!p_56113_.canSurvive(p_56116_, p_56117_)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            if (p_56113_.getValue(WATERLOGGED)) {
                p_56116_.scheduleTick(p_56117_, Fluids.WATER, Fluids.WATER.getTickDelay(p_56116_));
            }
            return super.updateShape(p_56113_, p_56114_, p_56115_, p_56116_, p_56117_, p_56118_);
        }

    }
    
    public FluidState getFluidState(BlockState p_56131_) {
        return p_56131_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_56131_);
    }

    public BlockState rotate(BlockState p_48722_, Rotation p_48723_) {
        return p_48722_.setValue(FACING, p_48723_.rotate(p_48722_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_48719_, Mirror p_48720_) {
        return p_48719_.rotate(p_48720_.getRotation(p_48719_.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56120_) {
        p_56120_.add(BOTTLES, WATERLOGGED, FACING);
    }

    public boolean isPathfindable(BlockState p_56104_, BlockGetter p_56105_, BlockPos p_56106_, PathComputationType p_56107_) {
        return false;
    }

    public BlockEntity newBlockEntity(BlockPos p_152759_, BlockState p_152760_) {
        return new BottleBlockEntity(p_152759_, p_152760_);
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean bool) {
        if (!state.is(state1.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof BottleBlockEntity) {
                Containers.dropContents(level, pos, ((BottleBlockEntity)blockentity).getItems());
            }

            super.onRemove(state, level, pos, state1, bool);
        }
    }

    public PushReaction getPistonPushReaction(BlockState p_53683_) {
        return PushReaction.DESTROY;
    }
}
