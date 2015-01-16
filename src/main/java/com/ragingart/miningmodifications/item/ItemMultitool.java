package com.ragingart.miningmodifications.item;


import com.google.common.collect.Sets;
import com.ragingart.miningmodifications.generics.BlockMM;
import com.ragingart.miningmodifications.generics.ItemToolMM;
import com.ragingart.miningmodifications.handler.ConfigHandler;
import com.ragingart.miningmodifications.init.ModBlocks;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.util.LogHelper;
import com.ragingart.miningmodifications.util.NBTHelper;
import com.ragingart.miningmodifications.util.ToolHelper;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;
import java.util.Set;

public class ItemMultitool extends ItemToolMM
{
    private static final Set blocksEffectiveAgainst = Sets.newHashSet(ModBlocks.Ore);
    private static final String[] modes = new String[]{"Mining Mode","Wrench Mode","Rotation Mode"};
    private int consume = ConfigHandler.miningEnergyBase;

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

    public long getStartTime(ItemStack itemStack){return NBTHelper.getLong(itemStack,"startTime");}


    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){

        if(!worldIn.isRemote && playerIn.isSneaking()){
            LogHelper.info("mySide U");
            Block aBlock = worldIn.getBlockState(pos).getBlock();
            switch(getMode(stack)){
                case 0:
                    return false;
                case 1:
                    if(aBlock instanceof BlockMM){
                        ((BlockMM) aBlock).dismantleBlock(playerIn,worldIn,pos,worldIn.getBlockState(pos),true);
                        return true;
                    }
                    return false;
                case 2:
                    aBlock.rotateBlock(worldIn,pos,side);
                    return true;
                default:
                    return false;
            }
        }

        return false;
    }


    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer playerIn, int count) {
        if(!playerIn.worldObj.isRemote) {
            LogHelper.info("mySide UT");
            MovingObjectPosition mOP = this.getMovingObjectPositionFromPlayer(playerIn.worldObj,playerIn,true);

            if(mOP==null || mOP.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK){
                return;
            }

            int r = 2;

            float hardness = 0;

            playerIn.addPotionEffect(new PotionEffect(1, 5, 1));

            ToolHelper.HFE[] HF = ToolHelper.getHarvestField(mOP.func_178782_a(),mOP.field_178784_b,r);

            for (ToolHelper.HFE aHF : HF) {
                aHF.mBlock = playerIn.worldObj.getBlockState(aHF.mPos).getBlock();
                hardness += aHF.mBlock.getBlockHardness(playerIn.worldObj,aHF.mPos);
            }

            int maxTick = (int)hardness*ConfigHandler.miningSpeedModificator/(EnchantmentHelper.getEfficiencyModifier(playerIn)+1);
            /*
            if(maxTick>ConfigHandler.maxMiningTime){
                maxTick=ConfigHandler.maxMiningTime;
            }
            */

            int runTick = (int)((System.currentTimeMillis()-getStartTime(stack))/50);
            if(runTick >= maxTick){
                for (ToolHelper.HFE aHFE : HF) {
                    if (aHFE.mBlock != Blocks.air && aHFE.mBlock != Blocks.bedrock && aHFE.mBlock != Blocks.lava && aHFE.mBlock != Blocks.water) {
                        List<ItemStack> drops = aHFE.mBlock.getDrops(playerIn.worldObj,aHFE.mPos,playerIn.worldObj.getBlockState(aHFE.mPos),EnchantmentHelper.getFortuneModifier(playerIn));
                        for (ItemStack drop : drops) {
                            EntityItem dropEntity = new EntityItem(playerIn.worldObj,aHFE.mPos.getX(),aHFE.mPos.getY(),aHFE.mPos.getZ(),drop);
                            playerIn.worldObj.spawnEntityInWorld(dropEntity);
                        }
                        aHFE.mBlock.removedByPlayer(playerIn.worldObj,aHFE.mPos,playerIn,false);
                    }
                }
                consume += runTick*ConfigHandler.miningEnergyModificator;
                consume /= (EnchantmentHelper.getEnchantmentLevel(34,stack)+1);

                if(playerIn.capabilities.isCreativeMode || consume > extractEnergy(stack,consume,false)){
                    playerIn.clearItemInUse();
                }

                consume = ConfigHandler.miningEnergyBase;
                NBTHelper.setLong(stack, "startTime", System.currentTimeMillis());
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
        return EnumAction.BOW;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer entityPlayer, int i) {
        entityPlayer.clearItemInUse();
    }
}
