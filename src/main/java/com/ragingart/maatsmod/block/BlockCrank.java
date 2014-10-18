package com.ragingart.maatsmod.block;


import com.ragingart.maatsmod.generics.BlockMachinePP;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.tileentity.TileEntityCrank;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


/**
 * Created by MaaT on 25.09.2014.
 */
public class BlockCrank extends BlockMachinePP implements ITileEntityProvider {
    public BlockCrank() {
        super(Names.Blocks.CRANK);
        this.setHarvestLevel("wrench", 4);
        this.setHardness(7.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCrank();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        TileEntity aTile = world.getTileEntity(x,y,z);
        if(aTile instanceof TileEntityCrank) {
            ((TileEntityCrank) aTile).provideMusclePower();
            return true;
        }
        return false;
    }
}
