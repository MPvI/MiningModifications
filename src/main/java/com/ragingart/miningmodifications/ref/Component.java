package com.ragingart.miningmodifications.ref;


import com.ragingart.miningmodifications.init.ModBlocks;
import com.ragingart.miningmodifications.init.ModItems;
import net.minecraft.item.ItemStack;

public class Component {
    // OreDictionary Names for Recipe Components
    public static final String SMALL_GOLD = "nuggetGold";
    public static final String NORMAL_IRON = "ingotIron";
    public static final String GEM = "gemOlivine";
    public static final String SMALL_RS = "dustRedstone";
    public static final String BIG_RS = "blockRedstone";
    public static final String NORMAL_NICKEL = "ingotNickel";

    public static ItemStack MULTITOOLBLADE(){return new ItemStack(ModItems.multitoolblade);}
    public static ItemStack MULTITOOLHANDLE(){return new ItemStack(ModItems.multitoolhandle);}
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
    public static ItemStack CIRCUITWIRE(int x) {return new ItemStack(ModItems.circuit_wire,x);}
    public static ItemStack CIRCUIT() {return new ItemStack(ModItems.circuit);}
}
