package com.ragingart.maatsmod.init;

import cofh.api.modhelpers.ThermalExpansionHelper;
import com.ragingart.maatsmod.ref.Component;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {

    public static void init()
    {
        /* Items */
        // Shaped
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.MULTITOOL(),"r d"," s ","s  ",'s', Component.HANDLE,'r',Component.BATTERY(1),'d',Component.GEM));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.BATTERY(1)," g ","iri","iri",'g', Component.SMALL_GOLD,'i', Component.NORMAL_IRON,'r',Component.RS));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.CASING(1),"sis","iri","sis",'s',Component.SCREW(1),'i',Component.NORMAL_IRON,'r',Component.RS));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.PLATFORM(1)," e ","ebe"," e ",'b',Component.BATTERY(1),'e',Component.PLATFORM_EXT(1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.PLATFORM_EXT(1),"sis","ibi","sis",'i',Component.NORMAL_IRON,'s',Component.SCREW(1),'b',Component.BATTERY(1)));
        // Shapeless
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.SCREW(4),Component.NORMAL_NICKEL,Component.NORMAL_NICKEL));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_ENERGY(1),Component.CASING(1),Component.BATTERY(1),Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_INPUT(1),Component.CASING(1),Blocks.chest,Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_OUTPUT(1),Component.CASING(1),Blocks.hopper,Component.SCREW(1)));


        /* Blocks */
        //Shaped
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.Charger)," cc","crc"," ce",'c',Component.CASING(1),'r',Component.RS,'e',Component.CASING_ENERGY(1)));
        //Shapeless


        /* Furnace */
        GameRegistry.addSmelting(new ItemStack(ModBlocks.Ore),new ItemStack(ModItems.ingot_nickel),0);
        GameRegistry.addSmelting(new ItemStack(ModItems.dust_nickel),new ItemStack(ModItems.ingot_nickel),0);


        /* Thermal Expansion */
        /* Pulverizer */
        ThermalExpansionHelper.addPulverizerRecipe(1000,new ItemStack(ModBlocks.Ore), new ItemStack(ModItems.dust_nickel,2));

        /* Furnace */
        ThermalExpansionHelper.addFurnaceRecipe(500, new ItemStack(ModItems.dust_nickel), new ItemStack(ModItems.ingot_nickel));
        ThermalExpansionHelper.addFurnaceRecipe(1000, new ItemStack(ModBlocks.Ore), new ItemStack(ModItems.ingot_nickel));
    }
}
