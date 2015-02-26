package com.ragingart.miningmodifications.client.renderer.tileentity;

import com.ragingart.miningmodifications.client.renderer.model.ModelCable;
import com.ragingart.miningmodifications.ref.Models;
import com.ragingart.miningmodifications.tileentity.TileEntityCable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileRendererCable extends TileEntitySpecialRenderer{

    ModelCable model = new ModelCable();


    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        GL11.glPushMatrix();
        GL11.glTranslated(x,y,z);
        this.bindTexture(Models.Cable);
        model.render(0.0625F,(TileEntityCable)tileEntity);
        GL11.glPopMatrix();
    }
}
