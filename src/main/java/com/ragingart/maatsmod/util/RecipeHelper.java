package com.ragingart.maatsmod.util;

import com.ragingart.maatsmod.init.ModItems;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by XtraX on 18.10.2014.
 */
public class RecipeHelper {

    public static class RecipeCompactor{
        public static final int[] duration = new int[]{
                0};
        public static final Item[] input = new Item[]{
                Items.iron_ingot};
        public static final Item[] output = new Item[]{
                ModItems.plate_iron};

        public static boolean checkItem(Item item){
            for(int i = 0; i < duration.length; i++){
                if(input[i] == item)
                    return true;
            }
            return false;
        }
        public static Item getOutputByInput(Item item){
            for(int i = 0; i < duration.length; i++){
                if(input[i] == item)
                    return output[i];
            }
            return null;
        }
    }

    public static class RecipeSharpeningWheel{
        public static final int[] duration = new int[]{
                0};
        public static final Item[] input = new Item[]{
                ModItems.ingot_nickel};
        public static final Item[] output = new Item[]{
                ModItems.screw};

        public static boolean checkItem(Item item){
            for(int i = 0; i < duration.length; i++){
                if(input[i] == item)
                    return true;
            }
            return false;
        }
        public static Item getOutputByInput(Item item){
            for(int i = 0; i < duration.length; i++){
                if(input[i] == item)
                    return output[i];
            }
            return null;
        }
    }

    public static void addCompactorRecipe(int duration, ItemStack input, ItemStack output){

        if (input == null || output == null) {
            return;
        }

        NBTTagCompound toSend = new NBTTagCompound();
        toSend.setInteger("MP", duration);
        toSend.setTag("input", new NBTTagCompound());
        toSend.setTag("output", new NBTTagCompound());
        input.writeToNBT(toSend.getCompoundTag("input"));
        output.writeToNBT(toSend.getCompoundTag("output"));
        FMLInterModComms.sendMessage("MaatsMod", "CompactorRecipe", toSend);
    }
}
