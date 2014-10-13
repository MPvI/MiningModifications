package com.ragingart.maatsmod.tileentity;

import com.ragingart.maatsmod.ref.Fluids;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityWaterTurbine extends TileEntityEnergyGen {

    @Override
    public void updateEntity()
    {
        if(tank.getCapacity() != 1000)
            tank.setCapacity(1000);
        super.updateEntity();
        if(!worldObj.isRemote){
            Block bblock = this.worldObj.getBlock(this.xCoord + ForgeDirection.UP.offsetX, this.yCoord + ForgeDirection.UP.offsetY, this.zCoord + ForgeDirection.UP.offsetZ);
            if(bblock instanceof BlockDynamicLiquid) {

                BlockDynamicLiquid ablock = (BlockDynamicLiquid) bblock;
                int test = ablock.getDamageValue(worldObj,this.xCoord + ForgeDirection.UP.offsetX, this.yCoord + ForgeDirection.UP.offsetY, this.zCoord + ForgeDirection.UP.offsetZ);
                //worldObj. ChatMessage(new ChatComponentText(String.valueOf(test)));
                if (ablock.getMaterial() == Blocks.flowing_water.getMaterial()) {
                    tank.fill(new FluidStack(Fluids.ID.HIGHHELDWATER.ordinal(), 1000), true);
                    this.worldObj.setBlock(this.xCoord + ForgeDirection.UP.offsetX, this.yCoord + ForgeDirection.UP.offsetY, this.zCoord + ForgeDirection.UP.offsetZ, Blocks.air);
                    this.worldObj.setBlock(this.xCoord + ForgeDirection.DOWN.offsetX, this.yCoord + ForgeDirection.DOWN.offsetY, this.zCoord + ForgeDirection.DOWN.offsetZ, Blocks.water);
                }
            }
            else{
                this.worldObj.setBlock(this.xCoord + ForgeDirection.DOWN.offsetX, this.yCoord + ForgeDirection.DOWN.offsetY, this.zCoord + ForgeDirection.DOWN.offsetZ, Blocks.air);
            }
            if(tank.getFluidAmount() != 0 && tank.getFluid().getFluid().getID() == Fluids.ID.HIGHHELDWATER.ordinal()){
                energy.receiveEnergy(tank.drain(1000, true).amount, false);
            }
        }
    }


    @Override
    public int[] validPorts() {
        return new int[]{0,1,4,5};
    }

    @Override
    public boolean isWorkDone() {
        return hasWork;
    }

    @Override
    public void writeToNBT(NBTTagCompound cmpd) {
        super.writeToNBT(cmpd);
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd) {
        super.readFromNBT(cmpd);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

}
