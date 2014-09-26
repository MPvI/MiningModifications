package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.block.*;
import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.item.ItemBlockCharger;
import com.ragingart.maatsmod.item.ItemBlockNepouitOre;
import com.ragingart.maatsmod.item.ItemBlockPlatformBase;
import com.ragingart.maatsmod.item.ItemBlockPlatformExt;
import com.ragingart.maatsmod.ref.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static final BlockMM Ore = new BlockNepouitOre();
    public static final BlockMM Charger = new BlockCharger();
    public static final BlockMM Energy = new BlockCreativeEnergy();
    public static final BlockMM PlatformBase = new BlockPlatformBase();
    public static final BlockMM PlatformExt = new BlockPlatformExt();

    public static void init()
    {
        GameRegistry.registerBlock(Ore,ItemBlockNepouitOre.class ,Names.Blocks.ORE);
        GameRegistry.registerBlock(Charger,ItemBlockCharger.class,Names.Blocks.CHARGER);
        GameRegistry.registerBlock(Energy,Names.Blocks.CENERGY);
        GameRegistry.registerBlock(PlatformBase,ItemBlockPlatformBase.class,Names.Blocks.PLATFORM_BASE);
        GameRegistry.registerBlock(PlatformExt, ItemBlockPlatformExt.class,Names.Blocks.PLATFORM_EXT);
    }
}
