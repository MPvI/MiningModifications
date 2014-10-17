package com.ragingart.maatsmod.tileentity;

import cofh.api.energy.TileEnergyHandler;
import com.ragingart.maatsmod.util.RFHelper;

/**
 * Created by MaaT on 16.10.2014.
 */
public class TileEntityCable extends TileEnergyHandler {
    @Override
    public void updateEntity() {
        RFHelper.transferEnergyToAdjacent(this,1000);
    }
}
