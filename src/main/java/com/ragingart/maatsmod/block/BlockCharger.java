package com.ragingart.maatsmod.block;


import com.ragingart.maatsmod.MaatsMod;
import com.ragingart.maatsmod.generics.BlockMM;
import com.ragingart.maatsmod.tileentity.TileEntityCharger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCharger extends BlockMM implements ITileEntityProvider{


    @SideOnly(Side.CLIENT)
    private IIcon[][] blockIcons = new IIcon[6][3];


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
        return blockIcons[0][0];
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
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world,int x,int y,int z,int side){
        if(side==5) {
            if(world.getTileEntity(x,y,z) instanceof TileEntityCharger){
               TileEntityCharger te = (TileEntityCharger) world.getTileEntity(x,y,z);
               if(te.getHasContainer()){
                   if(te.isActive()){
                       return blockIcons[4][2];
                   }
                   else return blockIcons[4][1];
               }
               else return blockIcons[4][0];
            }
        }
        if(side==0) return blockIcons[1][0];
        return blockIcons[0][0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcons[0][0]= iconRegister.registerIcon("maatsmod:casing");
        blockIcons[1][0] = iconRegister.registerIcon("maatsmod:casing_energy");
        //casing_input = iconRegister.registerIcon("maatsmod:casing_input");
        //casing_output = iconRegister.registerIcon("maatsmod:casing_output");
        blockIcons[4][2] = iconRegister.registerIcon("maatsmod:charger_front_on");
        blockIcons[4][0] = iconRegister.registerIcon("maatsmod:charger_front_off");
        blockIcons[4][1] = iconRegister.registerIcon("maatsmod:charger_front_off_bat");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return new TileEntityCharger();
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {

            if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityCharger) {
                System.out.println(((TileEntityCharger) world.getTileEntity(x, y, z)).getEnergyStored(ForgeDirection.DOWN));
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