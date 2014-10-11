package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.generics.ItemMM;
import com.ragingart.maatsmod.generics.ItemToolMM;
import com.ragingart.maatsmod.item.*;
import com.ragingart.maatsmod.ref.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
    //Tools
    public static final ItemToolMM multitool = new ItemMultitool();
    public static final ItemToolMM spear = new ItemSpear();
    //Parts
    public static final ItemMM battery = new ItemRSBattery();
    public static final ItemMM casing = new ItemCasing(Names.Items.CASING[0]);
    public static final ItemMM screw = new ItemScrew();
    //Worldgen
    public static final ItemMM ingot_nickel = new ItemIngotNickel();
    public static final ItemMM dust_nickel = new ItemDustNickel();



    public static void init()
    {
        //Tools
        GameRegistry.registerItem(multitool, Names.Items.MULTITOOL);
        //GameRegistry.registerItem(spear,Names.Items.WEAPON_SPEAR);
        //Parts
        GameRegistry.registerItem(battery,Names.Items.BATTERY);
        GameRegistry.registerItem(casing,Names.Items.CASING[0]);
        GameRegistry.registerItem(screw,Names.Items.SCREW_NICKEL);
        //Worldgen
        GameRegistry.registerItem(ingot_nickel,Names.Items.INGOT_NICKEL);
        GameRegistry.registerItem(dust_nickel,Names.Items.DUST_NICKEL);
    }
}
