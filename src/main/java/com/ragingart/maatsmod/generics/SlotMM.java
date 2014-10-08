package com.ragingart.maatsmod.generics;

import com.ragingart.maatsmod.tileentity.TileEntityEnergyGen;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * Created by XtraX on 07.10.2014.
 */
public class SlotMM extends Slot {
    public SlotMM(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return TileEntityFurnace.getItemBurnTime(itemStack) > 0;
    }
}
