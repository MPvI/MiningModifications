package com.ragingart.maatsmod.block;


import com.ragingart.maatsmod.MaatsMod;
import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.init.ModItems;
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
    @SideOnly(Side.CLIENT)
    private IIcon front_txt_on;
    @SideOnly(Side.CLIENT)
    private IIcon front_txt_off;
    @SideOnly(Side.CLIENT)
    private IIcon front_txt_off_bat;
    @SideOnly(Side.CLIENT)
    private IIcon casing_energy;
    private TileEntityCharger mTileEntity;
    private boolean active = false;
    private boolean hasEnergyContainer = false;
    /*
    @SideOnly(Side.CLIENT)
    private IIcon casing_output;
    @SideOnly(Side.CLIENT)
    private IIcon casing_input;
    */

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
    public TileEntity createNewTileEntity(World world, int meta){
        mTileEntity= new TileEntityCharger();
        hasEnergyContainer=mTileEntity.getHasContainer();
        return mTileEntity;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {

            if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityCharger) {
                player.openGui(MaatsMod.instance, 1, world, x, y, z);
            }
            /*
            Block block = world.getBlock(x,y,z);
            if (block instanceof BlockCharger){
                 TileEntity te = world.getTileEntity(x,y,z);
                    if(te instanceof TileEntityCharger){
                        System.out.println(((TileEntityCharger) te).getEnergyStored(ForgeDirection.DOWN));
                    }
            }*/
            return true;

    }
}