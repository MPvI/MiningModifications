package com.ragingart.maatsmod.handler;

import com.ragingart.maatsmod.client.gui.container.GuiCharger;
import com.ragingart.maatsmod.container.ContainerCharger;
import com.ragingart.maatsmod.tileentity.TileEntityCharger;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntityCharger tileEntityCharger = (TileEntityCharger) world.getTileEntity(x, y, z);
        return new ContainerCharger(player.inventory,tileEntityCharger);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntityCharger tileEntityCharger = (TileEntityCharger) world.getTileEntity(x, y, z);
        return new GuiCharger(player.inventory,tileEntityCharger);
    }
}
