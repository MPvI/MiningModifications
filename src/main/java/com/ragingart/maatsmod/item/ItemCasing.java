package com.ragingart.maatsmod.item;


import com.ragingart.maatsmod.generics.ItemMM;
import com.ragingart.maatsmod.generics.TileEntityMachineMM;
import com.ragingart.maatsmod.network.PacketHandler;
import com.ragingart.maatsmod.network.messages.MessageItemCasing;
import com.ragingart.maatsmod.util.CasingHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemCasing extends ItemMM {

    protected CasingHelper.Port mPort;

    public ItemCasing(String name, int port) {
        super(name);
        setPort(port);
    }

    public CasingHelper.Port getPort() {
        return mPort;
    }

    private void setPort(CasingHelper.Port aPort){
        mPort=aPort;
    }

    private void setPort(int i){
        setPort(CasingHelper.Port.values()[i]);
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

        TileEntity aTile = world.getTileEntity(x, y, z);

        if(aTile instanceof TileEntityMachineMM && itemStack.getItem() instanceof ItemCasing && ((TileEntityMachineMM) aTile).canAcceptPort(getPort().ordinal())){
                PacketHandler.INSTANCE.sendToServer(new MessageItemCasing(side, getPort().ordinal(), x, y, z));
        }
        return true;
    }
}
