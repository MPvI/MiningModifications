package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.creativetab.CreativeTabMM;
import com.ragingart.maatsmod.ref.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
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
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    public void onBlockWrenched(World world,int x,int y,int z){
        this.dropBlockAsItem(world,x,y,z,new ItemStack(this));
    }
}
