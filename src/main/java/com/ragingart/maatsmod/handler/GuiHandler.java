package com.ragingart.maatsmod.handler;

import com.ragingart.maatsmod.client.gui.container.GuiCharger;
import com.ragingart.maatsmod.client.gui.container.GuiDischarger;
import com.ragingart.maatsmod.client.gui.container.GuiRFEnergyStorage;
import com.ragingart.maatsmod.client.gui.container.GuiWaterTurbine;
import com.ragingart.maatsmod.container.ContainerCharger;
import com.ragingart.maatsmod.container.ContainerDischarger;
import com.ragingart.maatsmod.container.ContainerRFEnergyStorage;
import com.ragingart.maatsmod.container.ContainerWaterTurbine;
import com.ragingart.maatsmod.ref.Gui;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (ID == Gui.ID.CHARGER.ordinal()){
            return new ContainerCharger(player.inventory, tileEntity);
        }
        else if(ID == Gui.ID.DISCHARGER.ordinal()){
            return new ContainerDischarger(player.inventory,tileEntity);
        }
        else if(ID == Gui.ID.WATERTURBINE.ordinal()){
            return new ContainerWaterTurbine(player.inventory,tileEntity);
        }
        else if(ID == Gui.ID.RFENERGYSTORAGE.ordinal()){
            return new ContainerRFEnergyStorage(player.inventory,tileEntity);
        }
        return 0;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (ID == Gui.ID.CHARGER.ordinal()){
            return new GuiCharger(player.inventory,tileEntity);
        }
        else if(ID == Gui.ID.DISCHARGER.ordinal()){
            return new GuiDischarger(player.inventory,tileEntity);
        }
        else if(ID == Gui.ID.WATERTURBINE.ordinal()){
            return new GuiWaterTurbine(player.inventory,tileEntity);
        }
        else if(ID == Gui.ID.RFENERGYSTORAGE.ordinal()){
            return new GuiRFEnergyStorage(player.inventory,tileEntity);
        }
        return 0;
    }
}
