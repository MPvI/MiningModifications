package com.ragingart.maatsmod.item;


import cofh.api.energy.IEnergyHandler;
import com.google.common.collect.Sets;
import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.generics.ItemToolMM;
import com.ragingart.maatsmod.init.ModBlocks;
import com.ragingart.maatsmod.ref.Names;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Set;

public class ItemMultitool extends ItemToolMM
{
    private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{ModBlocks.Ore,ModBlocks.Charger});



    public ItemMultitool()
    {
        super(3.0F, ToolMaterial.EMERALD, blocksEffectiveAgainst);
        this.setUnlocalizedName(Names.Items.MULTITOOL);
        setHarvestLevel("pickaxe",3);
    }


    /* Item */
    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
        return false;
    }


    @Override
    public boolean onItemUseFirst(ItemStack itemStack,EntityPlayer entityPlayer,World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ){

        if(!world.isRemote) {
            String message= "You just clicked on a Block at ("+x+","+y+","+z+") on Side "+meta+".";
            TileEntity tileEntity;
            tileEntity = world.getTileEntity(x, y, z);
            if (tileEntity instanceof IEnergyHandler) {
                message=message+" It contains "+((IEnergyHandler) tileEntity).getEnergyStored(ForgeDirection.UNKNOWN)+" RF";
            }
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
        }
        return false;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack,EntityPlayer entityPlayer,World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ){
        Block block = world.getBlock(x, y, z);
        return !(block instanceof BlockMM && entityPlayer.isSneaking()) || ((BlockMM) block).onBlockWrenched(world, entityPlayer, x, y, z);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if(!world.isRemote) {
            String message = "You just clicked on Air, your Multitool was charged by "+receiveEnergy(itemStack, 3, false)+" RF and does now contain "+getEnergyStored(itemStack)+"RF!";
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
        }
        return itemStack;
    }

    @Override
    public void addInformation(ItemStack itemStack,EntityPlayer entityPlayer, List list, boolean b){
        String info = "Energy: "+getEnergyStored(itemStack)+" / "+getMaxEnergyStored(itemStack);
        list.add(info);
    }


}
