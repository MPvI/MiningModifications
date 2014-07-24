package com.ragingart.maatsmod.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Recipes {

    public static void init()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.multitool),"r d"," s ","s  ",'s',"stickWood",'r',new ItemStack(ModItems.battery),'d',"gemDiamond"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.battery)," g ","iri","iri",'g',"nuggetGold",'i',"ingotIron",'r',"dustRedstone"));
    }
}
