package com.ragingart.maatsmod.network.messages;

import com.ragingart.maatsmod.generics.TileEntityMachineMM;
import com.ragingart.maatsmod.util.MachineHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by MaaT on 02.09.2014.
 */
public class MessageTileEntityMachineMM implements IMessage,IMessageHandler<MessageTileEntityMachineMM,IMessage>{

    private int[] ports = new int[5];
    private int x;
    private int y;
    private int z;
    private int state;

    public MessageTileEntityMachineMM(){

    }

    public MessageTileEntityMachineMM(TileEntityMachineMM te){
        for(int i=0;i<ports.length;i++) {
            ports[i]=te.getMachineHelper().getPort(i).ordinal();
        }
        x=te.xCoord;
        y=te.yCoord;
        z=te.zCoord;
        state=te.getMachineHelper().getState();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        for(int i=0;i<ports.length;i++) {
            ports[i]=buf.readInt();
        }
        x=buf.readInt();
        y=buf.readInt();
        z=buf.readInt();
        state=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for(int k: ports){
            buf.writeInt(k);
        }
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(state);
    }

    @Override
    public IMessage onMessage(MessageTileEntityMachineMM message, MessageContext ctx) {
        Minecraft aClient = FMLClientHandler.instance().getClient();
        TileEntity aTile = aClient.theWorld.getTileEntity(message.x,message.y,message.z);


        if(aTile instanceof TileEntityMachineMM){
            MachineHelper aHelper = ((TileEntityMachineMM) aTile).getMachineHelper();
            for(int i=0;i<ports.length;i++) {
                aHelper.setPort(i,message.ports[i]);
            }
            aHelper.setState(message.state);
            aClient.theWorld.markBlockForUpdate(message.x,message.y,message.z);
        }
        return null;
    }
}
