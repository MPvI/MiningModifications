package com.ragingart.maatsmod.network.messages;

import com.ragingart.maatsmod.item.ItemCasing;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

/**
 * Created by MaaT on 07.09.2014.
 */
public class MessageItemCasingChange implements IMessage,IMessageHandler<MessageItemCasingChange,IMessage>{

    public int side;
    public int port;
    public int x;
    public int y;
    public int z;


    public MessageItemCasingChange(){

    }

    public MessageItemCasingChange(int side,int port, int x, int y, int z){
        this.side = side;
        this.port = port;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.side = buf.readInt();
        this.port = buf.readInt();
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(side);
        buf.writeInt(port);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    @Override
    public IMessage onMessage(MessageItemCasingChange message, MessageContext ctx){
        Minecraft aClient = FMLClientHandler.instance().getClient();
        ItemStack aItem = aClient.thePlayer.getHeldItem();

        if(aItem != null && aItem.getItem() instanceof ItemCasing){
            aItem.stackSize--;
            aClient.theWorld.markBlockForUpdate(message.x,message.y,message.z);
        }
        return null;
    }
}
