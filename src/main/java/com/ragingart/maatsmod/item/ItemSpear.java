package com.ragingart.maatsmod.item;

import com.ragingart.maatsmod.generics.ItemToolMM;
import com.ragingart.maatsmod.ref.Names;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by MaaT on 04.09.2014.
 */
public class ItemSpear extends ItemToolMM {
    public ItemSpear(){
        super(Names.Items.WEAPON_SPEAR,10.0F);
        this.setFull3D();
    }


    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        return 0.0F;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 1000;
    }


    @Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.bow;
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
    public boolean onItemUse(ItemStack itemStack,EntityPlayer entityPlayer,World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ){



        return false;
    }


    @Override
    public void addSpecialInfo(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {

    }
}
