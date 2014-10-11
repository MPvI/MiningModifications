package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.ref.Names;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Created by XtraX on 11.10.2014.
 */
public class ModFluids {
    public static Fluid afluid = new Fluid(Names.Fluids.HIGHHELDWATER);

    public static void init() {
        FluidRegistry.registerFluid(afluid);
    }
}
