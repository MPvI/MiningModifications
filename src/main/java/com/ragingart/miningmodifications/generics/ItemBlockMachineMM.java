package com.ragingart.miningmodifications.generics;

import com.ragingart.miningmodifications.util.MachineHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.List;

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
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player, World world,BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState metadata) {
        boolean result = super.placeBlockAt(itemStack, player, world, pos, side, hitX, hitY, hitZ, metadata);
        if(!world.isRemote && result) {
            MachineHelper aHelper = ((TileEntityMachineMM) world.getTileEntity(pos)).getMachineHelper();
            if(MachineHelper.isHelperSavedToItemStack(itemStack)){
                aHelper.getPortsFromNBT(itemStack.getTagCompound());
            }else if (side != EnumFacing.getFront(5)) {
                aHelper.rotatePortsDirectlyToFace(side);
            }
        }
        return result;
    }
}
