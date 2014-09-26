package com.ragingart.maatsmod.tileentity;

import com.ragingart.maatsmod.generics.TileEntityMM;

/**
 * Created by MaaT on 26.09.2014.
 */
public class TileEntityPlatformExt extends TileEntityMM {
    private int animationTimer = 0;

    public int getAnimationTimer(){
        return this.animationTimer;
    }

    @Override
    public void updateEntity() {
        animationTimer++;
    }
}
