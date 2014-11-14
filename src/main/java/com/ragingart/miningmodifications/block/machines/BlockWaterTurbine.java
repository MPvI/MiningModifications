package com.ragingart.miningmodifications.block.machines;


import com.ragingart.miningmodifications.MiningModifications;
import com.ragingart.miningmodifications.generics.BlockMachineMM;
import com.ragingart.miningmodifications.ref.Gui;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityWaterTurbine;
import com.ragingart.miningmodifications.util.CasingHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockWaterTurbine extends BlockMachineMM{

    public BlockWaterTurbine()
    {
        super(Names.Blocks.WATERTURBINE);
        this.setHardness(7.0F);
        this.setHarvestLevel("wrench", 4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iR)
    {
        mCasingHelper = new CasingHelper(iR,Names.Textures.Blocks.WaterTurbine.FRONT);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){return new TileEntityWaterTurbine();}

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
            if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityWaterTurbine) {
                player.openGui(MiningModifications.instance, Gui.ID.WATERTURBINE.ordinal(), world, x, y, z);
            }
            return true;
    }

    @Override
    public void breakBlock(World wobj, int x, int y, int z, Block aBlock, int meta){
        super.breakBlock(wobj, x, y, z , aBlock, meta);
        if(wobj.getBlock(x+ ForgeDirection.DOWN.offsetX, y+ ForgeDirection.DOWN.offsetY, z+ForgeDirection.DOWN.offsetZ).getMaterial() == Material.water) {
            wobj.setBlockMetadataWithNotify(x + ForgeDirection.DOWN.offsetX, y + ForgeDirection.DOWN.offsetY, z + ForgeDirection.DOWN.offsetZ,8, 0);
            wobj.setBlock(x, y, z, Blocks.flowing_water);
            wobj.setBlockMetadataWithNotify(x, y , z ,8, 0);
        }
    }
}