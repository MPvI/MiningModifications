package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.api.IMusclePower;
import com.ragingart.maatsmod.network.PacketHandler;
import com.ragingart.maatsmod.network.messages.MessageTileEntityMachinePP;
import com.ragingart.maatsmod.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by XtraX on 18.10.2014.
 */
public abstract class TileEntityMachinePP extends TileEntityMM implements IMusclePower,IInventory {

    protected ItemStack inventory[] = new ItemStack[2];
    private ForgeDirection facing=ForgeDirection.EAST;

    protected int timer = -1;

    public TileEntityMachinePP(){
        super();
    }

    @Override
    public void updateEntity() {
        if(!worldObj.isRemote && (timer == -1 || timer%10==0)) {
            PacketHandler.INSTANCE.sendToAll(new MessageTileEntityMachinePP(this));
            timer=0;
        }
        timer++;
    }

    public void setFacing(ForgeDirection forgeDirection){
        switch(forgeDirection){
            case UP:
            case DOWN:
                facing = facing.getRotation(forgeDirection);
                break;
            default:
                facing = forgeDirection;
                break;
        }

    }

    public ForgeDirection getFacing() {
        return facing;
    }

    /*IMusclePower*/

    @Override
    public boolean canAcceptMusclePower(ForgeDirection from){
        return true;
    }


    /* IInventory */

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack){return false;}

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
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
        inventory[slotIndex] = itemStack;
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
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public void writeToNBT(NBTTagCompound cmpd){
        super.writeToNBT(cmpd);
        NBTHelper.saveInventoryToNBT(cmpd,inventory);
        cmpd.setInteger("Facing",facing.ordinal());
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd){
        super.readFromNBT(cmpd);
        inventory = NBTHelper.getInventoryFromNBT(cmpd);
        facing = ForgeDirection.getOrientation(cmpd.getInteger("Facing"));
    }
}
