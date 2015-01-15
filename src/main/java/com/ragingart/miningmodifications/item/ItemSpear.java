package com.ragingart.miningmodifications.item;

import com.ragingart.miningmodifications.generics.ItemToolMM;
import com.ragingart.miningmodifications.ref.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSpear extends ItemToolMM {
    public ItemSpear(){
        super(Names.Items.WEAPON_SPEAR,10.0F);
        this.setFull3D();
    }



    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 1000;
    }


    @Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.BOW;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
        //-
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        return p_77659_1_;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
        return false;
    }

}
