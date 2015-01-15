package com.ragingart.miningmodifications.api;

import net.minecraft.util.EnumFacing;


public abstract interface IMusclePower {

    public abstract boolean canAcceptMusclePower(EnumFacing from);

    public abstract int receiveMusclePower(int amount);

}
