package com.ragingart.miningmodifications.network;


import com.ragingart.miningmodifications.network.messages.*;
import com.ragingart.miningmodifications.ref.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by MaaT on 02.09.2014.
 */
public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static void init(){
        INSTANCE.registerMessage(MessageTileEntityMachineMM.class,MessageTileEntityMachineMM.class,0, Side.CLIENT);
        INSTANCE.registerMessage(MessageItemCasing.class,MessageItemCasing.class,1,Side.SERVER);
        INSTANCE.registerMessage(MessageTileEntityMachinePP.class,MessageTileEntityMachinePP.class,2,Side.CLIENT);
        INSTANCE.registerMessage(MessageButtonClick.class,MessageButtonClick.class,3,Side.SERVER);
        INSTANCE.registerMessage(MessageLimitInput.class,MessageLimitInput.class,4,Side.SERVER);
    }
}
