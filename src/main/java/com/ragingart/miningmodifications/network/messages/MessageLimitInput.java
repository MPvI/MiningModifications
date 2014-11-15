package com.ragingart.miningmodifications.network.messages;

import com.ragingart.miningmodifications.item.ItemVoidpack;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

/**
 * Created by MaaT on 14.11.2014.
 */public class MessageLimitInput implements IMessage,IMessageHandler<MessageLimitInput,IMessage> {
    public int containerIndex;
    public int slot;
    public int number;

    public MessageLimitInput(){

    }

    public MessageLimitInput(int c, int s, int n){
        this.containerIndex = c;
        this.slot = s;
        this.number=n;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        containerIndex = buf.readInt();
        slot = buf.readInt();
        number = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(containerIndex);
        buf.writeInt(slot);
        buf.writeInt(number);
    }

    @Override
    public IMessage onMessage(MessageLimitInput message, MessageContext ctx) {
        ItemVoidpack.setNumberToKeep(ctx.getServerHandler().playerEntity.inventory.mainInventory[message.containerIndex], message.slot,message.number);
        return null;
    }

}
