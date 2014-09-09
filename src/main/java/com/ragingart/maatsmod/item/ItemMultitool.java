package com.ragingart.maatsmod.item;


import com.google.common.collect.Sets;
import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.generics.ItemToolMM;
import com.ragingart.maatsmod.init.ModBlocks;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.util.NBTHelper;
import com.ragingart.maatsmod.util.ToolHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemMultitool extends ItemToolMM
{
    private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{ModBlocks.Ore,ModBlocks.Charger});
    private int fortuneLevel = 0;

    public ItemMultitool()
    {
        super(3.0F, ToolMaterial.EMERALD, blocksEffectiveAgainst);
        this.setUnlocalizedName(Names.Items.MULTITOOL);
        setHarvestLevel("pickaxe",3);
    }


    public void setMode(ItemStack itemStack,boolean mode){
        NBTHelper.setBoolean(itemStack,"Mode",mode);
    }

    public boolean getMode(ItemStack itemStack){
        return NBTHelper.getBoolean(itemStack,"Mode");
    }

    public void setBlockHarvestFieldRoot(ItemStack itemStack, int x, int y, int z, int side){
        NBTHelper.setInteger(itemStack,"Side",side);
        NBTHelper.setInteger(itemStack,"HarvestRootX",x);
        NBTHelper.setInteger(itemStack,"HarvestRootY",y);
        NBTHelper.setInteger(itemStack,"HarvestRootZ",z);
    }

    public int[][] getBlockHarvestField(ItemStack itemStack){
        int side = NBTHelper.getInt(itemStack,"Side");
        int rootX = NBTHelper.getInt(itemStack,"HarvestRootX");
        int rootY = NBTHelper.getInt(itemStack,"HarvestRootY");
        int rootZ = NBTHelper.getInt(itemStack,"HarvestRootZ");

        return ToolHelper.getHarvestField(rootX,rootY,rootZ,side);
    }

    /* Item */
    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
        return false;
    }

    /*
        @Override
        public boolean onItemUseFirst(ItemStack itemStack,EntityPlayer entityPlayer,World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){




            if(!world.isRemote && !mode) {
                String message= "You just clicked on a Block at ("+x+","+y+","+z+") on Side "+side+".";
                TileEntity tileEntity;
                tileEntity = world.getTileEntity(x, y, z);
                if (tileEntity instanceof IEnergyHandler) {
                    message=message+" It contains "+((IEnergyHandler) tileEntity).getEnergyStored(ForgeDirection.UNKNOWN)+" RF";
                }
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
            }
            return false;
        }


    */
    @Override
    public boolean onItemUse(ItemStack itemStack,EntityPlayer entityPlayer,World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
        Block block = world.getBlock(x, y, z);
        setBlockHarvestFieldRoot(itemStack, x, y, z, side);
        if(block instanceof BlockMM && entityPlayer.isSneaking())
            ((BlockMM) block).onBlockWrenched(world,entityPlayer,x,y,z);

        return false;
    }

    @Override
    public void onUsingTick(ItemStack itemStack, EntityPlayer entityPlayer, int count) {

        if(!entityPlayer.worldObj.isRemote) {
            int[][] harvestField = getBlockHarvestField(itemStack);
            Block[] blocksToHarvest = new Block[9];
            float hardness = 0;
            for (int i = 0; i < 9; i++) {
                blocksToHarvest[i]=entityPlayer.worldObj.getBlock(harvestField[i][0],harvestField[i][1],harvestField[i][2]);
                hardness += blocksToHarvest[i].getBlockHardness(entityPlayer.worldObj,0,0,0);
            }
            if(entityPlayer.getItemInUseDuration() >= hardness){
                for (int i = 0; i < 9; i++) {
                    if(blocksToHarvest[i]!= Blocks.air && blocksToHarvest[i]!=Blocks.bedrock && blocksToHarvest[i]!=Blocks.lava && blocksToHarvest[i]!=Blocks.water) {
                        ArrayList<ItemStack> drops = blocksToHarvest[i].getDrops(entityPlayer.worldObj,harvestField[i][0], harvestField[i][1], harvestField[i][2],blocksToHarvest[i].getDamageValue(entityPlayer.worldObj,harvestField[i][0], harvestField[i][1], harvestField[i][2]),fortuneLevel);
                        for(ItemStack drop :drops) {
                            EntityItem dropEntity = new EntityItem(entityPlayer.worldObj, harvestField[i][0], harvestField[i][1], harvestField[i][2],drop);
                            entityPlayer.worldObj.spawnEntityInWorld(dropEntity);
                        }
                        blocksToHarvest[i].removedByPlayer(entityPlayer.worldObj,entityPlayer,harvestField[i][0], harvestField[i][1], harvestField[i][2],false);
                    }
                }

            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {

            if (entityPlayer.isSneaking()) {
                setMode(itemStack, !getMode(itemStack));
                if(!world.isRemote) {
                    String message;
                    if (getMode(itemStack)) {
                        message = "Mining Mode";
                    } else {
                        message = "Normal Mode";
                    }
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
                }
            } else if (getMode(itemStack)) {
                entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
            }

       /*
        if(!world.isRemote && !mode) {
            String message = "You just clicked on Air, your Multitool was charged by "+receiveEnergy(itemStack, 3, false)+" RF and does now contain "+getEnergyStored(itemStack)+"RF!";
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
        }*/
        return itemStack;
    }

    @Override
    public void addInformation(ItemStack itemStack,EntityPlayer entityPlayer, List list, boolean b){
        String info = "Energy: "+getEnergyStored(itemStack)+" / "+getMaxEnergyStored(itemStack);
        list.add(info);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 10000;
    }


    @Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.bow;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
        //-
    }
}
