package com.ragingart.maatsmod.client.renderer.player;

import api.player.render.RenderPlayerAPI;
import api.player.render.RenderPlayerBase;
import net.minecraft.entity.EntityLivingBase;

/**
 * Created by MaaT on 04.09.2014.
 */
public class RenderPlayerMM extends RenderPlayerBase{

    public RenderPlayerMM(RenderPlayerAPI renderPlayerAPI) {
        super(renderPlayerAPI);
    }

    @Override
    public void renderModel(EntityLivingBase paramEntityLivingBase, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {

            super.renderModel(paramEntityLivingBase, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6);

    }
}
