package com.ragingart.maatsmod.handler;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;

    public static void init(File configFile)
    {
        config = new Configuration(configFile);
        boolean configValue=false;

        try
        {
            config.load();

            configValue =config.get(Configuration.CATEGORY_GENERAL, "configValue", true,"Example").getBoolean(true);

        }
        catch (Exception e)
        {

        }
        finally
        {
            if(config.hasChanged())
            {
                config.save();
            }
        }

    }
    //TODO
    //Future Content for higher Forge Version
    /*
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
    public void onConfigChangedEvent(ConfigChangedEvent.onConfigChangedEvent event)
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
    */
}
