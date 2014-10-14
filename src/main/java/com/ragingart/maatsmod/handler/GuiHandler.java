package com.ragingart.maatsmod.handler;

import com.ragingart.maatsmod.ref.Gui;
import com.ragingart.maatsmod.util.LogHelper;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    public Object getSidedGuiElement(Class sideType, Object... args){
        try {
            return sideType.getDeclaredConstructors()[0].newInstance(args[0],args[1]);
        }catch(Exception e){
            LogHelper.info("Missing correct implementation for "+sideType.toString());
            return 0;
        }
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return getSidedGuiElement(Gui.container[ID], player.inventory, world.getTileEntity(x, y, z));
    }
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return getSidedGuiElement(Gui.gui[ID], player.inventory, world.getTileEntity(x, y, z));
    }
}
