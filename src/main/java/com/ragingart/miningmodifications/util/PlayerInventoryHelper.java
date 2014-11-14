package com.ragingart.miningmodifications.util;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by MaaT on 11.10.2014.
 */
public class PlayerInventoryHelper {
    public static int hasItem(InventoryPlayer inv,ItemStack itemStack){
        for (int i = 0; i < inv.mainInventory.length; i++) {
            if(inv.mainInventory[i]!=null && inv.mainInventory[i].getItem()==itemStack.getItem() && inv.mainInventory[i].getItemDamage()==itemStack.getItemDamage()){
                return i;
            }
        }
        return -1;
    }

    public static boolean decrItem(InventoryPlayer inv, ItemStack itemStack){
        int slot = hasItem(inv,itemStack);
        if(slot != -1){
            if (--inv.mainInventory[slot].stackSize <= 0)
            {
                inv.mainInventory[slot] = null;
            }
            return true;
        }
        return false;
    }
}
