package com.detrav.gui;

import com.detrav.entities.EntityElectricTunnelBore;
import com.detrav.gui.containers.DetravElectricTunnelBoreContainer;
import com.detrav.items.DetravMetaGeneratedTool01;
import gregtech.api.GregTech_API;
import mods.railcraft.client.gui.EntityGui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Detrav on 26.08.2016.
 */
public class DetravGuiElectricTunnelBore extends GuiContainer {
    public static final int GUI_ID = 50;

    EntityElectricTunnelBore bore;
    private static final ResourceLocation back = new ResourceLocation("gregtech:textures/gui/gui_bore.png");


    public DetravGuiElectricTunnelBore(InventoryPlayer inv, EntityElectricTunnelBore aBore)
    {
        super(new DetravElectricTunnelBoreContainer(inv,aBore));
        bore = aBore;
        xSize = 176;
        ySize = 194;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        mc.renderEngine.bindTexture(back);
        if (bore.getMaterial() != null) {
            short[] tColors = bore.getMaterial().getRGBA();
            GL11.glColor4f(tColors[0]/255F, tColors[1]/255F, tColors[2]/255F, 1F);
        } else GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
