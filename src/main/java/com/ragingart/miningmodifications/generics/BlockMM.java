package com.ragingart.miningmodifications.generics;

import cofh.api.block.IDismantleable;
import com.ragingart.miningmodifications.creativetab.CreativeTabMM;
import com.ragingart.miningmodifications.ref.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

public class BlockMM extends Block implements IDismantleable
{

    public BlockMM(Material material)
    {
        super(material);
        this.setCreativeTab(CreativeTabMM.MM_TAB);
    }

    public BlockMM(Material material,String name)
    {
        this(material);
        this.setBlockName(name);
    }

    public BlockMM(String name)
    {
        this(Material.rock,name);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", Names.getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    // Names
    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Names.MOD_PREFIX, Names.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }


    @Override
    public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection face) {
            if(!worldObj.isRemote) {
                TileEntity aTile = worldObj.getTileEntity(x,y,z);
                if(aTile instanceof TileEntityMachineMM){
                    ((TileEntityMachineMM) aTile).getMachineHelper().rotatePortsDirectlyToFace(face);
                    return true;
                }else if(aTile instanceof  TileEntityMachinePP){
                    ((TileEntityMachinePP) aTile).setFacing(face);
                }
            }
            return false;
    }

    @Override
    public ArrayList<ItemStack> dismantleBlock(EntityPlayer player, World world, int x, int y, int z, boolean returnDrops) {
        if(!world.isRemote) {
            dropBlockAsItem(world, x, y, z, new ItemStack(this));
            removedByPlayer(world, player, x, y, z, false);
        }
        return null;
    }

    @Override
    public boolean canDismantle(EntityPlayer player, World world, int x, int y, int z) {
        return true;
    }
}
