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

                modelSpear.render((Entity)data[1],0.0F,0.0F,0.0F,0.0F,0.0F,0.0625F);

                GL11.glPopMatrix();
            }
            case EQUIPPED_FIRST_PERSON: {

                GL11.glPushMatrix();

                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID.toLowerCase(),"textures/models/weapon_spear.png"));

                modelSpear.render((Entity)data[1],0.0F,0.0F,0.0F,0.0F,0.0F,0.0625F);

                GL11.glPopMatrix();

            }
            default:
                break;
        }
    }
}
