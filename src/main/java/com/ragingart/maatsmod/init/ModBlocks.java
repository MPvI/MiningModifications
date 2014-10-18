package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.block.*;
import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.item.*;
import com.ragingart.maatsmod.ref.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static final BlockMM Ore = new BlockPecoraitOre();

    public static final BlockMM Charger = new BlockCharger();
    public static final BlockMM Energy = new BlockCreativeEnergy();
    public static final BlockMM PlatformBase = new BlockPlatformBase();
    public static final BlockMM FluxField = new BlockFluxField();
    public static final BlockMM WaterTurbine = new BlockWaterTurbine();
    public static final BlockMM RFEnergyStorage = new BlockRFEnergyStorage();
    public static final BlockMM Discharger = new BlockDischarger();
    public static final BlockMM Cable = new BlockCable();
    public static final BlockMM LIS = new BlockLaserIsotopeSeperator();
    public static final BlockMM Crank = new BlockCrank();
    public static final BlockMM Compactor = new BlockCompactor();
    public static final BlockMM SharpeningWheel = new BlockSharpeningWheel();

    public static void init()
    {
        GameRegistry.registerBlock(Ore,ItemBlockPecoraitOre.class ,Names.Blocks.ORE);

        GameRegistry.registerBlock(Charger,ItemBlockCharger.class,Names.Blocks.CHARGER);
        GameRegistry.registerBlock(Discharger, ItemBlockDischarger.class,Names.Blocks.DISCHARGER);
        GameRegistry.registerBlock(LIS,ItemBlockLaserIsotopeSeperator.class,Names.Blocks.LIS);

        GameRegistry.registerBlock(Energy,Names.Blocks.CENERGY);
        GameRegistry.registerBlock(WaterTurbine, ItemBlockWaterTurbine.class,Names.Blocks.WATERTURBINE);
        GameRegistry.registerBlock(RFEnergyStorage, ItemBlockRFEnergyStorage.class,Names.Blocks.RFENERGYSTORAGE);
        GameRegistry.registerBlock(Cable,ItemBlockCable.class,Names.Blocks.CABLE);

        GameRegistry.registerBlock(PlatformBase,ItemBlockPlatformBase.class,Names.Blocks.PLATFORM_BASE);
        GameRegistry.registerBlock(FluxField, ItemBlockFluxField.class,Names.Blocks.FLUXFIELD);

        GameRegistry.registerBlock(Crank, ItemBlockCrank.class, Names.Blocks.CRANK);
        GameRegistry.registerBlock(Compactor, ItemBlockCompactor.class, Names.Blocks.COMPACTOR);
        GameRegistry.registerBlock(SharpeningWheel, ItemBlockSharpeningWheel.class, Names.Blocks.SHARPENINGWHEEL);

    }
}
