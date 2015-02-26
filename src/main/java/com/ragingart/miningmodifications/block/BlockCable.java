package com.ragingart.miningmodifications.block;

import cofh.lib.util.helpers.EnergyHelper;
import com.ragingart.miningmodifications.api.IMultiBlockPart;
import com.ragingart.miningmodifications.generics.BlockMM;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.ref.RenderIds;
import com.ragingart.miningmodifications.tileentity.TileEntityCable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class BlockCable extends BlockMM implements ITileEntityProvider,IMultiBlockPart{

    public final float bb6 = 6.0F/16.0F;
    public final float bb10 = 10.0F/16.0F;
    public final AxisAlignedBB xMinus = AxisAlignedBB.getBoundingBox(0,bb6,bb6,bb6,bb10,bb10);
    public final AxisAlignedBB xPlus  = AxisAlignedBB.getBoundingBox(bb10,bb6,bb6,1,bb10,bb10);
    public final AxisAlignedBB zMinus = AxisAlignedBB.getBoundingBox(bb6,bb6,0,bb10,bb10,bb6);
    public final AxisAlignedBB zPlus  = AxisAlignedBB.getBoundingBox(bb6,bb6,bb10,bb10,bb10,1);
    public final AxisAlignedBB yMinus = AxisAlignedBB.getBoundingBox(bb6,0,bb6,bb10,bb6,bb10);
    public final AxisAlignedBB yPlus  = AxisAlignedBB.getBoundingBox(bb6,bb10,bb6,bb10,1,bb10);

    public final AxisAlignedBB[] connectors = {yMinus,yPlus,zMinus,zPlus,xMinus,xPlus};

    public BlockCable() {
        super(Material.circuits, Names.Blocks.CABLE);
    }

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0,0,0,1,1,1);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        TileEntity aTile = world.getTileEntity(x, y, z);
        AxisAlignedBB result = AxisAlignedBB.getBoundingBox(bb6, bb6, bb6, bb10, bb10, bb10);
        for (int i = 0; i < 6; i++) {
            if (EnergyHelper.isAdjacentEnergyConnectableFromSide(aTile, i)) {
                result = result.func_111270_a(connectors[i]);
            }
        }
        this.setBlockBounds((float) result.minX, (float) result.minY, (float) result.minZ, (float) result.maxX, (float) result.maxY, (float) result.maxZ);
    }


    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity) {
        this.setBlockBounds(0,0,0,1,1,1);
        super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
    }



    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityCable();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return RenderIds.Cable;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getID() {
        return 3;
    }
}
