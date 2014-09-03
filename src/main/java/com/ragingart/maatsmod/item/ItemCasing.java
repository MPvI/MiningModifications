package com.ragingart.maatsmod.item;


import com.ragingart.maatsmod.generics.BlockMachineMM;
import com.ragingart.maatsmod.generics.ItemMM;
import com.ragingart.maatsmod.generics.TileEntityMachineMM;
import com.ragingart.maatsmod.network.PacketHandler;
import com.ragingart.maatsmod.network.messages.MessageItemCasing;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.util.CasingHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemCasing extends ItemMM {

    protected CasingHelper.Port mPort;

    public ItemCasing() {
        super(Names.Textures.Blocks.CASING);
        mPort = CasingHelper.Port.BLANK;
    }

    public CasingHelper.Port getPort() {
        return mPort;
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
