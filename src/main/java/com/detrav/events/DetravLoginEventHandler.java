package com.detrav.events;

import com.detrav.entities.EntityElectricTunnelBore;
import com.detrav.net.DetravAttrRequestEntity04;
import com.detrav.net.DetravModePacket03;
import com.detrav.net.DetravNetwork;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

/**
 * Created by wital_000 on 18.04.2016.
 */
public class DetravLoginEventHandler {
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.isCanceled()) return;
        if (!event.world.isRemote) {
            if (event.entity instanceof EntityPlayerMP) {
                DetravNetwork.INSTANCE.sendToPlayer(new DetravModePacket03((EntityPlayerMP) event.entity), (EntityPlayerMP) event.entity);
            }
        }
        if (event.world.isRemote) {
            if (event.entity instanceof EntityElectricTunnelBore) {
                DetravNetwork.INSTANCE.sendToServer(
                        new DetravAttrRequestEntity04(
                                event.entity.getEntityId(),
                                Minecraft.getMinecraft().thePlayer.getEntityId(),
                                Minecraft.getMinecraft().thePlayer.worldObj.provider.dimensionId
                        ));
            }
        }
        //if(Minecraft.getMinecraft().thePlayer!=null)
        //Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(""+(event.entity instanceof EntityPlayerMP)+" | " + event.world.isRemote));
    }


    static boolean inited = false;

    public static void register() {
        if (!inited) {
            inited = true;
            DetravLoginEventHandler handler = new DetravLoginEventHandler();
            MinecraftForge.EVENT_BUS.register(handler);
            FMLCommonHandler.instance().bus().register(handler);
        }
    }
}
