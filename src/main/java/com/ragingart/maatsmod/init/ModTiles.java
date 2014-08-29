package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.tileentity.TileEntityCharger;
import com.ragingart.maatsmod.tileentity.TileEntityCreativeEnergy;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by MaaT on 28.08.2014.
 */
public class ModTiles {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityCharger.class, "charger");
        GameRegistry.registerTileEntity(TileEntityCreativeEnergy.class, "crenergy");
    }
}
