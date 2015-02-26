package com.ragingart.miningmodifications.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

/**
 * For TileEntities
 * Created by MaaT on 01.09.2014.
 */
public class MachineHelper {
    protected CasingHelper.Port[] mPorts = new CasingHelper.Port[6];
    protected ForgeDirection facing = ForgeDirection.EAST;
    protected boolean hasInventory=false;
    protected int state = 0;


    public MachineHelper(){
        this(new CasingHelper.Port[]{CasingHelper.Port.BLANK, CasingHelper.Port.BLANK, CasingHelper.Port.BLANK, CasingHelper.Port.BLANK, CasingHelper.Port.BLANK,null});
    }

    public MachineHelper(CasingHelper.Port[] ports){
        this(ports,false);
    }

    public MachineHelper(CasingHelper.Port[] ports,boolean hasInventory){
        this.mPorts = ports;
        this.hasInventory=hasInventory;
    }

    // Ports
    public boolean hasPort(int side,CasingHelper.Port port){
        return side != facing.ordinal() && mPorts[side] == port;
    }

    public boolean hasPort(CasingHelper.Port port){
        boolean result = false;
        for (int i = 0; i < 5; i++) {
            result = result || hasPort(i,port);
        }
        return result;
    }

    public CasingHelper.Port getPort(int side){
        return mPorts[side];
    }

    public CasingHelper.Port setPort(int side,int port){
        if(port == 6){
            mPorts[side]=null;
            facing=ForgeDirection.getOrientation(side);
            return null;
        }else{
            CasingHelper.Port oldPort = mPorts[side];
            mPorts[side] = CasingHelper.Port.values()[port];
            return oldPort;
        }

    }
    public void rotatePortsDirectlyToFace(ForgeDirection face){
        if(face==facing.getOpposite()){
            switch (face) {
                case UP:
                case DOWN:
                    rotatePortsToFacing(ForgeDirection.NORTH);
                    break;
                case NORTH:
                case SOUTH:
                    rotatePortsToFacing(ForgeDirection.EAST);
                    break;
                case WEST:
                case EAST:
                    rotatePortsToFacing(ForgeDirection.NORTH);
                    break;
            }
        }
        rotatePortsToFacing(face);
    }
    public void rotatePortsToFacing(ForgeDirection facing){
        rotatePortsAroundAxis(this.facing.getRotation(facing));
    }

    public void rotatePortsAroundAxis(ForgeDirection axis){
        CasingHelper.Port[] aHelper = new CasingHelper.Port[6];
        for (int i = 0; i < 6; i++) {
            CasingHelper.Port tmp = mPorts[ForgeDirection.getOrientation(i).getRotation(axis).ordinal()];
            if(tmp==null){
                facing = ForgeDirection.getOrientation(i);
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
    public ForgeDirection getFacing(){
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
        facing = ForgeDirection.values()[cmpd.getInteger("Facing")];
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
                list.add(EnumChatFormatting.values()[3 + i] + ForgeDirection.getOrientation(i).toString() + ": " + EnumChatFormatting.values()[9 + k] + CasingHelper.Port.values()[k].toString());
            }
        }
    }

    public void setFacing(ForgeDirection facing) {
        this.facing = facing;
    }

}
