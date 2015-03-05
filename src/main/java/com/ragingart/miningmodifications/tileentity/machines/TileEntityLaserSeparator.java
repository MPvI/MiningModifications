package com.ragingart.miningmodifications.tileentity.machines;


import com.ragingart.miningmodifications.api.IMultiBlockPart;
import com.ragingart.miningmodifications.generics.TileEntityMachineMultiBlockMM;
import com.ragingart.miningmodifications.init.ModBlocks;
import com.ragingart.miningmodifications.init.ModItems;
import com.ragingart.miningmodifications.item.ItemBlockPecoraitOre;
import com.ragingart.miningmodifications.util.CasingHelper;
import com.ragingart.miningmodifications.util.MachineHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityLaserSeparator extends TileEntityMachineMultiBlockMM {


    static{
        int[][] mBase=new int[][]{{0,0,0,0,0},{2,3,1,3,2},{0,0,2,0,0}}; // 6 Parts;
        int[][] aMachinePart = new int[][]{{0,0,0,0,0},{0,0,2,0,0},{0,0,0,0,0}}; // 1 Part
        int[][] aConnectionPart = new int[][]{{0,0,0,0,0},{0,3,3,3,0},{0,0,0,0,0}}; // 3 Parts

        mStructure = new int[][][]{aMachinePart,aConnectionPart,mBase,aConnectionPart,aMachinePart};
        mOffsets = new int[]{2,1,2};
        mNumberOfParts = 14;
    }

    private long processTimer=0L;
    private boolean isWorkDone=true;
    private boolean readyForSeparation=false;

    public MultiBlockPort energy = null;
    public MultiBlockPort input = null;
    public MultiBlockPort output1 = null;
    public MultiBlockPort output2 = null;
    public MultiBlockPort foutput = null;


    @Override
    public void updateEntity() {
        super.updateEntity();
        if(!worldObj.isRemote && (timer==-1 || timer%50==0)) {
            if(checkStructure(worldObj,xCoord-mOffsets[0],yCoord-mOffsets[1],zCoord-mOffsets[2])) {
                process();
            }
        }
    }

    @Override
    public boolean checkStructure(World world, int x, int y, int z) {
        {
            int aNumberOfParts=0;
            int nEnergyPorts=0;
            int nItemInputs=0;
            int nItemOutputs=0;
            int nFluidOutputs=0;

            for (int i = 0; i < mStructure.length; i++) {
                for (int j = 0; j < mStructure[i].length; j++) {
                    for (int k = 0; k < mStructure[i][j].length; k++) {
                        Block aBlock = world.getBlock(x + i, y + j, z + k);
                        if(aBlock instanceof IMultiBlockPart){
                            int id = ((IMultiBlockPart) aBlock).getID();
                            if(mStructure[i][j][k]==id){
                                if(id==2){
                                    TileEntity aTile = world.getTileEntity(x+i,y+j,z+k);

                                    if(aTile instanceof TileEntityMachineBlock) {
                                        MachineHelper aHelper = ((TileEntityMachineBlock) aTile).getMachineHelper();
                                        if(aHelper.hasPort(CasingHelper.Port.ENERGY)){
                                            energy = new MultiBlockPort(CasingHelper.Port.ENERGY,x+i,y+j,z+k);
                                            nEnergyPorts++;
                                            aNumberOfParts++;
                                        }
                                        else if(aHelper.hasPort(CasingHelper.Port.INPUT)){
                                            input = new MultiBlockPort(CasingHelper.Port.INPUT,x+i,y+j,z+k);
                                            nItemInputs++;
                                            aNumberOfParts++;
                                        }
                                        else if(aHelper.hasPort(CasingHelper.Port.OUTPUT)){
                                            if(output1 == null) {
                                                output1 = new MultiBlockPort(CasingHelper.Port.OUTPUT, x + i, y + j, z + k);
                                            }else{
                                                output2 = new MultiBlockPort(CasingHelper.Port.OUTPUT, x + i, y + j, z + k);
                                            }
                                            nItemOutputs++;
                                            aNumberOfParts++;
                                        }
                                        else if(aHelper.hasPort(CasingHelper.Port.FOUTPUT)){
                                            foutput = new MultiBlockPort(CasingHelper.Port.FOUTPUT,x+i,y+j,z+k);
                                            nFluidOutputs++;
                                            aNumberOfParts++;
                                        }
                                    }


                                }else {
                                    aNumberOfParts++;
                                }
                            }
                        }
                    }
                }
            }
            return aNumberOfParts==mNumberOfParts && nItemOutputs == 2 && nEnergyPorts == 1 && nFluidOutputs == 1 && nItemInputs == 1;
        }
    }

    // 2x SiO -> Si + SiO2
    // 1x Si + O2 -> SiO2
    // 4x H20 + O2 -> 2x H2O2 + 2 coolingH2O
    public void process(){

        boolean energyBufferFilled,inputMaterialPresent,outputsHaveSpace,coolerIsReady;

        if(isWorkDone()) {
            energyBufferFilled = energy.getTile(worldObj).getMaxEnergyStored(ForgeDirection.UNKNOWN) == energy.getTile(worldObj).getEnergyStored(ForgeDirection.UNKNOWN);
            inputMaterialPresent = input.getTile(worldObj).getStackInSlot(0) != null && input.getTile(worldObj).getStackInSlot(0).getItem() instanceof ItemBlockPecoraitOre;
            outputsHaveSpace = output1.getTile(worldObj).getStackInSlot(0) == null && output2.getTile(worldObj).getStackInSlot(0) == null;
            coolerIsReady = foutput.getTile(worldObj).getFluidAmount() <= 1000;

            readyForSeparation = energyBufferFilled && inputMaterialPresent && outputsHaveSpace && coolerIsReady;
        }

        if(readyForSeparation) {
            this.setInventorySlotContents(0, input.getTile(worldObj).decrStackSize(0, 1));
            if (energy.getTile(worldObj).extractEnergy(ForgeDirection.UNKNOWN, 100000, false) == 100000) {
                processTimer = System.currentTimeMillis();
                isWorkDone = false;
                readyForSeparation = false;
            }
        }

        if(!isWorkDone()) {
            if (System.currentTimeMillis() - processTimer >= 25000) {
                this.setInventorySlotContents(0, null);
                output1.getTile(worldObj).setInventorySlotContents(0, new ItemStack(ModItems.dust_nickel));
                output2.getTile(worldObj).setInventorySlotContents(0, new ItemStack(ModItems.dust_nickel));
                foutput.getTile(worldObj).fill(ForgeDirection.UNKNOWN, new FluidStack(1, 1000), true);
                if(foutput.getTile(worldObj).getFluidAmount() == 2000){
                    foutput.getTile(worldObj).drain(ForgeDirection.UNKNOWN,2000,true);
                    worldObj.spawnEntityInWorld(new EntityItem(worldObj,xCoord,yCoord+1,zCoord,new ItemStack(ModBlocks.Ore)));
                }
                isWorkDone = true;
            } else {
                System.out.println(System.currentTimeMillis() - processTimer);
            }
        }
    }



    @Override
    public void writeToNBT(NBTTagCompound cmpd) {
        cmpd.setLong("processTimer",processTimer);
        cmpd.setBoolean("isWorkDone",isWorkDone);
        super.writeToNBT(cmpd);
    }

    @Override
    public void readFromNBT(NBTTagCompound cmpd) {
        processTimer=cmpd.getLong("processTimer");
        isWorkDone=cmpd.getBoolean("isWorkDone");
        super.readFromNBT(cmpd);
    }

    @Override
    public int[] validPorts() {
        return new int[0];
    }

    @Override
    public boolean isWorkDone() {
        return isWorkDone;
    }

}
