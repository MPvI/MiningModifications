package com.ragingart.maatsmod.block;

import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.tileentity.TileEntityCreativeEnergy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCreativeEnergy extends BlockMM implements ITileEntityProvider{


    public BlockCreativeEnergy(){
        super("crenergy");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCreativeEnergy();
    }


    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side,int meta)
    {
        switch(side) {
            case 1: return blockIcons[1];
            default: return blockIcons[0];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iR)
    {
        blockIcons = new IIcon[6];
        blockIcons[0] = iR.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.CASING);
        blockIcons[1] = iR.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.ENERGY);
    }
}
