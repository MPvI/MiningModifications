package com.ragingart.maatsmod.client.renderer.item;

import com.ragingart.maatsmod.client.renderer.model.ModelSpear;
import com.ragingart.maatsmod.ref.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by MaaT on 04.09.2014.
 */
public class ItemRendererSpear implements IItemRenderer {

    protected ModelSpear modelSpear;

    public ItemRendererSpear(){
        modelSpear=new ModelSpear();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch (type){
            case EQUIPPED: return true;
            case EQUIPPED_FIRST_PERSON: return true;
            default: return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch (type){
            case EQUIPPED:{
                GL11.glPushMatrix();

                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID.toLowerCase(),"textures/models/weapon_spear.png"));

                GL11.glScalef(3.0F,3.0F,3.0F);
                GL11.glRotatef(40.0F,0.0F,-1.0F,0.0F);
                GL11.glRotatef(10.0F,0.0F,0.0F,1.0F);
                GL11.glTranslatef(0.2F,0.0F,0.0F);
                modelSpear.render((Entity)data[1],0.0F,0.0F,0.0F,0.0F,0.0F,0.0625F);

                GL11.glPopMatrix();
                break;
            }
            case EQUIPPED_FIRST_PERSON: {

                GL11.glPushMatrix();

                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID.toLowerCase(),"textures/models/weapon_spear.png"));

                GL11.glScalef(3.0F,3.0F,3.0F);
                GL11.glTranslatef(0.5F,0.4F,0.2F);
                GL11.glRotatef(120.0F,0.0F,-1.0F,0.0F);
                modelSpear.render((Entity)data[1],0.0F,0.0F,0.0F,0.0F,0.0F,0.0625F);

                GL11.glPopMatrix();
                break;
            }

            default:
                break;
        }
    }
}

/*

    Axis X = playlookdir
    Axis Z = lefttorightarm
    Axis Y = headtofeat

 */
