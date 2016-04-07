package com.detrav.items.tools;

import com.detrav.enums.DetravSimpleItems;
import com.detrav.enums.Textures01;
import com.detrav.items.behaviours.BehaviourDetravPortableCharger;
import com.detrav.items.behaviours.BehaviourDetravToolProPick;
import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.damagesources.GT_DamageSources;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.interfaces.IToolStats;
import gregtech.api.items.GT_MetaGenerated_Tool;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

/**
 * Created by wital_000 on 07.04.2016.
 */
public class DetravToolPortableCharger implements IToolStats {
    public int getToolDamagePerBlockBreak() {
        return GT_Mod.gregtechproxy.mHardRock ? 50 : 100;
    }

    public int getToolDamagePerDropConversion() {
        return 100;
    }

    public int getToolDamagePerContainerCraft() {
        return 100;
    }

    public int getToolDamagePerEntityAttack() {
        return 200;
    }

    public int getBaseQuality() {
        return 0;
    }

    public float getBaseDamage() {
        return 1.0F;
    }

    @Override
    public int getHurtResistanceTime(int i, Entity entity) {
        return i;
    }

    public float getSpeedMultiplier() {
        return 1F;
    }

    public float getMaxDurabilityMultiplier() {
        return 1F;
    }

    @Override
    public DamageSource getDamageSource(EntityLivingBase aPlayer, Entity aEntity) {
        return GT_DamageSources.getCombatDamage((aPlayer instanceof EntityPlayer) ? "player" : "mob", aPlayer, (aEntity instanceof EntityLivingBase) ? getDeathMessage(aPlayer, (EntityLivingBase) aEntity) : null);
    }

    public String getCraftingSound() {
        return null;
    }

    public String getEntityHitSound() {
        return null;
    }

    public String getBreakingSound() {
        return (String) GregTech_API.sSoundList.get(Integer.valueOf(0));
    }

    @Override
    public Enchantment[] getEnchantments(ItemStack itemStack) {
        return new Enchantment[0];
    }

    @Override
    public int[] getEnchantmentLevels(ItemStack itemStack) {
        return new int[0];
    }

    public String getMiningSound() {
        return null;
    }

    public boolean canBlock() {
        return false;
    }

    public boolean isCrowbar() {
        return false;
    }

    @Override
    public boolean isGrafter() {
        return false;
    }

    @Override
    public boolean isChainsaw() {
        return false;
    }

    @Override
    public boolean isWrench() {
        return false;
    }

    @Override
    public boolean isWeapon() {
        return false;
    }

    @Override
    public boolean isRangedWeapon() {
        return false;
    }

    @Override
    public boolean isMiningTool() {
        return false;
    }

    public boolean isMinableBlock(Block aBlock, byte aMetaData) {
        return false;
    }

    @Override
    public int convertBlockDrops(List<ItemStack> list, ItemStack itemStack, EntityPlayer entityPlayer, Block block, int i, int i1, int i2, byte b, int i3, boolean b1, BlockEvent.HarvestDropsEvent harvestDropsEvent) {
        return 0;
    }

    public ItemStack getBrokenItem(ItemStack aStack) {
        return null;
    }

    @Override
    public float getNormalDamageAgainstEntity(float v, Entity entity, ItemStack itemStack, EntityPlayer entityPlayer) {
        return v;
    }

    @Override
    public float getMagicDamageAgainstEntity(float v, Entity entity, ItemStack itemStack, EntityPlayer entityPlayer) {
        return v;
    }

    public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ?
                Textures01.mTextures[4]:
                Textures01.mTextures[5];
    }

    public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? GT_MetaGenerated_Tool.getPrimaryMaterial(aStack).mRGBa : GT_MetaGenerated_Tool.getSecondaryMaterial(aStack).mRGBa;
    }

    public void onStatsAddedToTool(GT_MetaGenerated_Tool aItem, int aID) {
        aItem.addItemBehavior(aID, new BehaviourDetravPortableCharger());
    }

    public void onToolCrafted(ItemStack aStack, EntityPlayer aPlayer) {

        aPlayer.triggerAchievement(AchievementList.openInventory);
        aPlayer.triggerAchievement(AchievementList.mineWood);
        aPlayer.triggerAchievement(AchievementList.buildWorkBench);
    }

    public IChatComponent getDeathMessage(EntityLivingBase aPlayer, EntityLivingBase aEntity) {
        return new ChatComponentText(EnumChatFormatting.RED + aEntity.getCommandSenderName() + EnumChatFormatting.WHITE + " \"CHARGED!\" by " + EnumChatFormatting.GREEN + aPlayer.getCommandSenderName() + EnumChatFormatting.WHITE);
    }
}
