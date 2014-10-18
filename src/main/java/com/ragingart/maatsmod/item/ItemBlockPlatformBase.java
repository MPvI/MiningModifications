package com.ragingart.maatsmod.item;

import com.ragingart.maatsmod.generics.ItemBlockMM;
import net.minecraft.block.Block;

/**
 * Created by MaaT on 25.09.2014.
 */
public class ItemBlockPlatformBase extends ItemBlockMM {
    public ItemBlockPlatformBase(Block block) {
        super(block);
        setMaxStackSize(1);
    }

}
