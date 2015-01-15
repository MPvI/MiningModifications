package com.ragingart.miningmodifications.tileentity.machines;

import com.ragingart.miningmodifications.generics.TileEntityEnergyGen;
import com.ragingart.miningmodifications.ref.Fluids;
import com.ragingart.miningmodifications.util.CasingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityWaterTurbine extends TileEntityEnergyGen {

    public TileEntityWaterTurbine(){
        super();
        tank.setCapacity(1000);
        energy.setCapacity(1000);
    }

    @Override
    public void updateEntity(){
        super.updateEntity();
        if(!worldObj.isRemote){
            checkWaterFlow();
            isActive();
        }
    }

    private void isActive(){
        Block aBlock = worldObj.getBlockState(pos.offsetDown()).getBlock();
        int aMeta = aBlock.getMetaFromState(worldObj.getBlockState(pos.offsetDown()));

        if(tank.getFluidAmount() != 0 && tank.getFluid().getFluid().getID() == Fluids.ID.HIGHHELDWATER.ordinal()) {
            if (machineHelper.hasPort(0, CasingHelper.Port.FOUTPUT) && (aBlock.getMaterial() == Material.air || aBlock.getMaterial() == Material.water)) {
                machineHelper.setState(2);
                worldObj.setBlockState(pos.offsetDown(), Blocks.flowing_water.getDefaultState());
                worldObj.markBlockForUpdate(pos.offsetDown());
                energy.receiveEnergy(tank.drain(1000, true).amount, false);
            } else {
                machineHelper.setState(1);
                if (aBlock.getMaterial() == Material.water && aMeta == 0) {
                    worldObj.setBlockToAir(pos.offsetDown());
                }
            }
        } else{
            machineHelper.setState(0);
            if (aBlock.getMaterial() == Material.water && aMeta == 0) {
                worldObj.setBlockToAir(pos.offsetDown());
            }
        }
    }

    private void checkWaterFlow(){
        Block aBlock = worldObj.getBlockState(pos.offsetUp()).getBlock();
        int aMeta = aBlock.getMetaFromState(worldObj.getBlockState(pos.offsetUp()));
        if(machineHelper.hasPort(1,CasingHelper.Port.FINPUT) && aMeta >= 8  && aBlock.getMaterial()== Material.water && tank.getFluidAmount() == 0) {
            machineHelper.setState(1);

            int waterAbove = 1;
            for(int i = 1; i < 7; i++) {
                aBlock = worldObj.getBlockState(pos.offsetUp(i)).getBlock();
                aMeta = aBlock.getMetaFromState(worldObj.getBlockState(pos.offsetUp(i)));
                if(aMeta < 8  || aBlock.getMaterial()!= Material.water)
                    break;
                waterAbove++;
            }
            tank.fill(new FluidStack(Fluids.ID.HIGHHELDWATER.ordinal(), waterAbove), true);
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
