package com.brugli.decorativebottles.event;

import com.brugli.decorativebottles.DecorativeBottles;
import com.brugli.decorativebottles.block.client.renderer.BottleBlockRenderer;
import com.brugli.decorativebottles.block.entity.DecorativeBottlesBlockEntities;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
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

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            return;
        }
        event.addSprite(BottleBlockRenderer.WATER_FILLER);
        event.addSprite(BottleBlockRenderer.DRAGON_BREATH_FILLER);
        event.addSprite(BottleBlockRenderer.HONEY_FILLER);
        event.addSprite(BottleBlockRenderer.NIGHT_VISION_FILLER);
        event.addSprite(BottleBlockRenderer.INVISIBILITY_FILLER);
        event.addSprite(BottleBlockRenderer.LEAPING_FILLER);
        event.addSprite(BottleBlockRenderer.FIRE_RESISTANCE_FILLER);
        event.addSprite(BottleBlockRenderer.SWIFTNESS_FILLER);
        event.addSprite(BottleBlockRenderer.SLOWNESS_FILLER);
        event.addSprite(BottleBlockRenderer.TURTLE_MASTER_FILLER);
        event.addSprite(BottleBlockRenderer.WATER_BREATHING_FILLER);
        event.addSprite(BottleBlockRenderer.HEALING_FILLER);
        event.addSprite(BottleBlockRenderer.HARMING_FILLER);
        event.addSprite(BottleBlockRenderer.POISON_FILLER);
        event.addSprite(BottleBlockRenderer.REGENERATION_FILLER);
        event.addSprite(BottleBlockRenderer.STRENGTH_FILLER);
        event.addSprite(BottleBlockRenderer.WEAKNESS_FILLER);
        event.addSprite(BottleBlockRenderer.LUCK_FILLER);
        event.addSprite(BottleBlockRenderer.SLOW_FALLING_FILLER);
    }
}
