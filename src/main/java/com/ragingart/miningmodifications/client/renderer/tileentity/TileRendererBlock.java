package com.ragingart.miningmodifications.client.renderer.tileentity;

import com.ragingart.miningmodifications.ref.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileRendererBlock extends TileEntitySpecialRenderer {
    @Override
    public void renderTileEntityAt(TileEntity aTile, double x, double y, double z, float tick, int p_180535_9_) {

            GL11.glPushMatrix();
            Tessellator tess = Tessellator.getInstance();
            WorldRenderer woRender = tess.getWorldRenderer();

            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "textures/blocks/casing.png"));

            woRender.startDrawingQuads();
            woRender.addVertexWithUV(x, y, z, 0, 0);
            woRender.addVertexWithUV(x, y+1, z, 0, 1);
            woRender.addVertexWithUV(x+1, y+1, z, 1, 1);
            woRender.addVertexWithUV(x+1, y, z, 1, 0);

            tess.draw();
            GL11.glPopMatrix();

    }
}
