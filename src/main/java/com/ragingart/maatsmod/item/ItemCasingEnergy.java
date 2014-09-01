package com.ragingart.maatsmod.item;


import com.ragingart.maatsmod.util.IconHelper;

public class ItemCasingEnergy extends ItemCasing{

    public ItemCasingEnergy()
    {
        super();
        this.setUnlocalizedName("casing_energy");
        this.mPort = IconHelper.Port.ENERGY;
    }
}
