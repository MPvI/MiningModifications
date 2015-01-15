package com.ragingart.miningmodifications.generics;

import com.ragingart.miningmodifications.util.CasingHelper;
import com.ragingart.miningmodifications.util.MachineHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;

public abstract class BlockMachineMM extends BlockMM implements ITileEntityProvider{
    @SideOnly(Side.CLIENT)
    protected CasingHelper mCasingHelper;


    public BlockMachineMM(String name){
        super(name);
    }

    // Textures
    // Within Inventory
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side,int meta)
    {
        if(side==4){
            return mCasingHelper.getIcon(null, 0);
        }
        return mCasingHelper.defaultIcon();
    }

    // Within World
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, BlockPos pos, int side) {
        TileEntity te = world.getTileEntity(pos);
        if(te!=null && te instanceof TileEntityMachineMM){
            MachineHelper aHelper = ((TileEntityMachineMM) te).getMachineHelper();
            return mCasingHelper.getIcon(aHelper.getPort(side), aHelper.getState());
        }
        return mCasingHelper.defaultIcon();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        mCasingHelper = new CasingHelper(iconRegister,null);
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
