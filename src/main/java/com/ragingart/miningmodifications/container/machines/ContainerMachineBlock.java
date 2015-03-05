package com.ragingart.miningmodifications.container.machines;

import com.ragingart.miningmodifications.generics.ContainerMM;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityMachineBlock;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

public class ContainerMachineBlock extends ContainerMM {
    public ContainerMachineBlock(InventoryPlayer invPlayer, TileEntity tileEntity) {
        switch (((TileEntityMachineBlock)tileEntity).getMultiBlockMode())
        {
            case 0:
                this.addSlotToContainer(new Slot((TileEntityMachineBlock)tileEntity, 0, 80, 22));
                break;
            case 1:
                break;
            case 2:
                break;
        }
        this.addPlayerInventory(invPlayer);
    }
}
