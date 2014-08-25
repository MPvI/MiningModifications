package com.ragingart.maatsmod.item;

import cofh.api.energy.IEnergyContainerItem;
import com.ragingart.maatsmod.generics.ItemMM;
import net.minecraft.item.ItemStack;

public class ItemRSBattery extends ItemMM implements IEnergyContainerItem
{
    public ItemRSBattery()
    {
        super("battery");
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return 0;
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return 0;
    }
}
