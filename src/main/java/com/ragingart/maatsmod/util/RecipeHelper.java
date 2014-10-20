package com.ragingart.maatsmod.util;

import com.ragingart.maatsmod.generics.ItemBlockMM;
import com.ragingart.maatsmod.init.ModBlocks;
import com.ragingart.maatsmod.init.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by XtraX on 18.10.2014.
 */
public class RecipeHelper {

    public static class Compactor{
        public static final int[] duration = new int[]{
                85};
        public static final Item[] input = new Item[]{
                Items.iron_ingot};
        public static final Item[] output = new Item[]{
                ModItems.plate_iron};
        public static int[] inputamount =new int[]{
                1};
        public static int[] outputamount =new int[]{
                1};


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

        public static int getInputAmount(Item iteminput){
            for(int i = 0; i < duration.length; i++){
                if(input[i] == iteminput)
                    return inputamount[i];
            }
            return 100;
        }
        public static int getOutputAmount(Item iteminput){
            for(int i = 0; i < duration.length; i++){
                if(input[i] == iteminput)
                    return outputamount[i];
            }
            return 0;
        }
        public static int getDuration(Item iteminput){
            for(int i = 0; i < duration.length; i++){
                if(input[i] == iteminput)
                    return duration[i];
            }
            return 0;
        }
    }


    public static class SharpeningWheel{
        public static final int[] duration = new int[]{
                100};
        public static final Item[] input = new Item[]{
                ModItems.ingot_nickel};
        public static final Item[] output = new Item[]{
                ModItems.screw};
        public static int[] inputamount =new int[]{
                2};
        public static int[] outputamount =new int[]{
                4};


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

        public static int getInputAmount(Item iteminput){
            for(int i = 0; i < duration.length; i++){
                if(input[i] == iteminput)
                    return inputamount[i];
            }
            return 100;
        }
        public static int getOutputAmount(Item iteminput){
            for(int i = 0; i < duration.length; i++){
                if(input[i] == iteminput)
                    return outputamount[i];
            }
            return 0;
        }
        public static int getDuration(Item iteminput){
            for(int i = 0; i < duration.length; i++){
                if(input[i] == iteminput)
                    return duration[i];
            }
            return 0;
        }
    }


    public static class Grinder{
        public static final int[] duration = new int[]{
                20};
        public static final Item[] input = new Item[]{
                ItemBlockMM.getItemFromBlock(ModBlocks.Ore)};
        public static final Item[] output = new Item[]{
                ModItems.dust_nickel};
        public static int[] inputamount =new int[]{
                2};
        public static int[] outputamount =new int[]{
                3};


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

        public static int getInputAmount(Item item){
            for(int i = 0; i < duration.length; i++){
                if(input[i] == item)
                    return inputamount[i];
            }
            return 100;
        }
        public static int getOutputAmount(Item item){
            for(int i = 0; i < duration.length; i++){
                if(input[i] == item)
                    return outputamount[i];
            }
            return 0;
        }
        public static int getDuration(Item item){
            for(int i = 0; i < duration.length; i++){
                if(input[i] == item)
                    return duration[i];
            }
            return 0;
        }
    }
}
