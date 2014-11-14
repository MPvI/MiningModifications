package com.ragingart.miningmodifications.client.gui.container;

import cofh.lib.gui.container.ContainerInventoryItem;
import com.ragingart.miningmodifications.client.gui.element.ElementToggleButton;
import com.ragingart.miningmodifications.container.ContainerVoidpack;
import com.ragingart.miningmodifications.generics.GuiMM;
import com.ragingart.miningmodifications.init.ModItems;
import com.ragingart.miningmodifications.item.ItemVoidpack;
import com.ragingart.miningmodifications.network.PacketHandler;
import com.ragingart.miningmodifications.network.messages.MessageButtonClick;
import com.ragingart.miningmodifications.ref.Reference;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Created by MaaT on 02.11.2014.
 */
public class GuiVoidpack extends GuiMM{

    public GuiVoidpack(InventoryPlayer inventory,ItemStack aStack) {
        super(new ContainerVoidpack(inventory,aStack),new ResourceLocation(Reference.MOD_ID.toLowerCase(),"textures/gui/voidpack.png"));
        xSize = 176;
        ySize = 140;
        name = ModItems.voidpack.getUnlocalizedName();
    }

    @Override
    public void initGui() {
        super.initGui();
        for (int i = 0; i < 5; i++) {
            int x = 51 + 15 * i;
            int y = 15;
            if(this.inventorySlots instanceof ContainerInventoryItem) {
                elements.add(new ElementToggleButton(this, x, y, i + "", xSize, 5, xSize, 10, xSize, 0, 14, 5, Reference.MOD_ID.toLowerCase() + ":textures/gui/voidpack.png", ItemVoidpack.isMetaState(((ContainerInventoryItem) this.inventorySlots).getContainerStack(), i)));
            }
        }
    }

    @Override
    protected void updateElementInformation() {
        for (int i = 0; i < 5; i++) {
            ItemStack aStack = ((Slot) inventorySlots.inventorySlots.get(36 + i)).getStack();
            if (aStack == null) {
                elements.get(i).setEnabled(false);
            } else {
                elements.get(i).setEnabled(aStack.getHasSubtypes());
            }
        }
    }

    @Override
    public void handleElementButtonClick(String buttonName, int mouseButton) {
        int i = Integer.parseInt(buttonName);
        if(this.inventorySlots instanceof ContainerInventoryItem) {
            ItemVoidpack.toggleMetaState(((ContainerInventoryItem) this.inventorySlots).getContainerStack(), i);
            ((ElementToggleButton)elements.get(i)).toggleButton();
            Class ct = this.inventorySlots.getClass();
            int k = 0;
            try {
                k = ct.getField("containerIndex").getInt(inventorySlots);
            }catch (Exception e) {
                // NOOP
            }
            PacketHandler.INSTANCE.sendToServer(new MessageButtonClick(k,i));
        }
    }
}
