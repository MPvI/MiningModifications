package com.ragingart.maatsmod.util;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.lib.util.helpers.EnergyHelper;
import com.ragingart.maatsmod.generics.TileEntityMachineMM;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

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

    public void setFacing(ForgeDirection facing) {
        this.facing = facing;
    }

    public static int transferEnergy(ItemStack inventory, EnergyStorage energy){
        IEnergyContainerItem item = (IEnergyContainerItem) inventory.getItem();

        int maxExtract = item.getEnergyStored(inventory);
        maxExtract = item.extractEnergy(inventory ,maxExtract, true);
        int maxInsert = energy.getMaxEnergyStored()-energy.getEnergyStored();
        int transferRate = Math.min(maxExtract, maxInsert);

        item.extractEnergy(inventory ,transferRate, false);
        energy.modifyEnergyStored(transferRate);
        return transferRate;
    }

    public static int transferEnergy(EnergyStorage energy, ItemStack inventory){
        IEnergyContainerItem item = (IEnergyContainerItem) inventory.getItem();

        int maxExtract = energy.getEnergyStored();
        int maxInsert = item.getMaxEnergyStored(inventory)-item.getEnergyStored(inventory);
        maxInsert = item.receiveEnergy(inventory ,maxInsert, true);
        int transferRate = Math.min(maxExtract, maxInsert);

        energy.extractEnergy(transferRate, false);
        item.receiveEnergy(inventory, transferRate, false);

        return transferRate;
    }

    public static void transferEnergyToAdjacent(TileEntityMachineMM tile){
        int num_consum = 0;
        int id_consum[] = new int[6];
        for (int i = 0; i < 6; i++) {
            if (EnergyHelper.isAdjacentEnergyHandlerFromSide(tile, i) && tile.canConnectEnergy(ForgeDirection.values()[i])) {
                id_consum[num_consum]=i;
                num_consum++;
            }
        }
        if (num_consum > 0 && tile.getEnergyStored(ForgeDirection.UNKNOWN) >= 10*num_consum){
            for (int i = 0; i < num_consum; i++) {
                int getTransferedEnergy = EnergyHelper.insertEnergyIntoAdjacentEnergyHandler(tile, id_consum[i], 10, false);
                tile.extractEnergy(ForgeDirection.UNKNOWN, getTransferedEnergy, false);
            }
        }
        else if(num_consum > 0){
            int max_output = tile.getEnergyStored(ForgeDirection.UNKNOWN)/num_consum;
            for (int i = 0; i < num_consum; i++) {
                int getTransferedEnergy = EnergyHelper.insertEnergyIntoAdjacentEnergyHandler(tile, id_consum[i], max_output, false);
                tile.extractEnergy(ForgeDirection.UNKNOWN, getTransferedEnergy, false);
            }
        }
    }
}
