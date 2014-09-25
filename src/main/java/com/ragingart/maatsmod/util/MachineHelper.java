package com.ragingart.maatsmod.util;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by MaaT on 01.09.2014.
 */
public class MachineHelper {
    protected CasingHelper.Port[] mPorts = new CasingHelper.Port[5];
    protected boolean hasInventory=false;
    protected int state = 0;


    public MachineHelper(){
        this(new CasingHelper.Port[]{CasingHelper.Port.BLANK, CasingHelper.Port.BLANK, CasingHelper.Port.BLANK, CasingHelper.Port.BLANK, CasingHelper.Port.BLANK});
    }

    public MachineHelper(CasingHelper.Port[] ports){
        mPorts = ports;
    }

    public MachineHelper(CasingHelper.Port[] ports,boolean hasInventory){
        this(ports);
        this.hasInventory=hasInventory;
    }

    public CasingHelper.Port getPort(int side){
        return mPorts[side];
    }

    public int getState(){
        return state;
    }

    public void setState(int s){
        state = s;
    }

    public CasingHelper.Port setPort(int side,int port){
        if(side!=5) {
            CasingHelper.Port oldPort = mPorts[side];
            mPorts[side] = CasingHelper.Port.values()[port];
            return oldPort;
        }
        return null;
    }

    public boolean hasPort(int side,CasingHelper.Port port){
        return side != 5 && mPorts[side] == port;
    }

    public void writePortsToNBT(NBTTagCompound cmpd){
        cmpd.setInteger("State",state);
        NBTTagCompound Ports = new NBTTagCompound();
        for(int i=0;i<mPorts.length;i++) {
            String key = "Port "+i;
            Ports.setInteger(key,mPorts[i].ordinal());
        }
        cmpd.setTag("Ports",Ports);
    }

    public void getPortsFromNBT(NBTTagCompound cmpd){
        state = cmpd.getInteger("State");
        if(cmpd.hasKey("Ports")){
            NBTTagCompound Ports = cmpd.getCompoundTag("Ports");
            for(int i=0;i<mPorts.length;i++) {
                String key = "Port "+i;
                mPorts[i] = CasingHelper.Port.values()[Ports.getInteger(key)];
            }
        }
    }

}
