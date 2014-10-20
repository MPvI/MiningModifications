package com.ragingart.maatsmod.tileentity.handmachines;


import com.ragingart.maatsmod.generics.TileEntityMachinePP;
import com.ragingart.maatsmod.util.RecipeHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by MaaT on 16.10.2014.
 */
public class TileEntityCompactor extends TileEntityMachinePP {

    private int animTimer = 0;
    private int remainingActiveTime = 0;

    @Override
    public void updateEntity() {
        super.updateEntity();

        if(remainingActiveTime > 0){
            animTimer++;
            remainingActiveTime--;
        }

        if(animTimer == 100){
            animTimer=0;
        }
    }

    public int getAnimTimer(){
        return this.animTimer;
    }

    /*IMusclePower*/

    @Override
    public boolean canAcceptMusclePower(ForgeDirection from){
        return from==getFacing();
    }

    @Override
    public int receiveMusclePower(int amount){
        if(inventory[0] != null && RecipeHelper.Compactor.checkItem(inventory[0].getItem()) && RecipeHelper.Compactor.getInputAmount(inventory[0].getItem()) <= inventory[0].stackSize){
            remainingActiveTime = RecipeHelper.Compactor.getDuration(inventory[0].getItem());
            if(inventory[1] != null && RecipeHelper.Compactor.getOutputByInput(inventory[0].getItem()) == inventory[1].getItem() && inventory[1].stackSize+RecipeHelper.Compactor.getOutputAmount(inventory[0].getItem()) <= 64)
                inventory[1] = new ItemStack(inventory[1].getItem(), inventory[1].stackSize+RecipeHelper.Compactor.getOutputAmount(inventory[0].getItem()));
            else
                inventory[1] = new ItemStack(RecipeHelper.Compactor.getOutputByInput(inventory[0].getItem()), RecipeHelper.Compactor.getOutputAmount(inventory[0].getItem()));
            if(inventory[0].stackSize == RecipeHelper.Compactor.getOutputAmount(inventory[0].getItem()))
                inventory[0] = null;
            else
                inventory[0] = new ItemStack(inventory[0].getItem(), inventory[0].stackSize-RecipeHelper.Compactor.getInputAmount(inventory[0].getItem()));
            return remainingActiveTime;
        }
        return 0;
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
