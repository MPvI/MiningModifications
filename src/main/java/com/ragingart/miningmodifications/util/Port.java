package com.ragingart.miningmodifications.util;

import com.ragingart.miningmodifications.ref.Component;
import net.minecraft.item.ItemStack;

public enum Port {
        BLANK,
        ENERGY,
        INPUT,
        OUTPUT,
        FINPUT,
        FOUTPUT;

        public static ItemStack getItemFromPort(Port port){
            return getItemFromPort(port.ordinal());
        }

        public static ItemStack getItemFromPort(int port){
            switch (port){
                case 1:
                    return Component.CASING_ENERGY(1);
                case 2:
                    return Component.CASING_INPUT(1);
                case 3:
                    return Component.CASING_OUTPUT(1);
                case 4:
                    return Component.CASING_FINPUT(1);
                case 5:
                    return Component.CASING_FOUTPUT(1);
                default:
                    return Component.CASING(1);
            }
        }
    }


   /*
    sides
    0 bot
    1 top
    2 right
    3 left
    4 back
    5 front
     */