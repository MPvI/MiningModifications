package com.ragingart.miningmodifications.item;

import com.ragingart.miningmodifications.generics.ItemBlockMM;
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
