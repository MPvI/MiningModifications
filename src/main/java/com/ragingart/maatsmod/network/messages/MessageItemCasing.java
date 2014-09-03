package com.ragingart.maatsmod.network.messages;

import com.ragingart.maatsmod.generics.TileEntityMachineMM;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by MaaT on 02.09.2014.
 */
public class MessageItemCasing implements IMessage,IMessageHandler<MessageItemCasing,IMessage> {

    int side;
    int port;
    int x;
    int y;
    int z;

    public MessageItemCasing(){

    }

    public MessageItemCasing(int side,int port, int x, int y, int z){
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
    public IMessage onMessage(MessageItemCasing message, MessageContext ctx) {
        World world = ctx.getServerHandler().playerEntity.worldObj;
            TileEntity te = world.getTileEntity(message.x, message.y, message.z);
            if (te instanceof TileEntityMachineMM) {
                if(((TileEntityMachineMM) te).getMachineHelper().setPort(message.side, message.port)) {
                    te.markDirty();
                    return new MessageTileEntityMachineMM((TileEntityMachineMM) te);
                }
            }
        return null;
    }
}
