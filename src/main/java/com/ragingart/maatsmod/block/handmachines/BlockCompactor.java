package com.ragingart.maatsmod.block.handmachines;


import com.ragingart.maatsmod.MaatsMod;
import com.ragingart.maatsmod.generics.BlockMachinePP;
import com.ragingart.maatsmod.ref.Gui;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.ref.RenderIds;
import com.ragingart.maatsmod.tileentity.handmachines.TileEntityCompactor;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


/**
 * Created by MaaT on 25.09.2014.
 */
public class BlockCompactor extends BlockMachinePP implements ITileEntityProvider {

    public BlockCompactor() {
        super(Names.Blocks.COMPACTOR);
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
    public boolean renderAsNormalBlock() {
        return false;
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCompactor();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {

        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityCompactor) {
            player.openGui(MaatsMod.instance, Gui.ID.COMPACTOR.ordinal(), world, x, y, z);
        }
        return true;
    }
}
