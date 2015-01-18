package com.ragingart.miningmodifications.block.handmachines;


import com.ragingart.miningmodifications.generics.BlockMachinePP;
import com.ragingart.miningmodifications.itemblock.handmachines.ItemBlockCrank;
import com.ragingart.miningmodifications.ref.RenderIds;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntityCrank;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCrank extends BlockMachinePP{

    public BlockCrank() {
        super("crank",ItemBlockCrank.class);
        this.setHarvestLevel("wrench", 4);
        this.setHardness(7.0F);
    }


    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return RenderIds.Crank;
    }


    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos) {
        TileEntity aTile = world.getTileEntity(pos);
        if(aTile != null && aTile instanceof TileEntityCrank){
            EnumFacing dir = ((TileEntityCrank) aTile).checkLink();
            switch (dir){
                case NORTH:
                    this.setBlockBounds(0.225F,0.35F,0,0.775F,0.9F,0.4F);
                    break;
                case SOUTH:
                    this.setBlockBounds(0.225F,0.35F,0.6F,0.775F,0.9F,1);
                    break;
                case EAST:
                    this.setBlockBounds(0.6F,0.35F,0.225F,1,0.9F,0.775F);
                    break;
                case WEST:
                    this.setBlockBounds(0,0.35F,0.225F,0.4F,0.9F,0.775F);
                    break;
                case DOWN:
                    this.setBlockBounds(0.225F,0,0.225F,0.775F,0.4F,0.775F);
                    break;
                default:
                    this.setBlockBounds(0,0,0,1,1,1);
                    break;
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCrank();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        return ((TileEntityCrank)worldIn.getTileEntity(pos)).provideMusclePower();
    }
}
