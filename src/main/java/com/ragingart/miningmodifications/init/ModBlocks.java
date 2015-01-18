package com.ragingart.miningmodifications.init;

import com.ragingart.miningmodifications.block.*;
import com.ragingart.miningmodifications.block.handmachines.BlockCompactor;
import com.ragingart.miningmodifications.block.handmachines.BlockCrank;
import com.ragingart.miningmodifications.block.handmachines.BlockGrinder;
import com.ragingart.miningmodifications.block.handmachines.BlockSharpeningWheel;
import com.ragingart.miningmodifications.block.machines.*;
import com.ragingart.miningmodifications.generics.BlockMM;

public class ModBlocks {
    public static BlockMM Ore;
    public static BlockMM Charger;
    public static BlockMM Energy ;
    public static BlockMM PlatformBase;
    public static BlockMM FluxField;
    public static BlockMM WaterTurbine;
    public static BlockMM RFEnergyStorage;
    public static BlockMM Discharger;
    public static BlockMM Cable;
    public static BlockMM LS;
    public static BlockMM MachineBlock;
    public static BlockMM Crank;
    public static BlockMM Compactor;
    public static BlockMM SharpeningWheel;
    public static BlockMM Grinder;

    public static void init(){
        Ore = new BlockPecoraitOre();
        Charger = new BlockCharger();
        Energy = new BlockCreativeEnergy();
        PlatformBase = new BlockPlatformBase();
        FluxField = new BlockFluxField();
        WaterTurbine = new BlockWaterTurbine();
        RFEnergyStorage = new BlockRFEnergyStorage();
        Discharger = new BlockDischarger();
        Cable = new BlockCable();
        LS = new BlockLaserSeperator();
        MachineBlock = new BlockMachineBlock();
        Crank = new BlockCrank();
        Compactor = new BlockCompactor();
        SharpeningWheel = new BlockSharpeningWheel();
        Grinder = new BlockGrinder();
    }
}
