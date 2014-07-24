package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.generics.ItemMM;
import com.ragingart.maatsmod.generics.ItemToolMM;
import com.ragingart.maatsmod.item.ItemMultitool;
import com.ragingart.maatsmod.item.ItemRSBattery;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
    public static final ItemToolMM multitool = new ItemMultitool();
    public static final ItemMM battery = new ItemRSBattery();

    public static void init()
    {
        GameRegistry.registerItem(multitool, "multitool");
        GameRegistry.registerItem(battery,"rsbattery");
    }
}
