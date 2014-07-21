package com.ragingart.maatsmod.item;


import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.Set;

public class ItemMultitool extends ItemToolMM
{
    private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{Blocks.stone});
    public ItemMultitool()
    {
        super(3.0F,ToolMaterial.IRON,blocksEffectiveAgainst);
        this.setUnlocalizedName("multitool");
    }


}
