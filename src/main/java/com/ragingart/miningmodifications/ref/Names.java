package com.ragingart.miningmodifications.ref;


public class Names {
    public static final String MOD_PREFIX = Reference.MOD_ID.toLowerCase() + ":";
    public static final String INFO_PREFIX = "description.";


    public static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    public static final class Blocks{
        // Creative
        public static final String CENERGY = "crenergy";
        // Worldgen
        public static final String ORE = "pecorait_ore";
        // Utility
        public static final String PLATFORM_BASE = "platformBase";
        public static final String FLUXFIELD = "fluxfield";
        // Energy
        public static final String CHARGER = "charger";
        public static final String DISCHARGER = "discharger";
        public static final String WATERTURBINE = "waterturbine";
        public static final String RFENERGYSTORAGE = "rfenergystorage";
        public static final String CABLE = "cable";
        // Machines
        public static final String LS = "laserseparator";
        // Handmachines
        public static final String CRANK = "crank";
        public static final String COMPACTOR = "compactor";
        public static final String SHARPENINGWHEEL = "sharpeningwheel";
        public static final String GRINDER = "grinder";
        public static final String MACHINEBLOCK = "machineblock";
    }
    
    public static final class Items{
        public static final String CASING[] = new String[]{
            "casing",
            "casing_energy",
            "casing_input",
            "casing_output",
            "casing_finput",
            "casing_foutput"
        };
        public static final String BATTERY ="rsbattery";
        public static final String MULTITOOL = "multitool";
        public static final String SCREW_NICKEL = "screw_nickel";
        public static final String INGOT_NICKEL = "ingot_nickel";
        public static final String DUST_NICKEL = "dust_nickel";
        public static final String PLATE_IRON = "plate_iron";

        public static final String WEAPON_SPEAR = "weapon_spear";
        public static final String VOIDPACK = "voidpack";
    }
    
    public static final class Textures {
        public static final class Blocks{

            public static final class Charger{

                public static final String FRONT_OFF = "charger_front_off";
                public static final String FRONT_OFF_BAT = "charger_front_off_bat";
                public static final String FRONT_ON= "charger_front_on";

                public static final String[] FRONT = new String[]{FRONT_OFF,FRONT_OFF_BAT,FRONT_ON};
            }
            public static final class Discharger {

                public static final String FRONT_OFF = "discharger_front_off";
                public static final String FRONT_OFF_BAT = "discharger_front_off_bat";
                public static final String FRONT_ON= "discharger_front_on";

                public static final String[] FRONT = new String[]{FRONT_OFF, FRONT_OFF_BAT,FRONT_ON};
            }
            public static final class WaterTurbine {

                public static final String FRONT_OFF = "waterturbine_front_off";
                public static final String FRONT_OFF_WATER = "waterturbine_front_off_bat";
                public static final String FRONT_ON = "waterturbine_front_on";

                public static final String[] FRONT = new String[]{FRONT_OFF,FRONT_OFF_WATER,FRONT_ON};
            }
            public static final class RFEnergyStorage {

                public static final String FRONT_EMPTY = "rfcell_front_off";
                public static final String FRONT_WORKING = "rfcell_front_working";
                public static final String FRONT_FULL = "rfcell_front_full";

                public static final String[] FRONT = new String[]{FRONT_EMPTY,FRONT_WORKING,FRONT_FULL};
            }
        }
    }

    public static final class Fluids{
        public static final String HIGHHELDWATER = "highheldwater";
    }
}
