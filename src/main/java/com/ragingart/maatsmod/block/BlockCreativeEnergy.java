package com.ragingart.maatsmod.block;

import com.ragingart.maatsmod.generics.BlockMachineMM;
import com.ragingart.maatsmod.tileentity.TileEntityCreativeEnergy;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCreativeEnergy extends BlockMachineMM implements ITileEntityProvider{


    public BlockCreativeEnergy(){
        super("crenergy");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCreativeEnergy();
    }

}
