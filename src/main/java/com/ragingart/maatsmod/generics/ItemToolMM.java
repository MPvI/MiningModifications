package com.ragingart.maatsmod.generics;

import cofh.api.energy.IEnergyContainerItem;
import com.ragingart.maatsmod.creativetab.CreativeTabMM;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.util.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.Set;

public class ItemToolMM extends ItemTool implements IEnergyContainerItem
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
        this.maxOut=25;
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

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
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
}
