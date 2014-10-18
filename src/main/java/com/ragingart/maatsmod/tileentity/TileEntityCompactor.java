package com.ragingart.maatsmod.tileentity;


import com.ragingart.maatsmod.generics.TileEntityMachinePP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by MaaT on 16.10.2014.
 */
public class TileEntityCompactor extends TileEntityMachinePP {

    public TileEntityCompactor(){
        super();
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
    }


    /*IMusclePower*/

    @Override
    public boolean canAcceptMusclePower(){
        return true;
    }

    @Override
    public void receiveMusclePower(int amount){
        if(inventory[0] != null && inventory[0].getItem() == Items.coal && inventory[1] == null){
            int stackSize = inventory[0].stackSize;
            inventory[1] = new ItemStack(Items.diamond, stackSize);
            inventory[0] = null;
        }
    }

    /* IInventory */

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }
}
