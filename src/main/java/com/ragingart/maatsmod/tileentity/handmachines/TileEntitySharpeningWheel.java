package com.ragingart.maatsmod.tileentity.handmachines;


import com.ragingart.maatsmod.generics.TileEntityMachinePP;
import com.ragingart.maatsmod.util.RecipeHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by MaaT on 16.10.2014.
 */
public class TileEntitySharpeningWheel extends TileEntityMachinePP {

    public TileEntitySharpeningWheel(){
        super();
    }

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
        return from==ForgeDirection.UP;
    }

    @Override
    public int receiveMusclePower(int amount){
        if(inventory[0] != null && RecipeHelper.RecipeSharpeningWheel.checkItem(inventory[0].getItem())){
            remainingActiveTime=40;
            if(inventory[1] != null && RecipeHelper.RecipeSharpeningWheel.getOutputByInput(inventory[0].getItem()) == inventory[1].getItem())
                inventory[1] = new ItemStack(inventory[1].getItem(), inventory[1].stackSize+1);
            else
                inventory[1] = new ItemStack(RecipeHelper.RecipeSharpeningWheel.getOutputByInput(inventory[0].getItem()), 1);
            if(inventory[0].stackSize == 1)
                inventory[0] = null;
            else
                inventory[0] = new ItemStack(inventory[0].getItem(), inventory[0].stackSize-1);
        }
        return remainingActiveTime;
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
