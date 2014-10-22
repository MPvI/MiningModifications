package com.ragingart.maatsmod.block;

import com.ragingart.maatsmod.api.IMultiBlockPart;
import com.ragingart.maatsmod.generics.BlockMachineMM;
import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.tileentity.TileEntityMachineBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by MaaT on 22.10.2014.
 */
public class BlockMachineBlock extends BlockMachineMM implements IMultiBlockPart {
    public BlockMachineBlock(){
        super(Names.Blocks.MACHINEBLOCK);
    }

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityMachineBlock();
    }
}
