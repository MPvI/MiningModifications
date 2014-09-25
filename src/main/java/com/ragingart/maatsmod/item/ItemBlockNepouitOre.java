package com.ragingart.maatsmod.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockNepouitOre extends ItemBlock{
    public ItemBlockNepouitOre(Block block)
    {
        super(block);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {
        String structure = "Ni3[(OH)4|Si2O5]";
        list.add(structure);
    }
}
