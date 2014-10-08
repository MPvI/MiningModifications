package com.ragingart.maatsmod.container;

import cofh.lib.gui.slot.SlotEnergy;
import com.ragingart.maatsmod.generics.ContainerMM;
import com.ragingart.maatsmod.tileentity.TileEntityCharger;
import com.ragingart.maatsmod.tileentity.TileEntityEnergyExt;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerEnergyExt extends ContainerMM{

    public ContainerEnergyExt(InventoryPlayer invPlayer, TileEntityEnergyExt tileEntityEnergyExt){
        this.addSlotToContainer(new SlotEnergy(tileEntityEnergyExt, 0, 80, 22));
        this.addPlayerInventory(invPlayer);
    }

}
