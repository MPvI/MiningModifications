package com.ragingart.maatsmod.generics;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;
import com.ragingart.maatsmod.network.PacketHandler;
import com.ragingart.maatsmod.network.messages.MessageTileEntityMachineMM;
import com.ragingart.maatsmod.util.CasingHelper;
import com.ragingart.maatsmod.util.MachineHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * Created by MaaT on 29.08.2014.
 */

public abstract class TileEntityMachineMM extends TileEntityMM implements IEnergyHandler,ISidedInventory,IFluidHandler {

    protected EnergyStorage energy = new EnergyStorage(100000);
    protected FluidTank tank = new FluidTank(50000);
    protected MachineHelper machineHelper = new MachineHelper();
    protected ItemStack inventory;


    protected int timer = -1;
    @Override
    public void updateEntity(){
        if(!worldObj.isRemote && (timer == -1 || timer%10==0)) {
            PacketHandler.INSTANCE.sendToAll(new MessageTileEntityMachineMM(this));
            timer=0;
        }
        timer++;
    }

    @Override
    public void writeToNBT(NBTTagCompound cmpd) {
        super.writeToNBT(cmpd);
        energy.writeToNBT(cmpd);
        machineHelper.writePortsToNBT(cmpd);
        tank.writeToNBT(cmpd);
        NBTTagCompound inv = new NBTTagCompound();
        if(inventory != null)inventory.writeToNBT(inv);
        cmpd.setTag("Inventory",inv);
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd) {
        super.readFromNBT(cmpd);
        energy = energy.readFromNBT(cmpd);
        machineHelper.getPortsFromNBT(cmpd);
        tank = tank.readFromNBT(cmpd);
        inventory = ItemStack.loadItemStackFromNBT(cmpd.getCompoundTag("Inventory"));
    }

    /* Custom */
    public MachineHelper getMachineHelper(){
        return machineHelper;
    }

    public void setMachineHelper(MachineHelper aHelper){
        this.machineHelper=aHelper;
    }

    public void setEnergy(int e)
    {
        this.energy.setEnergyStored(e);
    }

    /**
     * Overwrite with acceptable ports in childclass
     * @return valid ports for machine
     */
    public abstract int[] validPorts();

    /**
     * Only determines if item is ready for extraction / machine ready for insertion
     * @return
     */
    public abstract boolean isWorkDone();

    public boolean canAcceptPort(int i){
        for(int p:validPorts()){
            if(p==i){
                return true;
            }
        }
        return false;
    }

    /* IEnergyHandler */

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return energy.receiveEnergy(maxReceive,simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return energy.extractEnergy(maxExtract,simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {

        return energy.getEnergyStored();

    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {

        return energy.getMaxEnergyStored();

    }

    /* IEnergyConnection */

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return machineHelper.hasPort(from.ordinal(), CasingHelper.Port.ENERGY);
    }

    /* ISidedInventory */

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack) {
        return itemStack.getItem() instanceof IEnergyContainerItem;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        if(machineHelper.hasPort(side,CasingHelper.Port.INPUT) || machineHelper.hasPort(side, CasingHelper.Port.OUTPUT)) {
            return new int[]{0};
        }
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack item, int side) {
        return isWorkDone() && isItemValidForSlot(slot, item) && machineHelper.hasPort(side, CasingHelper.Port.INPUT);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack item, int side) {
        return isWorkDone() && machineHelper.hasPort(side, CasingHelper.Port.OUTPUT);
    }


    /* IInventory */

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return inventory;
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= decrementAmount)
            {
                setInventorySlotContents(slotIndex, null);
            }
            else
            {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
        inventory = itemStack;
    }

    @Override
    public String getInventoryName() {
        return worldObj.getBlock(xCoord,yCoord,zCoord).getLocalizedName();
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    /* IFluidHandler */

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if(machineHelper.hasPort(from.ordinal(),CasingHelper.Port.FINPUT))
            return tank.fill(resource,doFill);
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if(machineHelper.hasPort(from.ordinal(),CasingHelper.Port.FOUTPUT))
            return tank.drain(1000,doDrain);
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        if(machineHelper.hasPort(from.ordinal(),CasingHelper.Port.FOUTPUT))
            return tank.drain(maxDrain>1000?1000:maxDrain,doDrain);
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return machineHelper.hasPort(from.ordinal(), CasingHelper.Port.FINPUT);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return machineHelper.hasPort(from.ordinal(), CasingHelper.Port.FOUTPUT);
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[0];
    }

    public int getFluidAmount(){return tank.getFluidAmount();}

    public int getFluidCapacity(){return tank.getCapacity();}

    public int getFluidID(){
        if(tank.getFluid() != null) {
            return tank.getFluid().fluidID;
        }
        return 0;
    }

    public FluidTank getTank(){return tank;}
}
