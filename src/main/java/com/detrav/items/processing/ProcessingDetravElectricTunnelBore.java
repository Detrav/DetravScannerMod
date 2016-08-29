package com.detrav.items.processing;

import com.detrav.items.DetravMetaGeneratedTool01;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import net.minecraft.item.ItemStack;

/**
 * Created by Detrav on 26.08.2016.
 */
public class ProcessingDetravElectricTunnelBore implements gregtech.api.interfaces.IOreRecipeRegistrator {

    public ProcessingDetravElectricTunnelBore()
    {
        OrePrefixes.plateDense.add(this);
    }

    @Override
    public void registerOre(OrePrefixes orePrefixes, Materials materials, String s, String s1, ItemStack itemStack) {
        if(!orePrefixes.doGenerateItem(materials)) return;

        GT_ModHandler.
                addShapelessCraftingRecipe(
                        DetravMetaGeneratedTool01.INSTANCE.getToolWithStats(4, 1, materials, materials.mHandleMaterial, null),
                        new Object[]{itemStack});
    }
}
