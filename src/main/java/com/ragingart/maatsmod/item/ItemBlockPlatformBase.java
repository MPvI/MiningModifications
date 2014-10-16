package com.ragingart.maatsmod.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * Created by MaaT on 25.09.2014.
 */
public class ItemBlockPlatformBase extends ItemBlock{
    public ItemBlockPlatformBase(Block block) {
        super(block);
        setMaxStackSize(1);
    }
}
