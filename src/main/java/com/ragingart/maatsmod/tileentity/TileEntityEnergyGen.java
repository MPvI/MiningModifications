package com.ragingart.maatsmod.tileentity;

import cofh.api.energy.IEnergyContainerItem;
import cofh.lib.util.helpers.EnergyHelper;
import com.ragingart.maatsmod.generics.TileEntityMachineMM;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergyGen extends TileEntityMachineMM implements IInventory {

    private ItemStack inventory;
    private int burntime = 0;



    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if(!worldObj.isRemote) {
            int burnenergy = 1;
            int add_burntime = TileEntityFurnace.getItemBurnTime(inventory);
            int get_energy = getEnergyStored(ForgeDirection.UNKNOWN);
            int get_maxenergy = getMaxEnergyStored(ForgeDirection.UNKNOWN);
            if (burntime == 0 && add_burntime != 0 && get_energy + add_burntime <= get_maxenergy) {
                if (decrStackSize(0, 1) != null) {
                    burntime = add_burntime / burnenergy;
                }
            }
            if (burntime > 0) {
                receiveEnergy(ForgeDirection.UNKNOWN, burnenergy, false);
                burntime--;
            }
            int num_consum = 0;
            int id_consum[] = new int[6];
            for (int i = 0; i < 6; i++) {
                if (EnergyHelper.isAdjacentEnergyHandlerFromSide(this, i) && canConnectEnergy(ForgeDirection.values()[i])) {
                    id_consum[num_consum]=i;
                    num_consum++;
                }
            }
            if (num_consum > 0 && getEnergyStored(ForgeDirection.UNKNOWN) >= 10*num_consum){
                for (int i = 0; i < num_consum; i++) {
                    EnergyHelper.insertEnergyIntoAdjacentEnergyHandler(this, id_consum[i], 10, false);
                    extractEnergy(ForgeDirection.UNKNOWN, 10, false);
                }
            }
            else if(num_consum > 0){
                int max_output = getEnergyStored(ForgeDirection.UNKNOWN)/num_consum;
                for (int i = 0; i < num_consum; i++) {
                    EnergyHelper.insertEnergyIntoAdjacentEnergyHandler(this, id_consum[i], max_output, false);
                    extractEnergy(ForgeDirection.UNKNOWN, max_output, false);
                }
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound cmpd) {
        super.writeToNBT(cmpd);
        NBTTagCompound inv = new NBTTagCompound();
        if(inventory != null)inventory.writeToNBT(inv);
        cmpd.setTag("Inventory",inv);
        cmpd.setInteger("Burntime",burntime);
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd) {
        super.readFromNBT(cmpd);
        inventory = ItemStack.loadItemStackFromNBT(cmpd.getCompoundTag("Inventory"));
        burntime = cmpd.getInteger("Burntime");
    }

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
        return "EnergyGen";
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
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }


}
