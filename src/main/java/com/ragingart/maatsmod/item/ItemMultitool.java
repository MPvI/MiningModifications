package com.ragingart.maatsmod.item;


import com.google.common.collect.Sets;
import com.ragingart.maatsmod.generics.ItemToolMM;
import com.ragingart.maatsmod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Set;

public class ItemMultitool extends ItemToolMM
{
    private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{ModBlocks.Ore});


    public ItemMultitool()
    {
        super(3.0F,ToolMaterial.EMERALD,blocksEffectiveAgainst);
        this.setHarvestLevel("pickaxe",3);
        this.setUnlocalizedName("multitool");
    }



    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
        return false;
    }


    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        System.out.println("rightclick on block");
        System.out.println(par4 + " / " + par5 + " / " + par6 + " / " + par7);
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        System.out.println("rightclick on air");
        return par1ItemStack;
    }

}
