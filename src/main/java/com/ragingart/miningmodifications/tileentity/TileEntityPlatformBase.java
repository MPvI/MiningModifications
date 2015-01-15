package com.ragingart.miningmodifications.tileentity;

import com.ragingart.miningmodifications.block.BlockFluxField;
import com.ragingart.miningmodifications.generics.TileEntityMM;
import com.ragingart.miningmodifications.handler.ConfigHandler;
import com.ragingart.miningmodifications.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.ArrayList;

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
            platform.add(new Vec3(Double.parseDouble(coords[0]),Double.parseDouble(coords[1]),Double.parseDouble(coords[2])));
        }
    }

    public void togglePlatform(){
        if(!worldObj.isRemote) {
            if(isExtracted){
                destroyPlatform();
            } else {
                createPlatform();
            }
            this.markDirty();
        }
    }

    public void destroyPlatform(){
        for (int i = 1; i < platform.size(); i++) {
            Vec3 target = platform.get(i);
            BlockPos blockPos = new BlockPos(target.xCoord,target.yCoord,target.zCoord);
            Block aBlock = worldObj.getBlockState(blockPos).getBlock();
            if(aBlock instanceof BlockFluxField) {
                aBlock.breakBlock(worldObj,blockPos,worldObj.getBlockState(blockPos));
            }

        }
        isExtracted=false;
        platform.clear();
    }

    private void createPlatform(){
        Vec3 center = new Vec3(pos.getX(),pos.getY(),pos.getZ());
        platform.add(center);
        fillAdjAirBlocks(this.worldObj, center);
        isExtracted = true;
    }

    public void fillAdjAirBlocks(World world,Vec3 center){
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                BlockPos blockPos = new BlockPos(center.xCoord+i,center.yCoord,center.zCoord+j);
                if(world.getBlockState(blockPos).getBlock().isReplaceable(world,blockPos)){
                    Vec3 target = new Vec3(blockPos.getX(),blockPos.getY(),blockPos.getZ());
                    if(platform.get(0).distanceTo(target)< ConfigHandler.maxPlatformRadius && platform.size()<= ConfigHandler.maxPlatformSize) {
                        world.setBlockState(blockPos,ModBlocks.FluxField.getDefaultState());
                        platform.add(target);
                        fillAdjAirBlocks(world,target);
                    }
                }
            }
        }
    }


}
