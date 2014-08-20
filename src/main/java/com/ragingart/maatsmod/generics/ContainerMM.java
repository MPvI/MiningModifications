package com.ragingart.maatsmod.generics;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public abstract class ContainerMM extends Container {

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }

    protected void addPlayerInventory(InventoryPlayer inventoryPlayer) {

        for(int inventoryRowIndex = 0; inventoryRowIndex<3;++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex) {
                 this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 58 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for(int actionBarSlotIndex = 0;actionBarSlotIndex<9;++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 116));
        }
    }
}
