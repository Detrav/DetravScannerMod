package com.detrav.gui.containers;

import com.detrav.entities.EntityElectricTunnelBore;
import forestry.core.gui.slots.SlotLocked;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Detrav on 26.08.2016.
 */
public class DetravElectricTunnelBoreContainer extends Container {



    public DetravElectricTunnelBoreContainer(InventoryPlayer inv, EntityElectricTunnelBore bore) {


        addSlotToContainer(new Slot(bore,0,17,6));//drill
        addSlotToContainer(new Slot(bore,1,36,6));//battery

        for (int i = 0; i < 9; i++)
            addSlotToContainer(new Slot(bore,i+ 2,8 + i * 18,44));//bricks

        for (int i = 0; i < 9; i++)
            addSlotToContainer(new Slot(bore,i+11,8 + i * 18,63));//torch

        for (int i = 0; i < 9; i++)
            addSlotToContainer(new Slot(bore,i+20,8 + i * 18,63));//rails

        bindPlayerInventory(inv);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer,
                        j + i * 9 + 9,
                        8 + j * 18,
                        112 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            ItemStack stackInSlot = inventoryPlayer.getStackInSlot(i);
                addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 170));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int aSlot)
    {
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }
}
