package com.ragingart.miningmodifications.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelLaserSeparator extends ModelBase {
    public ModelRenderer Core;
    public ModelRenderer Arm1;
    public ModelRenderer Mirror1;
    public ModelRenderer Topping1;
    public ModelRenderer Topping2;

    public ModelLaserSeparator() {
        this.textureWidth = 128;
        this.textureHeight = 128;

        this.Arm1       = new ModelRenderer(this, 0, 64);
        this.Mirror1    = new ModelRenderer(this, 0, 32);
        this.Core       = new ModelRenderer(this, 0, 0);
        this.Topping1   = new ModelRenderer(this, 0, 0);
        this.Topping2   = new ModelRenderer(this, 0, 0);

        this.Arm1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mirror1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Core.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Topping1.setRotationPoint(14, -4, 0);
        this.Topping2.setRotationPoint(14, -4, 0);

        this.Arm1.addBox(4.0F, 0.0F, -7.0F, 21, 8, 14, 0.0F);
        this.Mirror1.addBox(-12.0F, 4.0F, 6.0F, 24, 1, 16, 0.0F);
        this.Core.addBox(-8.0F, 0.0F, -8.0F, 16, 8, 16, 0.0F);
        this.Topping1.addBox(-1.5F, -0.3F, -2.0F, 3, 1, 4, 0.0F);
        this.Topping2.addBox(-2.5F, -0.31F, -1.0F, 5, 1, 2, 0.0F);

        this.setRotateAngle(Arm1, 0.0F, 0.0F, -0.2617993877991494F);
        this.setRotateAngle(Mirror1, 0.4363323129985824F, 0.7853981633974483F, 0.0F);
        this.setRotateAngle(Topping1,0.0F, 0.0F, -0.2617993877991494F);
        this.setRotateAngle(Topping2,0.0F, 0.0F, -0.2617993877991494F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

    }

    public void renderBase(float f5)
    {
        this.Core.render(f5);
    }

    public void renderFull(float f5) {
        renderBase(f5);
        GL11.glPushMatrix();
        for (int i = 0; i < 4; i++) {
            GL11.glRotatef(90*i,0,1,0);
            this.Mirror1.render(f5);
            this.Arm1.render(f5);
            this.Topping1.render(f5);
            this.Topping2.render(f5);
        }
        GL11.glPopMatrix();
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

