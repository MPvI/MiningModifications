package com.ragingart.miningmodifications.init;

import cofh.api.modhelpers.ThermalExpansionHelper;
import com.ragingart.miningmodifications.generics.ItemBlockMM;
import com.ragingart.miningmodifications.ref.Component;
import com.ragingart.miningmodifications.util.RecipeHelper;
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
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.MULTITOOLBLADE()," d ","dsd","   ",'d',"gemDiamond",'s',Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.MULTITOOLHANDLE(),"www","o o"," o ",'w',"plankWood",'o',new ItemStack(Blocks.obsidian)));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.MULTITOOL(),"bdb","oco"," h ",'b',Component.MULTITOOLBLADE(),'d',"gemDiamond",'o',Component.GEM,'h',Component.MULTITOOLHANDLE(),'c',Component.CIRCUIT()));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.BATTERY(1)," g ","iri","iri",'g', Component.SMALL_GOLD,'i', Component.NORMAL_IRON,'r',Component.SMALL_RS));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.CASING(1),"sis","iri","sis",'s',Component.SCREW(1),'i',"plateIron",'r',Component.SMALL_RS));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.voidpack),"iei","ifi","sis",'i',"plateIron",'e',Items.ender_eye,'f',Component.FLUXFIELD(1),'s',Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.CIRCUIT(),"www","rsr","www",'w',Component.CIRCUITWIRE(1),'r',Component.SMALL_RS,'s',Component.GEM));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.CIRCUITWIRE(24),"sss","gng","sss",'s',"itemSilicon",'n',Component.NORMAL_NICKEL,'g',Component.SMALL_GOLD));
        // Shapeless
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_ENERGY(1),Component.CASING(1),Component.BATTERY(1),Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_INPUT(1),Component.CASING(1),Blocks.chest,Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_OUTPUT(1),Component.CASING(1),Blocks.hopper,Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_FINPUT(1),Component.CASING_INPUT(1),Items.bucket,Component.SCREW(1)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Component.CASING_FOUTPUT(1),Component.CASING_OUTPUT(1),Items.bucket,Component.SCREW(1)));


        /* Blocks */
        //Shaped
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.PLATFORM(1),"cec","ebe","cec",'b',Component.CIRCUIT(),'e',Component.FLUXFIELD(1),'c',Component.CASING(1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(Component.FLUXFIELD(1),"rrr","rbr","rrr",'r',Component.BIG_RS,'b',Component.BATTERY(1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.Charger),"gcc","src","ncc",'c',Component.CASING(1),'r',Component.BIG_RS,'g',Component.SMALL_GOLD,'n',Component.SCREW(1),'s',Component.CIRCUIT()));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.Discharger),"ncc","src","gcc",'c',Component.CASING(1),'r',Component.BIG_RS,'g',Component.SMALL_GOLD,'n',Component.SCREW(1),'s',Component.CIRCUIT()));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.WaterTurbine),"bcc"," rc","bcc",'c',Component.CASING(1),'r',Component.BIG_RS,'b',Items.bucket));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.RFEnergyStorage),"bcc"," rc","bcc",'c',Component.CASING(1),'r',Component.BIG_RS,'b',Component.BATTERY(1)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.Cable,16),"rgr","nnn","rgr",'r',Component.BIG_RS,'g',Component.SMALL_GOLD,'n',Component.NORMAL_NICKEL));
        //Handmachines
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.Crank), "a a", " b ", " c ", 'a', "stickWood", 'b', "cobblestone", 'c', "ingotIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.Compactor), "bcb", "aca", "bcb", 'a', "logWood", 'b', "cobblestone", 'c', "ingotIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.SharpeningWheel), "bcb", "ada", "beb", 'a', "logWood", 'b', "cobblestone", 'c', "ingotIron",'d',"blockIron",'e',Blocks.obsidian));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.Grinder),"ada","beb","aca",'a',"cobblestone",'b',"plankWood",'c',"stone",'d',"ingotIron",'e',"plateIron"));
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


        /* MachineRecipes*/

        //Compactor
        RecipeHelper.addCompactorRecipe(85, 2, 1, Items.iron_ingot, ModItems.plate_iron);

        //Sharpening Wheel
        RecipeHelper.addScharpeningWheelRecipe(100, 1, 2, ModItems.ingot_nickel, ModItems.screw);

        //Grinder
        RecipeHelper.addGrinderRecipe(200, 2, 3, ItemBlockMM.getItemFromBlock(ModBlocks.Ore), ModItems.dust_nickel);
    }
}
