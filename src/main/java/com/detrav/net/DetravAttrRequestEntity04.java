package com.detrav.net;

import com.detrav.entities.EntityElectricTunnelBore;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by Detrav on 26.08.2016.
 */
public class DetravAttrRequestEntity04 extends DetravPacket {

    int eWorld;
    int eId;
    int ePlayer;

    @Override
    public int getPacketID() {
        return 4;
    }

    public DetravAttrRequestEntity04() {

    }

    public DetravAttrRequestEntity04(int aId, int aPlayer, int aWorld) {
        eId = aId;
        ePlayer = aPlayer;
        eWorld = aWorld;
    }

    @Override
    public byte[] encode() {
        ByteArrayDataOutput tOut = ByteStreams.newDataOutput(1);
        tOut.writeInt(eId);
        tOut.writeInt(ePlayer);
        tOut.writeInt(eWorld);
        return tOut.toByteArray();
    }

    @Override
    public Object decode(ByteArrayDataInput aData) {
        int aId = aData.readInt();
        int aPlayer = aData.readInt();
        int aWorld = aData.readInt();
        return new DetravAttrRequestEntity04(aId, aPlayer, aWorld);
    }

    @Override
    public void process() {
        WorldServer[] Worlds = DimensionManager.getWorlds();
        World aWorld = null;
        for (WorldServer world : Worlds) {
            if (world.provider.dimensionId == eWorld) {
                aWorld = world;
                break;
            }
        }
        if (aWorld == null) return;
        Entity e1 = aWorld.getEntityByID(eId);
        if (!(e1 instanceof EntityElectricTunnelBore)) return;
        Entity e2 = aWorld.getEntityByID(ePlayer);
        if (!(e2 instanceof EntityPlayerMP)) return;

        EntityElectricTunnelBore bore = (EntityElectricTunnelBore)e1;
        EntityPlayerMP player = (EntityPlayerMP)e2;

        DetravNetwork.INSTANCE.sendToPlayer(new DetravAttrResponseEntity05(bore.getEntityId(),bore.getMaterial().toString()), player);
        //player.addChatMessage(new ChatComponentText("test"));
    }
}
