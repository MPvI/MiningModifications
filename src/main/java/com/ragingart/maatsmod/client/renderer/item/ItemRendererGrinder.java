package com.ragingart.maatsmod.client.renderer.item;


import com.ragingart.maatsmod.client.renderer.model.ModelGrinder;
import com.ragingart.maatsmod.ref.Models;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by MaaT on 19.10.2014.
 */
public class ItemRendererGrinder implements IItemRenderer {

    ModelGrinder model = new ModelGrinder();

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
        GL11.glTranslatef(0,0.5F,0);
        Minecraft.getMinecraft().renderEngine.bindTexture(Models.Grinder);

        switch (type) {
            case EQUIPPED:
                model.render(0, 0.0625F);
            case EQUIPPED_FIRST_PERSON:
                model.render(0, 0.0625F);
            case INVENTORY:
                model.render(0, 0.0625F);
            case ENTITY:
                model.render(0, 0.0625F);
            default:
                break;
        }
        GL11.glPopMatrix();
    }
}
