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
    private ResourceLocation texIhandler=new ResourceLocation(Reference.MOD_ID.toLowerCase(),"textures/gui/texIhandler.png");
    private ResourceLocation texEhandler=new ResourceLocation(Reference.MOD_ID.toLowerCase(),"textures/gui/texEhandler.png");
    private ResourceLocation texFhandler=new ResourceLocation(Reference.MOD_ID.toLowerCase(),"textures/gui/texFhandler.png");

    public GuiMachineBlock(InventoryPlayer invPlayer,TileEntity tileEntity) {
        super(new ContainerMachineBlock(invPlayer,tileEntity));
        machineBlock = (TileEntityMachineBlock) tileEntity;
        xSize = 176;
        ySize = 140;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        String containerName = machineBlock.getInventoryName();
        String k;
        switch (machineBlock.getMultiBlockMode()) {
            case 0:
                k = "Itemhandler";
                break;
            case 1:
                k = "Energy";
                break;
            case 2:
                k = "Cooler";
                break;
            default:
                k = " ";
                break;
        }

        fontRendererObj.drawString(containerName + " - " + k, xSize / 2 - fontRendererObj.getStringWidth(containerName + " - " + k) / 2, 6, 4210752);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 93, 4210752);
        //fontRendererObj.drawString(String.valueOf(k), 3*xSize / 4  - fontRendererObj.getStringWidth(String.valueOf(k)) / 2, 35, 4210752);
        //fontRendererObj.drawString(String.valueOf(v), 3*xSize / 4  - fontRendererObj.getStringWidth(String.valueOf(v)) / 2, 42, 4210752);

    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float v, int i, int i1) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        switch (machineBlock.getMultiBlockMode()){
            case 0:
                this.mc.getTextureManager().bindTexture(texIhandler);
                this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
                break;
            case 1:
                this.mc.getTextureManager().bindTexture(texEhandler);
                this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
                for (int j = 0; j < 18; j++) {
                    int seg = machineBlock.getMaxEnergyStored(ForgeDirection.UNKNOWN) / 18;
                    if (machineBlock.getEnergyStored(ForgeDirection.UNKNOWN) >= (j + 1) * seg) {
                        this.drawTexturedModalRect(xStart+ 52 + 4 * j,yStart+22, xSize, 0, 3, 11);
                    }
                }
                break;
            case 2:
                this.mc.getTextureManager().bindTexture(texFhandler);
                this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
                if(machineBlock.getFluidAmount()>=0){
                    this.drawTexturedModalRect(xStart+ 48,yStart+18, xSize, 0, 16,16);
                }
                if(machineBlock.getFluidAmount()>=1000){
                    this.drawTexturedModalRect(xStart+ 48 + 21,yStart+18, xSize, 16, 16,16);
                }
                if(machineBlock.getFluidAmount()>=2000){
                    this.drawTexturedModalRect(xStart+ 48 + 21*2,yStart+18, xSize, 32, 16,16);
                }
                if(machineBlock.getStackInSlot(0)==null){
                    this.drawTexturedModalRect(xStart+ 48 + 21*3,yStart+18, xSize, 32, 16,16);
                }
                break;
            default:
                this.mc.getTextureManager().bindTexture(texIhandler);
                this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
                break;
        }
    }
}
