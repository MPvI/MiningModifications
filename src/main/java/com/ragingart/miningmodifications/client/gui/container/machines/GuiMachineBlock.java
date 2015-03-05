package com.ragingart.miningmodifications.client.gui.container.machines;

import com.ragingart.miningmodifications.container.machines.ContainerMachineBlock;
import com.ragingart.miningmodifications.ref.Reference;
import com.ragingart.miningmodifications.tileentity.machines.TileEntityMachineBlock;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class GuiMachineBlock extends GuiContainer{

    private TileEntityMachineBlock machineBlock;

    public GuiMachineBlock(InventoryPlayer invPlayer,TileEntity tileEntity) {
        super(new ContainerMachineBlock(invPlayer,tileEntity));
        machineBlock = (TileEntityMachineBlock) tileEntity;
        xSize = 176;
        ySize = 140;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
            String containerName = machineBlock.getInventoryName();
            fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
            fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 93, 4210752);
            String s;
            switch(machineBlock.getMultiBlockMode()){
                case 0:
                    s="Itemhandler";
                    break;
                case 1:
                    s="Energy: "+machineBlock.getEnergyStored(ForgeDirection.UNKNOWN);
                    break;
                case 2:
                    s="Cooler: "+machineBlock.getFluidAmount()+"/2";
                    break;
                default:
                    s=" ";
                    break;
            }
            fontRendererObj.drawString(String.valueOf(s), xSize / 2 - fontRendererObj.getStringWidth(String.valueOf(s)) / 2, 45, 4210752);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float v, int i, int i1) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID.toLowerCase(),"textures/gui/charger.png"));
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}
