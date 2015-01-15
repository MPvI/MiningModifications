package com.ragingart.miningmodifications.client.renderer.tileentity;


import com.ragingart.miningmodifications.client.renderer.model.ModelGrinder;
import com.ragingart.miningmodifications.ref.Models;
import com.ragingart.miningmodifications.tileentity.handmachines.TileEntityGrinder;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

public class TileRendererGrinder extends TileEntitySpecialRenderer {

    ModelGrinder model = new ModelGrinder();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick, int p_180535_9_) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.0625, z + 0.5);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glRotatef(180,1,0,0);
        bindTexture(Models.Grinder);
        if(tileEntity instanceof TileEntityGrinder){
            EnumFacing facing = ((TileEntityGrinder) tileEntity).getFacing();
            switch(facing){
                case NORTH:
                    GL11.glRotatef(270,0,1,0);
                    break;
                case SOUTH:
                    GL11.glRotatef(90,0,1,0);
                    break;
                case WEST:
                    GL11.glRotatef(180,0,1,0);
                    break;
                case EAST:
                    break;
                default:
                    break;
            }
            model.render(((TileEntityGrinder) tileEntity).getAnimTimer(),0.0625F);
        }
        GL11.glPopMatrix();
    }
}
