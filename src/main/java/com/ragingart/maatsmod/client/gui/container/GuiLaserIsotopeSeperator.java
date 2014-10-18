package com.ragingart.maatsmod.client.gui.container;

import com.ragingart.maatsmod.container.ContainerLaserIsotopeSeperator;
import com.ragingart.maatsmod.generics.GuiMM;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by MaaT on 18.10.2014.
 */
public class GuiLaserIsotopeSeperator extends GuiMM{



    public GuiLaserIsotopeSeperator(InventoryPlayer inventory, TileEntity tileEntity) {
        super(new ContainerLaserIsotopeSeperator(inventory,tileEntity));

    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

    }
}
