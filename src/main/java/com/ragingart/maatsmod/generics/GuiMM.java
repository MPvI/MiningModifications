package com.ragingart.maatsmod.generics;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by MaaT on 10.10.2014.
 */
public abstract class GuiMM extends GuiContainer {

    protected GuiMM(Container p_i1072_1_) {
        super(p_i1072_1_);
    }

    protected GuiMM(){
        this(null);
    }

    public abstract GuiMM get(EntityPlayer entityPlayer,TileEntity aTile);
}
