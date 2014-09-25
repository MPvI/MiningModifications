package com.ragingart.maatsmod.ref;


import com.ragingart.maatsmod.init.ModItems;
import net.minecraft.item.ItemStack;

public class Component {
    // OreDictionary Names for Recipe Components
    public static final String SMALL_GOLD = "nuggetGold";
    public static final String NORMAL_IRON = "ingotIron";
    public static final String HANDLE = "stickWood";
    public static final String GEM = "gemDiamond";
    public static final String RS = "dustRedstone";
    public static final String NORMAL_NICKEL = "ingotNickel";

    public static ItemStack BATTERY(int x) {return new ItemStack(ModItems.battery,x);}
    public static ItemStack CASING(int x) {return new ItemStack(ModItems.casing,x);}
    public static ItemStack CASING_ENERGY(int x) {return new ItemStack(ModItems.casing_energy,x);}
    public static ItemStack CASING_INPUT(int x) {return new ItemStack(ModItems.casing_input,x);}
    public static ItemStack CASING_OUTPUT(int x) {return new ItemStack(ModItems.casing_output,x);}
    public static ItemStack MULTITOOL() {return new ItemStack(ModItems.multitool);}
    public static ItemStack SCREW(int x) {return new ItemStack(ModItems.screw,x);}

}
