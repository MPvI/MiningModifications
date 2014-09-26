package com.ragingart.maatsmod.proxy;

import com.ragingart.maatsmod.client.renderer.item.ItemRendererMultitool;
import com.ragingart.maatsmod.client.renderer.item.ItemRendererSpear;
import com.ragingart.maatsmod.client.renderer.tileentity.TileRendererPlatformBase;
import com.ragingart.maatsmod.client.renderer.tileentity.TileRendererPlatformExt;
import com.ragingart.maatsmod.init.ModItems;
import com.ragingart.maatsmod.ref.RenderIds;
import com.ragingart.maatsmod.tileentity.TileEntityPlatformBase;
import com.ragingart.maatsmod.tileentity.TileEntityPlatformExt;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy{
    public void registerModels(){
        RenderIds.PlatformBase = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.PlatformExt = RenderingRegistry.getNextAvailableRenderId();

        MinecraftForgeClient.registerItemRenderer(ModItems.spear,new ItemRendererSpear());
        MinecraftForgeClient.registerItemRenderer(ModItems.multitool,new ItemRendererMultitool());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlatformBase.class,new TileRendererPlatformBase());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlatformExt.class,new TileRendererPlatformExt());
    }
}
