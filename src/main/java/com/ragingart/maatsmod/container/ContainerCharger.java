package com.ragingart.maatsmod.container;

import com.ragingart.maatsmod.generics.ContainerMM;
import com.ragingart.maatsmod.tileentity.TileEntityCharger;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerCharger extends ContainerMM{

    public ContainerCharger(InventoryPlayer invPlayer, TileEntityCharger tileEntityCharger){
        this.addSlotToContainer(new Slot(tileEntityCharger,0,80,22));
        this.addPlayerInventory(invPlayer);
    }

}
