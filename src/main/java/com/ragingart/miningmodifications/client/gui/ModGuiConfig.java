package com.ragingart.miningmodifications.client.gui;
//TODO

import com.ragingart.miningmodifications.handler.ConfigHandler;
import com.ragingart.miningmodifications.ref.Reference;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;

public class ModGuiConfig extends GuiConfig
{
    static List<IConfigElement> list = new ArrayList<IConfigElement>();
    public ModGuiConfig(GuiScreen guiScreen) {
        super(guiScreen,
                list,
                Reference.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }
}