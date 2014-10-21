package com.ragingart.maatsmod.item;

import com.ragingart.maatsmod.generics.ItemMM;
import com.ragingart.maatsmod.ref.Names;
import net.minecraft.entity.Entity;
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
    public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        super.onUpdate(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        //entityPlayer.openGui(MaatsMod.instance, Gui.ID.VOIDPACK,entityPlayer.worldObj,entityPlayer.serverPosX,entityPlayer.serverPosY,entityPlayer.serverPosY);
        return itemStack;
    }

}
