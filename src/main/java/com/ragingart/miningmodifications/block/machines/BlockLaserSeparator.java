package com.ragingart.miningmodifications.block.machines;

import com.ragingart.miningmodifications.api.IMultiBlockPart;
import com.ragingart.miningmodifications.generics.BlockMachineMM;
import com.ragingart.miningmodifications.generics.TileEntityMachineMultiBlockMM;
import com.ragingart.miningmodifications.proxy.ClientProxy;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.ref.RenderIds;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityLaserSeparator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLaserSeparator extends BlockMachineMM  implements IMultiBlockPart {
    public BlockLaserSeparator() {
        super(Names.Blocks.LS);
        this.setBlockBounds(0,0,0,1,1,1);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        return ((TileEntityMachineMultiBlockMM)world.getTileEntity(x,y,z)).checkStructure(world,x-2,y-1,z-2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return super.getIcon(side, meta);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityLaserSeparator();
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        if(world.getTileEntity(x,y,z) instanceof TileEntityLaserSeparator ){
            TileEntityLaserSeparator aTile = (TileEntityLaserSeparator) world.getTileEntity(x,y,z);
            if(aTile.isCompleteStructure()){
                this.setBlockBounds(-1,0,-1,2,1,2);
            }else{
                this.setBlockBounds(0,0,0,1,1,1);
            }
        }
    }

    @Override
    public boolean canRenderInPass(int pass) {
        ClientProxy.renderPass = pass;
        return true;
    }

    @Override
    public int getRenderType()
    {
        return RenderIds.LSC;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }



    @Override
    public int getID() {
        return 1;
    }
}
