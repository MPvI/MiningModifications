package com.ragingart.maatsmod.handler;

import com.ragingart.maatsmod.ref.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;

    public static int miningEnergyModificator = 2;
    public static int miningSpeedModificator = 2;
    public static int oreGenMax = 30;
    public static int oreGenMin = 6;
    public static boolean oreGen = true;
    public static int maxPlatformSize = 608;
    public static int maxPlatformRadius = 14;


    public static void init(File configFile)
    {
        if(config == null)
        {
        config = new Configuration(configFile);
        loadConfig();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfig();
        }
    }

    private static void loadConfig()
    {

        oreGen = config.getBoolean("oreGen", Configuration.CATEGORY_GENERAL, true, "Should Népouit be generated");

        oreGenMax = config.getInt("oreGenMax",Configuration.CATEGORY_GENERAL,30,1,50,"max y layer for Népouit");
        oreGenMin = config.getInt("oreGenMin",Configuration.CATEGORY_GENERAL,6,1,30,"min y layer for Népouit");

        miningEnergyModificator = config.getInt("miningEnergyModificator",Configuration.CATEGORY_GENERAL,2,1,10,"1 very low energy use - 10 very high energy use");
        miningSpeedModificator = config.getInt("miningSpeedModificator",Configuration.CATEGORY_GENERAL,2,1,10,"1 extreme fast mining - 10 extreme slow mining");

        maxPlatformSize = config.getInt("maxPlatformSize",Configuration.CATEGORY_GENERAL,608,10,1000,"max Size for the Platform");
        maxPlatformRadius = config.getInt("maxPlatformRadius",Configuration.CATEGORY_GENERAL,14,1,100,"max Radius for the Platform");


        if(config.hasChanged())
        {
            config.save();
        }
    }

}
