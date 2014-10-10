package com.ragingart.maatsmod.container;

import cofh.lib.gui.slot.SlotEnergy;
import com.ragingart.maatsmod.generics.ContainerMM;
import com.ragingart.maatsmod.tileentity.TileEntityCharger;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class ContainerCharger extends ContainerMM{

    public ContainerCharger(InventoryPlayer invPlayer,TileEntity tileEntity){
        this.addSlotToContainer(new SlotEnergy((TileEntityCharger)tileEntity, 0, 80, 22));
        this.addPlayerInventory(invPlayer);
    }
}
