package com.ragingart.maatsmod.item;

import cofh.api.energy.IEnergyContainerItem;
import com.ragingart.maatsmod.generics.ItemMM;
import com.ragingart.maatsmod.util.LogHelper;
import com.ragingart.maatsmod.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemRSBattery extends ItemMM implements IEnergyContainerItem
{
    private int cap;
    private int maxIn;
    private int maxOut;

    public ItemRSBattery()
    {
        super("battery");
        cap=100000;
        maxIn=500;
        maxOut=500;
    }

    @Override
    public void addInformation(ItemStack itemStack,EntityPlayer entityPlayer, List list, boolean b){
        String info = "Energy: "+getEnergyStored(itemStack)+" / "+getMaxEnergyStored(itemStack);
        list.add(info);
    }


    /* IEnergyContainerItem */
    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int energy = NBTHelper.getInt(container,"Energy");
        int energyReceived = Math.min(cap - energy, Math.min(this.maxIn, maxReceive));

        if (!simulate) {
            energy += energyReceived;
            NBTHelper.setInteger(container,"Energy",energy);
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        int energy = NBTHelper.getInt(container,"Energy");
        int energyExtracted = Math.min(energy, Math.min(this.maxOut, maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
            NBTHelper.setInteger(container,"Energy",energy);
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return NBTHelper.getInt(container,"Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return cap;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack,World world, EntityPlayer entityPlayer){
        LogHelper.info(((ItemRSBattery)itemStack.getItem()).getEnergyStored(itemStack));
        return itemStack;
    }
}
