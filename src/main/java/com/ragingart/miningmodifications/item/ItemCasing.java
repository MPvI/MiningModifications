package com.ragingart.miningmodifications.item;


import com.ragingart.miningmodifications.generics.ItemMM;
import com.ragingart.miningmodifications.generics.TileEntityMachineMM;
import com.ragingart.miningmodifications.network.PacketHandler;
import com.ragingart.miningmodifications.network.messages.MessageItemCasing;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.util.CasingHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
    public void addSpecialInfo(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {
        super.addSpecialInfo(itemStack,entityPlayer,list,b);
        String info = StatCollector.translateToLocal(Names.INFO_PREFIX + Names.getUnwrappedUnlocalizedName(getUnlocalizedName())+"."+itemStack.getItemDamage());
        Collections.addAll(list, info.split("%"));
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {

        TileEntity aTile = world.getTileEntity(pos);

        if(aTile instanceof TileEntityMachineMM && stack.getItem() instanceof ItemCasing && ((TileEntityMachineMM) aTile).canAcceptPort(stack.getItemDamage())){
                PacketHandler.INSTANCE.sendToServer(new MessageItemCasing(side.ordinal(), stack.getItemDamage(), pos.getX(),pos.getY(),pos.getZ()));
        }
        return true;
    }
}
