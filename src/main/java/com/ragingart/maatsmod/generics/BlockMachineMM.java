package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.util.CasingHelper;
import com.ragingart.maatsmod.util.MachineHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by MaaT on 01.09.2014.
 */
public abstract class BlockMachineMM extends BlockMM implements ITileEntityProvider{
    @SideOnly(Side.CLIENT)
    protected CasingHelper mCasingHelper;


    public BlockMachineMM(String name){
        super(name);
    }

    // Textures
    // Within Inventory
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side,int meta)
    {
        if(side==4){
            return mCasingHelper.getIcon(null, 0);
        }
        return mCasingHelper.defaultIcon();
    }

    // Within World
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileEntity te = world.getTileEntity(x,y,z);
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
    public boolean onBlockWrenched(World world,EntityPlayer entityPlayer,int x,int y,int z) {
        if(!world.isRemote) {
            try {
                ItemStack block = new ItemStack(this);
                block.setTagCompound(new NBTTagCompound());
                ((TileEntityMachineMM) world.getTileEntity(x, y, z)).getMachineHelper().writePortsToNBT(block.getTagCompound());
                this.dropBlockAsItem(world, x, y, z, block);
            }catch (Throwable e){
                //noop
            }
            return this.removedByPlayer(world, entityPlayer, x, y, z, false);
        }
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        try {
            ((TileEntityMachineMM) world.getTileEntity(x, y, z)).getMachineHelper().getPortsFromNBT(itemStack.getTagCompound());
        }catch (Throwable e){
            //noop
        }
    }

    @Override
    public boolean canPlaceBlockOnSide(World p_149707_1_, int p_149707_2_, int p_149707_3_, int p_149707_4_, int p_149707_5_) {
        return super.canPlaceBlockOnSide(p_149707_1_, p_149707_2_, p_149707_3_, p_149707_4_, p_149707_5_);
    }
}
