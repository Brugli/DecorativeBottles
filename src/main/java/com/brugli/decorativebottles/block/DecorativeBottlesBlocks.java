package com.brugli.decorativebottles.block;

import com.brugli.decorativebottles.DecorativeBottles;
import com.brugli.decorativebottles.block.custom.BottleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DecorativeBottlesBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DecorativeBottles.MODID);

    public static final RegistryObject<Block> GLASS_BOTTLE_BLOCK = BLOCKS.register("glass_bottle_block",
            () -> new BottleBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
}
