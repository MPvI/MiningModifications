package com.ragingart.maatsmod.generics;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityMM extends TileEntity {


    @Override
    public void writeToNBT(NBTTagCompound cmpd){
        super.writeToNBT(cmpd);
        writeSpecialNBT(cmpd);
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd){
        super.readFromNBT(cmpd);
        readSpecialNBT(cmpd);
    }

    public abstract void writeSpecialNBT(NBTTagCompound cmpd);

    public abstract void readSpecialNBT(NBTTagCompound cmpd);
}
