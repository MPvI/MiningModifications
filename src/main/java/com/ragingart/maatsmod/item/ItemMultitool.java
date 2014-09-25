package com.ragingart.maatsmod.item;


import com.google.common.collect.Sets;
import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.generics.ItemToolMM;
import com.ragingart.maatsmod.handler.ConfigHandler;
import com.ragingart.maatsmod.init.ModBlocks;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.util.NBTHelper;
import com.ragingart.maatsmod.util.ToolHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemMultitool extends ItemToolMM
{
    private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{ModBlocks.Ore,ModBlocks.Charger});
    private int runningTick = 0;
    private int consume = 45;

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


    @Override
    public boolean onItemUse(ItemStack itemStack,EntityPlayer entityPlayer,World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
        Block block = world.getBlock(x, y, z);
        if(block instanceof BlockMM && entityPlayer.isSneaking()){
            ((BlockMM) block).onBlockWrenched(world,entityPlayer,x,y,z);
            return true;
        }else {
            return false;
        }
    }


    @Override
    public void onUsingTick(ItemStack itemStack, EntityPlayer entityPlayer, int count) {

        if(!entityPlayer.worldObj.isRemote) {
            ++runningTick;
            consume += runningTick;

            //this.extractEnergy(itemStack,runningTick,false);

            entityPlayer.addPotionEffect(new PotionEffect(1,3,3));

            MovingObjectPosition mOP = Minecraft.getMinecraft().objectMouseOver;

            if(mOP.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK){
                return;
            }

            int[][] harvestField = ToolHelper.getHarvestField(mOP.blockX,mOP.blockY,mOP.blockZ,mOP.sideHit);

            Block[] blocksToHarvest = new Block[9];

            float hardness = 0;

            for (int i = 0; i < 9; i++) {
                int x = harvestField[i][0];
                int y = harvestField[i][1];
                int z = harvestField[i][2];
                blocksToHarvest[i]=entityPlayer.worldObj.getBlock(x,y,z);
                hardness += blocksToHarvest[i].getBlockHardness(entityPlayer.worldObj,0,0,0);
                entityPlayer.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(blocksToHarvest[i]) + "_" + entityPlayer.worldObj.getBlockMetadata(x,y,z),x,y,z,0.5D,0.5D,0.5D);
            }

            int maxTick = (int)hardness*ConfigHandler.miningSpeedModificator/(EnchantmentHelper.getEfficiencyModifier(entityPlayer)+1);

            if(runningTick >= maxTick){
                for (int i = 0; i < 9; i++) {

                    int x = harvestField[i][0];
                    int y = harvestField[i][1];
                    int z = harvestField[i][2];

                    if(blocksToHarvest[i]!= Blocks.air && blocksToHarvest[i]!=Blocks.bedrock && blocksToHarvest[i]!=Blocks.lava && blocksToHarvest[i]!=Blocks.water) {
                        ArrayList<ItemStack> drops = blocksToHarvest[i].getDrops(entityPlayer.worldObj,x,y,z,blocksToHarvest[i].getDamageValue(entityPlayer.worldObj,x,y,z),EnchantmentHelper.getFortuneModifier(entityPlayer));
                        for(ItemStack drop :drops) {
                            EntityItem dropEntity = new EntityItem(entityPlayer.worldObj,x,y,z,drop);
                            entityPlayer.worldObj.spawnEntityInWorld(dropEntity);
                        }
                        blocksToHarvest[i].removedByPlayer(entityPlayer.worldObj,entityPlayer,x,y,z,false);
                    }
                }

                consume *= ConfigHandler.miningEnergyModificator;

                if(extractEnergy(itemStack,consume,false)<consume){
                    entityPlayer.clearItemInUse();
                }

                runningTick = 0;
                consume = 45;
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
            } else if (getMode(itemStack) && !entityPlayer.isUsingItem() && this.getEnergyStored(itemStack)>0) {
                entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
            }
            return itemStack;
    }

    @Override
    public void addInformation(ItemStack itemStack,EntityPlayer entityPlayer, List list, boolean b){
        String info = "Energy: "+getEnergyStored(itemStack)+" / "+getMaxEnergyStored(itemStack);
        list.add(info);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return 10000;
    }


    @Override
    public EnumAction getItemUseAction(ItemStack itemStack) {
        return EnumAction.bow;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer entityPlayer, int p_77615_4_) {

    }

}
