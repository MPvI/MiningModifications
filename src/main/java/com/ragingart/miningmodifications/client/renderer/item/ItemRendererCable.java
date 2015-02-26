package com.ragingart.miningmodifications.client.renderer.item;

import com.ragingart.miningmodifications.client.renderer.model.ModelCable;
import com.ragingart.miningmodifications.ref.Models;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRendererCable implements IItemRenderer {

    private ModelCable model = new ModelCable();

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
                Minecraft.getMinecraft().renderEngine.bindTexture(Models.Cable);
                model.render(0.07F);
                GL11.glPopMatrix();
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glPushMatrix();
                Minecraft.getMinecraft().renderEngine.bindTexture(Models.Cable);
                model.render(0.07F);
                GL11.glPopMatrix();
                break;
            case INVENTORY:
                GL11.glPushMatrix();
                Minecraft.getMinecraft().renderEngine.bindTexture(Models.Cable);
                model.render(0.07F);
                GL11.glPopMatrix();
                break;
            case ENTITY:
                GL11.glPushMatrix();
                Minecraft.getMinecraft().renderEngine.bindTexture(Models.Cable);
                model.render(0.07F);
                GL11.glPopMatrix();
                break;
            default:
                break;
        }

    }
}
