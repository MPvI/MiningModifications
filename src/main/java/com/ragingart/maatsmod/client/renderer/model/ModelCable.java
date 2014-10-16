package com.ragingart.maatsmod.client.renderer.model;

import cofh.lib.util.helpers.EnergyHelper;
import com.ragingart.maatsmod.tileentity.TileEntityCable;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

/**
 * Created by MaaT on 16.10.2014.
 */
public class ModelCable extends ModelBase {
    private static final float[][] rotationAngles = new float[][]{{0,0,-0.5F*(float)Math.PI},{0,0,0.5F*(float)Math.PI},{0,-0.5F*(float)Math.PI,0},{0,0.5F*(float)Math.PI,0},{0,(float)Math.PI,0},{0,0,0}};
    private ModelRenderer mainBox;
    private ModelRenderer connection;

    public ModelCable(){
        mainBox = new ModelRenderer(this,0,0);
        mainBox.setTextureSize(20,16);
        mainBox.setRotationPoint(0,0,0);
        mainBox.addBox(-2,-2,-2,4,4,4);

        connection = new ModelRenderer(this,0,8);
        connection.setTextureSize(20,16);
        connection.setRotationPoint(0,0,0);
        connection.addBox(-8,-2,-2,6,4,4);
    }


    public void render(float f,TileEntityCable aTile) {
        mainBox.render(f);
        if(aTile!=null) {
            for (int i = 0; i < 6; i++) {
                if (EnergyHelper.isAdjacentEnergyHandlerFromSide(aTile, i)) {
                    connection.rotateAngleX = rotationAngles[i][0];
                    connection.rotateAngleY = rotationAngles[i][1];
                    connection.rotateAngleZ = rotationAngles[i][2];
                    connection.render(f);
                }
            }
        }
    }
}
