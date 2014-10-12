package com.ragingart.maatsmod.block;

import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.ref.RenderIds;
import com.ragingart.maatsmod.tileentity.TileEntityPlatformBase;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by MaaT on 25.09.2014.
 */
public class BlockPlatformBase extends BlockMM implements ITileEntityProvider {

    public BlockPlatformBase() {
        super(Names.Blocks.PLATFORM_BASE);
        setHarvestLevel("shovel",0);
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

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        TileEntity aTile = world.getTileEntity(x,y,z);
        if(aTile instanceof TileEntityPlatformBase) {
            ((TileEntityPlatformBase) aTile).togglePlatform();
            return true;
        }
        return false;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        if(hasTileEntity(meta) && world.getTileEntity(x,y,z) instanceof TileEntityPlatformBase){
            ((TileEntityPlatformBase) world.getTileEntity(x,y,z)).destroyPlatform();
            world.removeTileEntity(x,y,z);
        }
    }

}
