package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.ref.Names;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

import java.util.Collections;
import java.util.List;

/**
 * Created by MaaT on 18.10.2014.
 */
public abstract class ItemBlockMM extends ItemBlock {
    public ItemBlockMM(Block block) {
        super(block);
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
