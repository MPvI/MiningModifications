package com.ragingart.maatsmod.tileentity.handmachines;


import com.ragingart.maatsmod.generics.TileEntityMachinePP;
import com.ragingart.maatsmod.util.RecipeHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by MaaT on 16.10.2014.
 */
public class TileEntityGrinder extends TileEntityMachinePP {

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
        return true;//from==getFacing();
    }

    @Override
    public int receiveMusclePower(int amount){
        if(inventory[0] != null && RecipeHelper.Grinder.checkItem(inventory[0].getItem()) && RecipeHelper.Grinder.getInputAmount(inventory[0].getItem()) <= inventory[0].stackSize){
            remainingActiveTime = RecipeHelper.Grinder.getDuration(inventory[0].getItem());
            if(inventory[1] != null && RecipeHelper.Grinder.getOutputByInput(inventory[0].getItem()) == inventory[1].getItem() && inventory[1].stackSize+RecipeHelper.Grinder.getOutputAmount(inventory[0].getItem()) <= 64)
                inventory[1] = new ItemStack(inventory[1].getItem(), inventory[1].stackSize+RecipeHelper.Grinder.getOutputAmount(inventory[0].getItem()));
            else
                inventory[1] = new ItemStack(RecipeHelper.Grinder.getOutputByInput(inventory[0].getItem()), RecipeHelper.Grinder.getOutputAmount(inventory[0].getItem()));
            if(inventory[0].stackSize == RecipeHelper.Grinder.getOutputAmount(inventory[0].getItem()))
                inventory[0] = null;
            else
                inventory[0] = new ItemStack(inventory[0].getItem(), inventory[0].stackSize-RecipeHelper.Grinder.getInputAmount(inventory[0].getItem()));
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
