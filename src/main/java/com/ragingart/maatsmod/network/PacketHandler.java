package com.ragingart.maatsmod.network;


import com.ragingart.maatsmod.network.messages.MessageItemCasing;
import com.ragingart.maatsmod.network.messages.MessageTileEntityMachineMM;
import com.ragingart.maatsmod.network.messages.MessageTileEntityMachinePP;
import com.ragingart.maatsmod.ref.Reference;
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
    }
}
