package com.ragingart.maatsmod.init;

import cofh.api.modhelpers.ThermalExpansionHelper;
import com.ragingart.maatsmod.ref.Component;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {

    public static void init()
    {
        /* Items */
        // Shaped
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.MULTITOOL,"r d"," s ","s  ",'s', Component.HANDLE,'r',Component.BATTERY,'d',Component.GEM));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.BATTERY," g ","iri","iri",'g', Component.SMALL_GOLD,'i', Component.NORMAL_IRON,'r',Component.RS));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.CASING,"sis","iri","sis",'s',Component.SCREW,'i',Component.NORMAL_IRON,'r',Component.RS));
        // Shapeless
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.screw,4),Component.NORMAL_NICKEL));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_ENERGY,Component.CASING,Component.BATTERY,Component.SCREW));


        /* Blocks */
        //Shaped
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.Charger)," cc","crc"," ce",'c',Component.CASING,'r',Component.RS,'e',Component.CASING_ENERGY));
        //Shapeless


        /* Furnace */
        GameRegistry.addSmelting(new ItemStack(ModBlocks.Ore),new ItemStack(ModItems.ingot_nickel),0);


        /* Thermal Expansion */
        /* Pulverizer */
        ThermalExpansionHelper.addPulverizerRecipe(1000,new ItemStack(ModBlocks.Ore), new ItemStack(ModItems.dust_nickel,2));

        /* Furnace */
        ThermalExpansionHelper.addFurnaceRecipe(500, new ItemStack(ModItems.dust_nickel), new ItemStack(ModItems.ingot_nickel));
        ThermalExpansionHelper.addFurnaceRecipe(1000, new ItemStack(ModBlocks.Ore), new ItemStack(ModItems.ingot_nickel));
    }
}
