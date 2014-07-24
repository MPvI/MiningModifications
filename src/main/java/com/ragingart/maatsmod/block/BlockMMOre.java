package com.ragingart.maatsmod.block;

import com.ragingart.maatsmod.generics.BlockMM;

public class BlockMMOre extends BlockMM {
    public BlockMMOre()
    {
        super();
        this.setBlockName("oreMM");
        this.setHardness(5.0F);
        this.setHarvestLevel("pickaxe",3);
    }


}
