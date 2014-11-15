package com.ragingart.miningmodifications.client.gui.element;

import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.ElementButton;

/**
 * Created by MaaT on 14.11.2014.
 */
public class ElementButtonLabeled extends ElementButton {

    private String label;

    public ElementButtonLabeled(GuiBase gui, int posX, int posY, String name, int sheetX, int sheetY, int hoverX, int hoverY, int disabledX, int disabledY, int sizeX, int sizeY, String texture, int n) {
        super(gui, posX, posY, name, sheetX, sheetY, hoverX, hoverY, disabledX, disabledY, sizeX, sizeY, texture);
        label=String.valueOf(n);
    }

    public void setLabel(String s){
        label = s;
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        int w = getFontRenderer().getStringWidth(label);
        int x = posX+getWidth()/2-w/2;
        int y = posY+getHeight()/2-3;
        getFontRenderer().drawString(label,x,y,16777215);
    }
}
