package com.ragingart.maatsmod.util;

import com.ragingart.maatsmod.ref.Names;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * Created by MaaT on 01.09.2014.
 */
public class CasingHelper {

    protected IIcon mFrontIcons[];
    protected IIcon mSideIcons[] = new IIcon[5];
    protected IIcon mPortIcons[] = new IIcon[4];
    protected Port[] mPorts = new Port[5];


    public CasingHelper(IIconRegister iconRegister, String[] front){
        initPorts(iconRegister);
        initFront(iconRegister, front);
    }

    public IIcon getIcon(int side,int state){
        if(side==5){
            return mFrontIcons[state];
        }else{
            updateSideIcons();
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

    public void initPorts(IIconRegister iconRegister){
        mPortIcons[0]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.CASING);
        mPortIcons[1]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.ENERGY);
        mPortIcons[2]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.INPUT);
        mPortIcons[3]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.OUTPUT);
    }

    public void setPort(int side,Port port){
        if(side!=5)mPorts[side]=port;
    }

    public void updateSideIcons(){
        for(int i=0;i<mSideIcons.length;i++){
            if (mPorts[i]!=null){
                mSideIcons[i]=mPortIcons[mPorts[i].ordinal()];
            }else{
                mSideIcons[i]=mPortIcons[0];
            }
        }
    }

    public IIcon defaultIcon(){
        return mPortIcons[0];
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