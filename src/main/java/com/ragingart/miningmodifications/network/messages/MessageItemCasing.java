package com.ragingart.miningmodifications.network.messages;

import com.ragingart.miningmodifications.generics.TileEntityMachineMM;
import com.ragingart.miningmodifications.util.PlayerInventoryHelper;
import com.ragingart.miningmodifications.util.Port;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageItemCasing implements IMessage,IMessageHandler<MessageItemCasing,IMessage> {

    public int side;
    public int port;
    public int x;
    public int y;
    public int z;

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
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        TileEntityMachineMM aTile = (TileEntityMachineMM) player.worldObj.getTileEntity(new BlockPos(message.x, message.y, message.z));

        if (message.side != aTile.getMachineHelper().getFacing().ordinal()){
            if (PlayerInventoryHelper.decrItem(player.inventory, Port.getItemFromPort(message.port))) {
                Port oldPort = aTile.getMachineHelper().setPort(message.side, message.port);
                if (oldPort != null)player.inventory.addItemStackToInventory(Port.getItemFromPort(oldPort));
            }
        }

        aTile.markDirty();
        return null;
    }
}
