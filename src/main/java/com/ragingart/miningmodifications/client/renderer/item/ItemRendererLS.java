package com.ragingart.miningmodifications.client.renderer.item;

import com.ragingart.miningmodifications.client.renderer.model.ModelLaserSeparator;
import com.ragingart.miningmodifications.ref.Models;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRendererLS implements IItemRenderer {
    public ModelLaserSeparator model = new ModelLaserSeparator();

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

                Minecraft.getMinecraft().renderEngine.bindTexture(Models.LaserSeparator);

                GL11.glRotatef(180, -1.0F, 0.0F, 0.0F);
                model.renderBase(0.0625F);

                GL11.glPopMatrix();
                break;
            case EQUIPPED_FIRST_PERSON:

                GL11.glPushMatrix();

                Minecraft.getMinecraft().renderEngine.bindTexture(Models.LaserSeparator);

                GL11.glRotatef(180, -1.0F, 0.0F, 0.0F);
                model.renderBase(0.0625F);

                GL11.glPopMatrix();
                break;
            case INVENTORY:

                GL11.glPushMatrix();

                Minecraft.getMinecraft().renderEngine.bindTexture(Models.LaserSeparator);

                GL11.glRotatef(180, -1.0F, 0.0F, 0.0F);
                model.renderBase(0.0625F);

                GL11.glPopMatrix();
                break;
            case ENTITY:

                GL11.glPushMatrix();

                Minecraft.getMinecraft().renderEngine.bindTexture(Models.LaserSeparator);

                GL11.glRotatef(180, -1.0F, 0.0F, 0.0F);

                model.renderBase(0.0625F);

                GL11.glPopMatrix();
                break;
            default:
                break;
        }
    }
}
