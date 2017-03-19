package com.detrav.items.behaviours;

import com.detrav.items.DetravMetaGeneratedTool01;
import com.detrav.net.DetravNetwork;
import com.detrav.net.DetravProPickPacket00;
import gregtech.api.GregTech_API;
import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.objects.ItemData;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import gregtech.common.blocks.GT_Block_Ores_Abstract;
import gregtech.common.blocks.GT_TileEntity_Ores;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wital_000 on 19.03.2016.
 */
public class BehaviourDetravToolElectricProPick extends BehaviourDetravToolProPick {

    public BehaviourDetravToolElectricProPick(int aCosts) {
        super(aCosts);
    }

    public ItemStack onItemRightClick(GT_MetaBase_Item aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {

        if (!aWorld.isRemote) {
            int data = DetravMetaGeneratedTool01.INSTANCE.getToolGTDetravData(aStack).intValue();
            //Проверяем если нажат шифт
            if (aPlayer.isSneaking()) {
                data++;
                if (data > 3) data = 0;
                switch (data) {
                    case 0:
                        aPlayer.addChatMessage(new ChatComponentText("Set Mode: Ore, Any Rock Block"));
                        break;
                    case 1:
                        aPlayer.addChatMessage(new ChatComponentText("Set Mode: Ore (with small), Any Rock Block"));
                        break;
                    case 2:
                        aPlayer.addChatMessage(new ChatComponentText("Set Mode: Oil, Any Block"));
                        break;
                    case 3:
                        aPlayer.addChatMessage(new ChatComponentText("Set Mode: Pollution, Any Block"));
                        break;
                    default:
                        aPlayer.addChatMessage(new ChatComponentText("Set Mode: ERROR"));
                        break;
                }
                DetravMetaGeneratedTool01.INSTANCE.setToolGTDetravData(aStack, (long) data);
                return super.onItemRightClick(aItem, aStack, aWorld, aPlayer);
            }

            //aPlayer.openGui();
            DetravMetaGeneratedTool01 tool = (DetravMetaGeneratedTool01) aItem;
            //aWorld.getChunkFromBlockCoords()
            int cX = ((int) aPlayer.posX) >> 4;
            int cZ = ((int) aPlayer.posZ) >> 4;
            int size = aItem.getHarvestLevel(aStack, "") + 1;
            List<Chunk> chunks = new ArrayList<Chunk>();
            //aPlayer.addChatMessage(new ChatComponentText("Scanning Begin, wait!"));
            //DetravProPickPacket00 packet = new DetravProPickPacket00();
            for (int i = -size; i <= size; i++)
                for (int j = -size; j <= size; j++)
                    if (i != -size && i != size && j != -size && j != size)
                        chunks.add(aWorld.getChunkFromChunkCoords(cX + i, cZ + j));
            size = size - 1;
            //c.gene
            DetravProPickPacket00 packet = new DetravProPickPacket00();
            packet.ptype = (int) data;
            packet.chunkX = cX;
            packet.chunkZ = cZ;
            packet.size = size;
            for (Chunk c : chunks) {
                for (int x = 0; x < 16; x++)
                    for (int z = 0; z < 16; z++) {
                        int ySize = c.getHeightValue(x, z);//(int)aPlayer.posY;//c.getHeightValue(x, z);
                        for (int y = 1; y < ySize; y++) {
                            switch (data) {
                                case 0:
                                case 1:
                                    Block tBlock = c.getBlock(x,y,z);
                                    short tMetaID = (short)c.getBlockMetadata(x,y,z);
                                    if (tBlock instanceof GT_Block_Ores_Abstract) {
                                        TileEntity tTileEntity = c.getTileEntityUnsafe(x,y,z);
                                        if ((tTileEntity!=null)
                                                && (tTileEntity instanceof GT_TileEntity_Ores)
                                                && ((GT_TileEntity_Ores) tTileEntity).mNatural == true) {
                                            tMetaID = (short)((GT_TileEntity_Ores) tTileEntity).getMetaData();
                                            try {

                                                String name = GT_LanguageManager.getTranslation(
                                                        tBlock.getUnlocalizedName() + "." + tMetaID + ".name");
                                                if (name.startsWith("Small")) if (data != 1) continue;
                                                packet.addBlock(c.xPosition * 16 + x, y, c.zPosition * 16 + z, tMetaID);
                                            }
                                            catch(Exception e) {
                                                String name = tBlock.getUnlocalizedName() + ".";
                                                if (name.contains(".small.")) if (data != 1) continue;
                                                packet.addBlock(c.xPosition * 16 + x, y, c.zPosition * 16 + z, tMetaID);
                                            }
                                        }
                                    }
                                    else if (data == 1) {
                                        ItemData tAssotiation = GT_OreDictUnificator.getAssociation(new ItemStack(tBlock, 1, tMetaID));
                                        if ((tAssotiation != null) && (tAssotiation.mPrefix.toString().startsWith("ore"))) {
                                            try {
                                                packet.addBlock(c.xPosition * 16 + x, y, c.zPosition * 16 + z, (short)tAssotiation.mMaterial.mMaterial.mMetaItemSubID);
                                            }
                                            catch (Exception e)
                                            {

                                            }
                                        }
                                    }
                                    break;
                                case 2:
                                    FluidStack fStack = GT_Utility.getUndergroundOil(aWorld, c.xPosition * 16 + x, c.zPosition * 16 + z);
                                    if (fStack.amount > 10000) {
                                        packet.addBlock(c.xPosition * 16 + x, 2, c.zPosition * 16 + z, (short) (fStack.amount / 5000));
                                        packet.addBlock(c.xPosition * 16 + x, 1, c.zPosition * 16 + z, (short) fStack.getFluidID());
                                    }
                                    break;
                                case 3:
                                    float polution = (float) getPolution(aWorld, c.xPosition * 16 + x, c.zPosition * 16 + z);
                                    polution /= 2000000;
                                    polution *= -0xFF;
                                    if (polution > 0xFF)
                                        polution = 0xFF;
                                    polution = 0xFF - polution;
                                    packet.addBlock(c.xPosition * 16 + x, 1, c.zPosition * 16 + z, (short) polution);
                                    break;
                            }
                            if (data > 1) break;
                        }
                    }
            }
            packet.level = ((DetravMetaGeneratedTool01) aItem).getHarvestLevel(aStack, "");
            DetravNetwork.INSTANCE.sendToPlayer(packet, (EntityPlayerMP) aPlayer);
            if (!aPlayer.capabilities.isCreativeMode)
                tool.doDamage(aStack, this.mCosts * chunks.size());
        }
        return super.onItemRightClick(aItem, aStack, aWorld, aPlayer);
    }

    void addChatMassageByValue(EntityPlayer aPlayer, int value, String name) {
        if (value < 0) {
            aPlayer.addChatMessage(new ChatComponentText(foundTexts[6] + name));
        } else if (value < 1) {
            aPlayer.addChatMessage(new ChatComponentText(foundTexts[0]));
        } else
            aPlayer.addChatMessage(new ChatComponentText(foundTexts[6] + name + ": " + value));
    }

    public boolean onItemUse(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
        long data = DetravMetaGeneratedTool01.INSTANCE.getToolGTDetravData(aStack);
        if (data < 2) {
            if(aWorld.getBlock(aX,aY,aZ) == Blocks.bedrock)
            {
                if (!aWorld.isRemote) {
                    FluidStack fStack = GT_Utility.getUndergroundOil(aWorld,aX,aZ);
                    addChatMassageByValue(aPlayer,fStack.amount/5000,fStack.getLocalizedName());
                    if (!aPlayer.capabilities.isCreativeMode)
                        ((DetravMetaGeneratedTool01)aItem).doDamage(aStack, this.mCosts);
                }
                return true;
            }
            else {
                //if (aWorld.getBlock(aX, aY, aZ).getMaterial() == Material.rock || aWorld.getBlock(aX, aY, aZ) == GregTech_API.sBlockOres1) {
                if (!aWorld.isRemote) {
                    processOreProspecting((DetravMetaGeneratedTool01) aItem, aStack, aPlayer, aWorld.getChunkFromBlockCoords(aX, aZ), aWorld.getTileEntity(aX, aY, aZ), GT_OreDictUnificator.getAssociation(new ItemStack(aWorld.getBlock(aX, aY, aZ), 1, aWorld.getBlockMetadata(aX, aY, aZ))), new Random(aWorld.getSeed() + 3547 * aX + 1327 * aZ + 9973 * aY), 1000);
                    return true;
                }
                return true;
            }
            //}
            //return false;
        }
        if (data < 3)
            if (!aWorld.isRemote) {
                FluidStack fStack = GT_Utility.getUndergroundOil(aWorld, aX, aZ);
                addChatMassageByValue(aPlayer, fStack.amount / 5000, fStack.getLocalizedName());
                if (!aPlayer.capabilities.isCreativeMode)
                    ((DetravMetaGeneratedTool01) aItem).doDamage(aStack, this.mCosts);
                return true;
            }
        if (!aWorld.isRemote) {
            int polution = getPolution(aWorld, aX, aZ);
            addChatMassageByValue(aPlayer, polution, "Pollution");
        }
        return true;
    }
}