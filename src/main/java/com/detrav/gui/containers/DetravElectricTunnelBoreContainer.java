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

        addSlotToContainer(new Slot(bore,2,17,44));//bricks in
        addSlotToContainer(new Slot(bore,3,36,44));//bricks out

        addSlotToContainer(new Slot(bore,4,17,63));//torch in
        addSlotToContainer(new Slot(bore,5,36,63));//torch out

        addSlotToContainer(new Slot(bore,6,17,82));//torch in
        addSlotToContainer(new Slot(bore,7,36,82));//torch out


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
