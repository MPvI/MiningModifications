package com.ragingart.miningmodifications.client.renderer.item;

import com.ragingart.miningmodifications.client.renderer.model.ModelCrank;
import com.ragingart.miningmodifications.ref.Models;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by MaaT on 19.10.2014.
 */
public class ItemRendererCrank implements IItemRenderer {
    ModelCrank model = new ModelCrank();

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
        Minecraft.getMinecraft().renderEngine.bindTexture(Models.Crank);
        switch (type){
            case EQUIPPED:
                model.render(20,0.0625F);
                break;
            case EQUIPPED_FIRST_PERSON:
                model.render(20,0.0625F);
                break;
            case INVENTORY:
                model.render(20,0.0625F);
                break;
            case ENTITY:
                model.render(20,0.0625F);
                break;
            default:
                break;
        }
        GL11.glPopMatrix();
    }
}
