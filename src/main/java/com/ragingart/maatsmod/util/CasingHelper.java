package com.ragingart.maatsmod.util;

import com.ragingart.maatsmod.ref.Component;
import com.ragingart.maatsmod.ref.Names;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * For Blocks
 * Created by MaaT on 01.09.2014.
 */
public class CasingHelper {

    protected IIcon mFrontIcons[];
    protected IIcon mPortIcons[] = new IIcon[6];


    public CasingHelper(IIconRegister iconRegister, String[] front){
        initPorts(iconRegister);
        initFront(iconRegister, front);
    }

    public IIcon getIcon(Port p, int state){
        if(p!=null) {
            return mPortIcons[p.ordinal()];
        }else{
            return mFrontIcons[state];
        }
    }

    public IIcon defaultIcon(){
        return mPortIcons[0];
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
        mPortIcons[0]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Items.CASING);
        mPortIcons[1]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Items.CASING_ENERGY);
        mPortIcons[2]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Items.CASING_INPUT);
        mPortIcons[3]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Items.CASING_OUTPUT);
        mPortIcons[4]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Items.CASING_FINPUT);
        mPortIcons[5]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Items.CASING_FOUTPUT);
    }

    public static enum Port {
        BLANK,
        ENERGY,
        INPUT,
        OUTPUT,
        FINPUT,
        FOUTPUT;

        public static ItemStack getItemFromPort(Port port){
            return getItemFromPort(port.ordinal());
        }

        public static ItemStack getItemFromPort(int port){
            switch (port){
                case 1:
                    return Component.CASING_ENERGY(1);
                case 2:
                    return Component.CASING_INPUT(1);
                case 3:
                    return Component.CASING_OUTPUT(1);
                case 4:
                    return Component.CASING_FINPUT(1);
                case 5:
                    return Component.CASING_FOUTPUT(1);
                default:
                    return Component.CASING(1);
            }
        }
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