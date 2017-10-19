package com.detrav.utils;

import com.detrav.enums.DetravItemList;
import com.detrav.items.DetravMetaGeneratedItem01;
import com.detrav.items.DetravMetaGeneratedTool01;
import com.detrav.items.processing.ProcessingDetravPortableCharger;
import com.detrav.items.processing.ProcessingDetravShaping;
import com.detrav.items.processing.ProcessingDetravSmartPlunger;
import com.detrav.items.processing.ProcessingDetravToolProPick;
import com.detrav.tileentities.Detrav_MetaTileEntity_Boiler_Solar_High;
import com.detrav.tileentities.Detrav_MetaTileEntity_Boiler_Solar_Low;
import com.detrav.tileentities.Detrav_MetaTileEntity_Boiler_Solar_Medium;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;

/**
 * Created by Detrav on 19.10.2017.
 */
public class Detrav_AfterGTPreload_Loader implements Runnable {
    @Override
    public void run() {

        //items
        new DetravMetaGeneratedItem01();
        new DetravMetaGeneratedTool01();

        //recipes and etc
        new ProcessingDetravToolProPick();
        new ProcessingDetravShaping();
        if (DetravConfig.PORTABLE_CHARGER_ENABLE)
            new ProcessingDetravPortableCharger();
        if(DetravConfig.SMART_PLUNGER_ENABLE)
            new ProcessingDetravSmartPlunger();
        //new Detrav_MetaGenerated_Tool_01();
        //new ProcessingDetravToolProPick();

        if (DetravConfig.REPAIR_TOOL_ENABLE)
            GT_ModHandler.addCraftingRecipe(DetravMetaGeneratedTool01.INSTANCE.getToolWithStats(2, 1, Materials.Iron, Materials._NULL, null)
                    , GT_ModHandler.RecipeBits.DISMANTLEABLE | GT_ModHandler.RecipeBits.DO_NOT_CHECK_FOR_COLLISIONS | GT_ModHandler.RecipeBits.BUFFERED, new Object[]{"IBI", " I ", "III", Character.valueOf('I'), OrePrefixes.ingot.get(Materials.Iron), Character.valueOf('B'), OrePrefixes.block.get(Materials.Iron)});

        //ItemList.Machine_Bronze_Boiler_Solar.set(new GT_MetaTileEntity_Boiler_Solar(105, "boiler.solar", "Simple Solar Boiler").getStackForm(1L));
        if(DetravConfig.SOLAR_BOILERS_ENABLE) {

            DetravItemList.Solar_Boiler_Low.set(new Detrav_MetaTileEntity_Boiler_Solar_Low(2051, "boiler.bronze.solar", "Bronze Solar Boiler").getStackForm(1L));
            DetravItemList.Solar_Boiler_Medium.set(new Detrav_MetaTileEntity_Boiler_Solar_Medium(2052, "boiler.steel.solar", "Steel Solar Boiler").getStackForm(1L));
            DetravItemList.Solar_Boiler_High.set(new Detrav_MetaTileEntity_Boiler_Solar_High(2053, "boiler.stainless.steel.solar", "Stainless Steel Solar Boiler").getStackForm(1L));
        }
        //DetravItemList.Anvil.set()

        // GameRegistry.addRecipe(new DetravRepairRecipe());
    }
}
