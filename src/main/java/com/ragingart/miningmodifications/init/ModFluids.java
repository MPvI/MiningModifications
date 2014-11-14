package com.ragingart.miningmodifications.init;

import com.ragingart.miningmodifications.ref.Names;
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
