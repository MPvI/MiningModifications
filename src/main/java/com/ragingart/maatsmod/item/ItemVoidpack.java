package com.ragingart.maatsmod.item;

import com.ragingart.maatsmod.generics.ItemMM;
import com.ragingart.maatsmod.ref.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by MaaT on 12.10.2014.
 */
public class ItemVoidpack extends ItemMM {
    public ItemVoidpack() {
        super(Names.Items.VOIDPACK);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        //entityPlayer.openGui(MaatsMod.instance, Gui.ID.VOIDPACK,entityPlayer.worldObj,entityPlayer.serverPosX,entityPlayer.serverPosY,entityPlayer.serverPosY);
        return itemStack;
    }


}
