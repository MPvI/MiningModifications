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
        if(inventory[0] != null && RecipeHelper.RecipeCompactor.checkItem(inventory[0].getItem())){
            remainingActiveTime=85;
            if(inventory[1] != null && RecipeHelper.RecipeCompactor.getOutputByInput(inventory[0].getItem()) == inventory[1].getItem())
                inventory[1] = new ItemStack(inventory[1].getItem(), inventory[1].stackSize+1);
            else
                inventory[1] = new ItemStack(RecipeHelper.RecipeCompactor.getOutputByInput(inventory[0].getItem()), 1);
            if(inventory[0].stackSize == 1)
                inventory[0] = null;
            else
                inventory[0] = new ItemStack(inventory[0].getItem(), inventory[0].stackSize-1);
        }
        return  remainingActiveTime;
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
