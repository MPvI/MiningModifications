package com.ragingart.miningmodifications.init;


import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by MaaT on 30.08.2014.
 */
public class ModOreDict {
    public static void init(){
        OreDictionary.registerOre("dustNickel",ModItems.dust_nickel);
        OreDictionary.registerOre("ingotNickel",ModItems.ingot_nickel);
        OreDictionary.registerOre("oreNickel",ModBlocks.Ore);
        OreDictionary.registerOre("plateIron",ModItems.plate_iron);
    }
}
