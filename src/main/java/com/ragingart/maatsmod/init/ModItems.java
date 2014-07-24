package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.item.ItemMultitool;
import com.ragingart.maatsmod.generics.ItemToolMM;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
    public static final ItemToolMM Multitool = new ItemMultitool();

    public static void init()
    {
        GameRegistry.registerItem(Multitool, "multitool");
    }
}
