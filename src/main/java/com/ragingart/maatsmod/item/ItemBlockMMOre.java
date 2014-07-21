package com.ragingart.maatsmod.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockMMOre extends ItemBlock{
    public ItemBlockMMOre(Block block)
    {
        super(block);
        this.setHarvestLevel("pickaxe",2);
    }

}
