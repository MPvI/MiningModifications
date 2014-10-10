package com.ragingart.maatsmod.handler;

import com.ragingart.maatsmod.client.gui.container.GuiCharger;
import com.ragingart.maatsmod.client.gui.container.GuiDischarger;
import com.ragingart.maatsmod.client.gui.container.GuiEnergyGen;
import com.ragingart.maatsmod.container.ContainerCharger;
import com.ragingart.maatsmod.container.ContainerDischarger;
import com.ragingart.maatsmod.container.ContainerEnergyGen;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    private final Class[] container= new Class[]{
            ContainerCharger.class,
            ContainerEnergyGen.class,
            ContainerDischarger.class
    };
    private final Class[] gui= new Class[]{
            GuiCharger.class,
            GuiEnergyGen.class,
            GuiDischarger.class
    };

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        try {
            return container[ID].getDeclaredConstructors()[0].newInstance(player.inventory,world.getTileEntity(x, y, z));
        }catch(Exception e){return 0;}
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        try{
            return gui[ID].getDeclaredConstructors()[0].newInstance(player.inventory,world.getTileEntity(x, y, z));
        }catch (Exception e){return 0;}
    }
}
