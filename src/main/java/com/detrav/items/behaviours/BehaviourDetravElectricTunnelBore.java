package com.detrav.items.behaviours;

import com.detrav.entities.EntityElectricTunnelBore;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.common.items.behaviors.Behaviour_None;
import mods.railcraft.api.carts.CartTools;
import mods.railcraft.common.blocks.tracks.TrackTools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Detrav on 25.08.2016.
 */
public class BehaviourDetravElectricTunnelBore extends Behaviour_None {
    @Override
    public boolean onItemUse(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
        Block block = aWorld.getBlock(aX,aY,aZ);
        if(TrackTools.isRailBlock(block))
        {
            if (!aWorld.isRemote) {
                int meta = ((BlockRailBase) block).getBasicRailMetadata(aWorld, null, aX, aY, aZ);
                if (meta == 0 || meta == 1) {
                    int playerYaw = -90 - MathHelper.floor_float(aPlayer.rotationYaw);
                    for (; playerYaw > 360; playerYaw -= 360);
                    for (; playerYaw < 0; playerYaw += 360);
                    ForgeDirection facing = ForgeDirection.EAST;
                    if (Math.abs(90 - playerYaw) <= 45) {
                        facing = ForgeDirection.NORTH;
                    } else if (Math.abs(180 - playerYaw) <= 45) {
                        facing = ForgeDirection.WEST;
                    } else if (Math.abs(270 - playerYaw) <= 45) {
                        facing = ForgeDirection.SOUTH;
                    }

                    if (meta == 0 && facing == ForgeDirection.WEST) {
                        facing = ForgeDirection.NORTH;
                    } else if (meta == 0 && facing == ForgeDirection.EAST) {
                        facing = ForgeDirection.SOUTH;
                    } else if (meta == 1 && facing == ForgeDirection.SOUTH) {
                        facing = ForgeDirection.EAST;
                    } else if (meta == 1 && facing == ForgeDirection.NORTH) {
                        facing = ForgeDirection.WEST;
                    }

//					System.out.println("PlayerYaw = " + playerYaw + " Yaw = " + facing + " Meta = " + meta);

                    EntityMinecart bore = new EntityElectricTunnelBore(aWorld, (float) aX + 0.5F, (float) aY, (float) aZ + 0.5F, facing);
                    CartTools.setCartOwner(bore, aPlayer);
                    aWorld.spawnEntityInWorld(bore);
                    aStack.stackSize -= 1;
                }
            }
            return true;
        }
        return false;
    }
}
