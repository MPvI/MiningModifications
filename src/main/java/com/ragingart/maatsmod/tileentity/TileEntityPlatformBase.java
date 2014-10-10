package com.ragingart.maatsmod.tileentity;

import com.ragingart.maatsmod.generics.TileEntityMM;
import com.ragingart.maatsmod.handler.ConfigHandler;
import com.ragingart.maatsmod.init.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by MaaT on 26.09.2014.
 */
public class TileEntityPlatformBase extends TileEntityMM {
    private int animationTimer = 0;
    private boolean isExtracted = false;
    private ArrayList<Vec3> platform = new ArrayList<Vec3>();

    public int getAnimationTimer(){
        return this.animationTimer;
    }

    public boolean getExtractedState(){
        return isExtracted;
    }

    @Override
    public void updateEntity() {
        animationTimer++;
    }



    @Override
    public void writeToNBT(NBTTagCompound cmpd) {
        super.writeToNBT(cmpd);
        cmpd.setBoolean("isExtracted",getExtractedState());
        cmpd.setString("Platform",platformToString());
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd) {
        super.readFromNBT(cmpd);
        isExtracted = cmpd.getBoolean("isExtracted");
        platformFromString(cmpd.getString("Platform"));
    }


    public String platformToString(){
        String result=""+platform.size()+"_";

        for (Vec3 target : platform) {
            result += target.xCoord + ":" + target.yCoord + ":" + target.zCoord + "_";
        }

        return result;
    }

    public void platformFromString(String string){
        String parts[] = string.split("_");
        for (int i = 1; i < parts.length; i++) {
            String coords[] = parts[i].split(":");
            double x = Double.parseDouble(coords[0]);
            double y = Double.parseDouble(coords[1]);
            double z = Double.parseDouble(coords[2]);
            Vec3 target = Vec3.createVectorHelper(x,y,z);
            platform.add(target);
        }
    }

    public void togglePlatform(){
        if(!isExtracted) {
            Vec3 center = Vec3.createVectorHelper(this.xCoord, this.yCoord, this.zCoord);
            platform.add(center);
            fillAdjAirBlocks(this.worldObj, center);
            isExtracted = true;
        }else{
            destroyPlatform();
        }
        this.markDirty();
    }

    public void destroyPlatform(){
        for (int i = 1; i < platform.size(); i++) {
            Vec3 target = platform.get(i);
            this.worldObj.setBlockToAir((int)target.xCoord,(int)target.yCoord,(int)target.zCoord);
        }
        isExtracted=false;
        platform.clear();
    }

    public void fillAdjAirBlocks(World world,Vec3 center){
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int x = (int)center.xCoord+i;
                int y = (int)center.yCoord;
                int z = (int)center.zCoord+j;
                if(world.getBlock(x,y,z).isReplaceable(world,x,y,z)){
                    Vec3 target = Vec3.createVectorHelper(x,y,z);
                    if(platform.get(0).distanceTo(target)< ConfigHandler.maxPlatformRadius && platform.size()<= ConfigHandler.maxPlatformSize) {
                        world.setBlock(x, y, z, ModBlocks.PlatformExt);
                        ((TileEntityPlatformExt)world.getTileEntity(x,y,z)).origin=platform.get(0);
                        platform.add(target);
                        fillAdjAirBlocks(world,target);
                    }
                }
            }
        }
    }


}
