package com.ragingart.miningmodifications.block;

import com.ragingart.miningmodifications.api.IMultiBlockPart;
import com.ragingart.miningmodifications.generics.BlockMachineMM;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.tileentity.TileEntityMachineBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
