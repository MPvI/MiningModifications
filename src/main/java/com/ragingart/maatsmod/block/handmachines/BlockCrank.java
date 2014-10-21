package com.ragingart.maatsmod.block.handmachines;


import com.ragingart.maatsmod.generics.BlockMachinePP;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.ref.RenderIds;
import com.ragingart.maatsmod.tileentity.handmachines.TileEntityCrank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


/**
 * Created by MaaT on 25.09.2014.
 */
public class BlockCrank extends BlockMachinePP{
    public BlockCrank() {
        super(Names.Blocks.CRANK);
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
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        TileEntity aTile = world.getTileEntity(x,y,z);
        if(aTile != null && aTile instanceof TileEntityCrank){
            ForgeDirection dir = ((TileEntityCrank) aTile).checkLink();
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        return ((TileEntityCrank)world.getTileEntity(x,y,z)).provideMusclePower();
    }
}
