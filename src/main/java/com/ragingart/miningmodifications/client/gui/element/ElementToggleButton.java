package com.ragingart.miningmodifications.client.gui.element;

import cofh.lib.gui.GuiBase;
import cofh.lib.gui.element.ElementButton;
import cofh.lib.render.RenderHelper;

/**
 * Created by MaaT on 14.11.2014.
 */
public class ElementToggleButton extends ElementButton {

    int sheetX;
    int sheetY;
    int hoverX;
    int hoverY;
    int disabledX = 0;
    int disabledY = 0;
    boolean state;

    public ElementToggleButton(GuiBase gui, int posX, int posY, String name, int sheetX, int sheetY, int hoverX, int hoverY, int disabledX, int disabledY, int sizeX, int sizeY, String texture, boolean b) {
        super(gui, posX, posY, name, sheetX, sheetY, hoverX, hoverY, disabledX, disabledY, sizeX, sizeY, texture);
        this.sheetX = sheetX;
        this.sheetY = sheetY;
        this.hoverX = hoverX;
        this.hoverY = hoverY;
        this.disabledX = disabledX;
        this.disabledY = disabledY;
        setState(b);
    }

    @Override
    public boolean onMousePressed(int x, int y, int mouseButton) {
        gui.handleElementButtonClick(getName(), mouseButton);
        return true;
    }


    public boolean getState(){
        return state;
    }

    public void setState(boolean b){
        state = b;
    }

    public void toggleButton(){
        setState(!getState());
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float gameTicks) {

        RenderHelper.bindTexture(texture);
        if(!isEnabled()){
            drawTexturedModalRect(posX, posY, disabledX, disabledY+15, sizeX, sizeY);
        }else if(intersectsWith(mouseX, mouseY)) {
            drawTexturedModalRect(posX, posY, hoverX, hoverY, sizeX, sizeY);
        }else if(getState()){
            drawTexturedModalRect(posX, posY, sheetX, sheetY, sizeX, sizeY);
        }else{
            drawTexturedModalRect(posX, posY, disabledX, disabledY, sizeX, sizeY);
        }
    }
}
