package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.tileentity.*;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by MaaT on 28.08.2014.
 */
public class ModTiles {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityCharger.class,Names.Blocks.CHARGER);
        GameRegistry.registerTileEntity(TileEntityWaterTurbine.class,Names.Blocks.WATERTURBINE);
        GameRegistry.registerTileEntity(TileEntityRFEnergyStorage.class,Names.Blocks.RFENERGYSTORAGE);
        GameRegistry.registerTileEntity(TileEntityDischarger.class,Names.Blocks.DISCHARGER);
        GameRegistry.registerTileEntity(TileEntityCreativeEnergy.class,Names.Blocks.CENERGY);
        GameRegistry.registerTileEntity(TileEntityPlatformBase.class,Names.Blocks.PLATFORM_BASE);
        GameRegistry.registerTileEntity(TileEntityFluxField.class,Names.Blocks.FLUXFIELD);
        GameRegistry.registerTileEntity(TileEntityCable.class,Names.Blocks.CABLE);
        GameRegistry.registerTileEntity(TileEntityLaserIsotopeSeperator.class,Names.Blocks.LIS);
        GameRegistry.registerTileEntity(TileEntityCrank.class,Names.Blocks.CRANK);
        GameRegistry.registerTileEntity(TileEntityCompactor.class,Names.Blocks.COMPACTOR);
        GameRegistry.registerTileEntity(TileEntitySharpeningWheel.class,Names.Blocks.SHARPENINGWHEEL);
    }
}
