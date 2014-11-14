package com.ragingart.miningmodifications.generics;

import cofh.lib.gui.GuiBase;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/**
 * Created by MaaT on 10.10.2014.
 */
public abstract class GuiMM extends GuiBase {

    public GuiMM(Container p_i1072_1_) {
        super(p_i1072_1_);
    }

    protected GuiMM(Container container, ResourceLocation texture) {
        super(container, texture);
    }
}
