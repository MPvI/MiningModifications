package com.ragingart.miningmodifications.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelLaserCore extends ModelBase {
    public ModelRenderer LaserCore1;
    public ModelRenderer LaserCore2;
    public ModelRenderer LaserCore3;
    public ModelRenderer LaserCore4;

    public ModelLaserCore() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.LaserCore2 = new ModelRenderer(this, 0, 0);
        this.LaserCore2.setRotationPoint(-0.5F, -4.0F, 0.0F);
        this.LaserCore2.addBox(0.0F, 0.0F, 0.0F, 15, 1, 1, 0.0F);
        this.setRotateAngle(LaserCore2, 0.0F, 1.5707963267948966F, 0.0F);
        this.LaserCore4 = new ModelRenderer(this, 0, 0);
        this.LaserCore4.setRotationPoint(0.5F, -4.0F, 0.0F);
        this.LaserCore4.addBox(0.0F, 0.0F, 0.0F, 15, 1, 1, 0.0F);
        this.setRotateAngle(LaserCore4, 0.0F, -1.5707963267948966F, 0.0F);
        this.LaserCore1 = new ModelRenderer(this, 0, 0);
        this.LaserCore1.setRotationPoint(0.0F, -4.0F, -0.5F);
        this.LaserCore1.addBox(0.0F, 0.0F, 0.0F, 15, 1, 1, 0.0F);
        this.LaserCore3 = new ModelRenderer(this, 0, 0);
        this.LaserCore3.setRotationPoint(0.0F, -4.0F, 0.5F);
        this.LaserCore3.addBox(0.0F, 0.0F, 0.0F, 15, 1, 1, 0.0F);
        this.setRotateAngle(LaserCore3, 0.0F, 3.141592653589793F, 0.0F);
    }

    public void render(int t,float f5) {
        this.LaserCore1.setTextureOffset(0,2*(t%10));
        this.LaserCore2.setTextureOffset(0,2*(t%10));
        this.LaserCore3.setTextureOffset(0,2*(t%10));
        this.LaserCore4.setTextureOffset(0,2*(t%10));
        this.LaserCore1.render(f5);
        this.LaserCore2.render(f5);
        this.LaserCore3.render(f5);
        this.LaserCore4.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
