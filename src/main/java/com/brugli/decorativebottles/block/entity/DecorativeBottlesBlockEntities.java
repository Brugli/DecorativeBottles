package com.brugli.decorativebottles.block.entity;

import com.brugli.decorativebottles.DecorativeBottles;
import com.brugli.decorativebottles.block.DecorativeBottlesBlocks;
import com.brugli.decorativebottles.block.entity.custom.BottleBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DecorativeBottlesBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DecorativeBottles.MODID);

    public static final RegistryObject<BlockEntityType<BottleBlockEntity>> BOTTLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("bottle_block_entity", () ->
                    BlockEntityType.Builder.of(BottleBlockEntity::new,
                            DecorativeBottlesBlocks.GLASS_BOTTLE_BLOCK.get()).build(null));
}
