package com.ragingart.miningmodifications.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

public class ModelLaserCore extends ModelBase {
    public ModelRenderer LaserCore1;
    public ModelRenderer LaserCore2;
    public ModelRenderer LaserCore3;
    public ModelRenderer LaserCore4;

    public ModelLaserCore() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.LaserCore1 = new ModelRenderer(this, 0, 0);
        this.LaserCore1.setRotationPoint(15.5F, -4.0F, 0);
        this.LaserCore1.addBox(-15.0F, -0.5F, -0.5F, 15, 1, 1, 0.0F);

        this.LaserCore2 = new ModelRenderer(this, 0, 0);
        this.LaserCore2.setRotationPoint(0, -4.0F, -15.5F);
        this.LaserCore2.addBox(-0.5F, -0.5F, 0, 1, 1, 15, 0.0F);

        this.LaserCore3 = new ModelRenderer(this, 0, 0);
        this.LaserCore3.setRotationPoint(-15.5F, -4.0F, 0);
        this.LaserCore3.addBox(0.0F, -0.5F, -0.5F, 15, 1, 1, 0.0F);

        this.LaserCore4 = new ModelRenderer(this, 0, 0);
        this.LaserCore4.setRotationPoint(0, -4.0F, 15.5F);
        this.LaserCore4.addBox(-0.5F, -0.5F,-15.0F, 1, 1, 15, 0.0F);

    }

    public void render(int t,float f5) {

        float r = (float)(Math.PI*t/60);
        float d = (float)(Math.PI/18);
        float ds =(float)Math.sin((Math.PI/60)*t);
        float dc =(float)Math.cos((Math.PI / 60) * t);


        if(t!=-1) {
            this.setRotateAngle(LaserCore1, 0, d*ds, d*dc);
            this.setRotateAngle(LaserCore2, d*dc,d*ds, 0);
            this.setRotateAngle(LaserCore3, 0, d*ds, d*dc);
            this.setRotateAngle(LaserCore4, d*dc,d*ds, 0);
        }

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
        this.LaserCore1.render(f5);

        this.LaserCore2.render(f5);

        this.LaserCore3.render(f5);

        this.LaserCore4.render(f5);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
