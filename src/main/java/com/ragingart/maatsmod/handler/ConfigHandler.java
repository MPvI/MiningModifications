package com.ragingart.maatsmod.handler;

import com.ragingart.maatsmod.ref.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;

    public static boolean configValue = false;

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

        if(config.hasChanged())
        {
            config.save();
        }
    }

}
