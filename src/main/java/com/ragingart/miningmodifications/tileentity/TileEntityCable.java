package com.ragingart.miningmodifications.tileentity;

import cofh.api.energy.TileEnergyHandler;
import com.ragingart.miningmodifications.util.RFHelper;

/**
 * Created by MaaT on 16.10.2014.
 */
public class TileEntityCable extends TileEnergyHandler {

    public TileEntityCable(){
        super();
        storage.setCapacity(500);
    }

    @Override
    public void updateEntity() {
        RFHelper.transferEnergyToAdjacent(this, 1000);
    }
}
