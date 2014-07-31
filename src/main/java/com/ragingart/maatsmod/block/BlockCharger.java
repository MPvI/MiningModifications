package com.ragingart.maatsmod.block;


import cofh.api.energy.IEnergyHandler;
import com.ragingart.maatsmod.generics.BlockContainerMM;
import com.ragingart.maatsmod.generics.BlockMM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCharger extends BlockContainerMM implements IEnergyHandler{
    @SideOnly(Side.CLIENT)
    private IIcon front_txt_on;
    @SideOnly(Side.CLIENT)
    private IIcon front_txt_off;
    @SideOnly(Side.CLIENT)
    private IIcon front_txt_off_bat;
    @SideOnly(Side.CLIENT)
    private IIcon casing_energy;
    /*
    @SideOnly(Side.CLIENT)
    private IIcon casing_output;
    @SideOnly(Side.CLIENT)
    private IIcon casing_input;
    */
    private boolean active = true;
    private boolean hasEnergyContainer = true;

       public BlockCharger()
       {
           super("charger");
           this.setHardness(7.0F);
           this.setHarvestLevel("wrench",4);
       }


    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side,int meta)
    {
        //if(side==2) return casing_output;
        //if(side==3) return casing_input;
        if(side==5) return (active && hasEnergyContainer) ? front_txt_on : (!active && hasEnergyContainer) ? front_txt_off_bat : front_txt_off;
        if(side==0) return casing_energy;
        return blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon("maatsmod:casing");
        casing_energy = iconRegister.registerIcon("maatsmod:casing_energy");
        //casing_input = iconRegister.registerIcon("maatsmod:casing_input");
        //casing_output = iconRegister.registerIcon("maatsmod:casing_output");
        front_txt_on = iconRegister.registerIcon("maatsmod:charger_front_on");
        front_txt_off = iconRegister.registerIcon("maatsmod:charger_front_off");
        front_txt_off_bat = iconRegister.registerIcon("maatsmod:charger_front_off_bat");
    }



    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return 0;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return 0;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return from==ForgeDirection.DOWN ? true : false;
    }
}