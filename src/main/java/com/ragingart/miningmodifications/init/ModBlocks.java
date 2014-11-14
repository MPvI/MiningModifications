package com.ragingart.miningmodifications.init;

import com.ragingart.miningmodifications.block.*;
import com.ragingart.miningmodifications.block.handmachines.BlockCompactor;
import com.ragingart.miningmodifications.block.handmachines.BlockCrank;
import com.ragingart.miningmodifications.block.handmachines.BlockGrinder;
import com.ragingart.miningmodifications.block.handmachines.BlockSharpeningWheel;
import com.ragingart.miningmodifications.block.machines.*;
import com.ragingart.miningmodifications.generics.BlockMM;
import com.ragingart.miningmodifications.item.*;
import com.ragingart.miningmodifications.item.handmachines.ItemBlockCompactor;
import com.ragingart.miningmodifications.item.handmachines.ItemBlockCrank;
import com.ragingart.miningmodifications.item.handmachines.ItemBlockGrinder;
import com.ragingart.miningmodifications.item.handmachines.ItemBlockSharpeningWheel;
import com.ragingart.miningmodifications.item.machines.*;
import com.ragingart.miningmodifications.ref.Names;
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
    public static final BlockMM LS = new BlockLaserSeperator();
    public static final BlockMM MachineBlock = new BlockMachineBlock();
    public static final BlockMM Crank = new BlockCrank();
    public static final BlockMM Compactor = new BlockCompactor();
    public static final BlockMM SharpeningWheel = new BlockSharpeningWheel();
    public static final BlockMM Grinder = new BlockGrinder();

    public static void init()
    {
        GameRegistry.registerBlock(Ore,ItemBlockPecoraitOre.class ,Names.Blocks.ORE);

        GameRegistry.registerBlock(Charger,ItemBlockCharger.class,Names.Blocks.CHARGER);
        GameRegistry.registerBlock(Discharger, ItemBlockDischarger.class,Names.Blocks.DISCHARGER);
        GameRegistry.registerBlock(LS,ItemBlockLaserSeperator.class,Names.Blocks.LS);
        GameRegistry.registerBlock(MachineBlock,ItemBlockMachineBlock.class,Names.Blocks.MACHINEBLOCK);

        GameRegistry.registerBlock(Energy,Names.Blocks.CENERGY);
        GameRegistry.registerBlock(WaterTurbine, ItemBlockWaterTurbine.class,Names.Blocks.WATERTURBINE);
        GameRegistry.registerBlock(RFEnergyStorage, ItemBlockRFEnergyStorage.class,Names.Blocks.RFENERGYSTORAGE);
        GameRegistry.registerBlock(Cable,ItemBlockCable.class,Names.Blocks.CABLE);

        GameRegistry.registerBlock(PlatformBase,ItemBlockPlatformBase.class,Names.Blocks.PLATFORM_BASE);
        GameRegistry.registerBlock(FluxField, ItemBlockFluxField.class,Names.Blocks.FLUXFIELD);

        GameRegistry.registerBlock(Crank, ItemBlockCrank.class, Names.Blocks.CRANK);
        GameRegistry.registerBlock(Compactor, ItemBlockCompactor.class, Names.Blocks.COMPACTOR);
        GameRegistry.registerBlock(SharpeningWheel, ItemBlockSharpeningWheel.class, Names.Blocks.SHARPENINGWHEEL);
        GameRegistry.registerBlock(Grinder, ItemBlockGrinder.class, Names.Blocks.GRINDER);

    }
}
