package com.ragingart.miningmodifications.init;


import net.minecraftforge.oredict.OreDictionary;

public class ModOreDict {
    public static void init(){
        OreDictionary.registerOre("dustNickel",ModItems.dust_nickel);
        OreDictionary.registerOre("ingotNickel",ModItems.ingot_nickel);
        OreDictionary.registerOre("oreNickel",ModBlocks.Ore);
        OreDictionary.registerOre("plateIron",ModItems.plate_iron);
    }
}
