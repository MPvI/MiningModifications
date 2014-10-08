package com.ragingart.maatsmod.container;

import com.ragingart.maatsmod.generics.ContainerMM;
import com.ragingart.maatsmod.generics.SlotMM;
import com.ragingart.maatsmod.tileentity.TileEntityEnergyGen;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;

/**
 * Created by XtraX on 07.10.2014.
 */
public class ContainerEnergyGen extends ContainerMM{
    public ContainerEnergyGen(InventoryPlayer invPlayer, TileEntityEnergyGen tileEntityEnergyGen){
        this.addSlotToContainer(new SlotMM(tileEntityEnergyGen,0,80,22));
        this.addPlayerInventory(invPlayer);
    }
}
