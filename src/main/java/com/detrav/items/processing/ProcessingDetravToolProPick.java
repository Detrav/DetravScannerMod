package com.detrav.items.processing;

import com.detrav.enums.DetravItemList;
import com.detrav.enums.DetravSimpleItems;
import com.detrav.items.DetravMetaGeneratedItem01;
import com.detrav.items.DetravMetaGeneratedTool01;
import com.detrav.utils.DetravConfig;
import com.dreammaster.gthandler.CustomItemList;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.common.items.GT_MetaGenerated_Tool_01;
import net.minecraft.item.ItemStack;

/**
 * Created by wital_000 on 18.03.2016.
 */
public class ProcessingDetravToolProPick implements gregtech.api.interfaces.IOreRecipeRegistrator {
    public ProcessingDetravToolProPick() {
        OrePrefixes.toolHeadPickaxe.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        if(!aPrefix.doGenerateItem(aMaterial)) return;;
        if(DetravConfig.PROPICK_ENABLE)
        GT_ModHandler.
                addShapelessCraftingRecipe(
                        DetravMetaGeneratedTool01.INSTANCE.getToolWithStats(0, 1, aMaterial, aMaterial.mHandleMaterial, null),
                        new Object[]{DetravSimpleItems.toolHeadProPick.get(aMaterial), OrePrefixes.stick.get(aMaterial.mHandleMaterial)});

        if(DetravConfig.PROPICK_ELECTRIC_ENABLE) {
              GT_ModHandler.addCraftingRecipe(DetravMetaGeneratedTool01.INSTANCE.getToolWithStats(100, 1, aMaterial, Materials.Iridium, new long[]{102400000L, 32768L, 6L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"EHR", "CSC", "PBP", Character.valueOf('S'), ItemList.Cover_Screen, Character.valueOf('R'), ItemList.Sensor_LuV, Character.valueOf('H'), OrePrefixes.toolHeadDrill.get(aMaterial),Character.valueOf('E'), ItemList.Emitter_LuV,Character.valueOf('C'), OrePrefixes.circuit.get(Materials.Ultimate), Character.valueOf('P'), OrePrefixes.plate.get(Materials.Iridium), Character.valueOf('B'), CustomItemList.BatteryHull_LuV_Full.get(1L, new Object[0])});
              GT_ModHandler.addCraftingRecipe(DetravMetaGeneratedTool01.INSTANCE.getToolWithStats(102, 1, aMaterial, Materials.Neutronium, new long[]{409600000L, 131072L, 7L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"EHR", "CSC", "PBP", Character.valueOf('S'), ItemList.Cover_Screen, Character.valueOf('R'), ItemList.Sensor_ZPM, Character.valueOf('H'), OrePrefixes.toolHeadDrill.get(aMaterial), Character.valueOf('E'), ItemList.Emitter_ZPM,Character.valueOf('C'), OrePrefixes.circuit.get(Materials.Superconductor), Character.valueOf('P'), OrePrefixes.plate.get(Materials.Neutronium), Character.valueOf('B'), CustomItemList.BatteryHull_ZPM_Full.get(1L, new Object[0])});
              GT_ModHandler.addCraftingRecipe(DetravMetaGeneratedTool01.INSTANCE.getToolWithStats(104, 1, aMaterial, Materials.Osmium, new long[]{1638400000L, 524288L, 8L, -1L}), GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"EHR", "CSC", "PBP", Character.valueOf('S'), ItemList.Cover_Screen, Character.valueOf('R'), ItemList.Sensor_UV, Character.valueOf('H'), OrePrefixes.toolHeadDrill.get(aMaterial), Character.valueOf('E'), ItemList.Emitter_UV,Character.valueOf('C'), OrePrefixes.circuit.get(Materials.Master), Character.valueOf('P'), OrePrefixes.plate.get(Materials.Osmium), Character.valueOf('B'), CustomItemList.BatteryHull_UV_Full.get(1L, new Object[0])});
        }

        GT_ModHandler.addCraftingRecipe(DetravItemList.Shape_Extruder_ProPick.get(1L,new Object[0]),
                new Object[]{"h","X","M", Character.valueOf('X'),DetravSimpleItems.toolHeadProPick.get(aMaterial),Character.valueOf('M'),ItemList.Shape_Empty});
    }
}