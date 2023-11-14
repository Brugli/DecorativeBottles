package com.brugli.decorativebottles;

import com.brugli.decorativebottles.block.DecorativeBottlesBlocks;
import com.brugli.decorativebottles.block.entity.DecorativeBottlesBlockEntities;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DecorativeBottles.MODID)
public class DecorativeBottles
{
    public static final String MODID = "decorativebottles";

    public DecorativeBottles()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        DecorativeBottlesBlocks.BLOCKS.register(bus);
        DecorativeBottlesBlockEntities.BLOCK_ENTITIES.register(bus);
    }
}
