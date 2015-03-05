package com.ragingart.miningmodifications.init;

import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.tileentity.TileEntityCable;
import com.ragingart.miningmodifications.tileentity.TileEntityCreativeEnergy;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityMachineBlock;
import com.ragingart.miningmodifications.tileentity.TileEntityPlatformBase;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntityCompactor;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntityCrank;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntityGrinder;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntitySharpeningWheel;
import com.ragingart.miningmodifications.tileentity.machines.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTiles {
    public static void init() {

        GameRegistry.registerTileEntity(TileEntityMachineBlock.class,Names.Blocks.MACHINEBLOCK);
        GameRegistry.registerTileEntity(TileEntityCharger.class,Names.Blocks.CHARGER);
        GameRegistry.registerTileEntity(TileEntityWaterTurbine.class,Names.Blocks.WATERTURBINE);
        GameRegistry.registerTileEntity(TileEntityRFEnergyStorage.class,Names.Blocks.RFENERGYSTORAGE);
        GameRegistry.registerTileEntity(TileEntityDischarger.class,Names.Blocks.DISCHARGER);
        GameRegistry.registerTileEntity(TileEntityCreativeEnergy.class,Names.Blocks.CENERGY);
        GameRegistry.registerTileEntity(TileEntityPlatformBase.class,Names.Blocks.PLATFORM_BASE);
        GameRegistry.registerTileEntity(TileEntityCable.class,Names.Blocks.CABLE);
        GameRegistry.registerTileEntity(TileEntityLaserSeparator.class,Names.Blocks.LS);
        GameRegistry.registerTileEntity(TileEntityCrank.class,Names.Blocks.CRANK);
        GameRegistry.registerTileEntity(TileEntityCompactor.class,Names.Blocks.COMPACTOR);
        GameRegistry.registerTileEntity(TileEntitySharpeningWheel.class,Names.Blocks.SHARPENINGWHEEL);
        GameRegistry.registerTileEntity(TileEntityGrinder.class,Names.Blocks.GRINDER);

    }
}
