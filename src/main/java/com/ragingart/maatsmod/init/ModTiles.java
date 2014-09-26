package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.tileentity.TileEntityCharger;
import com.ragingart.maatsmod.tileentity.TileEntityCreativeEnergy;
import com.ragingart.maatsmod.tileentity.TileEntityPlatformBase;
import com.ragingart.maatsmod.tileentity.TileEntityPlatformExt;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by MaaT on 28.08.2014.
 */
public class ModTiles {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityCharger.class,Names.Blocks.CHARGER);
        GameRegistry.registerTileEntity(TileEntityCreativeEnergy.class,Names.Blocks.CENERGY);
        GameRegistry.registerTileEntity(TileEntityPlatformBase.class,Names.Blocks.PLATFORM_BASE);
        GameRegistry.registerTileEntity(TileEntityPlatformExt.class,Names.Blocks.PLATFORM_EXT);
    }
}
