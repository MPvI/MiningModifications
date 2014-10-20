package com.ragingart.maatsmod.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelGrinder extends ModelBase {

    //Static Group
    ModelRenderer Base;
    ModelRenderer Sockel;
    ModelRenderer Sockel2;
    ModelRenderer Sockel3;
    ModelRenderer Sockel4;
    ModelRenderer Sockel5;
    ModelRenderer Stutze1;
    ModelRenderer Stutze2;
    ModelRenderer Quertrager;

    //Rotation Group
    ModelRenderer WelleRot;
    ModelRenderer FlugeRot;
    ModelRenderer GrinderRot;

    public ModelGrinder() {
        textureWidth = 128;
        textureHeight = 128;
        initStaticGroup();
        initRotatorGroup();
    }

    private void initStaticGroup() {
        Base = new ModelRenderer(this, 0, 92);
        Base.addBox(-16F, -2F, -16F, 32, 4, 32);
        Base.setRotationPoint(0F, 0F, 0F);
        Base.setTextureSize(128, 128);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        Sockel = new ModelRenderer(this, 24, 64);
        Sockel.addBox(-13F, -1F, -13F, 26, 2, 26);
        Sockel.setRotationPoint(0F, -3F, 0F);
        Sockel.setTextureSize(128, 128);
        Sockel.mirror = true;
        setRotation(Sockel, 0F, 0F, 0F);
        Sockel2 = new ModelRenderer(this, 72, 88);
        Sockel2.addBox(-13F, -1F, -1F, 26, 2, 2);
        Sockel2.setRotationPoint(0F, -5F, -10F);
        Sockel2.setTextureSize(128, 128);
        Sockel2.mirror = true;
        setRotation(Sockel2, 0F, 0F, 0F);
        Sockel3 = new ModelRenderer(this, 88, 72);
        Sockel3.addBox(-1F, -1F, -9F, 2, 2, 18);
        Sockel3.setRotationPoint(-12F, -5F, 0F);
        Sockel3.setTextureSize(128, 128);
        Sockel3.mirror = true;
        setRotation(Sockel3, 0F, 0F, 0F);
        Sockel4 = new ModelRenderer(this, 72, 88);
        Sockel4.addBox(-13F, -1F, -1F, 26, 2, 2);
        Sockel4.setRotationPoint(0F, -5F, 10F);
        Sockel4.setTextureSize(128, 128);
        Sockel4.mirror = true;
        setRotation(Sockel4, 0F, 0F, 0F);
        Sockel5 = new ModelRenderer(this, 88, 72);
        Sockel5.addBox(-1F, -1F, -9F, 2, 2, 18);
        Sockel5.setRotationPoint(12F, -5F, 0F);
        Sockel5.setTextureSize(128, 128);
        Sockel5.mirror = true;
        setRotation(Sockel5, 0F, 0F, 0F);
        Stutze1 = new ModelRenderer(this, 0, 32);
        Stutze1.addBox(-14F, -14F, -2F, 28, 28, 4);
        Stutze1.setRotationPoint(0F, -16F, -13F);
        Stutze1.setTextureSize(128, 128);
        Stutze1.mirror = true;
        setRotation(Stutze1, 0F, 0F, 0F);
        Stutze2 = new ModelRenderer(this, 0, 32);
        Stutze2.addBox(-14F, -14F, -2F, 28, 28, 4);
        Stutze2.setRotationPoint(0F, -16F, 13F);
        Stutze2.setTextureSize(128, 128);
        Stutze2.mirror = true;
        setRotation(Stutze2, 0F, 0F, 0F);
        Quertrager = new ModelRenderer(this, 52, 92);
        Quertrager.addBox(-3F, -2F, -16F, 6, 4, 32);
        Quertrager.setRotationPoint(0F, -26F, 0F);
        Quertrager.setTextureSize(128, 128);
        Quertrager.mirror = true;
        setRotation(Quertrager, 0F, 0F, 0F);
    }

    private void initRotatorGroup() {
        FlugeRot = new ModelRenderer(this, 4, 64);
        FlugeRot.addBox(-0.5F, -2F, 0.5F, 1, 4, 2);
        FlugeRot.setRotationPoint(0F, -30F, 0F);
        FlugeRot.setTextureSize(128, 128);
        FlugeRot.mirror = true;
        setRotation(FlugeRot, 0F, 0F, 0F);
        GrinderRot = new ModelRenderer(this, 0, 0);
        GrinderRot.addBox(-10F, -4F, 0F, 10, 6, 4);
        GrinderRot.setRotationPoint(0F, -9F, 0F);
        GrinderRot.setTextureSize(128, 128);
        GrinderRot.mirror = true;
        setRotation(GrinderRot, 0F, 0F, 0F);
        WelleRot = new ModelRenderer(this, 0, 64);
        WelleRot.addBox(-0.5F, -10F, -0.5F, 1, 20, 1);
        WelleRot.setRotationPoint(0F, -20F, 0F);
        WelleRot.setTextureSize(128, 128);
        WelleRot.mirror = true;
        setRotation(WelleRot, 0F, 0F, 0F);
    }

    public void render(int animProgress, float f) {

        float anim = (float) Math.PI / 50.0F * animProgress;
        renderStaticGroup(f);

        renderRotatorGroup(f, -anim);

    }


    public void renderStaticGroup(float f5){
        Base.render(f5);
        Sockel.render(f5);
        Sockel2.render(f5);
        Sockel3.render(f5);
        Sockel4.render(f5);
        Sockel5.render(f5);
        Stutze1.render(f5);
        Stutze2.render(f5);
        Quertrager.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }


    private void renderRotatorGroup(float f,float anim) {
        WelleRot.rotateAngleY=-anim;
        WelleRot.render(f);
        renderRotatorPart(GrinderRot,8 ,f,-anim);
        renderRotatorPart(FlugeRot,4, f,-anim);
    }

    private void renderRotatorPart(ModelRenderer model,int num, float f,float anim){
        float quarterCake = 2.0F/num*(float)Math.PI;
        for (int i = 0; i < num; i++) {
            model.rotateAngleY=quarterCake*i+anim;
            model.render(f);
        }

    }
}
