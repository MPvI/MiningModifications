package com.ragingart.miningmodifications.client.renderer.tileentity;

import com.ragingart.miningmodifications.client.renderer.model.ModelLaserCore;
import com.ragingart.miningmodifications.client.renderer.model.ModelLaserSeparator;
import com.ragingart.miningmodifications.proxy.ClientProxy;
import com.ragingart.miningmodifications.ref.Models;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityLaserSeparator;
import com.ragingart.miningmodifications.util.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileRendererLaserSeparator extends TileEntitySpecialRenderer {

    public ModelLaserSeparator model = new ModelLaserSeparator();
    public ModelLaserCore core = new ModelLaserCore();


    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
        GL11.glRotatef(180, 1, 0, 0);

        if (tileEntity instanceof TileEntityLaserSeparator) {
            TileEntityLaserSeparator aTile = (TileEntityLaserSeparator) tileEntity;
            if (aTile.isCompleteStructure()) {
                if (aTile.getStackInSlot(0) != null) {
                    RenderHelper.renderItemstack(aTile.getWorldObj(), aTile.getStackInSlot(0));
                }
                bindTexture(Models.LaserSeparator);
                model.renderFull(0.0625F);
            } else {
                bindTexture(Models.LaserSeparator);
                model.renderBase(0.0625F);
            }
            if (ClientProxy.renderPass == 1 && !aTile.isWorkDone()) {
                bindTexture(Models.LaserSeparatorCore);
                core.render(aTile.getTimer(), 0.0625F);
            }
        }
        GL11.glPopMatrix();
    }
}
