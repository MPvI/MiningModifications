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
        if(inventory[0] != null) {
            if (RecipeHelper.SharpeningWheel.checkItem(inventory[0].getItem())){
                if(RecipeHelper.SharpeningWheel.getInputAmount(inventory[0].getItem()) <= inventory[0].stackSize) {
                    if (inventory[1] == null || inventory[1].stackSize + RecipeHelper.SharpeningWheel.getOutputAmount(inventory[0].getItem()) <= 64) {
                        remainingActiveTime = RecipeHelper.SharpeningWheel.getDuration(inventory[0].getItem());
                        if (inventory[1] != null && RecipeHelper.SharpeningWheel.getOutputByInput(inventory[0].getItem()) == inventory[1].getItem())
                            inventory[1] = new ItemStack(inventory[1].getItem(), inventory[1].stackSize + RecipeHelper.SharpeningWheel.getOutputAmount(inventory[0].getItem()));
                        else
                            inventory[1] = new ItemStack(RecipeHelper.SharpeningWheel.getOutputByInput(inventory[0].getItem()), RecipeHelper.SharpeningWheel.getOutputAmount(inventory[0].getItem()));
                        if (inventory[0].stackSize == RecipeHelper.SharpeningWheel.getOutputAmount(inventory[0].getItem()))
                            inventory[0] = null;
                        else
                            inventory[0] = new ItemStack(inventory[0].getItem(), inventory[0].stackSize - RecipeHelper.SharpeningWheel.getInputAmount(inventory[0].getItem()));
                        return remainingActiveTime;
                    }

                }
            }
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
