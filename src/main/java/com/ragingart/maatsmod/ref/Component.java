package com.ragingart.maatsmod.ref;


import com.ragingart.maatsmod.init.ModBlocks;
import com.ragingart.maatsmod.init.ModItems;
import net.minecraft.item.ItemStack;

public class Component {
    // OreDictionary Names for Recipe Components
    public static final String SMALL_GOLD = "nuggetGold";
    public static final String NORMAL_IRON = "ingotIron";
    public static final String HANDLE = "stickWood";
    public static final String GEM = "gemDiamond";
    public static final String SMALL_RS = "dustRedstone";
    public static final String BIG_RS = "blockRedstone";
    public static final String NORMAL_NICKEL = "ingotNickel";
    public static final String PLATE_IRON = "plateIron";

    public static ItemStack BATTERY(int x) {return new ItemStack(ModItems.battery,x);}
    public static ItemStack CASING(int x) {return new ItemStack(ModItems.casing,x,0);}
    public static ItemStack CASING_ENERGY(int x) {return new ItemStack(ModItems.casing,x,1);}
    public static ItemStack CASING_INPUT(int x) {return new ItemStack(ModItems.casing,x,2);}
    public static ItemStack CASING_OUTPUT(int x) {return new ItemStack(ModItems.casing,x,3);}
    public static ItemStack CASING_FINPUT(int x) {return new ItemStack(ModItems.casing,x,4);}
    public static ItemStack CASING_FOUTPUT(int x) {return new ItemStack(ModItems.casing,x,5);}
    public static ItemStack MULTITOOL() {return new ItemStack(ModItems.multitool);}
    public static ItemStack SCREW(int x) {return new ItemStack(ModItems.screw,x);}
    public static ItemStack PLATFORM(int x) {return new ItemStack(ModBlocks.PlatformBase,x);}
    public static ItemStack FLUXFIELD(int x){return new ItemStack(ModBlocks.FluxField,x);}
}
