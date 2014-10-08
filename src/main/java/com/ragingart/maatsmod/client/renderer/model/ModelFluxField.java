package com.ragingart.maatsmod.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * Created by MaaT on 07.10.2014.
 */
public class ModelFluxField extends ModelBase {
    public ModelRenderer model;
    public int anim = -16;


    public ModelFluxField(){
        model = new ModelRenderer(this);
        textureHeight=128;
        textureWidth=64;
        model.addBox(-16,-16,-16,
                      32,1,32);
        model.setRotationPoint(0,0,0);
        model.setTextureSize(128,32);
    }

    public void render() {
        //model.setTextureOffset(0,16+anim);
        model.render(0.0625F);
        /*anim++;
        if(anim>15){
            anim=-16;
        }
        */
    }
}
