package com.ragingart.miningmodifications.generics;

import cofh.api.energy.IEnergyContainerItem;
import com.ragingart.miningmodifications.creativetab.CreativeTabMM;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class ItemToolMM extends ItemTool implements IEnergyContainerItem
{
    private int cap;
    private int maxIn;
    private int maxOut;

    public ItemToolMM(String name,float dmg){
        this(dmg,ToolMaterial.EMERALD,null);
        setUnlocalizedName(name);
    }

    public ItemToolMM(float dmgVsEntity, Item.ToolMaterial  material, Set blocksEffectiveAgainst)
    {
        super(dmgVsEntity,material,blocksEffectiveAgainst);
        this.setCreativeTab(CreativeTabMM.MM_TAB);
        this.cap=30000;
        this.maxIn=500;
        this.maxOut=500;
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Names.MOD_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Names.MOD_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }


    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    /* IEnergyContainerItem */
    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        int energy = NBTHelper.getInt(container, "Energy");
        int energyReceived = Math.min(cap - energy, Math.min(this.maxIn, maxReceive));

        if (!simulate) {
            energy += energyReceived;
            NBTHelper.setInteger(container,"Energy",energy);
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        int energy = NBTHelper.getInt(container,"Energy");
        int energyExtracted = Math.min(energy, Math.min(this.maxOut, maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
            NBTHelper.setInteger(container,"Energy",energy);
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return NBTHelper.getInt(container, "Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return cap;
    }

    public void addSpecialInfo(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b){
        String info = StatCollector.translateToLocal(Names.INFO_PREFIX + Names.getUnwrappedUnlocalizedName(getUnlocalizedName()));
        Collections.addAll(list, info.split("%"));
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean b) {
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            list.add(EnumChatFormatting.WHITE + "Release " + EnumChatFormatting.RED + "Shift" + EnumChatFormatting.WHITE + " for less Information");
            list.add(EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "Info:");
            addSpecialInfo(itemStack,entityPlayer,list,b);
        }else{
            list.add(EnumChatFormatting.WHITE + "Hold " + EnumChatFormatting.GREEN + "Shift" + EnumChatFormatting.WHITE + " for more Information");
            list.add(EnumChatFormatting.GREEN + "" + EnumChatFormatting.ITALIC + "Energy:");
            list.add("   "+EnumChatFormatting.GREEN+getEnergyStored(itemStack)+EnumChatFormatting.GRAY+" / "+EnumChatFormatting.RED+getMaxEnergyStored(itemStack));
        }

    }
}
