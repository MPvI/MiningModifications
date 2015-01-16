package com.ragingart.miningmodifications.init;

import com.ragingart.miningmodifications.generics.ItemMM;
import com.ragingart.miningmodifications.generics.ItemToolMM;
import com.ragingart.miningmodifications.item.*;
import com.ragingart.miningmodifications.ref.Names;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
    //Tools
    public static final ItemToolMM multitool = new ItemMultitool();
    public static final ItemToolMM spear = new ItemSpear();
    //Parts
    public static final ItemMM battery = new ItemRSBattery();
    public static final ItemMM casing = new ItemCasing();
    public static final ItemMM screw = new ItemScrew();
    public static final ItemMM plate_iron = new ItemPlateIron();
    //Worldgen
    public static final ItemMM ingot_nickel = new ItemIngotNickel();
    public static final ItemMM dust_nickel = new ItemDustNickel();
    //
    public static final ItemMM voidpack = new ItemVoidpack();



    public static void init()
    {
        //Tools
        GameRegistry.registerItem(multitool, Names.Items.MULTITOOL);
        //GameRegistry.registerItem(spear,Names.Items.WEAPON_SPEAR);
        //Parts
        GameRegistry.registerItem(battery,Names.Items.BATTERY);
        GameRegistry.registerItem(casing,Names.Items.CASING[0]);
        GameRegistry.registerItem(screw,Names.Items.SCREW_NICKEL);
        GameRegistry.registerItem(plate_iron,Names.Items.PLATE_IRON);
        //Worldgen
        GameRegistry.registerItem(ingot_nickel,Names.Items.INGOT_NICKEL);
        GameRegistry.registerItem(dust_nickel,Names.Items.DUST_NICKEL);
        //
        GameRegistry.registerItem(voidpack,Names.Items.VOIDPACK);

    }
}
