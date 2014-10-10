package com.ragingart.maatsmod.container;

import com.ragingart.maatsmod.generics.ContainerMM;
import com.ragingart.maatsmod.generics.SlotMM;
import com.ragingart.maatsmod.tileentity.TileEntityEnergyGen;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by XtraX on 07.10.2014.
 */
public class ContainerEnergyGen extends ContainerMM{

    public ContainerEnergyGen(InventoryPlayer invPlayer, TileEntity tileEntity){
        this.addSlotToContainer(new SlotMM((TileEntityEnergyGen)tileEntity,0,80,22));
        this.addPlayerInventory(invPlayer);
    }
}
