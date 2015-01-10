package com.ragingart.miningmodifications;

import com.ragingart.miningmodifications.handler.ConfigHandler;
import com.ragingart.miningmodifications.handler.EventHandler;
import com.ragingart.miningmodifications.handler.GuiHandler;
import com.ragingart.miningmodifications.init.*;
import com.ragingart.miningmodifications.network.PacketHandler;
import com.ragingart.miningmodifications.proxy.IProxy;
import com.ragingart.miningmodifications.ref.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid= Reference.MOD_ID,name = Reference.MOD_NAME,version = Reference.VERSION,guiFactory = Reference.GUI_FACTORY,dependencies = Reference.DEPENDENCIES)
public class MiningModifications {

    @Mod.Instance(Reference.MOD_ID)
    public static MiningModifications instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide=Reference.SERVER_PROXY)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        // network handler
        PacketHandler.init();
        // config
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        // register Config to Eventbus
        FMLCommonHandler.instance().bus().register(new ConfigHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        // init items blocks
        ModItems.init();
        ModBlocks.init();
        ModFluids.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        /*
        try {
            ClientPlayerAPI.register(Reference.MOD_ID, ClientPlayerMM.class);
            RenderPlayerAPI.register(Reference.MOD_ID, RenderPlayerMM.class);
        }catch (Throwable e){

        }*/

        if(ConfigHandler.oreGen) {
            new ModWorldgen();
        }

        proxy.registerModels();
        //
        ModOreDict.init();
        //Recipes
        ModRecipes.init();
        //Tile Entities
        ModTiles.init();
        // gui
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // stuff
    }

}

/*
    finish laser seperator,
    rework recipes and high end components
    finish descriptions,

    pipes:
        receiver and transmitter in one block -> incoming energy pulse will emit shockwave
        fullblock glass cable with 3 colors, red energy, blue shockwave for itemtransport, yellow controlsignal
        moving items as entity - lock player pickup?
    miner:
        will also integrate a receiver and transmitter at some base block
        needs very high energy input and emits all items once mined with mini shockwaves
 */