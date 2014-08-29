package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.block.BlockCharger;
import com.ragingart.maatsmod.block.BlockCreativeEnergy;
import com.ragingart.maatsmod.block.BlockMMOre;
import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.item.ItemBlockCharger;
import com.ragingart.maatsmod.item.ItemBlockMMOre;
import com.ragingart.maatsmod.ref.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static final BlockMM Ore = new BlockMMOre();
    public static final BlockMM Charger = new BlockCharger();
    public static final BlockMM Energy = new BlockCreativeEnergy();

    public static void init()
    {
        GameRegistry.registerBlock(Ore, ItemBlockMMOre.class ,"oreMM");
        GameRegistry.registerBlock(Charger,ItemBlockCharger.class,Names.Blocks.CHARGER);
        GameRegistry.registerBlock(Energy,Names.Blocks.CENERGY);
    }
}
