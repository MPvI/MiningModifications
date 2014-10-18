package com.ragingart.maatsmod.container.machines;

import com.ragingart.maatsmod.generics.ContainerMM;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by XtraX on 07.10.2014.
 */
public class ContainerWaterTurbine extends ContainerMM{

    public ContainerWaterTurbine(InventoryPlayer invPlayer, TileEntity tileEntity){
       // this.addSlotToContainer(new SlotMM((TileEntityWaterTurbine)tileEntity, 0, 80, 22));
        this.addPlayerInventory(invPlayer);
    }
}
