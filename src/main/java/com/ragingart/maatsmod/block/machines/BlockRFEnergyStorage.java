package com.ragingart.maatsmod.block.machines;


import com.ragingart.maatsmod.MaatsMod;
import com.ragingart.maatsmod.generics.BlockMachineMM;
import com.ragingart.maatsmod.ref.Gui;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.tileentity.machines.TileEntityRFEnergyStorage;
import com.ragingart.maatsmod.util.CasingHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRFEnergyStorage extends BlockMachineMM{

    public BlockRFEnergyStorage()
    {
        super(Names.Blocks.RFENERGYSTORAGE);
        this.setHardness(7.0F);
        this.setHarvestLevel("wrench", 4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iR)
    {
        mCasingHelper = new CasingHelper(iR,Names.Textures.Blocks.RFEnergyStorage.FRONT);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return new TileEntityRFEnergyStorage();
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {

            if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityRFEnergyStorage) {
                player.openGui(MaatsMod.instance, Gui.ID.RFENERGYSTORAGE.ordinal(), world, x, y, z);
            }
            return true;

    }
}