package com.ragingart.maatsmod.container;

import cofh.lib.gui.slot.SlotEnergy;
import com.ragingart.maatsmod.generics.ContainerMM;
import com.ragingart.maatsmod.tileentity.TileEntityDischarger;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerDischarger extends ContainerMM{

    public ContainerDischarger(InventoryPlayer invPlayer,TileEntity tileEntity){
        this.addSlotToContainer(new SlotEnergy((TileEntityDischarger)tileEntity, 0, 80, 22));
        this.addPlayerInventory(invPlayer);
    }
}
