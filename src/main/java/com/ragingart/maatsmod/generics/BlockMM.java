package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.creativetab.CreativeTabMM;
import com.ragingart.maatsmod.ref.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockMM extends Block
{

    public BlockMM(Material material)
    {
        super(material);
        this.setCreativeTab(CreativeTabMM.MM_TAB);
    }

    public BlockMM(String name)
    {
        this(Material.rock);
        this.setBlockName(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    // Names
    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Names.MOD_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    // Custom
    public boolean onBlockWrenched(World world,EntityPlayer entityPlayer,int x,int y,int z){
        if(!world.isRemote) {
            this.dropBlockAsItem(world, x, y, z, new ItemStack(this));
            return this.removedByPlayer(world, entityPlayer, x, y, z, false);
        }
        return false;
    }
}
