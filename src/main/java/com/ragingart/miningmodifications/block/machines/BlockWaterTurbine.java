package com.ragingart.miningmodifications.block.machines;


import com.ragingart.miningmodifications.MiningModifications;
import com.ragingart.miningmodifications.generics.BlockMachineMM;
import com.ragingart.miningmodifications.ref.Gui;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityWaterTurbine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockWaterTurbine extends BlockMachineMM{

    public BlockWaterTurbine()
    {
        super(Names.Blocks.WATERTURBINE);
        this.setHardness(7.0F);
        this.setHarvestLevel("wrench", 4);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){return new TileEntityWaterTurbine();}

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
            if (!worldIn.isRemote && worldIn.getTileEntity(pos) instanceof TileEntityWaterTurbine) {
                playerIn.openGui(MiningModifications.instance, Gui.ID.WATERTURBINE.ordinal(), worldIn, pos.getX(),pos.getY(),pos.getZ());
            }
            return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
        super.breakBlock(worldIn,pos,state);

        if(worldIn.getBlockState(pos.offsetDown()).getBlock().getMaterial()==Material.water){
            worldIn.setBlockState(pos.offsetDown(),state);
        }
        /*TODO
        if(state.getBlock(x+ ForgeDirection.DOWN.offsetX, y+ ForgeDirection.DOWN.offsetY, z+ForgeDirection.DOWN.offsetZ).getMaterial() == Material.water) {
            wobj.setBlockMetadataWithNotify(x + ForgeDirection.DOWN.offsetX, y + ForgeDirection.DOWN.offsetY, z + ForgeDirection.DOWN.offsetZ,8, 0);
            wobj.setBlock(x, y, z, Blocks.flowing_water);
            wobj.setBlockMetadataWithNotify(x, y , z ,8, 0);
        }
        */
    }
}