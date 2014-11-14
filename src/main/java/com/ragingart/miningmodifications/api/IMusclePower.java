package com.ragingart.miningmodifications.api;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by XtraX on 18.10.2014.
 */
public abstract interface IMusclePower {

    public abstract boolean canAcceptMusclePower(ForgeDirection from);

    public abstract int receiveMusclePower(int amount);

}
