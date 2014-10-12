package com.ragingart.maatsmod.tileentity;

import com.ragingart.maatsmod.generics.TileEntityMM;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;

/**
 * Created by MaaT on 26.09.2014.
 */
public class TileEntityFluxField extends TileEntityMM {

    public Vec3 origin;

    @Override
    public void writeToNBT(NBTTagCompound cmpd) {
        super.writeToNBT(cmpd);
        cmpd.setInteger("originX",(int)origin.xCoord);
        cmpd.setInteger("originY",(int)origin.yCoord);
        cmpd.setInteger("originZ",(int)origin.zCoord);
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd) {
        super.readFromNBT(cmpd);
        int x = cmpd.getInteger("originX");
        int y = cmpd.getInteger("originY");
        int z = cmpd.getInteger("originZ");
        origin = Vec3.createVectorHelper(x,y,z);
    }
}
