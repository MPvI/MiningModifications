package com.ragingart.maatsmod.client.gui.container;

import com.ragingart.maatsmod.container.ContainerEnergyGen;
import com.ragingart.maatsmod.ref.Reference;
import com.ragingart.maatsmod.tileentity.TileEntityEnergyGen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

/**
 * Created by XtraX on 07.10.2014.
 */
public class GuiEnergyGen extends GuiContainer{
    private TileEntityEnergyGen tileEntityEnergyGen;

    public GuiEnergyGen(InventoryPlayer invPlayer, TileEntityEnergyGen tileEntityEnergyGen){
        super(new ContainerEnergyGen(invPlayer,tileEntityEnergyGen));
        this.tileEntityEnergyGen = tileEntityEnergyGen;
        xSize = 176;
        ySize = 140;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = StatCollector.translateToLocal(tileEntityEnergyGen.getInventoryName());
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 93, 4210752);
        fontRendererObj.drawString(String.valueOf(tileEntityEnergyGen.getEnergyStored(ForgeDirection.UNKNOWN)), xSize / 2 - fontRendererObj.getStringWidth(String.valueOf(tileEntityEnergyGen.getEnergyStored(ForgeDirection.UNKNOWN))) / 2, 45, 4210752);
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
