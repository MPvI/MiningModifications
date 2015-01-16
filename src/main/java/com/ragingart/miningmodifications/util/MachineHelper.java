package com.ragingart.miningmodifications.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;

import java.util.List;

/**
 * For TileEntities
 * Created by MaaT on 01.09.2014.
 */
public class MachineHelper {
    protected Port[] mPorts = new Port[6];
    protected EnumFacing facing = EnumFacing.EAST;
    protected boolean hasInventory=false;
    protected int state = 0;


    public MachineHelper(){
        this(new Port[]{Port.BLANK, Port.BLANK, Port.BLANK, Port.BLANK, Port.BLANK,null});
    }

    public MachineHelper(Port[] ports){
        this(ports,false);
    }

    public MachineHelper(Port[] ports,boolean hasInventory){
        this.mPorts = ports;
        this.hasInventory=hasInventory;
    }

    // Ports
    public boolean hasPort(int side,Port port){
        return side != facing.ordinal() && mPorts[side] == port;
    }

    public Port getPort(int side){
        return mPorts[side];
    }

    public Port setPort(int side,int port){
        if(port == 6){
            mPorts[side]=null;
            facing=EnumFacing.getFront(side);
            return null;
        }else{
            Port oldPort = mPorts[side];
            mPorts[side] = Port.values()[port];
            return oldPort;
        }

    }
    public void rotatePortsDirectlyToFace(EnumFacing face){
        if(face==facing.getOpposite()){
            switch (face) {
                case UP:
                case DOWN:
                    rotatePortsToFacing(EnumFacing.NORTH);
                    break;
                case NORTH:
                case SOUTH:
                    rotatePortsToFacing(EnumFacing.EAST);
                    break;
                case WEST:
                case EAST:
                    rotatePortsToFacing(EnumFacing.NORTH);
                    break;
            }
        }
        rotatePortsToFacing(face);
    }
    public void rotatePortsToFacing(EnumFacing facing){
        rotatePortsAroundAxis(this.facing.rotateAround(facing.getAxis()));
    }

    public void rotatePortsAroundAxis(EnumFacing axis){
        Port[] aHelper = new Port[6];
        for (int i = 0; i < 6; i++) {
            Port tmp = mPorts[EnumFacing.getFront(i).rotateAround(axis.getAxis()).ordinal()];
            if(tmp==null){
                facing = EnumFacing.getFront(i);
            }
            aHelper[i]=tmp;
        }
        mPorts = aHelper;
    }

    // State
    public int getState(){
        return state;
    }

    public void setState(int s){
        state = s;
    }


    // Facing
    public EnumFacing getFacing(){
        return this.facing;
    }

    // Saving
    public void writePortsToNBT(NBTTagCompound cmpd){
        cmpd.setInteger("State",state);
        cmpd.setInteger("Facing",facing.ordinal());
        NBTTagCompound Ports = new NBTTagCompound();
        for(int i=0;i<mPorts.length;i++) {
            String key = "Port " + i;
            if(i==facing.ordinal()) {
                Ports.setInteger(key,6);
            }else{
                Ports.setInteger(key, mPorts[i].ordinal());
            }
        }
        cmpd.setTag("Ports",Ports);
    }

    // Reading
    public void getPortsFromNBT(NBTTagCompound cmpd){
        state = cmpd.getInteger("State");
        facing = EnumFacing.values()[cmpd.getInteger("Facing")];
        if(cmpd.hasKey("Ports")){
            NBTTagCompound Ports = cmpd.getCompoundTag("Ports");
            for(int i=0;i<mPorts.length;i++) {
                    String key = "Port " + i;
                    setPort(i,Ports.getInteger(key));
            }
        }
    }

    public static boolean isHelperSavedToItemStack(ItemStack itemStack){
        if(itemStack.hasTagCompound()){
            NBTTagCompound TagCmpd = itemStack.getTagCompound();
            return TagCmpd.hasKey("State") && TagCmpd.hasKey("Facing") && TagCmpd.hasKey("Ports");
        }
        return false;
    }

    public static void addInformationString(ItemStack itemStack, List list) {
        NBTTagCompound Ports = itemStack.getTagCompound().getCompoundTag("Ports");
        for (int i = 0; i < 6; i++) {
            String key = "Port " + i;
            int k = Ports.getInteger(key);
            if (k < 6 && k > 0) {
                list.add(EnumChatFormatting.values()[3 + i] + EnumFacing.getFront(i).toString() + ": " + EnumChatFormatting.values()[9 + k] + Port.values()[k].toString());
            }
        }
    }

    public void setFacing(EnumFacing facing) {
        this.facing = facing;
    }

}
