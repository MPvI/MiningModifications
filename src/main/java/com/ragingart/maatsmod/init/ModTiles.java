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
        GameRegistry.registerTileEntity(TileEntityEnergyGen.class,Names.Blocks.ENERGYGEN);
        GameRegistry.registerTileEntity(TileEntityDischarger.class,Names.Blocks.DISCHARGER);
        GameRegistry.registerTileEntity(TileEntityCreativeEnergy.class,Names.Blocks.CENERGY);
        GameRegistry.registerTileEntity(TileEntityPlatformBase.class,Names.Blocks.PLATFORM_BASE);
        GameRegistry.registerTileEntity(TileEntityPlatformExt.class,Names.Blocks.PLATFORM_EXT);
    }
}
