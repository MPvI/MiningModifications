package com.ragingart.maatsmod.block.machines;

import com.ragingart.maatsmod.generics.BlockMachineMM;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.ref.RenderIds;
import com.ragingart.maatsmod.tileentity.machines.TileEntityLaserSeperator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by MaaT on 18.10.2014.
 */
public class BlockLaserSeperator extends BlockMachineMM {
    public BlockLaserSeperator() {
        super(Names.Blocks.LS);
        this.setBlockBounds(0,0.9F,0,1,1,1);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
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
        return false;
    }
}
