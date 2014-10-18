package com.ragingart.maatsmod.item;

import com.ragingart.maatsmod.generics.ItemMM;
import com.ragingart.maatsmod.ref.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by MaaT on 29.08.2014.
 */
public class ItemScrew extends ItemMM {

    public ItemScrew(){
        super(Names.Items.SCREW_NICKEL);
        setMaxStackSize(64);
    }

    @Override
    public void addSpecialInfo(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {

    }

}
