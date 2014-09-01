package com.ragingart.maatsmod.item;


import com.ragingart.maatsmod.util.CasingHelper;

public class ItemCasingEnergy extends ItemCasing{

    public ItemCasingEnergy()
    {
        super();
        this.setUnlocalizedName("casing_energy");
        this.mPort = CasingHelper.Port.ENERGY;
    }
}
