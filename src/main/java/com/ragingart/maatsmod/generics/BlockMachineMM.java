package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.util.CasingHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * Created by MaaT on 01.09.2014.
 */
public abstract class BlockMachineMM extends BlockMM {
    @SideOnly(Side.CLIENT)
    protected CasingHelper mCasingHelper;


    public BlockMachineMM(String name){
        super(name);
    }

    // Textures
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side,int meta)
    {
        return mCasingHelper.getIcon(side, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        mCasingHelper = new CasingHelper(iconRegister,null);
    }

    @SideOnly(Side.CLIENT)
    public CasingHelper getIconHelper(){
        return mCasingHelper;
    }
}
