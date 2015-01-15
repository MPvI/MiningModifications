package com.ragingart.miningmodifications.client.gui.container.machines;

import com.ragingart.miningmodifications.container.machines.ContainerCharger;
import com.ragingart.miningmodifications.ref.Reference;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityCharger;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class GuiCharger extends GuiContainer {

    private TileEntityCharger tileEntityCharger;

    public GuiCharger(InventoryPlayer invPlayer,TileEntity tileEntity){
        super(new ContainerCharger(invPlayer,tileEntity));
        this.tileEntityCharger = (TileEntityCharger)tileEntity;
        xSize = 176;
        ySize = 140;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = tileEntityCharger.getName();
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 93, 4210752);
        fontRendererObj.drawString(String.valueOf(tileEntityCharger.getEnergyStored(ForgeDirection.UNKNOWN)), xSize / 2 - fontRendererObj.getStringWidth(String.valueOf(tileEntityCharger.getEnergyStored(ForgeDirection.UNKNOWN))) / 2, 45, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID.toLowerCase(),"textures/gui/charger.png"));
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

}
