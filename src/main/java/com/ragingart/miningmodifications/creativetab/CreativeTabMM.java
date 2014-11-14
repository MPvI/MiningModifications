package com.ragingart.miningmodifications.creativetab;

import com.ragingart.miningmodifications.init.ModItems;
import com.ragingart.miningmodifications.ref.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.List;

public class CreativeTabMM {

    public static final CreativeTabs MM_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.multitool;
        }

        @Override
        public void displayAllReleventItems(List list) {
            super.displayAllReleventItems(list);


        }
    };
}
