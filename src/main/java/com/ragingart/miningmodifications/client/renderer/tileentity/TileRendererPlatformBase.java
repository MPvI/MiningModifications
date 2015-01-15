package com.ragingart.miningmodifications.client.renderer.tileentity;

import com.ragingart.miningmodifications.client.renderer.model.ModelPlatformBase;
import com.ragingart.miningmodifications.ref.Models;
import com.ragingart.miningmodifications.tileentity.TileEntityPlatformBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileRendererPlatformBase extends TileEntitySpecialRenderer{

    public ModelPlatformBase model= new ModelPlatformBase();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick, int p_180535_9_) {
        if(tileEntity instanceof TileEntityPlatformBase) {
            GL11.glPushMatrix();
            GL11.glTranslated(x + 0.5, y + 1, z + 0.5);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            GL11.glRotatef(180,0,0,1);
            bindTexture(Models.PlatformBase);
            model.render(0.0625F, ((TileEntityPlatformBase) tileEntity).getAnimationTimer());
            GL11.glPopMatrix();
        }
    }

}
