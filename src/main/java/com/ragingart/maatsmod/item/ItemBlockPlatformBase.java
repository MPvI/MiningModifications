package com.ragingart.maatsmod.item;

import com.ragingart.maatsmod.generics.ItemBlockMM;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by MaaT on 25.09.2014.
 */
public class ItemBlockPlatformBase extends ItemBlockMM {
    public ItemBlockPlatformBase(Block block) {
        super(block);
        setMaxStackSize(1);
    }

    @Override
    public void addSpecialInfo(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {

    }
}
