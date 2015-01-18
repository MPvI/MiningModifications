package com.ragingart.miningmodifications.block;

import com.ragingart.miningmodifications.generics.BlockMM;
import com.ragingart.miningmodifications.itemblock.ItemBlockPecoraitOre;
import net.minecraft.block.material.Material;

public class BlockPecoraitOre extends BlockMM {

    public BlockPecoraitOre()
    {
        super(Material.rock,"pecorait", ItemBlockPecoraitOre.class);
        this.setHardness(5.0F);
        this.setHarvestLevel("pickaxe",2);
    }


}
