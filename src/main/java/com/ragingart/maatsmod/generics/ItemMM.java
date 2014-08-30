package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.creativetab.CreativeTabMM;
import com.ragingart.maatsmod.ref.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMM extends Item
{
    public ItemMM()
    {
        super();
        this.setCreativeTab(CreativeTabMM.MM_TAB);
    }

    public ItemMM(String name){
        this();
        this.setUnlocalizedName(name);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Names.MOD_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Names.MOD_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

}
