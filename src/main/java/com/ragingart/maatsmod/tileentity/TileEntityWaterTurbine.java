package com.ragingart.maatsmod.tileentity;

import com.ragingart.maatsmod.ref.Fluids;
import com.ragingart.maatsmod.util.CasingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityWaterTurbine extends TileEntityEnergyGen {

    public TileEntityWaterTurbine(){
        super();
        tank.setCapacity(1000);
    }


    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if(!worldObj.isRemote){
            checkWaterFlow();
        }
    }



    private void checkWaterFlow(){
        int x = this.xCoord;
        int z = this.zCoord;
        int y_up = this.yCoord + ForgeDirection.UP.offsetY;
        int y_down = this.yCoord + ForgeDirection.DOWN.offsetY;


        Block aBlock = worldObj.getBlock(x, y_up, z);
        int aMeta = worldObj.getBlockMetadata(x, y_up, z);

        if(aBlock.getMaterial() != Material.water && aMeta != 8) {
            machineHelper.setState(0);
            // Bug 1: Wenn Machine Outputvalve hat und läuft und Output valve dann getauscht wird auf blank wird wasserblock nicht entfernt
            // Bug 2: Wenn 2 Turbinen nebeneinander sind #Erklärung
            if(worldObj.getBlock(x, y_down, z).getMaterial() == Material.water) // TODO this no good
                worldObj.setBlockToAir(x, y_down, z);

        } else if(machineHelper.hasPort(1,CasingHelper.Port.FINPUT) && aMeta == 8  && aBlock.getMaterial()== Material.water && tank.getFluidAmount() == 0) {
            machineHelper.setState(1);
            int new_y_up = y_up;

            int waterAbove = 1;
            for(int i = 0; i < 7; i++) {
                new_y_up += ForgeDirection.UP.offsetY;
                aBlock = worldObj.getBlock(x, new_y_up, z);
                aMeta = worldObj.getBlockMetadata(x, new_y_up, z);
                if(aMeta == 8  && aBlock.getMaterial()== Material.water)
                {
                    waterAbove++;
                    break;
                }
            }
            tank.fill(new FluidStack(Fluids.ID.HIGHHELDWATER.ordinal(), waterAbove), true);
            //LogHelper.info(waterAbove);
        }
        if(machineHelper.hasPort(0, CasingHelper.Port.FOUTPUT) && tank.getFluidAmount() != 0 && tank.getFluid().getFluid().getID() == Fluids.ID.HIGHHELDWATER.ordinal()){
            aBlock = worldObj.getBlock(x, y_down, z);
            if(aBlock.getMaterial() == Material.air || aBlock.getMaterial() == Material.water) {
                machineHelper.setState(2);
                worldObj.setBlock(x, y_down, z, Blocks.flowing_water);
                worldObj.markBlockForUpdate(x,y_down,z);
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
