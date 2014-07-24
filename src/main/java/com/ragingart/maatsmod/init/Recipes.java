package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.ref.Component;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Recipes {

    public static void init()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.multitool),"r d"," s ","s  ",'s', Component.HANDLE,'r',new ItemStack(ModItems.battery),'d',"gemDiamond"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.battery)," g ","iri","iri",'g', Component.SMALL_GOLD,'i', Component.NORMAL_IRON,'r',"dustRedstone"));
    }
}
