package com.ragingart.maatsmod.client.renderer.tileentity;

import com.ragingart.maatsmod.client.renderer.model.ModelFluxField;
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
    public ModelFluxField field = new ModelFluxField();


    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        if(tileEntity instanceof TileEntityPlatformBase) {
            GL11.glPushMatrix();
            GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.bindTexture(Models.PlatformBase);
            model.render(0.0625F, ((TileEntityPlatformBase) tileEntity).getAnimationTimer());
            /*if(((TileEntityPlatformBase) tileEntity).getExtractedState()){
                GL11.glScalef(-0.5F + ConfigHandler.maxPlatformRadius*2F, 1F, -0.5F +ConfigHandler.maxPlatformRadius*2F);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                this.bindTexture(Models.FluxField);
                field.render();
            }*/
            GL11.glPopMatrix();
        }
    }

}
