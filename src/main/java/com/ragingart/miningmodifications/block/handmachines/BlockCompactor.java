package com.ragingart.miningmodifications.block.handmachines;


import com.ragingart.miningmodifications.MiningModifications;
import com.ragingart.miningmodifications.generics.BlockMachinePP;
import com.ragingart.miningmodifications.itemblock.handmachines.ItemBlockCompactor;
import com.ragingart.miningmodifications.ref.Gui;
import com.ragingart.miningmodifications.ref.RenderIds;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntityCompactor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockCompactor extends BlockMachinePP{

    public BlockCompactor() {
        super("compactor",ItemBlockCompactor.class);
        this.setHarvestLevel("wrench", 4);
        this.setHardness(7.0F);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return RenderIds.Compactor;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCompactor();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {

        if (!worldIn.isRemote && worldIn.getTileEntity(pos) instanceof TileEntityCompactor) {
            playerIn.openGui(MiningModifications.instance, Gui.ID.COMPACTOR.ordinal(), worldIn, pos.getX(),pos.getY(),pos.getZ());
        }
        return true;
    }
}
