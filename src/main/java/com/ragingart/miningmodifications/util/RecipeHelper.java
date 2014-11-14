package com.ragingart.miningmodifications.util;


import com.ragingart.miningmodifications.ref.Recipes;
import com.ragingart.miningmodifications.tileentity.handmachines.recipe.RecipeCompactor;
import com.ragingart.miningmodifications.tileentity.handmachines.recipe.RecipeGrinder;
import com.ragingart.miningmodifications.tileentity.handmachines.recipe.RecipeSharpeningWheel;
import net.minecraft.item.Item;

/**
 * Created by XtraX on 18.10.2014.
 */


public class RecipeHelper {
    public static void addCompactorRecipe(int s_duration, int s_inputamount, int s_outputamount, Item s_input, Item s_output){
        if(Recipes.compactor == null)
            Recipes.compactor = new RecipeCompactor(s_duration,s_inputamount, s_outputamount,s_input,s_output);
        else
            Recipes.compactor.addRecipe(s_duration,s_inputamount, s_outputamount,s_input,s_output);

    }
    public static void addScharpeningWheelRecipe(int s_duration, int s_inputamount, int s_outputamount, Item s_input, Item s_output){
        if(Recipes.sharpeningwheel == null)
            Recipes.sharpeningwheel = new RecipeSharpeningWheel(s_duration,s_inputamount, s_outputamount,s_input,s_output);
        else
            Recipes.sharpeningwheel.addRecipe(s_duration,s_inputamount, s_outputamount,s_input,s_output);

    }
    public static void addGrinderRecipe(int s_duration, int s_inputamount, int s_outputamount, Item s_input, Item s_output){
        if(Recipes.grinder == null)
            Recipes.grinder = new RecipeGrinder(s_duration,s_inputamount, s_outputamount,s_input,s_output);
        else
            Recipes.grinder.addRecipe(s_duration,s_inputamount, s_outputamount,s_input,s_output);

    }
}
