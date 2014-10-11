package com.ragingart.maatsmod.block;

import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.tileentity.TileEntityCreativeEnergy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCreativeEnergy extends BlockMM implements ITileEntityProvider{


    public BlockCreativeEnergy(){
        super(Names.Blocks.CENERGY);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        blockIcon=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Items.CASING_ENERGY);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCreativeEnergy();
    }

}
