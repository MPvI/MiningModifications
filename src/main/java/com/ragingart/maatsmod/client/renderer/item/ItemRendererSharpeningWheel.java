package com.ragingart.maatsmod.client.renderer.item;

import com.ragingart.maatsmod.client.renderer.model.ModelSharpeningWheel;
import com.ragingart.maatsmod.ref.Models;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by MaaT on 20.10.2014.
 */
public class ItemRendererSharpeningWheel implements IItemRenderer {
    ModelSharpeningWheel model = new ModelSharpeningWheel();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch (type){
            case EQUIPPED:
                return true;
            case EQUIPPED_FIRST_PERSON:
                return true;
            case INVENTORY:
                return true;
            case ENTITY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        GL11.glPushMatrix();
        GL11.glScalef(0.5F,0.5F,0.5F);
        GL11.glRotatef(180,1,0,0);
        Minecraft.getMinecraft().renderEngine.bindTexture(Models.SharpeningWheel);
        switch (type) {
            case EQUIPPED:
                GL11.glTranslatef(1,-0.5F,-1);
                model.render(0,0.0625F);
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glTranslatef(1,-0.5F,-1);
                model.render(0,0.0625F);
                break;
            case INVENTORY:
                GL11.glTranslatef(0,0.8F,0);
                model.render(0,0.0625F);
                break;
            case ENTITY:
                model.render(0,0.0625F);
                break;
            default:
                break;
        }
        GL11.glPopMatrix();
    }

}
