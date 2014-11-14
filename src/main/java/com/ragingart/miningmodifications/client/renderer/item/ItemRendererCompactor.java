package com.ragingart.miningmodifications.client.renderer.item;

import com.ragingart.miningmodifications.client.renderer.model.ModelCompactor;
import com.ragingart.miningmodifications.ref.Models;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by MaaT on 19.10.2014.
 */
public class ItemRendererCompactor implements IItemRenderer {

    ModelCompactor model = new ModelCompactor();

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
        switch (type) {
            case EQUIPPED:
                GL11.glPushMatrix();
                GL11.glScalef(0.5F,0.5F,0.5F);
                GL11.glRotatef(180,1,0,0);
                Minecraft.getMinecraft().renderEngine.bindTexture(Models.Compactor);
                model.render(0, 0.0625F);
                GL11.glPopMatrix();
            case EQUIPPED_FIRST_PERSON:
                GL11.glPushMatrix();
                GL11.glScalef(0.5F,0.5F,0.5F);
                GL11.glRotatef(180,1,0,0);
                Minecraft.getMinecraft().renderEngine.bindTexture(Models.Compactor);
                model.render(0, 0.0625F);
                GL11.glPopMatrix();
            case INVENTORY:
                GL11.glPushMatrix();
                GL11.glScalef(0.5F,0.5F,0.5F);
                GL11.glRotatef(180,1,0,0);
                Minecraft.getMinecraft().renderEngine.bindTexture(Models.Compactor);
                model.render(0, 0.0625F);
                GL11.glPopMatrix();
            case ENTITY:
                GL11.glPushMatrix();
                GL11.glScalef(0.5F,0.5F,0.5F);
                GL11.glRotatef(180,1,0,0);
                Minecraft.getMinecraft().renderEngine.bindTexture(Models.Compactor);
                model.render(0, 0.0625F);
                GL11.glPopMatrix();
            default:
                break;
        }
    }
}
