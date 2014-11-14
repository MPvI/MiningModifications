package com.ragingart.miningmodifications.generics;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityMM extends TileEntity {

    @Override
    public void updateEntity() {
        super.updateEntity();
    }

    @Override
    public void writeToNBT(NBTTagCompound cmpd){
        super.writeToNBT(cmpd);
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd){
        super.readFromNBT(cmpd);
    }

}
