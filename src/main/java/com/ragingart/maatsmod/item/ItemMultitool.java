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
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemMultitool extends ItemToolMM
{
    private static final Set blocksEffectiveAgainst = Sets.newHashSet(ModBlocks.Ore);
    private static final String[] modes = new String[]{"Mining Mode","Wrench Mode","Rotation Mode"};
    private int runningTick = 0;
    private int consume = 45;

    public ItemMultitool()
    {
        super(3.0F, ToolMaterial.EMERALD, blocksEffectiveAgainst);
        this.setUnlocalizedName(Names.Items.MULTITOOL);
        setHarvestLevel("pickaxe",3);
        setHarvestLevel("shovel",3);
        setHarvestLevel("axe",3);
    }

    public void setMode(ItemStack itemStack,int mode){
        NBTHelper.setInteger(itemStack, "Mode", mode);
    }

    public int getMode(ItemStack itemStack){
        return NBTHelper.getInt(itemStack, "Mode");
    }



    @Override
    public boolean onItemUse(ItemStack itemStack,EntityPlayer entityPlayer,World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
        Block aBlock = world.getBlock(x, y, z);
        if(entityPlayer.isSneaking() && aBlock instanceof BlockMM){
            switch(getMode(itemStack)){
                case 0:
                    return false;
                case 1:
                    ((BlockMM) aBlock).dismantleBlock(entityPlayer,world, x, y, z,true);
                    return true;
                case 2:
                    aBlock.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side));
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }


    @Override
    public void onUsingTick(ItemStack itemStack, EntityPlayer entityPlayer, int count) {

        if(!entityPlayer.worldObj.isRemote) {
            int[] x = new int[9];
            int[] y = new int[9];
            int[] z = new int[9];
            float hardness = 0;
            Block[] blocksToHarvest = new Block[9];

            ++runningTick;
            consume += runningTick;


            entityPlayer.addPotionEffect(new PotionEffect(1,5,3));

            MovingObjectPosition mOP = this.getMovingObjectPositionFromPlayer(entityPlayer.worldObj,entityPlayer,true);

            if(mOP==null || mOP.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK){
                return;
            }

            int[][] harvestField = ToolHelper.getHarvestField(mOP.blockX,mOP.blockY,mOP.blockZ,mOP.sideHit);

            for (int i = 0; i < 9; i++) {
                x[i] = harvestField[i][0];
                y[i] = harvestField[i][1];
                z[i] = harvestField[i][2];
                blocksToHarvest[i]=entityPlayer.worldObj.getBlock(x[i],y[i],z[i]);
                hardness += blocksToHarvest[i].getBlockHardness(entityPlayer.worldObj,0,0,0);
            }

            int maxTick = (int)hardness*ConfigHandler.miningSpeedModificator/(EnchantmentHelper.getEfficiencyModifier(entityPlayer)+1);
            if(maxTick>ConfigHandler.maxMiningTime){
                maxTick=ConfigHandler.maxMiningTime;
            }

            if(runningTick >= maxTick){
                for (int i = 0; i < 9; i++) {

                    if(blocksToHarvest[i]!= Blocks.air && blocksToHarvest[i]!=Blocks.bedrock && blocksToHarvest[i]!=Blocks.lava && blocksToHarvest[i]!=Blocks.water) {
                        ArrayList<ItemStack> drops = blocksToHarvest[i].getDrops(entityPlayer.worldObj,x[i],y[i],z[i],blocksToHarvest[i].getDamageValue(entityPlayer.worldObj,x[i],y[i],z[i]),EnchantmentHelper.getFortuneModifier(entityPlayer));
                        for(ItemStack drop :drops) {
                            EntityItem dropEntity = new EntityItem(entityPlayer.worldObj,x[i],y[i],z[i],drop);
                            entityPlayer.worldObj.spawnEntityInWorld(dropEntity);
                        }
                        blocksToHarvest[i].removedByPlayer(entityPlayer.worldObj,entityPlayer,x[i],y[i],z[i],false);
                    }
                }

                consume *= ConfigHandler.miningEnergyModificator;
                consume /= (EnchantmentHelper.getEnchantmentLevel(34,itemStack)+1);

                if(consume > extractEnergy(itemStack,consume,false)){
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
                setMode(itemStack, getMode(itemStack) + 1);

                if (getMode(itemStack) > modes.length-1) {
                    setMode(itemStack, 0);
                }
                if(!world.isRemote) {
                    entityPlayer.addChatMessage(new ChatComponentText(modes[getMode(itemStack)]));
                }

            } else if (getMode(itemStack) == 0 && !entityPlayer.isUsingItem() && this.getEnergyStored(itemStack) > 0) {
                entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
            }

        return itemStack;
    }

    @Override
    public void addSpecialInfo(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {
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
        entityPlayer.clearItemInUse();
    }

}
