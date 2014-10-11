package com.ragingart.maatsmod.util;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.lib.util.helpers.EnergyHelper;
import com.ragingart.maatsmod.generics.TileEntityMachineMM;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by MaaT on 11.10.2014.
 */
public class RFHelper {
    public static boolean itemNeedsCharge(ItemStack itemStack){
        return itemStack.getItem() instanceof IEnergyContainerItem && ((IEnergyContainerItem) itemStack.getItem()).getMaxEnergyStored(itemStack) - ((IEnergyContainerItem) itemStack.getItem()).getEnergyStored(itemStack) > 0;
    }

    public static boolean itemCanCharge(ItemStack itemStack){
        return itemStack.getItem() instanceof IEnergyContainerItem && ((IEnergyContainerItem) itemStack.getItem()).getEnergyStored(itemStack)>0;
    }

    public static int transferEnergyFromItem(ItemStack inventory, EnergyStorage energy){
        IEnergyContainerItem item = (IEnergyContainerItem) inventory.getItem();

        int maxExtract = item.getEnergyStored(inventory);
        maxExtract = item.extractEnergy(inventory ,maxExtract, true);
        int maxInsert = energy.getMaxEnergyStored()-energy.getEnergyStored();
        int transferRate = Math.min(maxExtract, maxInsert);

        item.extractEnergy(inventory ,transferRate, false);
        energy.modifyEnergyStored(transferRate);
        return transferRate;
    }

    public static int transferEnergyToItem(EnergyStorage energy, ItemStack inventory){
        IEnergyContainerItem item = (IEnergyContainerItem) inventory.getItem();

        int maxExtract = energy.getEnergyStored();
        int maxInsert = item.getMaxEnergyStored(inventory)-item.getEnergyStored(inventory);
        maxInsert = item.receiveEnergy(inventory ,maxInsert, true);
        int transferRate = Math.min(maxExtract, maxInsert);

        energy.extractEnergy(transferRate, false);
        item.receiveEnergy(inventory, transferRate, false);

        return transferRate;
    }

    public static void transferEnergyToAdjacent(TileEntityMachineMM aTile){
        int num_consum = 0;
        int id_consum[] = new int[6];
        for (int i = 0; i < 6; i++) {
            if (EnergyHelper.isAdjacentEnergyHandlerFromSide(aTile, i) && aTile.canConnectEnergy(ForgeDirection.values()[i])) {
                id_consum[num_consum]=i;
                num_consum++;
            }
        }
        if (num_consum > 0 && aTile.getEnergyStored(ForgeDirection.UNKNOWN) >= 10*num_consum){
            for (int i = 0; i < num_consum; i++) {
                int getTransferedEnergy = EnergyHelper.insertEnergyIntoAdjacentEnergyHandler(aTile, id_consum[i], 10, false);
                aTile.extractEnergy(ForgeDirection.UNKNOWN, getTransferedEnergy, false);
            }
        }
        else if(num_consum > 0){
            int max_output = aTile.getEnergyStored(ForgeDirection.UNKNOWN)/num_consum;
            for (int i = 0; i < num_consum; i++) {
                int getTransferedEnergy = EnergyHelper.insertEnergyIntoAdjacentEnergyHandler(aTile, id_consum[i], max_output, false);
                aTile.extractEnergy(ForgeDirection.UNKNOWN, getTransferedEnergy, false);
            }
        }
    }
}
