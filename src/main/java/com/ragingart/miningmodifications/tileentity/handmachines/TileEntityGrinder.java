package com.ragingart.miningmodifications.tileentity.handmachines;


import com.ragingart.miningmodifications.generics.TileEntityMachinePP;
import com.ragingart.miningmodifications.ref.Recipes;
import com.ragingart.miningmodifications.tileentity.handmachines.recipe.RecipeGrinder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;

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
    public boolean canAcceptMusclePower(EnumFacing from){
        return from==EnumFacing.UP;
    }

    @Override
    public int receiveMusclePower(int amount){
        if(input != null) {
            Item inp_item = input.getItem();
            RecipeGrinder recipe = Recipes.grinder.getRecipe(inp_item);
            if (recipe != null && recipe.getInputAmount() <= input.stackSize) {
                if(output == null){
                    output = new ItemStack(recipe.getOutputByInput(), recipe.getOutputAmount());
                } else if(recipe.getOutputByInput() == output.getItem() && output.stackSize + recipe.getOutputAmount() <= 64){
                    output = new ItemStack(output.getItem(), output.stackSize + recipe.getOutputAmount());
                }else
                    return 0;

                if(input.stackSize == recipe.getInputAmount()){
                    input = null;
                }else{
                    input = new ItemStack(input.getItem(), input.stackSize - recipe.getInputAmount());
                }
                return remainingActiveTime = recipe.getDuration();
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

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }
}
