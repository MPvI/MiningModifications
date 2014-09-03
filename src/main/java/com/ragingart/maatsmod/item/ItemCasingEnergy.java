package com.ragingart.maatsmod.item;


import com.ragingart.maatsmod.ref.Names;
import com.ragingart.maatsmod.util.CasingHelper;

public class ItemCasingEnergy extends ItemCasing{

    public ItemCasingEnergy()
    {
        super();
        this.setUnlocalizedName(Names.Textures.Blocks.ENERGY);
        this.mPort = CasingHelper.Port.ENERGY;
    }
}
