package com.ragingart.maatsmod.handler;

import com.ragingart.maatsmod.ref.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;

    public static boolean configValue = false;

    public static int miningEnergyModificator = 2;
    public static int miningSpeedModificator = 2;


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
        configValue = config.getBoolean("configValue",Configuration.CATEGORY_GENERAL,false,"Example");

        miningEnergyModificator = config.getInt("miningEnergyModificator",Configuration.CATEGORY_GENERAL,2,1,10,"Mining Energy Modificator MEM- Energy Consumption Multitool: (45+runningTick)*MEM - Range: 1 - 10");
        miningSpeedModificator = config.getInt("miningSpeedModificator",Configuration.CATEGORY_GENERAL,2,1,10,"Mining Speed Modificator MSM- Harvest Duration Multitool: BlockHardness*MSM/EfficencyLevel in ticks - Range: 1 - 10");


        if(config.hasChanged())
        {
            config.save();
        }
    }

}
