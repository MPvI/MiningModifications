package com.ragingart.maatsmod.container;

import cofh.lib.gui.slot.SlotEnergy;
import com.ragingart.maatsmod.generics.ContainerMM;
import com.ragingart.maatsmod.tileentity.TileEntityDischarger;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerDischarger extends ContainerMM{

    public ContainerDischarger(InventoryPlayer invPlayer, TileEntityDischarger tileEntityDischarger){
        this.addSlotToContainer(new SlotEnergy(tileEntityDischarger, 0, 80, 22));
        this.addPlayerInventory(invPlayer);
    }

}
