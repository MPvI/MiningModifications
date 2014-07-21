package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.block.BlockMM;
import com.ragingart.maatsmod.block.BlockMMOre;
import com.ragingart.maatsmod.item.ItemBlockMMOre;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static final BlockMM Ore = new BlockMMOre();

    public static void init()
    {
        GameRegistry.registerBlock(Ore, ItemBlockMMOre.class ,"oreMM");
    }
}
