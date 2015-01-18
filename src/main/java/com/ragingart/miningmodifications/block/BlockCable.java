package com.ragingart.miningmodifications.block;

import com.ragingart.miningmodifications.api.IMultiBlockPart;
import com.ragingart.miningmodifications.generics.BlockMM;
import com.ragingart.miningmodifications.itemblock.ItemBlockCable;
import com.ragingart.miningmodifications.ref.RenderIds;
import com.ragingart.miningmodifications.tileentity.TileEntityCable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCable extends BlockMM implements ITileEntityProvider,IMultiBlockPart{

    public BlockCable() {
        super(Material.circuits,"cable", ItemBlockCable.class);
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
    public int getID() {
        return 3;
    }
}
