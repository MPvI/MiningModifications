package com.ragingart.miningmodifications.client.gui.slot;

import cofh.lib.gui.slot.SlotFalseCopy;
import com.ragingart.miningmodifications.item.ItemVoidpack;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by MaaT on 13.11.2014.
 */
public class SlotVoidpack extends SlotFalseCopy
{
    public SlotVoidpack(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return !(stack != null && stack.getItem() instanceof ItemVoidpack);
    }
}
