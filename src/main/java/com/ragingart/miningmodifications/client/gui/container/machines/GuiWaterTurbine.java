package com.ragingart.miningmodifications.client.gui.container.machines;

import com.ragingart.miningmodifications.container.machines.ContainerWaterTurbine;
import com.ragingart.miningmodifications.ref.Reference;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityWaterTurbine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiWaterTurbine extends GuiContainer {

    private TileEntityWaterTurbine tileEntityWaterTurbine;

    public GuiWaterTurbine(InventoryPlayer invPlayer, TileEntity tileEntity){
        super(new ContainerWaterTurbine(invPlayer, tileEntity));
        this.tileEntityWaterTurbine = (TileEntityWaterTurbine)tileEntity;
        xSize = 176;
        ySize = 140;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = tileEntityWaterTurbine.getName();
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);

        String astring = "HHWater: "+String.valueOf(tileEntityWaterTurbine.getFluidAmount())+"/"+String.valueOf(tileEntityWaterTurbine.getFluidCapacity());
        fontRendererObj.drawString(astring, (xSize/2) - (fontRendererObj.getStringWidth(astring)/2), 35, 4210752);

        astring = "RF: "+String.valueOf(tileEntityWaterTurbine.getEnergyStored(EnumFacing.UP))+"/"+String.valueOf(tileEntityWaterTurbine.getMaxEnergyStored(EnumFacing.UP));
        fontRendererObj.drawString(astring, (xSize/2) - (fontRendererObj.getStringWidth(astring)/2), 45, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID.toLowerCase(),"textures/gui/waterturbine.png"));
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}
