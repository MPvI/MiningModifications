package com.ragingart.miningmodifications.generics;

import com.ragingart.miningmodifications.creativetab.CreativeTabMM;
import com.ragingart.miningmodifications.ref.Names;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class BlockMM extends Block
{
    public Class<? extends ItemBlockMM> myItemBlockClass;
    public String myName;


    public BlockMM(Material material,String aName, Class<? extends ItemBlockMM> aItemBlockClass)
    {
        super(material);
        this.myName = aName;
        this.myItemBlockClass = aItemBlockClass;
        this.setCreativeTab(CreativeTabMM.MM_TAB);
        this.setUnlocalizedName(myName);
        GameRegistry.registerBlock(this,myItemBlockClass,myName);
    }

    public String getName(){
        return Names.getUnwrappedUnlocalizedName(super.getUnlocalizedName());
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Names.MOD_PREFIX, getName());
    }

    public boolean rotateBlock(World worldObj, BlockPos pos, EnumFacing face) {
            if(!worldObj.isRemote) {
                TileEntity aTile = worldObj.getTileEntity(pos);
                if(aTile instanceof TileEntityMachineMM){
                    ((TileEntityMachineMM) aTile).getMachineHelper().rotatePortsDirectlyToFace(face);
                    return true;
                }else if(aTile instanceof  TileEntityMachinePP){
                    ((TileEntityMachinePP) aTile).setFacing(face);
                }
            }
            return false;
    }

    public ArrayList<ItemStack> dismantleBlock(EntityPlayer playerIn, World worldIn, BlockPos pos,IBlockState state, boolean returnDrops) {
        if(!worldIn.isRemote) {
            dropBlockAsItem(worldIn,pos,state,0);
            removedByPlayer(worldIn,pos,playerIn,false);
        }
        return null;
    }

}
