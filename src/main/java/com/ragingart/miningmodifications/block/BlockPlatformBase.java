package com.ragingart.miningmodifications.block;

import com.ragingart.miningmodifications.generics.BlockMM;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.ref.RenderIds;
import com.ragingart.miningmodifications.tileentity.TileEntityPlatformBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntity aTile = worldIn.getTileEntity(pos);
        if(aTile instanceof TileEntityPlatformBase) {
            ((TileEntityPlatformBase) aTile).togglePlatform();
            return true;
        }
        return false;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if(hasTileEntity(state) && worldIn.getTileEntity(pos) instanceof TileEntityPlatformBase){
            ((TileEntityPlatformBase) worldIn.getTileEntity(pos)).destroyPlatform();
            worldIn.removeTileEntity(pos);
        }
    }

}
