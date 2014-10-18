package com.ragingart.maatsmod.block;

import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.ref.Names;

public class BlockPecoraitOre extends BlockMM {

    public BlockPecoraitOre()
    {
        super(Names.Blocks.ORE);
        this.setHardness(5.0F);
        this.setHarvestLevel("pickaxe",2);
    }


}
