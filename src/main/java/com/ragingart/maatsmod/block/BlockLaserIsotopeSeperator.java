package com.ragingart.maatsmod.block;

import com.ragingart.maatsmod.generics.BlockMachineMM;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.tileentity.TileEntityLaserIsotopeSeperator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by MaaT on 18.10.2014.
 */
public class BlockLaserIsotopeSeperator extends BlockMachineMM {
    public BlockLaserIsotopeSeperator() {
        super(Names.Blocks.LIS);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityLaserIsotopeSeperator();
    }
}
