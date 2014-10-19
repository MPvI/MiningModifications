package com.ragingart.maatsmod.tileentity.handmachines;

import com.ragingart.maatsmod.api.IMusclePower;
import com.ragingart.maatsmod.init.ModBlocks;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by MaaT on 16.10.2014.
 */
public class TileEntityCrank extends TileEntity implements IMusclePower{

    public TileEntityCrank(){
        super();
    }

    private int animTimer = 0;
    private int remainingActiveTime = 0;

    @Override
    public void updateEntity() {
        super.updateEntity();

        if(remainingActiveTime > 0){
            animTimer++;
            remainingActiveTime--;
        }

        if(animTimer == 100){
            animTimer=0;
        }
        if(!worldObj.isRemote && checkLink()==ForgeDirection.UNKNOWN){
            worldObj.setBlockToAir(xCoord,yCoord,zCoord);
            worldObj.spawnEntityInWorld(new EntityItem(worldObj,xCoord,yCoord,zCoord,new ItemStack(ModBlocks.Crank)));
        }
    }

    public int getAnimTimer() {
        return animTimer;
    }

    public ForgeDirection checkLink(){
        for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
            int x=this.xCoord+dir.offsetX;
            int y=this.yCoord+dir.offsetY;
            int z=this.zCoord+dir.offsetZ;

            TileEntity aTile = worldObj.getTileEntity(x,y,z);
            if(aTile instanceof IMusclePower){
                if(((IMusclePower) aTile).canAcceptMusclePower(dir.getOpposite())){
                    return dir;
                }
            }
        }
        return ForgeDirection.UNKNOWN;
    }

    public boolean provideMusclePower()
    {
        if(remainingActiveTime == 0) {
            ForgeDirection dir = checkLink();
            if (dir != ForgeDirection.UNKNOWN) {
                ((IMusclePower) worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ)).receiveMusclePower(1);
                remainingActiveTime=20;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canAcceptMusclePower(ForgeDirection from){
        return false;
    }

    @Override
    public void receiveMusclePower(int amount){}
}
