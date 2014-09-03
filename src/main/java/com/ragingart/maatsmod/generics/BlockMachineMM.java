package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.util.CasingHelper;
import com.ragingart.maatsmod.util.MachineHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * Created by MaaT on 01.09.2014.
 */
public abstract class BlockMachineMM extends BlockMM implements ITileEntityProvider{
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
        if(side==4){
            return mCasingHelper.getIcon(5,meta);
        }
        return mCasingHelper.defaultIcon();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileEntity te = world.getTileEntity(x,y,z);
        if(te!=null && te instanceof TileEntityMachineMM){
            MachineHelper aHelper = ((TileEntityMachineMM) te).getMachineHelper();
            if(side!=5) mCasingHelper.setPort(side, aHelper.getPort(side));
            return mCasingHelper.getIcon(side,aHelper.getState());
        }
        return mCasingHelper.getIcon(side,0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        mCasingHelper = new CasingHelper(iconRegister,null);
    }

}
