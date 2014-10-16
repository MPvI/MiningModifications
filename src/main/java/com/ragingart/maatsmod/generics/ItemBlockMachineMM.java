package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.util.MachineHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by MaaT on 16.10.2014.
 */
public class ItemBlockMachineMM extends ItemBlock {
    public ItemBlockMachineMM(Block block) {
        super(block);
    }


    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {
        if(itemStack.hasTagCompound()) {
            MachineHelper.addInformationString(itemStack.getTagCompound(), list);
        }
    }
}
