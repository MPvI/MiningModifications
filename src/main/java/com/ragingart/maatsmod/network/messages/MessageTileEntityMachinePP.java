package com.ragingart.maatsmod.network.messages;

import com.ragingart.maatsmod.generics.TileEntityMachinePP;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by MaaT on 19.10.2014.
 */
public class MessageTileEntityMachinePP implements IMessage,IMessageHandler<MessageTileEntityMachinePP,IMessage> {

    private ForgeDirection facing;
    private int x;
    private int y;
    private int z;

    public MessageTileEntityMachinePP(){

    }

    public MessageTileEntityMachinePP(TileEntityMachinePP aTile) {
        facing = aTile.getFacing();
        x = aTile.xCoord;
        y = aTile.yCoord;
        z = aTile.zCoord;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        facing = ForgeDirection.getOrientation(buf.readInt());
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
        TileEntity aTile = aClient.theWorld.getTileEntity(message.x,message.y,message.z);

        if(aTile instanceof TileEntityMachinePP){
            ((TileEntityMachinePP) aTile).setFacing(message.facing);
        }

        return null;
    }
}
