package com.ragingart.miningmodifications.item;


import cofh.api.block.IDismantleable;
import com.google.common.collect.Sets;
import com.ragingart.miningmodifications.generics.ItemToolMM;
import com.ragingart.miningmodifications.handler.ConfigHandler;
import com.ragingart.miningmodifications.init.ModBlocks;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.util.LogHelper;
import com.ragingart.miningmodifications.util.NBTHelper;
import com.ragingart.miningmodifications.util.ToolHelper;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
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
import java.util.Set;

public class ItemMultitool extends ItemToolMM
{
    private static final Set blocksEffectiveAgainst = Sets.newHashSet(ModBlocks.Ore);
    private static final String[] modes = new String[]{"Mining Mode","Wrench Mode","Rotation Mode"};
    private int consume = ConfigHandler.miningEnergyBase;
    private int runTick=0;


    public ItemMultitool()
    {
        super(3.0F, ToolMaterial.EMERALD, blocksEffectiveAgainst);
        this.setUnlocalizedName(Names.Items.MULTITOOL);
        setHarvestLevel("pickaxe",3);
        setHarvestLevel("shovel",3);
        setHarvestLevel("axe",3);
    }

    @Override
    public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        runTick++;
    }

    public void setMode(ItemStack itemStack,int mode){
        NBTHelper.setInteger(itemStack, "Mode", mode);
    }

    public int getMode(ItemStack itemStack){
        return NBTHelper.getInt(itemStack, "Mode");
    }

    public long getStartTime(ItemStack itemStack){return NBTHelper.getLong(itemStack,"startTime");}


    @Override
    public boolean onItemUse(ItemStack itemStack,EntityPlayer entityPlayer,World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){

        if(!world.isRemote && entityPlayer.isSneaking()){
            LogHelper.info("mySide U");
            Block aBlock = world.getBlock(x, y, z);
            switch(getMode(itemStack)){
                case 0:
                    return false;
                case 1:
                    if(aBlock instanceof IDismantleable){
                        ((IDismantleable) aBlock).dismantleBlock(entityPlayer,world, x, y, z,true);
                        return true;
                    }
                    return false;
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
            LogHelper.info("mySide UT");
            MovingObjectPosition mOP = this.getMovingObjectPositionFromPlayer(entityPlayer.worldObj,entityPlayer,true);

            if(mOP==null || mOP.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK){
                return;
            }

            int r = 2;

            float hardness = 0;

            entityPlayer.addPotionEffect(new PotionEffect(1,5,1));

            ToolHelper.HFE[] HF = ToolHelper.getHarvestField(mOP.blockX,mOP.blockY,mOP.blockZ,mOP.sideHit,r);

            for (ToolHelper.HFE aHF : HF) {
                aHF.mBlock = entityPlayer.worldObj.getBlock(aHF.x,aHF.y,aHF.z);
                hardness += aHF.mBlock.getBlockHardness(entityPlayer.worldObj,aHF.x,aHF.y,aHF.z);
            }

            int maxTick = (int)hardness*ConfigHandler.miningSpeedModificator/(EnchantmentHelper.getEfficiencyModifier(entityPlayer)+1);
            /*
            if(maxTick>ConfigHandler.maxMiningTime){
                maxTick=ConfigHandler.maxMiningTime;
            }
            */

            int runTick = (int)((System.currentTimeMillis()-getStartTime(itemStack))/50);
            if(runTick >= maxTick){
                for (ToolHelper.HFE aHF : HF) {
                    if (aHF.mBlock != Blocks.air && aHF.mBlock != Blocks.bedrock && aHF.mBlock != Blocks.lava && aHF.mBlock != Blocks.water) {
                        ArrayList<ItemStack> drops = aHF.mBlock.getDrops(entityPlayer.worldObj, aHF.x, aHF.y, aHF.z, aHF.mBlock.getDamageValue(entityPlayer.worldObj, aHF.x, aHF.y, aHF.z), EnchantmentHelper.getFortuneModifier(entityPlayer));
                        for (ItemStack drop : drops) {
                            EntityItem dropEntity = new EntityItem(entityPlayer.worldObj, aHF.x, aHF.y, aHF.z, drop);
                            entityPlayer.worldObj.spawnEntityInWorld(dropEntity);
                        }
                        aHF.mBlock.removedByPlayer(entityPlayer.worldObj, entityPlayer, aHF.x, aHF.y, aHF.z, false);
                    }
                }
                consume += runTick*ConfigHandler.miningEnergyModificator;
                consume /= (EnchantmentHelper.getEnchantmentLevel(34,itemStack)+1);

                if(entityPlayer.capabilities.isCreativeMode || consume > extractEnergy(itemStack,consume,false)){
                    entityPlayer.clearItemInUse();
                }

                consume = ConfigHandler.miningEnergyBase;
                NBTHelper.setLong(itemStack, "startTime", System.currentTimeMillis());
            }

            //if(entityPlayer)
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

            }else if (getMode(itemStack) == 0 && !entityPlayer.isUsingItem() && this.getEnergyStored(itemStack) > 0) {
                NBTHelper.setLong(itemStack,"startTime",System.currentTimeMillis());
                entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
            }
        return itemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return 100000;
    }


    @Override
    public EnumAction getItemUseAction(ItemStack itemStack) {
        return EnumAction.bow;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer entityPlayer, int i) {
        entityPlayer.clearItemInUse();
    }
}
