package com.ragingart.maatsmod.proxy;

import com.ragingart.maatsmod.client.renderer.item.ItemRendererMultitool;
import com.ragingart.maatsmod.client.renderer.item.ItemRendererPlatformBase;
import com.ragingart.maatsmod.client.renderer.item.ItemRendererPlatformExt;
import com.ragingart.maatsmod.client.renderer.item.ItemRendererSpear;
import com.ragingart.maatsmod.client.renderer.tileentity.TileRendererPlatformBase;
import com.ragingart.maatsmod.client.renderer.tileentity.TileRendererPlatformExt;
import com.ragingart.maatsmod.init.ModBlocks;
import com.ragingart.maatsmod.init.ModItems;
import com.ragingart.maatsmod.ref.RenderIds;
import com.ragingart.maatsmod.tileentity.TileEntityFluxField;
import com.ragingart.maatsmod.tileentity.TileEntityPlatformBase;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy{
    public void registerModels(){
        RenderIds.PlatformBase = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.PlatformExt = RenderingRegistry.getNextAvailableRenderId();

        MinecraftForgeClient.registerItemRenderer(ModItems.spear,new ItemRendererSpear());
        MinecraftForgeClient.registerItemRenderer(ModItems.multitool,new ItemRendererMultitool());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.PlatformExt),new ItemRendererPlatformExt());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.PlatformBase),new ItemRendererPlatformBase());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlatformBase.class,new TileRendererPlatformBase());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluxField.class,new TileRendererPlatformExt());
    }
}
