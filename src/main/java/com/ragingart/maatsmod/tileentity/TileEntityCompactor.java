package com.ragingart.maatsmod.tileentity;


import com.ragingart.maatsmod.generics.TileEntityMachinePP;
import com.ragingart.maatsmod.init.ModItems;
import net.minecraft.init.Items;
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
        if(inventory[0] != null && inventory[0].getItem() == Items.iron_ingot){
            if(inventory[1] != null)
                inventory[1] = new ItemStack(inventory[1].getItem(), inventory[1].stackSize+1);
            else
                inventory[1] = new ItemStack(ModItems.plate_iron, 1);
            if(inventory[0].stackSize == 1)
                inventory[0] = null;
            else
                inventory[0] = new ItemStack(inventory[0].getItem(), inventory[0].stackSize-1);
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
