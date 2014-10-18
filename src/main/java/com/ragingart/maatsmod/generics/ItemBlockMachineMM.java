package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.util.MachineHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

/**
 * Created by MaaT on 16.10.2014.
 */
public class ItemBlockMachineMM extends ItemBlockMM {


    public ItemBlockMachineMM(Block block) {
        super(block);
    }

    @Override
    public void addSpecialInfo(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {
        super.addSpecialInfo(itemStack,entityPlayer,list,b);
        list.add(EnumChatFormatting.GREEN+""+EnumChatFormatting.ITALIC+"Ports:");
        if(MachineHelper.isHelperSavedToItemStack(itemStack)) {
            MachineHelper.addInformationString(itemStack, list);
        }else{
            list.add(EnumChatFormatting.GRAY+"NONE");
        }
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean result = super.placeBlockAt(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        if(!world.isRemote && result) {
            MachineHelper aHelper = ((TileEntityMachineMM) world.getTileEntity(x, y, z)).getMachineHelper();
            if(MachineHelper.isHelperSavedToItemStack(itemStack)){
                aHelper.getPortsFromNBT(itemStack.getTagCompound());
            }else if (side != 5) {
                aHelper.rotatePortsDirectlyToFace(ForgeDirection.getOrientation(side));
            }
        }
        return result;
    }
}
