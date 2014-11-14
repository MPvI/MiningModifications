package com.ragingart.miningmodifications.block.machines;

import com.ragingart.miningmodifications.api.IMultiBlockPart;
import com.ragingart.miningmodifications.generics.BlockMachineMM;
import com.ragingart.miningmodifications.generics.TileEntityMachineMultiBlockMM;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.ref.RenderIds;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityLaserSeperator;
import com.ragingart.miningmodifications.util.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by MaaT on 18.10.2014.
 */
public class BlockLaserSeperator extends BlockMachineMM  implements IMultiBlockPart {
    public BlockLaserSeperator() {
        super(Names.Blocks.LS);
        this.setBlockBounds(0,0.9F,0,1,1,1);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        boolean result = ((TileEntityMachineMultiBlockMM)world.getTileEntity(x,y,z)).checkStructure(world,x-2,y-1,z-2);
        LogHelper.info(result);
        return result;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return super.getIcon(side, meta);
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
    public boolean renderAsNormalBlock()
    {
        return true;
    }

    @Override
    public int getID() {
        return 1;
    }
}
