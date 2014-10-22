package com.ragingart.maatsmod.block;

import com.ragingart.maatsmod.api.IMultiBlockPart;
import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.ref.RenderIds;
import com.ragingart.maatsmod.tileentity.TileEntityCable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by MaaT on 16.10.2014.
 */
public class BlockCable extends BlockMM implements ITileEntityProvider,IMultiBlockPart{
    public BlockCable() {
        super(Material.circuits, Names.Blocks.CABLE);
        this.setBlockBounds(6.0F/16.0F,6.0F/16.0F,6.0F/16.0F,10.0F/16.0F,10.0F/16.0F,10.0F/16.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityCable();
    }


    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return RenderIds.Cable;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getID() {
        return 3;
    }
}
