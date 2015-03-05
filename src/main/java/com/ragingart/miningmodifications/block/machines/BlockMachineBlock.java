package com.ragingart.miningmodifications.block.machines;

import com.ragingart.miningmodifications.MiningModifications;
import com.ragingart.miningmodifications.api.IMultiBlockPart;
import com.ragingart.miningmodifications.generics.BlockMachineMM;
import com.ragingart.miningmodifications.ref.Gui;
import com.ragingart.miningmodifications.ref.Names;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityMachineBlock;
import net.minecraft.entity.player.EntityPlayer;
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


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileEntityMachineBlock) {
            player.openGui(MiningModifications.instance, Gui.ID.MBLOCK.ordinal(), world, x, y, z);
        }
        return true;
    }
}
