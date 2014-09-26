package com.ragingart.maatsmod.ref;

public class Names {
    public static final String MOD_PREFIX = Reference.MOD_ID.toLowerCase() + ":";

    public static final class NBT {

    }

    public static final class Blocks{
        public static final String CHARGER = "charger";
        public static final String CENERGY = "crenergy";
        public static final String ORE = "nepouit_ore";
        public static final String PLATFORM_BASE = "platformBase";
        public static final String PLATFORM_EXT = "platformExt";
    }
    
    public static final class Items{
        public static final String CASING ="casing";
        public static final String CASING_ENERGY ="casing_energy";
        public static final String CASING_INPUT = "casing_input";
        public static final String CASING_OUTPUT = "casing_output";
        public static final String BATTERY ="rsbattery";
        public static final String MULTITOOL = "multitool";
        public static final String SCREW_NICKEL = "screw_nickel";
        public static final String INGOT_NICKEL = "ingot_nickel";
        public static final String DUST_NICKEL = "dust_nickel";

        public static final String WEAPON_SPEAR = "weapon_spear";
    }
    
    public static final class Textures {
        public static final class Blocks{

            public static final String CASING ="casing";
            public static final String ENERGY ="casing_energy";
            public static final String INPUT = "casing_input";
            public static final String OUTPUT = "casing_output";

            public static final class Charger{

                public static final String FRONT_OFF = "charger_front_off";
                public static final String FRONT_OFF_BAT = "charger_front_off_bat";
                public static final String FRONT_ON= "charger_front_on";

                public static final String[] FRONT = new String[]{FRONT_OFF,FRONT_OFF_BAT,FRONT_ON};
            }
            
            
        }
        
        public static final class Items{
            
        }
    }
}
