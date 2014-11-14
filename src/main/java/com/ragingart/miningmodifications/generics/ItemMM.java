package com.ragingart.miningmodifications.generics;

import com.ragingart.miningmodifications.creativetab.CreativeTabMM;
import com.ragingart.miningmodifications.ref.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

import java.util.Collections;
import java.util.List;

public abstract class ItemMM extends Item
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
        return String.format("item.%s%s", Names.MOD_PREFIX, Names.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Names.MOD_PREFIX, Names.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }


    public void addSpecialInfo(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b){
        String info = StatCollector.translateToLocal(Names.INFO_PREFIX + Names.getUnwrappedUnlocalizedName(getUnlocalizedName()));
        Collections.addAll(list, info.split("%"));
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            list.add(EnumChatFormatting.WHITE + "Release " + EnumChatFormatting.RED + "Shift" + EnumChatFormatting.WHITE + " for less Information");
            list.add(EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "Info:");
            addSpecialInfo(itemStack,entityPlayer,list,b);
        }else{
            list.add(EnumChatFormatting.WHITE + "Hold " + EnumChatFormatting.GREEN + "Shift" + EnumChatFormatting.WHITE + " for more Information");
        }

    }
}
