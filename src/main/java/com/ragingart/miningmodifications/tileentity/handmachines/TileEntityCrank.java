package com.ragingart.miningmodifications.tileentity.handmachines;

import com.ragingart.miningmodifications.api.IMusclePower;
import com.ragingart.miningmodifications.init.ModBlocks;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityCrank extends TileEntity implements IMusclePower{

    public TileEntityCrank(){
        super();
    }

    private int animTimer = 0;
    private int remainingActiveTime = 0;

    public void updateEntity() {

        if(remainingActiveTime > 0){
            animTimer++;
            remainingActiveTime--;
        }

        if(animTimer == 100){
            animTimer=0;
        }
        if(!worldObj.isRemote && checkLink()==null){
            worldObj.setBlockToAir(pos);
            worldObj.spawnEntityInWorld(new EntityItem(worldObj,pos.getX(),pos.getY(),pos.getZ(),new ItemStack(ModBlocks.Crank)));
        }
    }

    public int getAnimTimer() {
        return animTimer;
    }

    public EnumFacing checkLink(){
        for(EnumFacing dir : EnumFacing.values()){

            TileEntity aTile = worldObj.getTileEntity(pos.offset(dir));
            if(aTile instanceof IMusclePower){
                if(((IMusclePower) aTile).canAcceptMusclePower(dir.getOpposite())){
                    return dir;
                }
            }
        }
        return EnumFacing.DOWN;
    }

    public boolean provideMusclePower()
    {
        if(remainingActiveTime == 0) {
            EnumFacing dir = checkLink();
            if (dir != null) {
                remainingActiveTime=((IMusclePower) worldObj.getTileEntity(pos.offset(dir))).receiveMusclePower(1);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canAcceptMusclePower(EnumFacing from){
        return false;
    }

    @Override
    public int receiveMusclePower(int amount){return 0;}
}
