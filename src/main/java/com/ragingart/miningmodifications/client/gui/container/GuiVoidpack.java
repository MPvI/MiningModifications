package com.ragingart.miningmodifications.client.gui.container;

import cofh.lib.gui.container.ContainerInventoryItem;
import com.ragingart.miningmodifications.client.gui.element.ElementButtonLabeled;
import com.ragingart.miningmodifications.client.gui.element.ElementToggleButton;
import com.ragingart.miningmodifications.container.ContainerVoidpack;
import com.ragingart.miningmodifications.generics.GuiMM;
import com.ragingart.miningmodifications.init.ModItems;
import com.ragingart.miningmodifications.item.ItemVoidpack;
import com.ragingart.miningmodifications.network.PacketHandler;
import com.ragingart.miningmodifications.network.messages.MessageButtonClick;
import com.ragingart.miningmodifications.network.messages.MessageLimitInput;
import com.ragingart.miningmodifications.ref.Reference;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Created by MaaT on 02.11.2014.
 */
public class GuiVoidpack extends GuiMM{
    private String texture=Reference.MOD_ID.toLowerCase() + ":textures/gui/voidpack.png";
    private boolean limitInput;
    private int limitIndex;
    private String limitValue = "";
    private int limitCounter = 0;
    private int cID;

    public GuiVoidpack(InventoryPlayer inventory,ItemStack aStack) {
        super(new ContainerVoidpack(inventory,aStack),new ResourceLocation(Reference.MOD_ID.toLowerCase() + ":textures/gui/voidpack.png"));
        xSize = 176;
        ySize = 140;
        name = ModItems.voidpack.getUnlocalizedName()+".name";
        cID=inventory.currentItem;
    }

    @Override
    public void initGui() {
        super.initGui();

        ItemStack containerStack = ((ContainerInventoryItem) this.inventorySlots).getContainerStack();

        for (int i = 0; i < 5; i++) {
            int x = 51 + 15 * i;
            int y = 15;
            elements.add(new ElementToggleButton(this, x, y, String.valueOf(i), xSize, 5, xSize, 10, xSize, 0, 14, 5,texture,ItemVoidpack.getMetaState(containerStack, i)));
        }
        for (int i = 0; i < 5; i++) {
            int x = 51 + 15 * i;
            int y = 38;
            elements.add(new ElementButtonLabeled(this, x, y,String.valueOf(i+5), xSize, 20, xSize, 20, xSize, 20, 14, 9, texture,ItemVoidpack.getNumberToKeep(containerStack, i)));
        }
    }

    @Override
    protected void updateElementInformation() {
        for (int i = 0; i < 5; i++) {
            ItemStack aStack = ((Slot) inventorySlots.inventorySlots.get(36 + i)).getStack();
            if (aStack == null) {
                elements.get(i).setEnabled(false);
            } else {
                if(aStack.getHasSubtypes()) {
                    elements.get(i).setEnabled(true);
                    ((ElementToggleButton) elements.get(i)).setToolTip("description.miningmodifications:voidpackbutton");
                }
            }
        }
    }

    @Override
    public void handleElementButtonClick(String buttonName, int mouseButton) {

        int i = Integer.parseInt(buttonName);
        if(i<5) {
            if (this.inventorySlots instanceof ContainerInventoryItem) {
                ItemVoidpack.toggleMetaState(((ContainerInventoryItem) this.inventorySlots).getContainerStack(), i);
                ((ElementToggleButton) elements.get(i)).toggleButton();
                PacketHandler.INSTANCE.sendToServer(new MessageButtonClick(cID, i));
            }
        }else{
            limitInput=true;
            limitIndex=i-5;
        }
    }

    @Override
    protected void keyTyped(char characterTyped, int keyPressed) {

        if(limitInput){
            if(limitCounter < 3) {
                if (characterTyped >= '0' && characterTyped <= '9') {
                    limitValue += characterTyped;
                    limitCounter++;
                    ((ElementButtonLabeled)elements.get(limitIndex+5)).setLabel(limitValue);
                } else {
                    limitCounter=3;
                }
            }
            if(limitCounter==3){
                int limitReturn;
                try {
                    limitReturn = Integer.parseInt(limitValue);
                }catch (Exception e){
                    limitReturn = 64;
                }
                if (limitReturn < 0) {
                    limitReturn = 0;
                } else if (limitReturn > 99) {
                    limitReturn = 99;
                }

                ItemStack containerStack = ((ContainerInventoryItem) this.inventorySlots).getContainerStack();
                ItemVoidpack.setNumberToKeep(containerStack, limitIndex, limitReturn);
                PacketHandler.INSTANCE.sendToServer(new MessageLimitInput(cID, limitIndex, limitReturn));
                ((ElementButtonLabeled)elements.get(limitIndex+5)).setLabel(String.valueOf(limitReturn));

                limitInput = false;
                limitCounter = 0;
                limitValue = "";
                limitIndex = 0;
                return;
            }
            return;
        }
        super.keyTyped(characterTyped,keyPressed);
    }
}
