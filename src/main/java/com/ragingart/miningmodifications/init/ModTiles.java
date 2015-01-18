package com.ragingart.miningmodifications.init;

import com.ragingart.miningmodifications.tileentity.TileEntityCable;
import com.ragingart.miningmodifications.tileentity.TileEntityCreativeEnergy;
import com.ragingart.miningmodifications.tileentity.TileEntityMachineBlock;
import com.ragingart.miningmodifications.tileentity.TileEntityPlatformBase;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntityCompactor;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntityCrank;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntityGrinder;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntitySharpeningWheel;
import com.ragingart.miningmodifications.tileentity.machines.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTiles {
    public static void init() {

        GameRegistry.registerTileEntity(TileEntityMachineBlock.class,ModBlocks.MachineBlock.getName());
        GameRegistry.registerTileEntity(TileEntityCharger.class,ModBlocks.Charger.getName());
        GameRegistry.registerTileEntity(TileEntityWaterTurbine.class,ModBlocks.WaterTurbine.getName());
        GameRegistry.registerTileEntity(TileEntityRFEnergyStorage.class,ModBlocks.RFEnergyStorage.getName());
        GameRegistry.registerTileEntity(TileEntityDischarger.class,ModBlocks.Discharger.getName());
        GameRegistry.registerTileEntity(TileEntityCreativeEnergy.class,ModBlocks.Energy.getName());
        GameRegistry.registerTileEntity(TileEntityPlatformBase.class,ModBlocks.PlatformBase.getName());
        GameRegistry.registerTileEntity(TileEntityCable.class, ModBlocks.Cable.getName());
        GameRegistry.registerTileEntity(TileEntityLaserSeperator.class,ModBlocks.LS.getName());
        GameRegistry.registerTileEntity(TileEntityCrank.class,ModBlocks.Crank.getName());
        GameRegistry.registerTileEntity(TileEntityCompactor.class,ModBlocks.Compactor.getName());
        GameRegistry.registerTileEntity(TileEntitySharpeningWheel.class,ModBlocks.SharpeningWheel.getName());
        GameRegistry.registerTileEntity(TileEntityGrinder.class,ModBlocks.Grinder.getName());

    }
}
