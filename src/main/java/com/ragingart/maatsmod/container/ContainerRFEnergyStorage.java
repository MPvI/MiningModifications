package com.ragingart.maatsmod.container;

import com.ragingart.maatsmod.generics.ContainerMM;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerRFEnergyStorage extends ContainerMM{

    public ContainerRFEnergyStorage(InventoryPlayer invPlayer, TileEntity tileEntity){
        this.addPlayerInventory(invPlayer);
    }
}
