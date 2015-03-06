package com.ragingart.miningmodifications.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLaserSeparator extends ModelBase {
    public ModelRenderer Core;
    public ModelRenderer Arm1;
    public ModelRenderer Arm2;
    public ModelRenderer Arm3;
    public ModelRenderer Arm4;
    public ModelRenderer Mirror1;
    public ModelRenderer Mirror2;
    public ModelRenderer Mirror3;
    public ModelRenderer Mirror4;

    public ModelLaserSeparator() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Arm4 = new ModelRenderer(this, 0, 96);
        this.Arm4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Arm4.addBox(-7.0F, 0.0F, 4.0F, 14, 8, 21, 0.0F);
        this.setRotateAngle(Arm4, 0.2617993877991494F, 3.141592653589793F, 0.0F);
        this.Arm1 = new ModelRenderer(this, 0, 64);
        this.Arm1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Arm1.addBox(4.0F, 0.0F, -7.0F, 21, 8, 14, 0.0F);
        this.setRotateAngle(Arm1, 0.0F, 0.0F, -0.2617993877991494F);
        this.Mirror3 = new ModelRenderer(this, 0, 32);
        this.Mirror3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mirror3.addBox(-12.0F, 4.0F, 6.0F, 24, 1, 16, 0.0F);
        this.setRotateAngle(Mirror3, 0.4363323129985824F, -0.7853981633974483F, 0.0F);
        this.Arm2 = new ModelRenderer(this, 0, 64);
        this.Arm2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Arm2.addBox(4.0F, 0.0F, -7.0F, 21, 8, 14, 0.0F);
        this.setRotateAngle(Arm2, 0.0F, 3.141592653589793F, 0.2617993877991494F);
        this.Mirror4 = new ModelRenderer(this, 0, 32);
        this.Mirror4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mirror4.addBox(-12.0F, 4.0F, 6.0F, 24, 1, 16, 0.0F);
        this.setRotateAngle(Mirror4, 0.4363323129985824F, -2.356194490192345F, 0.0F);
        this.Mirror1 = new ModelRenderer(this, 0, 32);
        this.Mirror1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mirror1.addBox(-12.0F, 4.0F, 6.0F, 24, 1, 16, 0.0F);
        this.setRotateAngle(Mirror1, 0.4363323129985824F, 0.7853981633974483F, 0.0F);
        this.Mirror2 = new ModelRenderer(this, 0, 32);
        this.Mirror2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mirror2.addBox(-12.0F, 4.0F, 6.0F, 24, 1, 16, 0.0F);
        this.setRotateAngle(Mirror2, 0.4363323129985824F, 2.356194490192345F, 0.0F);
        this.Arm3 = new ModelRenderer(this, 0, 96);
        this.Arm3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Arm3.addBox(-7.0F, 0.0F, 4.0F, 14, 8, 21, 0.0F);
        this.setRotateAngle(Arm3, 0.2617993877991494F, 0.0F, 0.0F);
        this.Core = new ModelRenderer(this, 0, 0);
        this.Core.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Core.addBox(-8.0F, 0.0F, -8.0F, 16, 8, 16, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

    }

    public void renderMain(float f5)
    {
        this.Core.render(f5);
    }

    public void renderFull(float f5) {
        renderMain(f5);
        this.Mirror1.render(f5);
        this.Mirror2.render(f5);
        this.Mirror3.render(f5);
        this.Mirror4.render(f5);
        this.Arm1.render(f5);
        this.Arm2.render(f5);
        this.Arm3.render(f5);
        this.Arm4.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

