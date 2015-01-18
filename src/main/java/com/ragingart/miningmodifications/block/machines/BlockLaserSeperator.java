package com.ragingart.miningmodifications.block.machines;

import com.ragingart.miningmodifications.api.IMultiBlockPart;
import com.ragingart.miningmodifications.generics.BlockMachineMM;
import com.ragingart.miningmodifications.generics.TileEntityMachineMultiBlockMM;
import com.ragingart.miningmodifications.itemblock.machines.ItemBlockLaserSeperator;
import com.ragingart.miningmodifications.ref.RenderIds;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityLaserSeperator;
import com.ragingart.miningmodifications.util.LogHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockLaserSeperator extends BlockMachineMM  implements IMultiBlockPart {

    public BlockLaserSeperator() {
        super("laserseperator", ItemBlockLaserSeperator.class);
        this.setBlockBounds(0,0.9F,0,1,1,1);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        boolean result = ((TileEntityMachineMultiBlockMM)worldIn.getTileEntity(pos)).checkStructure(worldIn,pos.add(-2,-1,-2));
        LogHelper.info(result);
        return result;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityLaserSeperator();
    }



    @Override
    public int getRenderType()
    {
        return RenderIds.LSC;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getID() {
        return 1;
    }
}
