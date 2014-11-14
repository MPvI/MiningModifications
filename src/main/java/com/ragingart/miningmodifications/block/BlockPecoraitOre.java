package com.ragingart.miningmodifications.block;

import com.ragingart.miningmodifications.generics.BlockMM;
import com.ragingart.miningmodifications.ref.Names;

public class BlockPecoraitOre extends BlockMM {

    public BlockPecoraitOre()
    {
        super(Names.Blocks.ORE);
        this.setHardness(5.0F);
        this.setHarvestLevel("pickaxe",2);
    }


}
