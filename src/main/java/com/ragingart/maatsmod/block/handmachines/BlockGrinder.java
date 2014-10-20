package com.ragingart.maatsmod.block.handmachines;


import com.ragingart.maatsmod.MaatsMod;
import com.ragingart.maatsmod.generics.BlockMachinePP;
import com.ragingart.maatsmod.ref.Gui;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.tileentity.handmachines.TileEntityGrinder;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


/**
 * Created by MaaT on 25.09.2014.
 */
public class BlockGrinder extends BlockMachinePP implements ITileEntityProvider {

    public BlockGrinder() {
        super(Names.Blocks.GRINDER);
        this.setHarvestLevel("wrench", 4);
        this.setHardness(7.0F);
    }


    @Override
    public boolean isOpaqueCube() {
        return false;
    }


    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityGrinder();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {

        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityGrinder) {
            player.openGui(MaatsMod.instance, Gui.ID.GRINDER.ordinal(), world, x, y, z);
        }
        return true;
    }
}
