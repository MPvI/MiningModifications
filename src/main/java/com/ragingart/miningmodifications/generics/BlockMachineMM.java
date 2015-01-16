package com.ragingart.miningmodifications.generics;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public abstract class BlockMachineMM extends BlockMM implements ITileEntityProvider{

    public BlockMachineMM(String name){
        super(name);
    }

    @Override
    public ArrayList<ItemStack> dismantleBlock(EntityPlayer player, World world, BlockPos pos, IBlockState state, boolean returnDrops) {

        if(!world.isRemote) {
            try {
                ItemStack block = new ItemStack(this);
                block.setTagCompound(new NBTTagCompound());
                ((TileEntityMachineMM) world.getTileEntity(pos)).getMachineHelper().writePortsToNBT(block.getTagCompound());
                dropBlockAsItem(world, pos, state,0);
            }catch (Throwable e){
                //noop
            }
            removedByPlayer(world,pos,player,false);
        }
        return null;
    }

}
