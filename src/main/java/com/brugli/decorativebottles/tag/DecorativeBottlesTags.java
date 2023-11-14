package com.brugli.decorativebottles.tag;

import com.brugli.decorativebottles.DecorativeBottles;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class DecorativeBottlesTags {

    public static class Items {
        public static final TagKey<Item> POTIONS = tag("potions");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(DecorativeBottles.MODID, name));
        }
    }
}
