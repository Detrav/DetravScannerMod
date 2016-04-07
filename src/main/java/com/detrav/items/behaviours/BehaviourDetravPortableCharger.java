package com.detrav.items.behaviours;

import com.detrav.DetravScannerMod;
import com.detrav.gui.containers.DetravPortableChargerContainer;
import com.detrav.items.DetravMetaGeneratedTool01;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.common.items.behaviors.Behaviour_None;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by wital_000 on 07.04.2016.
 */
public class BehaviourDetravPortableCharger extends Behaviour_None {

    public ItemStack onItemRightClick(GT_MetaBase_Item aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
        DetravScannerMod.proxy.openPortableChargerGui(aPlayer);
        return super.onItemRightClick(aItem, aStack, aWorld, aPlayer);
        //aPlayer.openContainer
    }

    public void onUpdate(GT_MetaBase_Item aItem, ItemStack aStack, World aWorld, Entity aPlayer, int aTimer, boolean aIsInHand) {
        if(!aIsInHand) return;
        //if(aStack.getm)
        if(aWorld.isRemote) return;
        if(aPlayer instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)aPlayer;
            if(player.openContainer !=null)
            {
                if(player.openContainer instanceof DetravPortableChargerContainer)
                {
                    DetravPortableChargerContainer container = (DetravPortableChargerContainer)player.openContainer;
                    container.onUpdate(aItem,aStack,aWorld,aPlayer,1);
                }
                Long[] selfStats = DetravMetaGeneratedTool01.INSTANCE.getElectricStats(aStack);
                if(selfStats == null) return;

                long selfCharge = DetravMetaGeneratedTool01.INSTANCE.getRealCharge(aStack);
                if(selfCharge <0) return;
                //update info
                //long max = DetravMetaGeneratedTool01.getToolMaxDamage(aStack);
                //double p = ((double)selfCharge)/((double)selfStats[0]);
                //DetravMetaGeneratedTool01.setToolDamage(aStack,(long) (max * p) + 200);
            }
        }
    }

}
