package com.ragingart.maatsmod.block;


import com.ragingart.maatsmod.generics.BlockMM;

public class BlockCharger extends BlockMM {
       public BlockCharger()
       {
           super("charger");
           this.setHardness(7.0F);
           this.setHarvestLevel("wrench",4);
       }
}
