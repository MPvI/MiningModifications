package com.ragingart.miningmodifications.block.machines;


import com.ragingart.miningmodifications.MiningModifications;
import com.ragingart.miningmodifications.generics.BlockMachineMM;
import com.ragingart.miningmodifications.itemblock.machines.ItemBlockDischarger;
import com.ragingart.miningmodifications.ref.Gui;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityDischarger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockDischarger extends BlockMachineMM{

    public BlockDischarger()
    {
        super("discharger", ItemBlockDischarger.class);
        this.setHardness(7.0F);
        this.setHarvestLevel("wrench", 4);
    }

    @Override
    public boolean isOpaqueCube() {
        return true;
    }

    @Override
    public int getRenderType() {
        return 0;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return new TileEntityDischarger();
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {

            if (!worldIn.isRemote && worldIn.getTileEntity(pos) instanceof TileEntityDischarger) {
                playerIn.openGui(MiningModifications.instance, Gui.ID.DISCHARGER.ordinal(), worldIn, pos.getX(),pos.getY(),pos.getZ());
            }
            return true;

    }
}