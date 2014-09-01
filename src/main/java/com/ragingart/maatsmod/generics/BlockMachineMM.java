package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.util.IconHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * Created by MaaT on 01.09.2014.
 */
public abstract class BlockMachineMM extends BlockMM {
    @SideOnly(Side.CLIENT)
    protected IconHelper mIconHelper;


    public BlockMachineMM(String name){
        super(name);
    }

    // Textures
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side,int meta)
    {
        return mIconHelper.getIcon(side, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        mIconHelper = new IconHelper(iconRegister,null);
    }

    @SideOnly(Side.CLIENT)
    public IconHelper getIconHelper(){
        return mIconHelper;
    }
}
