package com.ragingart.maatsmod.init;

import cofh.api.modhelpers.ThermalExpansionHelper;
import com.ragingart.maatsmod.ref.Component;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {

    public static void init()
    {
        /* Items */
        // Shaped
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.MULTITOOL(),"r d"," s ","s  ",'s', Component.HANDLE,'r',Component.BATTERY(1),'d',Component.GEM));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.BATTERY(1)," g ","iri","iri",'g', Component.SMALL_GOLD,'i', Component.NORMAL_IRON,'r',Component.SMALL_RS));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.CASING(1),"sis","iri","sis",'s',Component.SCREW(1),'i',Component.PLATE_IRON,'r',Component.SMALL_RS));
        // Shapeless
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.SCREW(4),Component.NORMAL_NICKEL,Component.NORMAL_NICKEL));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_ENERGY(1),Component.CASING(1),Component.BATTERY(1),Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_INPUT(1),Component.CASING(1),Blocks.chest,Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_OUTPUT(1),Component.CASING(1),Blocks.hopper,Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_FINPUT(1),Component.CASING_INPUT(1),Items.bucket,Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_FOUTPUT(1),Component.CASING_OUTPUT(1),Items.bucket,Component.SCREW(1)));


        /* Blocks */
        //Shaped
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.PLATFORM(1),"cec","ebe","cec",'b',Component.BATTERY(1),'e',Component.FLUXFIELD(1),'c',Component.CASING(1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.FLUXFIELD(1),"rrr","rbr","rrr",'r',Component.BIG_RS,'b',Component.BATTERY(1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.Charger),"gcc"," rc","ncc",'c',Component.CASING(1),'r',Component.BIG_RS,'g',Component.SMALL_GOLD,'n',Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.Discharger),"ncc"," rc","gcc",'c',Component.CASING(1),'r',Component.BIG_RS,'g',Component.SMALL_GOLD,'n',Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.WaterTurbine),"bcc"," rc","bcc",'c',Component.CASING(1),'r',Component.BIG_RS,'b',Items.bucket));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.RFEnergyStorage),"bcc"," rc","bcc",'c',Component.CASING(1),'r',Component.BIG_RS,'b',Component.BATTERY(1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.Cable,16),"rgr","nnn","rgr",'r',Component.BIG_RS,'g',Component.SMALL_GOLD,'n',Component.NORMAL_NICKEL));
        //Shapeless


        /* Furnace */
        GameRegistry.addSmelting(new ItemStack(ModBlocks.Ore), new ItemStack(ModItems.ingot_nickel), 0);
        GameRegistry.addSmelting(new ItemStack(ModItems.dust_nickel),new ItemStack(ModItems.ingot_nickel),0);


        /* Thermal Expansion */
        /* Pulverizer */
        ThermalExpansionHelper.addPulverizerRecipe(1000,new ItemStack(ModBlocks.Ore), new ItemStack(ModItems.dust_nickel,2));

        /* Furnace */
        ThermalExpansionHelper.addFurnaceRecipe(500, new ItemStack(ModItems.dust_nickel), new ItemStack(ModItems.ingot_nickel));
        ThermalExpansionHelper.addFurnaceRecipe(1000, new ItemStack(ModBlocks.Ore), new ItemStack(ModItems.ingot_nickel));
    }
}
