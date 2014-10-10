package com.ragingart.maatsmod.ref;

import com.ragingart.maatsmod.client.gui.container.GuiCharger;
import com.ragingart.maatsmod.client.gui.container.GuiDischarger;
import com.ragingart.maatsmod.client.gui.container.GuiEnergyGen;
import com.ragingart.maatsmod.container.ContainerCharger;
import com.ragingart.maatsmod.container.ContainerDischarger;
import com.ragingart.maatsmod.container.ContainerEnergyGen;

/**
 * Created by XtraX on 07.10.2014.
 */
public class Gui {
    public static enum ID {
        CHARGER,
        DISCHARGER,
        GENERATOR
    }

    public static final Class[] container= new Class[]{
            ContainerCharger.class,
            ContainerDischarger.class,
            ContainerEnergyGen.class
    };

    public static final Class[] gui= new Class[]{
            GuiCharger.class,
            GuiDischarger.class,
            GuiEnergyGen.class
    };
}
