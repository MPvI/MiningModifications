package com.ragingart.maatsmod.item;

import com.ragingart.maatsmod.generics.ItemToolMM;
import com.ragingart.maatsmod.ref.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by MaaT on 04.09.2014.
 */
public class ItemSpear extends ItemToolMM {
    public ItemSpear(){
        super(Names.Items.WEAPON_SPEAR,10.0F);
        this.setFull3D();
    }



    @Override
    public boolean onItemUse(ItemStack itemStack,EntityPlayer entityPlayer,World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ){

        //MinecraftForge.EVENT_BUS.post(new RenderPlayerEvent.Post(entityPlayer,new RenderPlayerMM(),1.0F));

        return true;
    }


}
