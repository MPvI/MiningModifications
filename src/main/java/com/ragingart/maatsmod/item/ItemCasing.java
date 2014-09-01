package com.ragingart.maatsmod.item;


import com.ragingart.maatsmod.generics.BlockMachineMM;
import com.ragingart.maatsmod.generics.ItemMM;
import com.ragingart.maatsmod.util.CasingHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCasing extends ItemMM {

    protected CasingHelper.Port mPort = CasingHelper.Port.BLANK;

    public ItemCasing()
    {
        super();
        setUnlocalizedName("casing");
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

        Block block = world.getBlock(x, y, z);
        if(world.isRemote && block instanceof BlockMachineMM){
            ((BlockMachineMM) block).getIconHelper().setSideIcon(side,mPort);
            world.markBlockForUpdate(x,y,z);
        }
        return true;
    }
}
