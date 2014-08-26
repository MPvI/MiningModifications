package com.ragingart.maatsmod.item;


import cofh.api.energy.IEnergyContainerItem;
import com.google.common.collect.Sets;
import com.ragingart.maatsmod.block.BlockCharger;
import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.generics.ItemToolMM;
import com.ragingart.maatsmod.init.ModBlocks;
import com.ragingart.maatsmod.tileentity.TileEntityCharger;
import com.ragingart.maatsmod.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Set;

public class ItemMultitool extends ItemToolMM implements IEnergyContainerItem
{
    private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{ModBlocks.Ore,ModBlocks.Charger});
    private int cap;
    private int maxIn;
    private int maxOut;


    public ItemMultitool()
    {
        super(3.0F,ToolMaterial.EMERALD,blocksEffectiveAgainst);
        this.setHarvestLevel("pickaxe",3);
        this.setHarvestLevel("wrench",4);
        this.setUnlocalizedName("multitool");
        this.cap=3000;
        this.maxIn=100;
        this.maxOut=25;
    }



    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
        return false;
    }


    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int meta, float par8, float par9, float par10)
    {
        System.out.println("rightclick on block");
        System.out.println(x + " / " + y + " / " + z + " / " + meta);

        if(world.getBlock(x,y,z) instanceof BlockMM){
        BlockMM block = (BlockMM) world.getBlock(x,y,z);
        if (block instanceof BlockCharger){
            TileEntity te = world.getTileEntity(x, y, z);
            if(te instanceof TileEntityCharger){
                System.out.println(((TileEntityCharger) te).getEnergyStored(ForgeDirection.DOWN));
            }
        }
        if(entityPlayer.isSneaking()) {
            block.onBlockWrenched(world,x,y,z);
            block.removedByPlayer(world,entityPlayer,x,y,z,false);

        }
        LogHelper.info("Energy:" + getEnergyStored(itemStack));
        }
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        System.out.println("rightclick on air");
        receiveEnergy(par1ItemStack,10,false);
        return par1ItemStack;
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

        if (container.stackTagCompound == null) {
            container.stackTagCompound = new NBTTagCompound();
        }
        int energy = container.stackTagCompound.getInteger("Energy");
        int energyReceived = Math.min(cap - energy, Math.min(this.maxIn, maxReceive));

        if (!simulate) {
            energy += energyReceived;
            container.stackTagCompound.setInteger("Energy", energy);
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
            return 0;
        }
        int energy = container.stackTagCompound.getInteger("Energy");
        int energyExtracted = Math.min(energy, Math.min(this.maxOut, maxExtract));

        if (!simulate) {
            energy -= energyExtracted;
            container.stackTagCompound.setInteger("Energy", energy);
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored(ItemStack container) {

        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
            return 0;
        }
        return container.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return cap;
    }
}
