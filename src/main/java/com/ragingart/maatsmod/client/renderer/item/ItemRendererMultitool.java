package com.ragingart.maatsmod.client.renderer.item;

import com.ragingart.maatsmod.client.renderer.model.ModelMultitool;
import com.ragingart.maatsmod.ref.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by MaaT on 08.09.2014.
 */
public class ItemRendererMultitool implements IItemRenderer {

    protected ModelMultitool tool;

    public ItemRendererMultitool(){
        tool = new ModelMultitool();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch(type){
            case EQUIPPED:
                return true;
            case EQUIPPED_FIRST_PERSON:
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
        switch(type){
            case EQUIPPED:
                GL11.glPushMatrix();

                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID.toLowerCase(),"textures/models/multitool.png"));

                GL11.glScalef(1.8F,1.8F,1.8F);
                GL11.glRotatef(135.0F,0.0F,1.0F,0.0F);
                GL11.glRotatef(180.0F,1.0F,0.0F,0.0F);
                GL11.glRotatef(20.0F,0.0F,0.0F,-1.0F);
                GL11.glTranslatef(0.5F,-0.5F,0.0F);
                tool.render((Entity)data[1],0.0F,0.0F,0.0F,0.0F,0.0F,0.0625F);

                GL11.glPopMatrix();
                break;
            case EQUIPPED_FIRST_PERSON:

                break;
            default:
                break;
        }
    }
}
