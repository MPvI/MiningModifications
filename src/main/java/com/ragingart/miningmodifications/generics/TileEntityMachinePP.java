package com.ragingart.miningmodifications.generics;

import com.ragingart.miningmodifications.api.IMusclePower;
import com.ragingart.miningmodifications.network.PacketHandler;
import com.ragingart.miningmodifications.network.messages.MessageTileEntityMachinePP;
import com.ragingart.miningmodifications.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public abstract class TileEntityMachinePP extends TileEntityMM implements IMusclePower,IInventory {

    protected ItemStack input;
    protected ItemStack output;
    private EnumFacing facing=EnumFacing.EAST;

    protected int timer = -1;

    public TileEntityMachinePP(){
        super();
    }


    public void updateEntity() {
        if(!worldObj.isRemote && (timer == -1 || timer%10==0)) {
            PacketHandler.INSTANCE.sendToAll(new MessageTileEntityMachinePP(this));
            timer=0;
        }
        timer++;
    }

    public void setFacing(EnumFacing dir){
        switch(dir){
            case UP:
            case DOWN:
                facing = facing.rotateAround(dir.getAxis());
                break;
            default:
                facing = dir;
                break;
        }

    }

    public EnumFacing getFacing() {
        return facing;
    }

    /*IMusclePower*/

    @Override
    public boolean canAcceptMusclePower(EnumFacing from){
        return true;
    }


    /* IInventory */

    @Override
    public void clear() {

    }

    @Override
    public void openInventory(EntityPlayer playerIn) {

    }

    @Override
    public void closeInventory(EntityPlayer playerIn) {

    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack){return false;}

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if(slot == 0)
            return input;
        else
            return output;
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
        if(slotIndex == 0)
            input = itemStack;
        else
            output = itemStack;
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
    public void writeToNBT(NBTTagCompound cmpd){
        super.writeToNBT(cmpd);
        NBTHelper.saveItemstackToNBT(cmpd,"input", input);
        NBTHelper.saveItemstackToNBT(cmpd,"output", output);
        cmpd.setInteger("Facing",facing.ordinal());
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd){
        super.readFromNBT(cmpd);
        input = NBTHelper.getItemstackFromNBT(cmpd, "input");
        output = NBTHelper.getItemstackFromNBT(cmpd, "output");
        facing = EnumFacing.getFront(cmpd.getInteger("Facing"));
    }


}
