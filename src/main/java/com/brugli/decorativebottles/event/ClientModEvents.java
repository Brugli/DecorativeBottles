package com.brugli.decorativebottles.event;

import com.brugli.decorativebottles.DecorativeBottles;
import com.brugli.decorativebottles.block.client.renderer.BottleBlockRenderer;
import com.brugli.decorativebottles.block.entity.DecorativeBottlesBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DecorativeBottles.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(DecorativeBottlesBlockEntities.BOTTLE_BLOCK_ENTITY.get(), BottleBlockRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BottleBlockRenderer.BOTTLE_FILLER_LAYER, BottleBlockRenderer::createBottleFillerLayer);
    }
}
