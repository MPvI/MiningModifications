package com.ragingart.maatsmod.proxy;

import com.ragingart.maatsmod.init.ModTiles;

public abstract class CommonProxy implements IProxy {
    public void registerTileEntities() {
        ModTiles.init();
    }

}
