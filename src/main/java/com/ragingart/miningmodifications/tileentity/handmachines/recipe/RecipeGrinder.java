package com.ragingart.miningmodifications.tileentity.handmachines.recipe;

import net.minecraft.item.Item;

/**
 * Created by XtraX on 22.10.2014.
 */
public class RecipeGrinder {

    int inputamount, outputamount, duration;
    Item input, output;
    RecipeGrinder next;


    public RecipeGrinder(int s_duration, int s_inputamount, int s_outputamount, Item s_input, Item s_output){
        inputamount = s_inputamount;
        outputamount = s_outputamount;
        duration = s_duration;
        input = s_input;
        output = s_output;
        next = null;
    }

    public void addRecipe(int s_duration, int s_inputamount, int s_outputamount, Item s_input, Item s_output){
        if(next != null) {
            next.addRecipe(s_duration, s_inputamount, s_outputamount, s_input, s_output);
        }else{
            next = new RecipeGrinder(s_duration, s_inputamount, s_outputamount, s_input, s_output);
        }
    }

    public boolean checkItem(Item item) {
        if (input == item)
            return true;
        else if(next != null)
            return next.checkItem(item);
        else
            return false;
    }

    public Item getOutputByInput() {
        return output;
    }
    public Item getOutputByInput(Item item){
        if (input == item)
            return output;
        else if(next != null)
            return next.getOutputByInput(item);
        else
            return null;
    }

    public int getInputAmount() {
        return inputamount;
    }
    public int getInputAmount(Item item){
        if (input == item)
            return inputamount;
        else if(next != null)
            return next.getInputAmount(item);
        else
            return 0;
    }

    public int getOutputAmount() {
        return outputamount;
    }
    public int getOutputAmount(Item item){
        if (input == item)
            return outputamount;
        else if(next != null)
            return next.getOutputAmount(item);
        else
            return 0;
    }

    public int getDuration() {
        return duration;
    }
    public int getDuration(Item item){
        if (input == item)
            return duration;
        else if(next != null)
            return next.getDuration(item);
        else
            return 0;
    }

    public RecipeGrinder getRecipe(Item item){
        if (input == item)
            return this;
        else if(next != null)
            return next.getRecipe(item);
        else
            return null;
    }
}
