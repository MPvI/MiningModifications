package com.ragingart.miningmodifications.item;

import cofh.api.item.IInventoryContainerItem;
import com.ragingart.miningmodifications.MiningModifications;
import com.ragingart.miningmodifications.container.ContainerVoidpack;
import com.ragingart.miningmodifications.generics.ItemMM;
import com.ragingart.miningmodifications.ref.Gui;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.util.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.List;

public class ItemVoidpack extends ItemMM implements IInventoryContainerItem {

    public ItemVoidpack() {
        super(Names.Items.VOIDPACK);
    }

    private int tick=0;

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean b) {
        if(entity instanceof EntityPlayer) {
            if (tick % 20 == 0 && getState(itemStack)) {
                Item[] toErase = new Item[5];
                int[] meErase = new int[5];
                int[] nuErase = new int[5];
                if (itemStack.hasTagCompound() && !itemStack.getTagCompound().hasNoTags()) {
                    for (int j = 0; j < 5; j++) {
                        if (itemStack.getTagCompound().hasKey("Slot" + j)) {
                            ItemStack aStack = ItemStack.loadItemStackFromNBT(itemStack.getTagCompound().getCompoundTag("Slot" + j));
                            if (aStack != null) {
                                toErase[j] = aStack.getItem();
                                nuErase[j] = getNumberToKeep(itemStack,j);
                                if(getMetaState(itemStack, j)) {
                                    meErase[j] = aStack.getItemDamage();
                                }else{
                                    meErase[j] = -1;
                                }
                            }
                        }
                    }
                }
                ContainerVoidpack.doErase(toErase, meErase, nuErase,((EntityPlayer) entity).inventory);
                tick = 0;
            }
            tick++;
        }
    }

    @Override
    public void addSpecialInfo(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {
        String s ="";
        for (int i = 0; i < 5; i++) {
            s+= ItemVoidpack.getNumberToKeep(itemStack,i);
            s+= "-";
            s+= ItemVoidpack.getMetaState(itemStack,i) ? "M" : "I";
            if(i!=4)
            s+= "~~";
        }

        list.add(s);

        super.addSpecialInfo(itemStack, entityPlayer, list, b);
    }

    public static boolean getState(ItemStack stack) {return NBTHelper.hasTag(stack,"state") && NBTHelper.getBoolean(stack, "state"); }

    public static void setState(boolean state,ItemStack stack) {
        NBTHelper.setBoolean(stack,"state",state);
    }


    public static boolean getMetaState(ItemStack stack, int n) { return NBTHelper.hasTag(stack,"meta"+n) && NBTHelper.getBoolean(stack, "meta"+n); }

    public static void setMetaState(boolean state,ItemStack stack,int n) {
        NBTHelper.setBoolean(stack,"meta"+n,state);
    }

    public static void toggleMetaState(ItemStack aStack,int i){
        setMetaState(!getMetaState(aStack, i),aStack,i);
    }



    public static void setNumberToKeep(ItemStack stack, int i, int n){
        NBTHelper.setInteger(stack,"number"+i,n);
    }

    public static int getNumberToKeep(ItemStack stack, int i){
            return NBTHelper.getInt(stack,"number"+i);

    }



    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if(entityPlayer.isSneaking()){
            setState(!getState(itemStack),itemStack);
            if(!world.isRemote) {
                entityPlayer.addChatMessage(new ChatComponentText(getState(itemStack)? "Activated":"Deactivated"));
            }
        }else {
            entityPlayer.openGui(MiningModifications.instance, Gui.ID.VOIDPACK.ordinal(), entityPlayer.worldObj, (int)entityPlayer.posX, (int)entityPlayer.posY, (int)entityPlayer.posZ);
        }
        return itemStack;
    }

    @Override
    public int getSizeInventory(ItemStack container) {
        return 5;
    }

}
