package com.ragingart.miningmodifications.tileentity;

import cofh.api.energy.TileEnergyHandler;
import com.ragingart.miningmodifications.util.RFHelper;

public class TileEntityCable extends TileEnergyHandler {

    public TileEntityCable(){
        super();
        storage.setCapacity(500);
    }

    public void updateEntity() {
        RFHelper.transferEnergyToAdjacent(this, 1000);
    }
}
