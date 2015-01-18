package com.ragingart.miningmodifications.proxy;

import com.ragingart.miningmodifications.client.renderer.item.*;
import com.ragingart.miningmodifications.client.renderer.tileentity.*;
import com.ragingart.miningmodifications.init.ModBlocks;
import com.ragingart.miningmodifications.init.ModItems;
import com.ragingart.miningmodifications.ref.Reference;
import com.ragingart.miningmodifications.tileentity.TileEntityCable;
import com.ragingart.miningmodifications.tileentity.TileEntityPlatformBase;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntityCompactor;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntityCrank;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntityGrinder;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntitySharpeningWheel;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityDischarger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
    public void registerModels(){

        ItemModelMesher IMM = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

        //IMM.register(Item.getItemFromBlock(ModBlocks.Charger), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + ModBlocks.Charger.getName()));
        IMM.register(Item.getItemFromBlock(ModBlocks.Discharger), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + ModBlocks.Discharger.getName()));
        IMM.register(ModItems.casing, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + ModItems.casing.getName()));


        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDischarger.class,new TileRendererBlock());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlatformBase.class, new TileRendererPlatformBase());
        //ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluxField.class,new TileRendererFluxField());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class,new TileRendererCable());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCompactor.class,new TileRendererCompactor());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrank.class,new TileRendererCrank());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySharpeningWheel.class,new TileRendererSharpeningWheel());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrinder.class,new TileRendererGrinder());

        MinecraftForgeClient.registerItemRenderer(ModItems.spear,new ItemRendererSpear());
        MinecraftForgeClient.registerItemRenderer(ModItems.multitool,new ItemRendererMultitool());
        MinecraftForgeClient.registerItemRenderer(ModItems.voidpack,new ItemRendererVoidpack());
        //MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.FluxField),new ItemRendererFluxField());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.PlatformBase),new ItemRendererPlatformBase());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.Cable),new ItemRendererCable());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.Compactor),new ItemRendererCompactor());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.SharpeningWheel),new ItemRendererSharpeningWheel());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.Crank),new ItemRendererCrank());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.Grinder),new ItemRendererGrinder());

    }
}
