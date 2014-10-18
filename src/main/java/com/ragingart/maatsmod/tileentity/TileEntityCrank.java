package com.ragingart.maatsmod.tileentity;

import com.ragingart.maatsmod.inter.IMusclePower;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by MaaT on 16.10.2014.
 */
public class TileEntityCrank extends TileEntity implements IMusclePower{

    public TileEntityCrank(){
        super();
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
    }

    public boolean provideMusclePower()
    {
        int y_down = this.yCoord + ForgeDirection.DOWN.offsetY;
        TileEntity aTile = worldObj.getTileEntity(xCoord, y_down, zCoord);
        if(aTile instanceof IMusclePower && ((IMusclePower) aTile).canAcceptMusclePower()){
            ((IMusclePower) aTile).receiveMusclePower(1);
            return true;
        }
        return false;
    }

    @Override
    public boolean canAcceptMusclePower(){
        return false;
    }

    @Override
    public void receiveMusclePower(int amount){}
}
