package com.detrav.net;

import com.detrav.entities.EntityElectricTunnelBore;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import gregtech.api.enums.Materials;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

/**
 * Created by Detrav on 26.08.2016.
 */
public class DetravAttrResponseEntity05 extends DetravPacket {

    int eId;
    String eMaterial;

    @Override
    public int getPacketID() {
        return 5;
    }

    public DetravAttrResponseEntity05()
    {

    }

    public DetravAttrResponseEntity05(int aId, String aMaterial)
    {
        eId = aId;
        eMaterial = aMaterial;
    }

    @Override
    public byte[] encode() {
        ByteArrayDataOutput tOut = ByteStreams.newDataOutput(1);
        tOut.writeInt(eId);
        tOut.writeUTF(eMaterial);
        return tOut.toByteArray();
    }

    @Override
    public Object decode(ByteArrayDataInput aData) {
        int aId = aData.readInt();
        String aMaterual = aData.readUTF();
        return new DetravAttrResponseEntity05(aId, aMaterual);
    }


    @Override
    public void process() {
        Entity e1 = Minecraft.getMinecraft().thePlayer.worldObj.getEntityByID(eId);
        if (!(e1 instanceof EntityElectricTunnelBore)) return;

        EntityElectricTunnelBore bore = (EntityElectricTunnelBore)e1;
        Materials aMaterial = Materials.getRealMaterial(eMaterial);
        bore.setMaterial(aMaterial);
    }
}
