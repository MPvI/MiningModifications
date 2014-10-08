package com.ragingart.maatsmod.handler;

import com.ragingart.maatsmod.client.gui.container.GuiCharger;
import com.ragingart.maatsmod.client.gui.container.GuiDischarger;
import com.ragingart.maatsmod.client.gui.container.GuiEnergyGen;
import com.ragingart.maatsmod.container.ContainerCharger;
import com.ragingart.maatsmod.container.ContainerDischarger;
import com.ragingart.maatsmod.container.ContainerEnergyGen;
import com.ragingart.maatsmod.ref.Gui;
import com.ragingart.maatsmod.tileentity.TileEntityCharger;
import com.ragingart.maatsmod.tileentity.TileEntityDischarger;
import com.ragingart.maatsmod.tileentity.TileEntityEnergyGen;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (ID == Gui.ID.CHARGER.ordinal()){
            return new ContainerCharger(player.inventory, (TileEntityCharger) tileEntity);
        }
        else if(ID == Gui.ID.GENERATOR.ordinal()){
            return new ContainerEnergyGen(player.inventory, (TileEntityEnergyGen) tileEntity);
        }
        else if(ID == Gui.ID.DISCHARGER.ordinal()){
            return new ContainerDischarger(player.inventory, (TileEntityDischarger) tileEntity);
        }
        return 0;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (ID == Gui.ID.CHARGER.ordinal()){
            return new GuiCharger(player.inventory,(TileEntityCharger)tileEntity);
        }
        else if(ID == Gui.ID.GENERATOR.ordinal()){
            return new GuiEnergyGen(player.inventory,(TileEntityEnergyGen)tileEntity);
        }
        else if(ID == Gui.ID.DISCHARGER.ordinal()){
            return new GuiDischarger(player.inventory,(TileEntityDischarger)tileEntity);
        }
        return 0;
    }
}
