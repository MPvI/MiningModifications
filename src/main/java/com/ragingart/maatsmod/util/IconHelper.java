package com.ragingart.maatsmod.util;

import com.ragingart.maatsmod.ref.Names;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * Created by MaaT on 01.09.2014.
 */
public class IconHelper {

    protected IIcon mFrontIcons[];
    protected IIcon mSideIcons[] = new IIcon[5];
    protected IIcon mPortIcons[] = new IIcon[4];

    public IconHelper(IIconRegister iconRegister,String[] front){
        initSides(iconRegister);
        initFront(iconRegister, front);
    }

    public IIcon getIcon(int side,int meta){
        if(side==5 && meta <= mFrontIcons.length){
            return mFrontIcons[meta];
        }else{
            return mSideIcons[side];
        }
    }

    public void initFront(IIconRegister iconRegister, String[] front){
        if(front==null){
            mFrontIcons = new IIcon[1];
            mFrontIcons[0] = mPortIcons[0];
        }else{
            mFrontIcons = new IIcon[front.length];
            for (int i = 0; i < front.length; i++) {
                mFrontIcons[i] = iconRegister.registerIcon(Names.MOD_PREFIX + front[i]);
            }
        }
    }

    public void initSides(IIconRegister iconRegister){
        mPortIcons[0]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.CASING);
        mPortIcons[1]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.ENERGY);
        mPortIcons[2]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.INPUT);
        mPortIcons[3]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.OUTPUT);

        for(int i=0;i<5;i++){
            mSideIcons[i]= mPortIcons[0];
        }
    }


    public void setSideIcon(int side,Port port){
        if(side!=5)mSideIcons[side]=mPortIcons[port.ordinal()];
    }


    public static IIcon iconFromName(IIconRegister iconRegister,String name){
        return iconRegister.registerIcon(Names.MOD_PREFIX+name);
    }

    public static enum Port {
        BLANK,
        ENERGY,
        INPUT,
        OUTPUT
    }
}

   /*
    sides
    0 bot
    1 top
    2 right
    3 left
    4 back
    5 front
     */