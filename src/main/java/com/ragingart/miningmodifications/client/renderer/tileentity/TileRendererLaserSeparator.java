package com.ragingart.miningmodifications.client.renderer.tileentity;

import com.ragingart.miningmodifications.client.renderer.model.ModelLaserCore;
import com.ragingart.miningmodifications.client.renderer.model.ModelLaserSeparator;
import com.ragingart.miningmodifications.ref.Models;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityLaserSeparator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
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

            if(tileEntity instanceof TileEntityLaserSeparator){
                TileEntityLaserSeparator aTile = (TileEntityLaserSeparator) tileEntity;
                if(aTile.isCompleteStructure()){
                    if(aTile.getStackInSlot(0)!=null) {
                        GL11.glPushMatrix();
                        GL11.glTranslatef(0, -0.4F, 0);
                        GL11.glScalef(1.9F, 1.9F, 1.9F);
                        EntityItem aEntity = new EntityItem(aTile.getWorldObj(),0,0,0,aTile.getStackInSlot(0));
                        aEntity.hoverStart=0.0F;
                        RenderManager.instance.renderEntityWithPosYaw(aEntity, 0, 0, 0, 0, 0);
                        GL11.glPopMatrix();
                        if(!aTile.isWorkDone()){
                            bindTexture(Models.LaserSeparatorCore);
                            core.render(aTile.getTimer(),0.0625F);
                        }

                    }
                    bindTexture(Models.LaserSeparator);
                    model.renderFull(0.0625F);
                }else{
                    bindTexture(Models.LaserSeparator);
                    model.renderMain(0.0625F);
                }
            }
        GL11.glPopMatrix();
    }
}
