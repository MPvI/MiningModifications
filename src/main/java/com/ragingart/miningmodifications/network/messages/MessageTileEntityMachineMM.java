package com.ragingart.miningmodifications.network.messages;

import com.ragingart.miningmodifications.generics.TileEntityMachineMM;
import com.ragingart.miningmodifications.util.MachineHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class MessageTileEntityMachineMM implements IMessage,IMessageHandler<MessageTileEntityMachineMM,IMessage>{

    private MachineHelper aHelper;
    private int x;
    private int y;
    private int z;
    private int energy;
    private int famount;
    private int fid;
    private ItemStack inv;
    private boolean wD;

    public MessageTileEntityMachineMM(){
        aHelper=new MachineHelper();
    }

    public MessageTileEntityMachineMM(TileEntityMachineMM te){
        x=te.xCoord;
        y=te.yCoord;
        z=te.zCoord;
        energy=te.getEnergyStored(ForgeDirection.UNKNOWN);
        aHelper=te.getMachineHelper();
        famount = te.getFluidAmount();
        fid = te.getFluidID();
        inv = te.getStackInSlot(0);
        wD = te.isWorkDone();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x=buf.readInt();
        y=buf.readInt();
        z=buf.readInt();
        energy=buf.readInt();
        famount =buf.readInt();
        fid = buf.readInt();
        aHelper.setState(buf.readInt());
        aHelper.setFacing(ForgeDirection.getOrientation(buf.readInt()));
        for (int i = 0; i < 6; i++) {
            aHelper.setPort(i, buf.readInt());
        }
        try{
            inv = new ItemStack(Item.getItemById(buf.readShort()),buf.readByte(),buf.readShort());
        }catch (Exception e){
            inv = null;
        }
        wD=buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(energy);
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
        if(inv!=null) {
            buf.writeShort(Item.getIdFromItem(inv.getItem()));
            buf.writeByte(inv.stackSize);
            buf.writeShort(inv.getItemDamage());
        }
        buf.writeBoolean(wD);
    }

    @Override
    public IMessage onMessage(MessageTileEntityMachineMM message, MessageContext ctx) {
        Minecraft aClient = FMLClientHandler.instance().getClient();
        TileEntity aTile = aClient.theWorld.getTileEntity(message.x,message.y,message.z);

        if(aTile instanceof TileEntityMachineMM){

            ((TileEntityMachineMM) aTile).setMachineHelper(message.aHelper);

            ((TileEntityMachineMM) aTile).getTank().setFluid(new FluidStack(message.fid,message.famount));

            ((TileEntityMachineMM) aTile).setEnergy(message.energy);

            ((TileEntityMachineMM) aTile).setInventorySlotContents(0,message.inv);

            ((TileEntityMachineMM) aTile).setWorkDone(message.wD);

            aClient.theWorld.markBlockForUpdate(message.x,message.y,message.z);
        }
        return null;
    }
}
