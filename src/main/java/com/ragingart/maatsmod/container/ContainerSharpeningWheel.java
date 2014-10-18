package com.ragingart.maatsmod.container;


import com.ragingart.maatsmod.generics.ContainerMM;
import com.ragingart.maatsmod.tileentity.TileEntitySharpeningWheel;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

public class ContainerSharpeningWheel extends ContainerMM{

    public ContainerSharpeningWheel(InventoryPlayer invPlayer, TileEntity tileEntity){
        this.addSlotToContainer(new Slot((TileEntitySharpeningWheel)tileEntity, 0, 50, 22));
        this.addSlotToContainer(new Slot((TileEntitySharpeningWheel)tileEntity, 1, 110, 22));
        this.addPlayerInventory(invPlayer);
    }
}
