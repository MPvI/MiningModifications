package com.ragingart.miningmodifications.generics;

import com.ragingart.miningmodifications.creativetab.CreativeTabMM;
import com.ragingart.miningmodifications.ref.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockMM extends Block
{

    public BlockMM(Material material)
    {
        super(material);
        this.setCreativeTab(CreativeTabMM.MM_TAB);
    }

    public BlockMM(Material material,String name)
    {
        this(material);

        //this.setBlockName(name);
    }

    public BlockMM(String name)
    {
        this(Material.rock,name);
    }



    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        //blockIcon = iconRegister.registerIcon(String.format("%s", Names.getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    // Names
    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Names.MOD_PREFIX, Names.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }


    public boolean rotateBlock(World worldObj, BlockPos pos, EnumFacing face) {
            if(!worldObj.isRemote) {
                TileEntity aTile = worldObj.getTileEntity(pos);
                if(aTile instanceof TileEntityMachineMM){
                    ((TileEntityMachineMM) aTile).getMachineHelper().rotatePortsDirectlyToFace(face);
                    return true;
                }else if(aTile instanceof  TileEntityMachinePP){
                    ((TileEntityMachinePP) aTile).setFacing(face);
                }
            }
            return false;
    }


    public ArrayList<ItemStack> dismantleBlock(EntityPlayer playerIn, World worldIn, BlockPos pos,IBlockState state, boolean returnDrops) {
        if(!worldIn.isRemote) {
            dropBlockAsItem(worldIn,pos,state,0);
            removedByPlayer(worldIn,pos,playerIn,false);
        }
        return null;
    }


    public boolean canDismantle(EntityPlayer player, World world, int x, int y, int z) {
        return true;
    }
}
