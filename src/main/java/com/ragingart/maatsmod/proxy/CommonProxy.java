package com.ragingart.maatsmod.proxy;

import com.ragingart.maatsmod.tileentity.TileEntityCharger;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IProxy {
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityCharger.class,"charger");
    }
}
