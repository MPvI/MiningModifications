package com.ragingart.maatsmod.proxy;

import com.ragingart.maatsmod.client.renderer.item.ItemRendererSpear;
import com.ragingart.maatsmod.init.ModItems;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy{
    public void registerModels(){
        MinecraftForgeClient.registerItemRenderer(ModItems.spear,new ItemRendererSpear());
    }
}
