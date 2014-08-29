package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.generics.ItemMM;
import com.ragingart.maatsmod.generics.ItemToolMM;
import com.ragingart.maatsmod.item.*;
import com.ragingart.maatsmod.ref.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
    public static final ItemToolMM multitool = new ItemMultitool();
    public static final ItemMM battery = new ItemRSBattery();
    public static final ItemMM casing = new ItemCasingPlate();
    public static final ItemMM casing_energy = new ItemCasingEnergy();
    public static final ItemMM screw = new ItemScrew();


    public static void init()
    {
        GameRegistry.registerItem(multitool, Names.Items.MULTITOOL);
        GameRegistry.registerItem(battery,Names.Items.BATTERY);
        GameRegistry.registerItem(casing,Names.Items.CASING);
        GameRegistry.registerItem(casing_energy,Names.Items.CASING_ENERGY);
        GameRegistry.registerItem(screw,Names.Items.SCREW);
    }
}
