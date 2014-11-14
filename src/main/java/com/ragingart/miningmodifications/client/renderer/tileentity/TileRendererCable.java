package com.ragingart.miningmodifications.client.renderer.tileentity;

import com.ragingart.miningmodifications.client.renderer.model.ModelCable;
import com.ragingart.miningmodifications.ref.Models;
import com.ragingart.miningmodifications.tileentity.TileEntityCable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * Created by MaaT on 16.10.2014.
 */
public class TileRendererCable extends TileEntitySpecialRenderer{

    ModelCable model = new ModelCable();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
        //GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glRotatef(180,0,0,1);
        this.bindTexture(Models.Cable);
        model.render(0.0625F,(TileEntityCable)tileEntity);
        GL11.glPopMatrix();
    }
}
