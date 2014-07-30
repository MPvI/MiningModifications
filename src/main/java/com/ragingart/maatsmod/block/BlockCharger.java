package com.ragingart.maatsmod.block;


import com.ragingart.maatsmod.generics.BlockMM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCharger extends BlockMM implements ITileEntityProvider{
    @SideOnly(Side.CLIENT)
    private IIcon front_txt_on;
    @SideOnly(Side.CLIENT)
    private IIcon front_txt_off;


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
        if(side==1) return front_txt_on;
        if(side==2) return front_txt_off;
        return blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon("maatsmod:casing");
        front_txt_on = iconRegister.registerIcon("maatsmod:charger_front_on");
        front_txt_off = iconRegister.registerIcon("maatsmod:charger_front_off");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return null;
    }
}