package com.ragingart.miningmodifications.util;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

/**
 * Created by MaaT on 14.03.2015.
 */
public class RenderHelper {

    public static void renderItemstack(World world, ItemStack stack){
        GL11.glPushMatrix();
        GL11.glTranslatef(0, -0.4F, 0);
        GL11.glScalef(1.9F, 1.9F, 1.9F);
        EntityItem aEntity = new EntityItem(world,0,0,0,stack);
        aEntity.hoverStart=0.0F;
        RenderManager.instance.renderEntityWithPosYaw(aEntity, 0, 0, 0, 0, 0);
        GL11.glPopMatrix();
    }
}
