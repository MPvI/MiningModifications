package com.ragingart.miningmodifications.init;

import com.ragingart.miningmodifications.generics.ItemMM;
import com.ragingart.miningmodifications.generics.ItemToolMM;
import com.ragingart.miningmodifications.item.*;

public class ModItems {
    //Tools
    public static ItemToolMM multitool;
    public static ItemToolMM spear;
    //Parts
    public static ItemMM battery;
    public static ItemMM casing;
    public static ItemMM screw;
    public static ItemMM plate_iron;
    //Worldgen
    public static ItemMM ingot_nickel;
    public static ItemMM dust_nickel;
    //
    public static ItemMM voidpack;

    public static void init(){
        multitool = new ItemMultitool();
        spear = new ItemSpear();
        battery = new ItemRSBattery();
        casing = new ItemCasing();
        screw = new ItemScrew();
        plate_iron = new ItemPlateIron();
        ingot_nickel = new ItemIngotNickel();
        dust_nickel = new ItemDustNickel();
        voidpack = new ItemVoidpack();
    }
}
