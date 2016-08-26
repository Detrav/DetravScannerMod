package com.detrav.entities;

import com.detrav.enums.DetravItemList;
import mods.railcraft.api.carts.ILinkableCart;
import mods.railcraft.api.electricity.IElectricMinecart;
import mods.railcraft.common.carts.CartContainerBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Detrav on 25.08.2016.
 */
public class EntityElectricTunnelBore extends CartContainerBase implements IInventory, ILinkableCart, IElectricMinecart {

    protected static final int WATCHER_ID_FACING = 5;

    public EntityElectricTunnelBore(World world, double i, double j, double k) {
        this(world, i, j, k, ForgeDirection.SOUTH);
    }

    public EntityElectricTunnelBore(World world, double i, double j, double k, ForgeDirection f) {
        super(world);
        //hasInit = true;
        setPosition(i, j + (double) yOffset, k);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = i;
        prevPosY = j;
        prevPosZ = k;
//        cargoItems = new ItemStack[25];
        setFacing(f);
        //setSize(LENGTH, 2.7F);
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
        //data.setInteger("delay", getDelay());
        //data.setBoolean("active", isActive());
        //data.setInteger("burnTime", getBurnTime());
        //data.setInteger("fuel", fuel);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound data) {
        super.readEntityFromNBT(data);
        setFacing(ForgeDirection.getOrientation(data.getByte("facing")));
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
        return DetravItemList.Electric_Tunel_Bore.get(1L,new Object[0]);
    }

    @Override
    public int getMinecartType() {
        return -1;
    }

    @Override
    public int getSizeInventory() {
        return 25;
    }
}
