package com.ragingart.maatsmod.block;

import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.ref.RenderIds;
import com.ragingart.maatsmod.tileentity.TileEntityPlatformBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by MaaT on 25.09.2014.
 */
public class BlockPlatformBase extends BlockMM implements ITileEntityProvider {

    public BlockPlatformBase() {
        super(Names.Blocks.PLATFORM_BASE);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityPlatformBase();
    }

    @Override
    public int getRenderType() {
        return RenderIds.PlatformBase;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}
