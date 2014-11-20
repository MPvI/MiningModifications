package com.ragingart.miningmodifications.client.renderer.item;

import com.ragingart.miningmodifications.client.renderer.model.ModelMultitool;
import com.ragingart.miningmodifications.ref.Models;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by MaaT on 08.09.2014.
 */
public class ItemRendererMultitool implements IItemRenderer {

    protected ModelMultitool tool = new ModelMultitool();



    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch(type){
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

        float anim;

        try{
            anim = 16*(((EntityPlayer)data[1]).isUsingItem()?((EntityPlayer)data[1]).getItemInUseDuration():0);
        } catch (Throwable e) {
            anim = 33;
        }
        GL11.glPushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(Models.Multitool);
        switch(type){
            case EQUIPPED:
                /*
                GL11.glRotatef(180,0,0,1);
                GL11.glTranslatef(-1.7F,-0.4F,1.7F);
                GL11.glRotatef(45,0,1,0);
                GL11.glScalef(2,2,2);
                */
                tool.render(0.0625F, anim);
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glRotatef(45,0,1,0);
                GL11.glRotatef(180,1,0,0);
                GL11.glRotatef(10,0,0,-1);
                GL11.glScalef(2,2,2);
                GL11.glTranslatef(0,0,-1.0F);
                tool.render(0.0625F, anim);
                break;
            case INVENTORY:
                GL11.glRotatef(180,0,0,1);
                GL11.glTranslatef(-1,0,0);
                tool.render(0.0625F, anim);
                break;
            case ENTITY:
                tool.render(0.0625F, anim);
                break;
            default:
                break;
        }
        GL11.glPopMatrix();
    }
}
