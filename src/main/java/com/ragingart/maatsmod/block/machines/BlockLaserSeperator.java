package com.ragingart.maatsmod.block.machines;

import com.ragingart.maatsmod.generics.BlockMachineMM;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.tileentity.machines.TileEntityLaserSeperator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by MaaT on 18.10.2014.
 */
public class BlockLaserSeperator extends BlockMachineMM {
    public BlockLaserSeperator() {
        super(Names.Blocks.LIS);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return super.getIcon(side, meta);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityLaserSeperator();
    }
}
