package com.ragingart.miningmodifications.block.machines;


import com.ragingart.miningmodifications.MiningModifications;
import com.ragingart.miningmodifications.generics.BlockMachineMM;
import com.ragingart.miningmodifications.ref.Gui;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityCharger;
import com.ragingart.miningmodifications.util.CasingHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCharger extends BlockMachineMM{

    public BlockCharger()
    {
        super(Names.Blocks.CHARGER);
        this.setHardness(7.0F);
        this.setHarvestLevel("wrench", 4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iR)
    {
        mCasingHelper = new CasingHelper(iR,Names.Textures.Blocks.Charger.FRONT);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta){
        return new TileEntityCharger();
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {

            if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityCharger) {
                player.openGui(MiningModifications.instance, Gui.ID.CHARGER.ordinal(), world, x, y, z);
            }
            return true;

    }
}