package com.ragingart.maatsmod.client.gui.container.machines;

import com.ragingart.maatsmod.container.machines.ContainerLaserSeperator;
import com.ragingart.maatsmod.generics.GuiMM;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by MaaT on 18.10.2014.
 */
public class GuiLaserSeperator extends GuiMM{



    public GuiLaserSeperator(InventoryPlayer inventory, TileEntity tileEntity) {
        super(new ContainerLaserSeperator(inventory,tileEntity));

    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

    }
}
