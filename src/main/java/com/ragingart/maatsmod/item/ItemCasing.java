package com.ragingart.maatsmod.item;


import com.ragingart.maatsmod.generics.BlockMachineMM;
import com.ragingart.maatsmod.generics.ItemMM;
import com.ragingart.maatsmod.generics.TileEntityMachineMM;
import com.ragingart.maatsmod.network.PacketHandler;
import com.ragingart.maatsmod.network.messages.MessageItemCasing;
import com.ragingart.maatsmod.util.CasingHelper;
import net.minecraft.block.Block;
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

    /*
    @Override
    public IIcon getIconFromDamage(int p_77617_1_) {
        return super.getIconFromDamage(p_77617_1_);
    }

    @Override
    public boolean getHasSubtypes() {
        return true;
    }*/

    public void setPort(CasingHelper.Port aPort){
        mPort=aPort;
    }

    public void setPort(int i){
        setPort(CasingHelper.Port.values()[i]);
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        TileEntity te = world.getTileEntity(x, y, z);
        Block block = world.getBlock(x,y,z);
        if(te instanceof TileEntityMachineMM && block instanceof BlockMachineMM){
            PacketHandler.INSTANCE.sendToServer(new MessageItemCasing(side, getPort().ordinal(), x, y, z));
        }
        return true;
    }
}
