package com.ragingart.miningmodifications.proxy;

import com.ragingart.miningmodifications.init.ModTiles;

public abstract class CommonProxy implements IProxy {
    public void registerTileEntities() {
        ModTiles.init();
    }

}
