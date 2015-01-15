package com.ragingart.miningmodifications.client.renderer.tileentity;

import com.ragingart.miningmodifications.client.renderer.model.ModelSharpeningWheel;
import com.ragingart.miningmodifications.ref.Models;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntitySharpeningWheel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileRendererSharpeningWheel extends TileEntitySpecialRenderer{

    ModelSharpeningWheel model = new ModelSharpeningWheel();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick, int p_180535_9_) {
        if(tileEntity instanceof TileEntitySharpeningWheel) {
            GL11.glPushMatrix();
            GL11.glTranslated(x + 0.5, y + 0.0625, z + 0.5);
            GL11.glScalef(0.5F,0.5F,0.5F);
            GL11.glRotatef(180, 1, 0, 0);
            bindTexture(Models.SharpeningWheel);
            model.render(((TileEntitySharpeningWheel) tileEntity).getAnimTimer(), 0.0625F);
            GL11.glPopMatrix();
        }
    }
}
