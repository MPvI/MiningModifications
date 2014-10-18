package com.ragingart.maatsmod.item;


import com.ragingart.maatsmod.generics.ItemMM;
import com.ragingart.maatsmod.generics.TileEntityMachineMM;
import com.ragingart.maatsmod.network.PacketHandler;
import com.ragingart.maatsmod.network.messages.MessageItemCasing;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.util.CasingHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

public class ItemCasing extends ItemMM {

    protected IIcon[] casing_textures = new IIcon[CasingHelper.Port.values().length];

    public ItemCasing() {
        super(Names.Items.CASING[0]);
        setHasSubtypes(true);
    }


    @Override
    public void getSubItems(Item item, CreativeTabs p_150895_2_, List list) {
        for (int i = 0; i < CasingHelper.Port.values().length; i++) {
            list.add(new ItemStack(item,1,i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return "item."+Names.MOD_PREFIX+Names.Items.CASING[itemStack.getItemDamage()];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return casing_textures[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        for (int i = 0; i < CasingHelper.Port.values().length; i++) {
            casing_textures[i]=iconRegister.registerIcon(Names.MOD_PREFIX+Names.Items.CASING[i]);
        }
    }

    @Override
    public void addSpecialInfo(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {
        super.addSpecialInfo(itemStack,entityPlayer,list,b);
        String info = StatCollector.translateToLocal(Names.INFO_PREFIX + Names.getUnwrappedUnlocalizedName(getUnlocalizedName())+"."+itemStack.getItemDamage());
        Collections.addAll(list, info.split("%"));
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

        TileEntity aTile = world.getTileEntity(x, y, z);

        if(aTile instanceof TileEntityMachineMM && itemStack.getItem() instanceof ItemCasing && ((TileEntityMachineMM) aTile).canAcceptPort(itemStack.getItemDamage())){
                PacketHandler.INSTANCE.sendToServer(new MessageItemCasing(side, itemStack.getItemDamage(), x, y, z));
        }
        return true;
    }
}
