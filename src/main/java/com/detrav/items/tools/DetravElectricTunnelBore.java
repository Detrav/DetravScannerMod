package com.detrav.items.tools;

import com.detrav.enums.Textures01;
import com.detrav.items.behaviours.BehaviourDetravElectricTunnelBore;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.interfaces.IToolStats;
import gregtech.api.items.GT_MetaGenerated_Tool;
import mods.railcraft.common.core.Railcraft;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

/**
 * Created by Detrav on 26.08.2016.
 */
public class DetravElectricTunnelBore implements IToolStats {
    @Override
    public void onToolCrafted(ItemStack itemStack, EntityPlayer entityPlayer) {

    }

    @Override
    public void onStatsAddedToTool(GT_MetaGenerated_Tool gt_metaGenerated_tool, int i) {
        gt_metaGenerated_tool.addItemBehavior(i, new BehaviourDetravElectricTunnelBore());
    }

    @Override
    public int getToolDamagePerBlockBreak() {
        return 0;
    }

    @Override
    public int getToolDamagePerDropConversion() {
        return 0;
    }

    @Override
    public int getToolDamagePerContainerCraft() {
        return 0;
    }

    @Override
    public int getToolDamagePerEntityAttack() {
        return 0;
    }

    @Override
    public int getBaseQuality() {
        return 0;
    }

    @Override
    public float getBaseDamage() {
        return 0;
    }

    @Override
    public int getHurtResistanceTime(int i, Entity entity) {
        return 0;
    }

    @Override
    public float getSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getMaxDurabilityMultiplier() {
        return 0;
    }

    @Override
    public DamageSource getDamageSource(EntityLivingBase entityLivingBase, Entity entity) {
        return null;
    }

    @Override
    public String getMiningSound() {
        return null;
    }

    @Override
    public String getCraftingSound() {
        return null;
    }

    @Override
    public String getEntityHitSound() {
        return null;
    }

    @Override
    public String getBreakingSound() {
        return null;
    }

    @Override
    public Enchantment[] getEnchantments(ItemStack itemStack) {
        return new Enchantment[0];
    }

    @Override
    public int[] getEnchantmentLevels(ItemStack itemStack) {
        return new int[0];
    }

    @Override
    public boolean canBlock() {
        return false;
    }

    @Override
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

    @Override
    public boolean isMinableBlock(Block block, byte b) {
        return false;
    }

    @Override
    public int convertBlockDrops(List<ItemStack> list, ItemStack itemStack, EntityPlayer entityPlayer, Block block, int i, int i1, int i2, byte b, int i3, boolean b1, BlockEvent.HarvestDropsEvent harvestDropsEvent) {
        return 0;
    }

    @Override
    public ItemStack getBrokenItem(ItemStack itemStack) {
        return null;
    }

    @Override
    public float getNormalDamageAgainstEntity(float v, Entity entity, ItemStack itemStack, EntityPlayer entityPlayer) {
        return 0;
    }

    @Override
    public float getMagicDamageAgainstEntity(float v, Entity entity, ItemStack itemStack, EntityPlayer entityPlayer) {
        return 0;
    }

    @Override
    public IIconContainer getIcon(boolean b, ItemStack itemStack) {
        return Textures01.mTextures[7];
    }

    @Override
    public short[] getRGBa(boolean b, ItemStack itemStack) {
        return GT_MetaGenerated_Tool.getPrimaryMaterial(itemStack).mRGBa;
    }
}
