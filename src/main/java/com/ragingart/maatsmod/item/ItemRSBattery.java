package com.ragingart.maatsmod.item;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import com.ragingart.maatsmod.generics.ItemMM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRSBattery extends ItemMM implements IEnergyContainerItem
{
    private EnergyStorage internal = new EnergyStorage(100000);

    public ItemRSBattery()
    {
        super("battery");
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        return internal.receiveEnergy(maxReceive,simulate);
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        return internal.extractEnergy(maxExtract,simulate);
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return internal.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return internal.getMaxEnergyStored();
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int meta, float par8, float par9, float par10){
        System.out.println(internal.getEnergyStored());
        return true;
    }
}
