package com.ragingart.maatsmod.creativetab;

import com.ragingart.maatsmod.init.ModItems;
import com.ragingart.maatsmod.ref.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMM {
    public static final CreativeTabs MM_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.Multitool;
        }
    };
}
