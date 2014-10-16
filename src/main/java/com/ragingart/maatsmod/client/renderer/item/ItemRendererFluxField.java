package com.ragingart.maatsmod.client.renderer.item;

import com.ragingart.maatsmod.client.renderer.model.ModelPlatformBase;
import com.ragingart.maatsmod.ref.Models;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by MaaT on 26.09.2014.
 */
public class ItemRendererFluxField implements IItemRenderer {

    public ModelPlatformBase model = new ModelPlatformBase();

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
        switch (type){
            case EQUIPPED:

                GL11.glPushMatrix();

                Minecraft.getMinecraft().renderEngine.bindTexture(Models.FluxField);

                GL11.glRotatef(180, -1.0F, 0.0F, 0.0F);
                GL11.glScalef(0.5F, 0.5F, 0.5F);
                model.render(0.0625F,0);

                GL11.glPopMatrix();
                break;
            case EQUIPPED_FIRST_PERSON:

                GL11.glPushMatrix();

                Minecraft.getMinecraft().renderEngine.bindTexture(Models.FluxField);

                GL11.glRotatef(180, -1.0F, 0.0F, 0.0F);
                GL11.glScalef(0.5F, 0.5F, 0.5F);


                model.render(0.0625F,0);

                GL11.glPopMatrix();
                break;
            case INVENTORY:

                GL11.glPushMatrix();

                Minecraft.getMinecraft().renderEngine.bindTexture(Models.FluxField);

                GL11.glRotatef(180, -1.0F, 0.0F, 0.0F);
                GL11.glScalef(0.5F, 0.5F, 0.5F);
                model.render(0.0625F,0);

                GL11.glPopMatrix();
                break;
            case ENTITY:

                GL11.glPushMatrix();

                Minecraft.getMinecraft().renderEngine.bindTexture(Models.FluxField);

                GL11.glRotatef(180, -1.0F, 0.0F, 0.0F);
                GL11.glScalef(0.5F, 0.5F, 0.5F);

                model.render(0.0625F,0);

                GL11.glPopMatrix();
                break;
            default:
                break;
        }
    }
}
