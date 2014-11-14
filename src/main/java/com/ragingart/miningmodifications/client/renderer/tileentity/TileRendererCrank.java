package com.ragingart.miningmodifications.client.renderer.tileentity;

import com.ragingart.miningmodifications.client.renderer.model.ModelCrank;
import com.ragingart.miningmodifications.ref.Models;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntityCrank;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

/**
 * Created by MaaT on 19.10.2014.
 */
public class TileRendererCrank extends TileEntitySpecialRenderer {

    ModelCrank model = new ModelCrank();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
        GL11.glRotatef(180,1,0,0);
        bindTexture(Models.Crank);
        if(tileEntity instanceof TileEntityCrank) {
            ForgeDirection facing = ((TileEntityCrank) tileEntity).checkLink();
            switch(facing){
                case NORTH:
                    GL11.glRotatef(90,1,0,0);
                    GL11.glTranslatef(0,0,0.125F);
                    break;
                case SOUTH:
                    GL11.glRotatef(-90,1,0,0);
                    GL11.glTranslatef(0,0,-0.125F);
                    break;
                case WEST:
                    GL11.glRotatef(90,0,0,1);
                    GL11.glTranslatef(-0.125F,0,0);
                    break;
                case EAST:
                    GL11.glRotatef(-90,0,0,1);
                    GL11.glTranslatef(0.125F,0,0);
                    break;
                default:
                    break;
            }
            GL11.glTranslatef(0,0.5F,0);
            model.render(((TileEntityCrank) tileEntity).getAnimTimer(),0.0625F);
        }
        GL11.glPopMatrix();
    }
}
