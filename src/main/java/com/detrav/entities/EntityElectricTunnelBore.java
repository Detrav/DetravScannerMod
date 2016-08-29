package com.detrav.entities;

import com.detrav.DetravScannerMod;
import com.detrav.enums.DetravItemList;
import com.detrav.items.DetravMetaGeneratedTool01;
import gregtech.api.enums.Materials;
import mods.railcraft.api.carts.ILinkableCart;
import mods.railcraft.api.electricity.IElectricMinecart;
import mods.railcraft.common.carts.CartContainerBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Detrav on 25.08.2016.
 */
public class EntityElectricTunnelBore extends CartContainerBase implements IInventory, ILinkableCart, IElectricMinecart {

    protected static final int WATCHER_ID_FACING = 5;
    public static final float LENGTH = 6f;
    private boolean hasInit;
    Materials aMaterial;
    public static final float SPEED = 0.2f;

    public EntityElectricTunnelBore(World world, double i, double j, double k) {
        this(world, i, j, k, ForgeDirection.SOUTH);
    }

    public EntityElectricTunnelBore(World world, double i, double j, double k, ForgeDirection f) {
        super(world);
        aMaterial = Materials._NULL;
        hasInit = true;
        setPosition(i, j + (double) yOffset, k);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = i;
        prevPosY = j;
        prevPosZ = k;
//        cargoItems = new ItemStack[25];
        setFacing(f);
        setSize(LENGTH / 2.0F, 2.7F /2.0F);
    }

    public EntityElectricTunnelBore(World world) {
        this(world, 0, 0, 0, ForgeDirection.SOUTH);
    }


    @Override
    public boolean isLinkable() {
        return true;
    }

    @Override
    public boolean canLinkWithCart(EntityMinecart entityMinecart) {
        return true;
    }

    @Override
    public boolean hasTwoLinks() {
        return true;
    }

    @Override
    public float getLinkageDistance(EntityMinecart entityMinecart) {
        return 1.25f;
    }

    @Override
    public float getOptimalDistance(EntityMinecart entityMinecart) {
        return 1f;
    }

    @Override
    public boolean canBeAdjusted(EntityMinecart entityMinecart) {
        return false;
    }

    @Override
    public void onLinkCreated(EntityMinecart entityMinecart) {

    }

    public final ForgeDirection getFacing() {
        return ForgeDirection.getOrientation(dataWatcher.getWatchableObjectByte(WATCHER_ID_FACING));
    }

    protected final void setFacing(ForgeDirection facing) {
        dataWatcher.updateObject(WATCHER_ID_FACING, (byte) facing.ordinal());

        setYaw();
    }

    private void setYaw() {
        float yaw = 0;
        switch (getFacing()) {
            case NORTH:
                yaw = 90;
                break;
            case EAST:
                yaw = 0;
                break;
            case SOUTH:
                yaw = 270;
                break;
            case WEST:
                yaw = 180;
                break;
        }
        setRotation(yaw, rotationPitch);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound data) {
//        fuel = getFuel();
        super.writeEntityToNBT(data);
        data.setByte("facing", (byte) getFacing().ordinal());
        data.setString("material", aMaterial.toString());
        //data.setInteger("delay", getDelay());
        //data.setBoolean("active", isActive());
        //data.setInteger("burnTime", getBurnTime());
        //data.setInteger("fuel", fuel);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound data) {
        super.readEntityFromNBT(data);
        setFacing(ForgeDirection.getOrientation(data.getByte("facing")));
        setMaterial(Materials.getRealMaterial(data.getString("material")));
        //setDelay(data.getInteger("delay"));
        //setActive(data.getBoolean("active"));
        //setBurnTime(data.getInteger("burnTime"));
        //setFuel(data.getInteger("fuel"));
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        //dataWatcher.addObject(WATCHER_ID_FUEL, (byte) 0);
        //dataWatcher.addObject(WATCHER_ID_MOVING, (byte) 0);
        //dataWatcher.addObjectByDataType(WATCHER_ID_BORE_HEAD, 5);
        dataWatcher.addObject(WATCHER_ID_FACING, (byte) 0);
    }

    @Override
    public void onLinkBroken(EntityMinecart entityMinecart) {

    }

    @Override
    public ChargeHandler getChargeHandler() {
        return null;
    }

    @Override
    public List<ItemStack> getItemsDropped() {
        ArrayList items = new ArrayList();
        items.add(this.getCartItem());
        return items;
    }

    @Override
    public ItemStack getCartItem() {
        return DetravMetaGeneratedTool01.INSTANCE.getToolWithStats(4, 1, aMaterial, Materials._NULL, null);
        //return DetravItemList.Electric_Tunel_Bore.get(1L, new Object[0]);
    }

    @Override
    public int getMinecartType() {
        return -1;
    }

    @Override
    public int getSizeInventory() {
        return 25;
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity other) {
        if (other instanceof EntityLivingBase)
            return other.boundingBox;
        return null;
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return boundingBox;
    }

    @Override
    public void setPosition(double i, double j, double k) {
        if (!hasInit) {
            super.setPosition(i, j, k);
            return;
        }

        posX = i;
        posY = j;
        posZ = k;
        double w = 2.7 / 4.0;
        double h = 2.7 / 2.0;
        double l = LENGTH / 4.0;
        double x1 = i;
        double x2 = i;
        double z1 = k;
        double z2 = k;
        if (getFacing() == ForgeDirection.WEST || getFacing() == ForgeDirection.EAST) {
            x1 -= l;
            x2 += l;
            z1 -= w;
            z2 += w;
        } else {
            x1 -= w;
            x2 += w;
            z1 -= l;
            z2 += l;
        }

        boundingBox.setBounds(x1, (j - (double) yOffset) + (double) ySize, z1, x2, (j - (double) yOffset) + (double) ySize + h, z2);
    }

    public short[] getBaseColor()
    {
        return aMaterial.getRGBA();
    }

    public void setMaterial(Materials mMaterial)
    {
        aMaterial = mMaterial;
    }

    public Materials getMaterial() {
        return aMaterial;
    }

    @Override
    public boolean doInteract(EntityPlayer player) {
        DetravScannerMod.proxy.openElectricTunnelBoreGui(this.getEntityId(),player);
        return true;
    }

    @Override
    public float getMaxCartSpeedOnRail() {
        return SPEED*(aMaterial.dlevel + 1);
    }
}
