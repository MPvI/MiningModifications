package com.ragingart.maatsmod.client.renderer.tileentity;

import com.ragingart.maatsmod.client.renderer.model.ModelPlatformBase;
import com.ragingart.maatsmod.ref.Models;
import com.ragingart.maatsmod.tileentity.TileEntityPlatformBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * Created by MaaT on 26.09.2014.
 */
public class TileRendererPlatformBase extends TileEntitySpecialRenderer {

    public ModelPlatformBase model= new ModelPlatformBase();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        if(tileEntity instanceof TileEntityPlatformBase) {
            GL11.glPushMatrix();
            GL11.glTranslated(x + 0.5, y + 1, z + 0.5);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            GL11.glRotatef(180,0,0,1);
            this.bindTexture(Models.PlatformBase);
            model.render(0.0625F, ((TileEntityPlatformBase) tileEntity).getAnimationTimer());
            GL11.glPopMatrix();
        }
    }

}
