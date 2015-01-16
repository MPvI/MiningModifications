package com.ragingart.miningmodifications.network.messages;

import com.ragingart.miningmodifications.generics.TileEntityMachinePP;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTileEntityMachinePP implements IMessage,IMessageHandler<MessageTileEntityMachinePP,IMessage> {

    private EnumFacing facing;
    private int x;
    private int y;
    private int z;

    public MessageTileEntityMachinePP(){

    }

    public MessageTileEntityMachinePP(TileEntityMachinePP aTile) {
        facing = aTile.getFacing();
        x = aTile.getPos().getX();
        y = aTile.getPos().getY();
        z = aTile.getPos().getZ();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        facing = EnumFacing.getFront(buf.readInt());
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(facing.ordinal());
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }


    @Override
    public IMessage onMessage(MessageTileEntityMachinePP message, MessageContext ctx) {

        Minecraft aClient = FMLClientHandler.instance().getClient();
        TileEntity aTile = aClient.theWorld.getTileEntity(new BlockPos(message.x,message.y,message.z));

        if(aTile instanceof TileEntityMachinePP){
            ((TileEntityMachinePP) aTile).setFacing(message.facing);
        }

        return null;
    }

}
