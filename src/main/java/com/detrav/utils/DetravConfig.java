package com.detrav.utils;

import net.minecraftforge.common.config.Configuration;

public class DetravConfig {


    //3x3
    public static boolean DIGGING_ENABLE = true;
    public static int DIGGING_RADIUS = 1;
    public static boolean DIGGING_HIGHLIGHT = true;
    //extra_hp
    public static boolean EXTRA_HP_ENABLE = true;
    public static int EXTRA_HP_MAX_LEVEL = 200;
    public static int[] EXTRA_HP_LEVELS = new int[0];
    //repair_tool
    public static boolean REPAIR_TOOL_ENABLE = true;
    public static float REPAIR_RATE = 1.0f;
    public static float REPAIR_XP_RATE = 2.7f;
    //commands
    public static boolean BIOME_SCANNER_ENABLE = true;
    //portable_charger
    public static boolean PORTABLE_CHARGER_ENABLE = true;
    //propick
    public static boolean PROPICK_ENABLE = true;
    //electric_propick
    public static boolean PROPICK_ELECTRIC_ENABLE = true;
    public static int[] PROPICK_RADIUSES = new int[0];
    //tree_tap
    public static boolean TREE_TAP_ENABLE = true;
    //smart_plunger
    public static boolean SMART_PLUNGER_ENABLE = true;
    //solar_boilers
    public static boolean SOLAR_BOILERS_ENABLE = true;
    public static int SOLAR_BOILERS_LOW_STEAM = 150;
    public static int SOLAR_BOILERS_MEDIUM_STEAM = 375;
    public static int SOLAR_BOILERS_HIGH_STEAM = 750;

    //coins
    public static boolean COINS_ENABLE = true;



    public DetravConfig(Configuration config) {
        config.load();
        //general
        DIGGING_ENABLE = config.get("General", "Digging", true).getBoolean();
        EXTRA_HP_ENABLE = config.get("General", "ExtraHP", true).getBoolean();
        REPAIR_TOOL_ENABLE = config.get("General", "RepairTool", true).getBoolean();
        BIOME_SCANNER_ENABLE = config.get("General", "BiomeScannerCommand", true).getBoolean();
        PORTABLE_CHARGER_ENABLE = config.get("General", "PortableCharger", true).getBoolean();
        PROPICK_ENABLE = config.get("General", "ProPick", true).getBoolean();
        PROPICK_ELECTRIC_ENABLE = config.get("General", "ProPickElectric", true).getBoolean();
        TREE_TAP_ENABLE = config.get("General", "TreeTap", true).getBoolean();
        SMART_PLUNGER_ENABLE = config.get("General", "SmartPlunger", true).getBoolean();
        SOLAR_BOILERS_ENABLE = config.get("General", "SolarBoilers", true).getBoolean();
        COINS_ENABLE = config.get("General", "Coins", true).getBoolean();
        //3x3
        DIGGING_RADIUS = config.get("Digging", "Radius", 1).getInt();
        DIGGING_HIGHLIGHT = config.get("Digging", "HighLight", true).getBoolean();
        //extra_hp
        EXTRA_HP_MAX_LEVEL = config.get("ExtraHP","MaxLevel",200).getInt();
        if(EXTRA_HP_MAX_LEVEL<0) EXTRA_HP_MAX_LEVEL = 0;
        EXTRA_HP_LEVELS = new int[EXTRA_HP_MAX_LEVEL+1];
        for(int i=0; i<=EXTRA_HP_MAX_LEVEL; i++)
            EXTRA_HP_LEVELS[i] = config.get("ExtraHP","Level_" + String.format("%04d", i),
                    (int)(Math.log(i/10.0 + 1) * 20 / Math.log(EXTRA_HP_MAX_LEVEL/10.0 +1))
            ).getInt();
        //repair_tool
        REPAIR_XP_RATE = (float)config.get("RepairTool","XPRate", 2.7).getDouble();
        REPAIR_RATE = (float)config.get("RepairTool","Rate", 1.0).getDouble();
        //commands
        //portable_charger
        //propick
        //electric_propick
        PROPICK_RADIUSES = new int[10];
        for(int i =0; i< 10; i++)
            PROPICK_RADIUSES[i] = config.get("ProPick","RadiusLevel_"+ i,i * 2 + 1).getInt();
        //tree_tap
        //smart_plunger
        //solar_boilers




        if (config.hasChanged())
            config.save();
    }
}
