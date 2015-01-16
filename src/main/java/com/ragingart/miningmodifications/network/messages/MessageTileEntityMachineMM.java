package com.ragingart.miningmodifications.network.messages;

import com.ragingart.miningmodifications.generics.TileEntityMachineMM;
import com.ragingart.miningmodifications.util.MachineHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTileEntityMachineMM implements IMessage,IMessageHandler<MessageTileEntityMachineMM,IMessage> {

    private MachineHelper aHelper;
    private int x;
    private int y;
    private int z;
    private int energy;
    private int famount;
    private int fid;

    public MessageTileEntityMachineMM(){
        aHelper=new MachineHelper();
    }

    public MessageTileEntityMachineMM(TileEntityMachineMM te){
        x=te.getPos().getX();
        y=te.getPos().getY();
        z=te.getPos().getZ();
        //energy=te.getEnergyStored(EnumFacing.UP);
        aHelper=te.getMachineHelper();
        famount = te.getFluidAmount();
        fid = te.getFluidID();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x=buf.readInt();
        y=buf.readInt();
        z=buf.readInt();
        //energy=buf.readInt();
        famount =buf.readInt();
        fid = buf.readInt();
        aHelper.setState(buf.readInt());
        aHelper.setFacing(EnumFacing.getFront(buf.readInt()));
        for (int i = 0; i < 6; i++) {
            aHelper.setPort(i, buf.readInt());
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        //buf.writeInt(energy);
        buf.writeInt(famount);
        buf.writeInt(fid);
        buf.writeInt(aHelper.getState());
        buf.writeInt(aHelper.getFacing().ordinal());
        for (int i = 0; i < 6; i++) {
            if(aHelper.getPort(i)==null){
                buf.writeInt(6);
            }else {
                buf.writeInt(aHelper.getPort(i).ordinal());
            }
        }
    }

    @Override
    public IMessage onMessage(MessageTileEntityMachineMM message, MessageContext ctx) {
        Minecraft aClient = FMLClientHandler.instance().getClient();
        TileEntity aTile = aClient.theWorld.getTileEntity(new BlockPos(message.x,message.y,message.z));

        if(aTile instanceof TileEntityMachineMM){

            ((TileEntityMachineMM) aTile).setMachineHelper(message.aHelper);

            ((TileEntityMachineMM) aTile).getTank().setFluid(new FluidStack(message.fid,message.famount));

            ((TileEntityMachineMM) aTile).setEnergy(message.energy);
            aClient.theWorld.markBlockForUpdate(new BlockPos(message.x,message.y,message.z));
        }
        return null;
    }
}
