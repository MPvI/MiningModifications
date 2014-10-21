package com.ragingart.maatsmod.client.renderer.block;

import com.ragingart.maatsmod.init.ModBlocks;
import com.ragingart.maatsmod.ref.RenderIds;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

/**
 * Created by MaaT on 20.10.2014.
 */
public class BlockRendererFluxField implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }


    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
               drawPlatform(world, x, y, z,false);
               drawPlatform(world, x, y, z,true);
        GL11.glPopMatrix();
        return true;
    }

    private void drawPlatform(IBlockAccess world,int x,int y,int z,boolean inside){
        float a = 0;
        if(inside){
            a = 1;
        }
        float uMin = world.getBlock(x, y, z).getIcon(0, 0).getMinU();
        float uMax = world.getBlock(x, y, z).getIcon(0, 0).getMaxU();
        float vMin = world.getBlock(x, y, z).getIcon(0, 0).getMinV();
        float vMax = world.getBlock(x, y, z).getIcon(0, 0).getMaxV();

        Tessellator t = Tessellator.instance;

        t.setColorRGBA(255,255,255,130);
        t.addTranslation(x, y, z);

        //top
        t.addVertexWithUV(0, 1-0.1F*a, 0, uMin, vMin);//origin
        t.addVertexWithUV(0, 1-0.1F*a, 1, uMax, vMin);//1 unit up
        t.addVertexWithUV(1, 1-0.1F*a, 1, uMax, vMax);//1 unit up and to the right
        t.addVertexWithUV(1, 1-0.1F*a, 0, uMin, vMax);//1 unit to the right
        //bot
        t.addVertexWithUV(1, 0.9F+0.1F*a, 1, uMin, vMin);//origin
        t.addVertexWithUV(0, 0.9F+0.1F*a, 1, uMax, vMin);//1 unit up
        t.addVertexWithUV(0, 0.9F+0.1F*a, 0, uMax, vMax);//1 unit up and to the right
        t.addVertexWithUV(1, 0.9F+0.1F*a, 0, uMin, vMax);//1 unit to the right
        int b=1;
        if(inside){
            b=-1;
        }
        if(world.getBlock(x,y,z-b)!= ModBlocks.FluxField) {
            //north
            t.addVertexWithUV(1, 0.9F, a, uMin, vMin);//origin
            t.addVertexWithUV(0, 0.9F, a, uMax, vMin);//1 unit up and to the right
            t.addVertexWithUV(0, 1, a, uMax, vMax);//1 unit to the right
            t.addVertexWithUV(1, 1, a, uMin, vMax);//1 unit up
        }
        if(world.getBlock(x,y,z+b)!= ModBlocks.FluxField) {
            // south
            t.addVertexWithUV(0, 0.9F, 1 - a, uMin, vMin);//origin
            t.addVertexWithUV(1, 0.9F, 1 - a, uMax, vMin);//1 unit up and to the right
            t.addVertexWithUV(1, 1, 1 - a, uMax, vMax);//1 unit to the right
            t.addVertexWithUV(0, 1, 1 - a, uMin, vMax);//1 unit up
        }
        if(world.getBlock(x+b,y,z)!= ModBlocks.FluxField) {
            //east
            t.addVertexWithUV(1 - a, 0.9F, 1, uMin, vMin);
            t.addVertexWithUV(1 - a, 0.9F, 0, uMax, vMin);
            t.addVertexWithUV(1 - a, 1, 0, uMax, vMax);
            t.addVertexWithUV(1 - a, 1, 1, uMin, vMax);
        }
        if(world.getBlock(x-b,y,z)!= ModBlocks.FluxField) {
            //west
            t.addVertexWithUV(a, 0.9F, 0, uMin, vMin);
            t.addVertexWithUV(a, 0.9F, 1, uMax, vMin);
            t.addVertexWithUV(a, 1, 1, uMax, vMax);
            t.addVertexWithUV(a, 1, 0, uMin, vMax);
        }

        t.addTranslation(-x, -y, -z);
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return RenderIds.LSC;
    }
}
