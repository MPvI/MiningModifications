package com.ragingart.maatsmod.proxy;

import com.ragingart.maatsmod.client.renderer.item.*;
import com.ragingart.maatsmod.client.renderer.tileentity.*;
import com.ragingart.maatsmod.init.ModBlocks;
import com.ragingart.maatsmod.init.ModItems;
import com.ragingart.maatsmod.ref.RenderIds;
import com.ragingart.maatsmod.tileentity.TileEntityCable;
import com.ragingart.maatsmod.tileentity.TileEntityFluxField;
import com.ragingart.maatsmod.tileentity.TileEntityPlatformBase;
import com.ragingart.maatsmod.tileentity.handmachines.TileEntityCompactor;
import com.ragingart.maatsmod.tileentity.handmachines.TileEntityCrank;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy{
    public void registerModels(){
        RenderIds.PlatformBase = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.FluxField = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.Cable = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.Compactor = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.Crank = RenderingRegistry.getNextAvailableRenderId();

        MinecraftForgeClient.registerItemRenderer(ModItems.spear,new ItemRendererSpear());
        MinecraftForgeClient.registerItemRenderer(ModItems.multitool,new ItemRendererMultitool());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.FluxField),new ItemRendererFluxField());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.PlatformBase),new ItemRendererPlatformBase());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.Cable),new ItemRendererCable());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.Compactor),new ItemRendererCompactor());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.Crank),new ItemRendererCrank());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlatformBase.class,new TileRendererPlatformBase());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluxField.class,new TileRendererFluxField());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class,new TileRendererCable());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCompactor.class,new TileRendererCompactor());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrank.class,new TileRendererCrank());
    }
}
