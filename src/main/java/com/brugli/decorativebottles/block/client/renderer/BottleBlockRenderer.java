package com.brugli.decorativebottles.block.client.renderer;

import com.brugli.decorativebottles.DecorativeBottles;
import com.brugli.decorativebottles.block.custom.BottleBlock;
import com.brugli.decorativebottles.block.entity.custom.BottleBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.state.BlockState;

public class BottleBlockRenderer implements BlockEntityRenderer<BottleBlockEntity> {

    public static final ResourceLocation WATER_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/water_filled");
    public static final ResourceLocation DRAGON_BREATH_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/dragon_breath_filled");
    public static final ResourceLocation HONEY_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/honey_filled");
    public static final ResourceLocation NIGHT_VISION_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/night_vision_filled");
    public static final ResourceLocation INVISIBILITY_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/invisibility_filled");
    public static final ResourceLocation LEAPING_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/leaping_filled");
    public static final ResourceLocation FIRE_RESISTANCE_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/fire_resistance_filled");
    public static final ResourceLocation SWIFTNESS_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/swiftness_filled");
    public static final ResourceLocation SLOWNESS_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/slowness_filled");
    public static final ResourceLocation TURTLE_MASTER_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/turtle_master_filled");
    public static final ResourceLocation WATER_BREATHING_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/water_breathing_filled");
    public static final ResourceLocation HEALING_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/healing_filled");
    public static final ResourceLocation HARMING_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/harming_filled");
    public static final ResourceLocation POISON_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/poison_filled");
    public static final ResourceLocation REGENERATION_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/regeneration_filled");
    public static final ResourceLocation STRENGTH_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/strength_filled");
    public static final ResourceLocation WEAKNESS_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/weakness_filled");
    public static final ResourceLocation LUCK_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/luck_filled");
    public static final ResourceLocation SLOW_FALLING_FILLER = new ResourceLocation(DecorativeBottles.MODID, "layer/slow_falling_filled");

    public static final Material WATER_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, WATER_FILLER);
    public static final Material DRAGON_BREATH_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, DRAGON_BREATH_FILLER);
    public static final Material HONEY_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, HONEY_FILLER);
    public static final Material NIGHT_VISION_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, NIGHT_VISION_FILLER);
    public static final Material INVISIBILITY_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, INVISIBILITY_FILLER);
    public static final Material LEAPING_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, LEAPING_FILLER);
    public static final Material FIRE_RESISTANCE_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, FIRE_RESISTANCE_FILLER);
    public static final Material SWIFTNESS_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, SWIFTNESS_FILLER);
    public static final Material SLOWNESS_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, SLOWNESS_FILLER);
    public static final Material TURTLE_MASTER_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, TURTLE_MASTER_FILLER);
    public static final Material WATER_BREATHING_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, WATER_BREATHING_FILLER);
    public static final Material HEALING_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, HEALING_FILLER);
    public static final Material HARMING_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, HARMING_FILLER);
    public static final Material POISON_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, POISON_FILLER);
    public static final Material REGENERATION_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, REGENERATION_FILLER);
    public static final Material STRENGTH_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, STRENGTH_FILLER);
    public static final Material WEAKNESS_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, WEAKNESS_FILLER);
    public static final Material LUCK_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, LUCK_FILLER);
    public static final Material SLOW_FALLING_FILLER_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, SLOW_FALLING_FILLER);

    public static final ModelLayerLocation BOTTLE_FILLER_LAYER = new ModelLayerLocation(WATER_FILLER, "main");

    private final ModelPart oneBottleFiller;
    private final ModelPart twoBottleAFiller;
    private final ModelPart twoBottleBFiller;
    private final ModelPart threeBottleAFiller;
    private final ModelPart threeBottleBFiller;
    private final ModelPart threeBottleCFiller;
    private final ModelPart fourBottleCFiller;
    private final ModelPart fourBottleDFiller;

    public BottleBlockRenderer(BlockEntityRendererProvider.Context p_173621_) {
        ModelPart modelpart = p_173621_.bakeLayer(BOTTLE_FILLER_LAYER);
        this.oneBottleFiller = modelpart.getChild("one_root");
        this.twoBottleAFiller = modelpart.getChild("two_a_root");
        this.twoBottleBFiller = modelpart.getChild("two_b_root");
        this.threeBottleAFiller = modelpart.getChild("three_a_root");
        this.threeBottleBFiller = modelpart.getChild("three_b_root");
        this.threeBottleCFiller = modelpart.getChild("three_c_root");
        this.fourBottleCFiller = modelpart.getChild("four_c_root");
        this.fourBottleDFiller = modelpart.getChild("four_d_root");
    }

    public static LayerDefinition createBottleFillerLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition one_root = partdefinition.addOrReplaceChild("one_root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition body = one_root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition neck = one_root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition two_a_root = partdefinition.addOrReplaceChild("two_a_root", CubeListBuilder.create(), PartPose.offset(-3.5F, 24.0F, 1.5F));
        PartDefinition two_a_body = two_a_root.addOrReplaceChild("two_a_body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition two_a_neck = two_a_root.addOrReplaceChild("two_a_neck", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition two_b_root = partdefinition.addOrReplaceChild("two_b_root", CubeListBuilder.create(), PartPose.offset(3.5F, 24.0F, -1.5F));
        PartDefinition two_b_body = two_b_root.addOrReplaceChild("two_b_body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition two_b_neck = two_b_root.addOrReplaceChild("two_b_neck", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition three_a_root = partdefinition.addOrReplaceChild("three_a_root", CubeListBuilder.create(), PartPose.offset(-3.5F, 24.0F, 3.5F));
        PartDefinition three_a_body = three_a_root.addOrReplaceChild("three_a_body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition three_a_neck = three_a_root.addOrReplaceChild("three_a_neck", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition three_b_root = partdefinition.addOrReplaceChild("three_b_root", CubeListBuilder.create(), PartPose.offset(3.5F, 24.0F, 3.5F));
        PartDefinition three_b_body = three_b_root.addOrReplaceChild("three_b_body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition three_b_neck = three_b_root.addOrReplaceChild("three_b_neck", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition three_c_root = partdefinition.addOrReplaceChild("three_c_root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -3.5F));
        PartDefinition three_c_body = three_c_root.addOrReplaceChild("three_c_body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition three_c_neck = three_c_root.addOrReplaceChild("three_c_neck", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition four_c_root = partdefinition.addOrReplaceChild("four_c_root", CubeListBuilder.create(), PartPose.offset(-3.5F, 24.0F, -3.5F));
        PartDefinition four_c_body = four_c_root.addOrReplaceChild("four_c_body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition four_c_neck = four_c_root.addOrReplaceChild("four_c_neck", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition four_d_root = partdefinition.addOrReplaceChild("four_d_root", CubeListBuilder.create(), PartPose.offset(3.5F, 24.0F, -3.5F));
        PartDefinition four_d_body = four_d_root.addOrReplaceChild("four_d_body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition four_d_neck = four_d_root.addOrReplaceChild("four_d_neck", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -4.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void render(BottleBlockEntity bottleBlockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {

        ItemStack dragonBreath = Items.DRAGON_BREATH.getDefaultInstance();

        ItemStack honey = Items.HONEY_BOTTLE.getDefaultInstance();

        CompoundTag water = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER).getTag();
        CompoundTag mundane = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.MUNDANE).getTag();
        CompoundTag thick = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.THICK).getTag();
        CompoundTag awkward = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD).getTag();

        CompoundTag nightVision = PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.NIGHT_VISION).getTag();
        CompoundTag longNightVision = PotionUtils.setPotion(Items.POTION.getDefaultInstance(), Potions.LONG_NIGHT_VISION).getTag();

        CompoundTag invisibility = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.INVISIBILITY).getTag();
        CompoundTag longInvisibility = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_INVISIBILITY).getTag();

        CompoundTag leaping = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LEAPING).getTag();
        CompoundTag longLeaping = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_LEAPING).getTag();
        CompoundTag strongLeaping = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_LEAPING).getTag();

        CompoundTag fireResistance = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.FIRE_RESISTANCE).getTag();
        CompoundTag longFireResistance = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_FIRE_RESISTANCE).getTag();

        CompoundTag swiftness = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SWIFTNESS).getTag();
        CompoundTag longSwiftness = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_SWIFTNESS).getTag();
        CompoundTag strongSwiftness = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_SWIFTNESS).getTag();

        CompoundTag slowness = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOWNESS).getTag();
        CompoundTag longSlowness = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_SLOWNESS).getTag();
        CompoundTag strongSlowness = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_SLOWNESS).getTag();

        CompoundTag turtleMaster = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.TURTLE_MASTER).getTag();
        CompoundTag longTurtleMaster = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_TURTLE_MASTER).getTag();
        CompoundTag strongTurtleMaster = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_TURTLE_MASTER).getTag();

        CompoundTag waterBreathing = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER_BREATHING).getTag();
        CompoundTag longWaterBreathing = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_WATER_BREATHING).getTag();

        CompoundTag healing = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.HEALING).getTag();
        CompoundTag strongHealing = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_HEALING).getTag();

        CompoundTag harming = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.HARMING).getTag();
        CompoundTag strongHarming = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_HARMING).getTag();

        CompoundTag poison = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.POISON).getTag();
        CompoundTag longPoison = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_POISON).getTag();
        CompoundTag strongPoison = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_POISON).getTag();

        CompoundTag regeneration = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.REGENERATION).getTag();
        CompoundTag longRegeneration = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_REGENERATION).getTag();
        CompoundTag strongRegeneration = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_REGENERATION).getTag();

        CompoundTag strength = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRENGTH).getTag();
        CompoundTag longStrength = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_STRENGTH).getTag();
        CompoundTag strongStrength = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_STRENGTH).getTag();

        CompoundTag weakness = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WEAKNESS).getTag();
        CompoundTag longWeakness = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_WEAKNESS).getTag();

        CompoundTag luck = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LUCK).getTag();

        CompoundTag slowFalling = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOW_FALLING).getTag();
        CompoundTag longSlowFalling = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LONG_SLOW_FALLING).getTag();

        BlockState blockState = bottleBlockEntity.getBlockState();

        ItemStack item0 = bottleBlockEntity.getItems().get(0);
        ItemStack item1 = bottleBlockEntity.getItems().get(1);
        ItemStack item2 = bottleBlockEntity.getItems().get(2);
        ItemStack item3 = bottleBlockEntity.getItems().get(3);

//        Dragon Breath
        if (item0.is(dragonBreath.getItem())) {
            if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, DRAGON_BREATH_FILLER_LOCATION, i, j, false);
            }
            if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, DRAGON_BREATH_FILLER_LOCATION, i, j, false);
            }
            if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, DRAGON_BREATH_FILLER_LOCATION, i, j, false);
            }
            if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, DRAGON_BREATH_FILLER_LOCATION, i, j, false);
            }
        }
        if (item1.is(dragonBreath.getItem())) {
            if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, DRAGON_BREATH_FILLER_LOCATION, i, j, false);
            }
            if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, DRAGON_BREATH_FILLER_LOCATION, i, j, false);
            }
            if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, DRAGON_BREATH_FILLER_LOCATION, i, j, false);
            }
        }
        if (item2.is(dragonBreath.getItem())) {
            if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, DRAGON_BREATH_FILLER_LOCATION, i, j, false);
            }
            if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, DRAGON_BREATH_FILLER_LOCATION, i, j, false);
            }
        }
        if (item3.is(dragonBreath.getItem())) {
            if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, DRAGON_BREATH_FILLER_LOCATION, i, j, false);
            }
        }

//        Honey
        if (item0.is(honey.getItem())) {
            if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, HONEY_FILLER_LOCATION, i, j, false);
            }
            if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, HONEY_FILLER_LOCATION, i, j, false);
            }
            if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, HONEY_FILLER_LOCATION, i, j, false);
            }
            if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, HONEY_FILLER_LOCATION, i, j, false);
            }
        }
        if (item1.is(honey.getItem())) {
            if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, HONEY_FILLER_LOCATION, i, j, false);
            }
            if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, HONEY_FILLER_LOCATION, i, j, false);
            }
            if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, HONEY_FILLER_LOCATION, i, j, false);
            }
        }
        if (item2.is(honey.getItem())) {
            if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, HONEY_FILLER_LOCATION, i, j, false);
            }
            if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, HONEY_FILLER_LOCATION, i, j, false);
            }
        }
        if (item3.is(honey.getItem())) {
            if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, HONEY_FILLER_LOCATION, i, j, false);
            }
        }

//        Water, Mundane, Thick and Awkward
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(water) || item0.getTag().equals(mundane) || item0.getTag().equals(thick) || item0.getTag().equals(awkward)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, WATER_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, WATER_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, WATER_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, WATER_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(water) || item1.getTag().equals(mundane) || item1.getTag().equals(thick) || item1.getTag().equals(awkward)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, WATER_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, WATER_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, WATER_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(water) || item2.getTag().equals(mundane) || item2.getTag().equals(thick) || item2.getTag().equals(awkward)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, WATER_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, WATER_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(water) || item3.getTag().equals(mundane) || item3.getTag().equals(thick) || item3.getTag().equals(awkward)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, WATER_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Night Vision
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(nightVision) || item0.getTag().equals(longNightVision)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, NIGHT_VISION_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, NIGHT_VISION_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, NIGHT_VISION_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, NIGHT_VISION_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(nightVision) || item1.getTag().equals(longNightVision)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, NIGHT_VISION_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, NIGHT_VISION_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, NIGHT_VISION_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(nightVision) || item2.getTag().equals(longNightVision)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, NIGHT_VISION_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, NIGHT_VISION_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(nightVision) || item3.getTag().equals(longNightVision)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, NIGHT_VISION_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Invisibility
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(invisibility) || item0.getTag().equals(longInvisibility)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, INVISIBILITY_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, INVISIBILITY_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, INVISIBILITY_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, INVISIBILITY_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(invisibility) || item1.getTag().equals(longInvisibility)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, INVISIBILITY_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, INVISIBILITY_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, INVISIBILITY_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(invisibility) || item2.getTag().equals(longInvisibility)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, INVISIBILITY_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, INVISIBILITY_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(invisibility) || item3.getTag().equals(longInvisibility)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, INVISIBILITY_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Leaping
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(leaping) || item0.getTag().equals(longLeaping) || item0.getTag().equals(strongLeaping)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, LEAPING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, LEAPING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, LEAPING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, LEAPING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(leaping) || item1.getTag().equals(longLeaping) || item1.getTag().equals(strongLeaping)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, LEAPING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, LEAPING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, LEAPING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(leaping) || item2.getTag().equals(longLeaping) || item2.getTag().equals(strongLeaping)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, LEAPING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, LEAPING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(leaping) || item3.getTag().equals(longLeaping) || item3.getTag().equals(strongLeaping)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, LEAPING_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Fire Resistance
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(fireResistance) || item0.getTag().equals(longFireResistance)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, FIRE_RESISTANCE_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, FIRE_RESISTANCE_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, FIRE_RESISTANCE_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, FIRE_RESISTANCE_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(fireResistance) || item1.getTag().equals(longFireResistance)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, FIRE_RESISTANCE_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, FIRE_RESISTANCE_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, FIRE_RESISTANCE_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(fireResistance) || item2.getTag().equals(longFireResistance)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, FIRE_RESISTANCE_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, FIRE_RESISTANCE_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(fireResistance) || item3.getTag().equals(longFireResistance)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, FIRE_RESISTANCE_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Swiftness
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(swiftness) || item0.getTag().equals(longSwiftness) || item0.getTag().equals(strongSwiftness)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, SWIFTNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, SWIFTNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, SWIFTNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, SWIFTNESS_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(swiftness) || item1.getTag().equals(longSwiftness) || item1.getTag().equals(strongSwiftness)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, SWIFTNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, SWIFTNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, SWIFTNESS_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(swiftness) || item2.getTag().equals(longSwiftness) || item2.getTag().equals(strongSwiftness)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, SWIFTNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, SWIFTNESS_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(swiftness) || item3.getTag().equals(longSwiftness) || item3.getTag().equals(strongSwiftness)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, SWIFTNESS_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Slowness
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(slowness) || item0.getTag().equals(longSlowness) || item0.getTag().equals(strongSlowness)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, SLOWNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, SLOWNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, SLOWNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, SLOWNESS_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(slowness) || item1.getTag().equals(longSlowness) || item1.getTag().equals(strongSlowness)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, SLOWNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, SLOWNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, SLOWNESS_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(slowness) || item2.getTag().equals(longSlowness) || item2.getTag().equals(strongSlowness)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, SLOWNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, SLOWNESS_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(slowness) || item3.getTag().equals(longSlowness) || item3.getTag().equals(strongSlowness)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, SLOWNESS_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Turtle Master
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(turtleMaster) || item0.getTag().equals(longTurtleMaster) || item0.getTag().equals(strongTurtleMaster)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, TURTLE_MASTER_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, TURTLE_MASTER_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, TURTLE_MASTER_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, TURTLE_MASTER_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(turtleMaster) || item1.getTag().equals(longTurtleMaster) || item1.getTag().equals(strongTurtleMaster)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, TURTLE_MASTER_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, TURTLE_MASTER_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, TURTLE_MASTER_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(turtleMaster) || item2.getTag().equals(longTurtleMaster) || item2.getTag().equals(strongTurtleMaster)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, TURTLE_MASTER_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, TURTLE_MASTER_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(turtleMaster) || item3.getTag().equals(longTurtleMaster) || item3.getTag().equals(strongTurtleMaster)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, TURTLE_MASTER_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Water Breathing
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(waterBreathing) || item0.getTag().equals(longWaterBreathing)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, WATER_BREATHING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, WATER_BREATHING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, WATER_BREATHING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, WATER_BREATHING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(waterBreathing) || item1.getTag().equals(longWaterBreathing)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, WATER_BREATHING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, WATER_BREATHING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, WATER_BREATHING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(waterBreathing) || item2.getTag().equals(longWaterBreathing)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, WATER_BREATHING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, WATER_BREATHING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(waterBreathing) || item3.getTag().equals(longWaterBreathing)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, WATER_BREATHING_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Healing
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(healing) || item0.getTag().equals(strongHealing)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, HEALING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, HEALING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, HEALING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, HEALING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(healing) || item1.getTag().equals(strongHealing)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, HEALING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, HEALING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, HEALING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(healing) || item2.getTag().equals(strongHealing)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, HEALING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, HEALING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(healing) || item3.getTag().equals(strongHealing)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, HEALING_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Harming
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(harming) || item0.getTag().equals(strongHarming)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, HARMING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, HARMING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, HARMING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, HARMING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(harming) || item1.getTag().equals(strongHarming)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, HARMING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, HARMING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, HARMING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(harming) || item2.getTag().equals(strongHarming)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, HARMING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, HARMING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(harming) || item3.getTag().equals(strongHarming)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, HARMING_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Poison
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(poison) || item0.getTag().equals(longPoison) || item0.getTag().equals(strongPoison)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, POISON_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, POISON_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, POISON_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, POISON_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(poison) || item1.getTag().equals(longPoison) || item1.getTag().equals(strongPoison)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, POISON_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, POISON_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, POISON_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(poison) || item2.getTag().equals(longPoison) || item2.getTag().equals(strongPoison)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, POISON_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, POISON_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(poison) || item3.getTag().equals(longPoison) || item3.getTag().equals(strongPoison)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, POISON_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Regeneration
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(regeneration) || item0.getTag().equals(longRegeneration) || item0.getTag().equals(strongRegeneration)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, REGENERATION_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, REGENERATION_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, REGENERATION_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, REGENERATION_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(regeneration) || item1.getTag().equals(longRegeneration) || item1.getTag().equals(strongRegeneration)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, REGENERATION_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, REGENERATION_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, REGENERATION_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(regeneration) || item2.getTag().equals(longRegeneration) || item2.getTag().equals(strongRegeneration)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, REGENERATION_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, REGENERATION_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(regeneration) || item3.getTag().equals(longRegeneration) || item3.getTag().equals(strongRegeneration)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, REGENERATION_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Strength
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(strength) || item0.getTag().equals(longStrength) || item0.getTag().equals(strongStrength)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, STRENGTH_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, STRENGTH_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, STRENGTH_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, STRENGTH_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(strength) || item1.getTag().equals(longStrength) || item1.getTag().equals(strongStrength)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, STRENGTH_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, STRENGTH_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, STRENGTH_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(strength) || item2.getTag().equals(longStrength) || item2.getTag().equals(strongStrength)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, STRENGTH_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, STRENGTH_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(strength) || item3.getTag().equals(longStrength) || item3.getTag().equals(strongStrength)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, STRENGTH_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Weakness
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(weakness) || item0.getTag().equals(longWeakness)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, WEAKNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, WEAKNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, WEAKNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, WEAKNESS_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(weakness) || item1.getTag().equals(longWeakness)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, WEAKNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, WEAKNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, WEAKNESS_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(weakness) || item2.getTag().equals(longWeakness)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, WEAKNESS_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, WEAKNESS_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(weakness) || item3.getTag().equals(longWeakness)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, WEAKNESS_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Luck
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(luck)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, LUCK_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, LUCK_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, LUCK_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, LUCK_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(luck)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, LUCK_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, LUCK_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, LUCK_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(luck)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, LUCK_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, LUCK_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(luck)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, LUCK_FILLER_LOCATION, i, j, false);
                }
            }
        }

//        Slow Falling
        if (!(item0.getTag() == null)) {
            if (item0.getTag().equals(slowFalling) || item0.getTag().equals(longSlowFalling)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 1) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.oneBottleFiller, SLOW_FALLING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleAFiller, SLOW_FALLING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, SLOW_FALLING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleAFiller, SLOW_FALLING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item1.getTag() == null)) {
            if (item1.getTag().equals(slowFalling) || item1.getTag().equals(longSlowFalling)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 2) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.twoBottleBFiller, SLOW_FALLING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, SLOW_FALLING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleBFiller, SLOW_FALLING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item2.getTag() == null)) {
            if (item2.getTag().equals(slowFalling) || item2.getTag().equals(longSlowFalling)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 3) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.threeBottleCFiller, SLOW_FALLING_FILLER_LOCATION, i, j, false);
                }
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleCFiller, SLOW_FALLING_FILLER_LOCATION, i, j, false);
                }
            }
        }
        if (!(item3.getTag() == null)) {
            if (item3.getTag().equals(slowFalling) || item3.getTag().equals(longSlowFalling)) {
                if (blockState.getValue(BottleBlock.BOTTLES) == 4) {
                    this.renderGlowingBottle(bottleBlockEntity, poseStack, multiBufferSource, this.fourBottleDFiller, SLOW_FALLING_FILLER_LOCATION, i, j, false);
                }
            }
        }
    }

    private void renderBottle(BottleBlockEntity p_112435_, PoseStack p_173542_, MultiBufferSource p_173543_, ModelPart p_173544_, Material p_173546_, int p_173547_, int p_173548_, boolean p_173549_) {
        BlockState blockstate = p_112435_.getBlockState();

        p_173542_.pushPose();
        p_173542_.translate(0.5D, 1.5D, 0.5D);
        float f = blockstate.getValue(BottleBlock.FACING).getCounterClockWise().toYRot() - 90F;
        p_173542_.mulPose(Axis.YP.rotationDegrees(-f));
        p_173542_.mulPose(Axis.ZP.rotationDegrees(180F));
        VertexConsumer vertexconsumer = p_173546_.buffer(p_173543_, RenderType::entitySolid);
        p_173544_.render(p_173542_, vertexconsumer, p_173547_, p_173548_);
        p_173542_.popPose();
    }

    private void renderGlowingBottle(BottleBlockEntity bottleBlockEntity, PoseStack p_173542_, MultiBufferSource p_173543_, ModelPart p_173544_, Material p_173546_, int p_173547_, int p_173548_, boolean p_173549_) {
        BlockState blockstate = bottleBlockEntity.getBlockState();

        p_173542_.pushPose();
        p_173542_.translate(0.5D, 1.5D, 0.5D);
        float f = blockstate.getValue(BottleBlock.FACING).getCounterClockWise().toYRot() - 90F;
        p_173542_.mulPose(Axis.YP.rotationDegrees(-f));
        p_173542_.mulPose(Axis.ZP.rotationDegrees(180F));
        VertexConsumer vertexconsumer = p_173546_.buffer(p_173543_, RenderType::dragonExplosionAlpha);
        p_173544_.render(p_173542_, vertexconsumer, p_173547_, p_173548_);
        p_173542_.popPose();
    }
}