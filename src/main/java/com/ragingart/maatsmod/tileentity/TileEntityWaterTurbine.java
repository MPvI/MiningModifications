package com.ragingart.maatsmod.tileentity;

import com.ragingart.maatsmod.ref.Fluids;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
            checkWaterFlow();
        }
    }

    private void checkWaterFlow(){
        int x_up = this.xCoord + ForgeDirection.UP.offsetX;
        int y_up = this.yCoord + ForgeDirection.UP.offsetY;
        int z_up = this.zCoord + ForgeDirection.UP.offsetZ;
        int x_down = this.xCoord + ForgeDirection.DOWN.offsetX;
        int y_down = this.yCoord + ForgeDirection.DOWN.offsetY;
        int z_down = this.zCoord + ForgeDirection.DOWN.offsetZ;

        Block aBlock = this.worldObj.getBlock(x_up, y_up, z_up);
        int aMeta = worldObj.getBlockMetadata(x_up, y_up, z_up);

        if(aBlock.getMaterial() != Material.water && aMeta != 8) {
            if(this.worldObj.getBlock(x_down, y_down, z_down).getMaterial() == Material.water)
                this.worldObj.setBlockToAir(x_down, y_down, z_down);
        } else if(aMeta == 8  && aBlock.getMaterial()== Material.water) {
            tank.fill(new FluidStack(Fluids.ID.HIGHHELDWATER.ordinal(), 1000), true);
        }
        if(tank.getFluidAmount() != 0 && tank.getFluid().getFluid().getID() == Fluids.ID.HIGHHELDWATER.ordinal()){
            aBlock = this.worldObj.getBlock(x_down, y_down, z_down);
            if(aBlock.getMaterial() == Material.air || aBlock.getMaterial() == Material.water) {
                this.worldObj.setBlock(x_down, y_down, z_down, Blocks.flowing_water);
                aBlock = this.worldObj.getBlock(x_down, y_down, z_down);
                worldObj.scheduleBlockUpdate(x_down, y_down, z_down, aBlock, 1);
                energy.receiveEnergy(tank.drain(1000, true).amount, false);
            }
        }
    }


    @Override
    public int[] validPorts() {
        return new int[]{0,1};
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
