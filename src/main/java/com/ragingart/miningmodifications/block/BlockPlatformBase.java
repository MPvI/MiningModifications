package com.ragingart.miningmodifications.block;

import com.ragingart.miningmodifications.generics.BlockMM;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.ref.RenderIds;
import com.ragingart.miningmodifications.tileentity.TileEntityPlatformBase;
import com.ragingart.miningmodifications.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPlatformBase extends BlockMM implements ITileEntityProvider {

    public BlockPlatformBase() {
        super(Material.circuits,Names.Blocks.PLATFORM_BASE);
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
        if(aTile instanceof TileEntityPlatformBase && hitY==1.0F) {
            LogHelper.info("x:" + hitX + " y:" + hitY + " z:" + hitZ);

            ((TileEntityPlatformBase) aTile).togglePlatform(hitX,hitZ,entityPlayer);
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
