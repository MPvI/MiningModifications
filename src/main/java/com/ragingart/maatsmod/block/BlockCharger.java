package com.ragingart.maatsmod.block;


import com.ragingart.maatsmod.MaatsMod;
import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.tileentity.TileEntityCharger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCharger extends BlockMM implements ITileEntityProvider{


       public BlockCharger()
       {
           super(Names.Blocks.CHARGER);
           this.setHardness(7.0F);
           this.setHarvestLevel("wrench", 4);
       }


    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side,int meta)
    {
        switch(side) {
            case 0: return blockIcons[1];
            case 4: {
                switch (meta) {
                    case 0: return blockIcons[2];
                    case 1: return blockIcons[3];
                    case 2: return blockIcons[4];
                }
            }
            default: return blockIcons[0];
        }
    }


    /*
    sides
    0 bot
    1 top
    2 right
    3 left
    4 back
    5 front
     */

    /*
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world,int x,int y,int z,int side){

    }*/

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iR)
    {
        blockIcons = new IIcon[6];
        blockIcons[0] = iR.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.CASING);
        blockIcons[1] = iR.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.ENERGY);
        blockIcons[2] = iR.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.Charger.FRONT_OFF);
        blockIcons[3] = iR.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.Charger.FRONT_OFF_BAT);
        blockIcons[4] = iR.registerIcon(Names.MOD_PREFIX+Names.Textures.Blocks.Charger.FRONT_ON);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return new TileEntityCharger();
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {

            if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityCharger) {
                player.openGui(MaatsMod.instance, 1, world, x, y, z);
            }
            return true;

    }
}