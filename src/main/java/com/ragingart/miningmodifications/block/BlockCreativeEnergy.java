package com.ragingart.miningmodifications.block;

import com.ragingart.miningmodifications.generics.BlockMM;
import com.ragingart.miningmodifications.itemblock.machines.ItemBlockCreativeEnergy;
import com.ragingart.miningmodifications.tileentity.TileEntityCreativeEnergy;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCreativeEnergy extends BlockMM implements ITileEntityProvider{


    public BlockCreativeEnergy(){
        super(Material.rock,"creativeenergy",ItemBlockCreativeEnergy.class);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCreativeEnergy();
    }

}
