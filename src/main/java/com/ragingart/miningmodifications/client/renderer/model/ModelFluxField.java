package com.ragingart.miningmodifications.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * Created by MaaT on 07.10.2014.
 */
public class ModelFluxField extends ModelBase {
    public ModelRenderer model;

    public ModelFluxField(){
        model = new ModelRenderer(this,0,0);
        model.setTextureSize(128,33);
        model.setRotationPoint(0,0,0);
        model.addBox(-16,15,-16,
                      32,1,32);
        model.mirror=false;
    }

    public void render() {
        model.render(0.0625F);
    }
}
