package com.ragingart.miningmodifications.container;

import cofh.lib.gui.container.ContainerInventoryItem;
import com.ragingart.miningmodifications.client.gui.slot.SlotVoidpack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerVoidpack extends ContainerInventoryItem{

    public ContainerVoidpack(InventoryPlayer inventory,ItemStack stack) {
        super(stack, inventory);
        addPlayerInventory(inventory);
        addSlotToContainer(new SlotVoidpack(this.containerWrapper,0,50,22));
        addSlotToContainer(new SlotVoidpack(this.containerWrapper,1,65,22));
        addSlotToContainer(new SlotVoidpack(this.containerWrapper,2,80,22));
        addSlotToContainer(new SlotVoidpack(this.containerWrapper,3,95,22));
        addSlotToContainer(new SlotVoidpack(this.containerWrapper,4,110,22));
    }
    public static void doErase(Item[] toErase,int[] meErase,int[] nuErase,InventoryPlayer inventory){
        int[] nErase = new int[5];
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < toErase.length; j++) {
                ItemStack aStack = inventory.getStackInSlot(i);
                if(aStack!=null && aStack.getItem()!=null){
                    if(meErase[j]==-1){
                        if(aStack.getItem()==toErase[j]) {
                            if (nErase[j] >= nuErase[j]) {
                                inventory.setInventorySlotContents(i, null);
                            } else {
                                nErase[j] += aStack.stackSize;
                            }
                        }
                    }else{
                        if(aStack.getItem()==toErase[j] && aStack.getItemDamage()==meErase[j]) {
                            if (nErase[j] >= nuErase[j]) {
                                inventory.setInventorySlotContents(i, null);
                            } else {
                                nErase[j] += aStack.stackSize;
                            }
                        }
                    }
                }
            }
        }
    }

    protected void addPlayerInventory(InventoryPlayer inventoryPlayer) {

        for(int inventoryRowIndex = 0; inventoryRowIndex<3;++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex) {
                this.addSlotToContainer(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 58 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for(int actionBarSlotIndex = 0;actionBarSlotIndex<9;++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 116));
        }
    }
    /*
    @Override
    public ItemStack slotClick(int slotIndex, int v, int mode, EntityPlayer player) {

        try{
            boolean b = ((Slot)inventorySlots.get(slotIndex)).getStack().getItem() instanceof ItemVoidpack;
            if(b) return null;
        }catch (Throwable e){
            // NOOP
        }

        if (mode == 2 && v == containerIndex) {
            return null;
        }
        ItemStack rStack = null;
        InventoryPlayer inventoryPlayer = player.inventory;
        int holdStackSize;
        ItemStack aStack;

        if (mode == 5) {
            // Dragging only enabled within Playerinventory Index 0 to 35

                v = pressed,hold,released
                v = 0,1,2 L-Dragging
                v = 4,5,6 R-Dragging

            if(slotIndex < 36) {
                onDrag(slotIndex, v, inventoryPlayer);
            }
        } else if (this.field_94536_g != 0) {
            this.func_94533_d();
        } else {

                v = left,right click
                v = 0,1


            Slot aSlot;
            int aStackSize;
            ItemStack anotherStack;

            if ((mode == 0 || mode == 1) && (v == 0 || v == 1)) {
                if (slotIndex == -999) {
                    dropItem(v,inventoryPlayer);
                } else if (mode == 1) {
                    // Shift v-klick

                        If within Playerinventory do nothing, else Clear the Setup

                    if (slotIndex < 36) {
                        return null;
                    } else {
                        aSlot = (Slot) this.inventorySlots.get(slotIndex);
                        aSlot.putStack(null);
                    }
                } else {
                    if (slotIndex < 0) {
                        return null;
                    }
                    aSlot = (Slot) this.inventorySlots.get(slotIndex);

                    if (aSlot != null) {
                        aStack = aSlot.getStack();
                        ItemStack cursorStack = inventoryPlayer.getItemStack();

                        if (aStack != null) {
                            if(slotIndex < 36) {
                                rStack = aStack.copy();
                            }else if(cursorStack!=null){
                                rStack = setCopy(aSlot,cursorStack);
                            }
                        }
                        if (aStack == null) {
                            if(slotIndex < 36) {
                                if (cursorStack != null && aSlot.isItemValid(cursorStack)) {
                                    // v=0 >> alle, v=1 >> 1
                                    aStackSize = v == 0 ? cursorStack.stackSize : 1;

                                    if (aStackSize > aSlot.getSlotStackLimit()) {
                                        aStackSize = aSlot.getSlotStackLimit();
                                    }
                                    if (cursorStack.stackSize >= aStackSize) {
                                        aSlot.putStack(cursorStack.splitStack(aStackSize));
                                    }
                                    if (cursorStack.stackSize == 0) {
                                        inventoryPlayer.setItemStack((ItemStack) null);
                                    }
                                }
                            }else if(cursorStack != null){
                                rStack = setCopy(aSlot,cursorStack);
                            }
                        } else if (aSlot.canTakeStack(player)) {
                            if (cursorStack == null) {
                                aStackSize = v == 0 ? aStack.stackSize : (aStack.stackSize + 1) / 2;
                                anotherStack = aSlot.decrStackSize(aStackSize);
                                inventoryPlayer.setItemStack(anotherStack);

                                if (aStack.stackSize == 0) {
                                    aSlot.putStack((ItemStack) null);
                                }
                                aSlot.onPickupFromSlot(player, inventoryPlayer.getItemStack());
                            } else if (aSlot.isItemValid(cursorStack)) {
                                if (aStack.getItem() == cursorStack.getItem() && aStack.getItemDamage() == cursorStack.getItemDamage()
                                        && ItemStack.areItemStackTagsEqual(aStack, cursorStack)) {
                                    aStackSize = v == 0 ? cursorStack.stackSize : 1;

                                    if (aStackSize > aSlot.getSlotStackLimit() - aStack.stackSize) {
                                        aStackSize = aSlot.getSlotStackLimit() - aStack.stackSize;
                                    }
                                    if (aStackSize > cursorStack.getMaxStackSize() - aStack.stackSize) {
                                        aStackSize = cursorStack.getMaxStackSize() - aStack.stackSize;
                                    }
                                    cursorStack.splitStack(aStackSize);

                                    if (cursorStack.stackSize == 0) {
                                        inventoryPlayer.setItemStack((ItemStack) null);
                                    }
                                    aStack.stackSize += aStackSize;
                                    aSlot.putStack(aStack);
                                } else if (cursorStack.stackSize <= aSlot.getSlotStackLimit()) {
                                    aSlot.putStack(cursorStack);
                                    inventoryPlayer.setItemStack(aStack);
                                }
                            } else if (aStack.getItem() == cursorStack.getItem() && cursorStack.getMaxStackSize() > 1
                                    && (!aStack.getHasSubtypes() || aStack.getItemDamage() == cursorStack.getItemDamage())
                                    && ItemStack.areItemStackTagsEqual(aStack, cursorStack)) {
                                aStackSize = aStack.stackSize;

                                if (aStackSize > 0 && aStackSize + cursorStack.stackSize <= cursorStack.getMaxStackSize()) {
                                    cursorStack.stackSize += aStackSize;
                                    aStack = aSlot.decrStackSize(aStackSize);

                                    if (aStack.stackSize == 0) {
                                        aSlot.putStack((ItemStack) null);
                                    }
                                    aSlot.onPickupFromSlot(player, inventoryPlayer.getItemStack());
                                }
                            }
                        }
                        aSlot.onSlotChanged();
                    }
                }
            } else if (mode == 2 && v >= 0 && v < 9 && slotIndex < 36) {
                aSlot = (Slot) this.inventorySlots.get(slotIndex);

                if (aSlot.canTakeStack(player)) {
                    aStack = inventoryPlayer.getStackInSlot(v);
                    boolean flag = aStack == null || aSlot.inventory == inventoryPlayer && aSlot.isItemValid(aStack);
                    aStackSize = -1;

                    if (!flag) {
                        aStackSize = inventoryPlayer.getFirstEmptyStack();
                        flag |= aStackSize > -1;
                    }
                    if (aSlot.getHasStack() && flag) {
                        anotherStack = aSlot.getStack();
                        inventoryPlayer.setInventorySlotContents(v, anotherStack.copy());

                        if ((aSlot.inventory != inventoryPlayer || !aSlot.isItemValid(aStack)) && aStack != null) {
                            if (aStackSize > -1) {
                                inventoryPlayer.addItemStackToInventory(aStack);
                                aSlot.decrStackSize(anotherStack.stackSize);
                                aSlot.putStack((ItemStack) null);
                                aSlot.onPickupFromSlot(player, anotherStack);
                            }
                        } else {
                            aSlot.decrStackSize(anotherStack.stackSize);
                            aSlot.putStack(aStack);
                            aSlot.onPickupFromSlot(player, anotherStack);
                        }
                    } else if (!aSlot.getHasStack() && aStack != null && aSlot.isItemValid(aStack)) {
                        inventoryPlayer.setInventorySlotContents(v, (ItemStack) null);
                        aSlot.putStack(aStack);
                    }
                }
            } else if (mode == 3 && player.capabilities.isCreativeMode && inventoryPlayer.getItemStack() == null && slotIndex >= 0) {

                    Middleclick

                aSlot = (Slot) this.inventorySlots.get(slotIndex);

                if (aSlot != null && aSlot.getHasStack()) {
                    aStack = aSlot.getStack().copy();
                    aStack.stackSize = aStack.getMaxStackSize();
                    inventoryPlayer.setItemStack(aStack);
                }
            } else if (mode == 4 && inventoryPlayer.getItemStack() == null && slotIndex >= 0) {

                    Slotclick with q

                aSlot = (Slot) this.inventorySlots.get(slotIndex);

                if (aSlot != null && aSlot.getHasStack() && aSlot.canTakeStack(player)) {
                    aStack = aSlot.decrStackSize(v == 0 ? 1 : aSlot.getStack().stackSize);
                    aSlot.onPickupFromSlot(player, aStack);
                    player.dropPlayerItemWithRandomChoice(aStack, true);
                }
            } else if (mode == 6 && slotIndex >= 0) {
                /*
                    Doubleclick

                aSlot = (Slot) this.inventorySlots.get(slotIndex);
                aStack = inventoryPlayer.getItemStack();

                if (aStack != null && (aSlot == null || !aSlot.getHasStack() || !aSlot.canTakeStack(player))) {
                    holdStackSize = v == 0 ? 0 : this.inventorySlots.size() - 1;
                    aStackSize = v == 0 ? 1 : -1;

                    for (int i2 = 0; i2 < 2; ++i2) {
                        for (int j2 = holdStackSize; j2 >= 0 && j2 < this.inventorySlots.size() && aStack.stackSize < aStack.getMaxStackSize(); j2 += aStackSize) {
                            Slot slot3 = (Slot) this.inventorySlots.get(j2);

                            if (slot3.getHasStack() && func_94527_a(slot3, aStack, true) && slot3.canTakeStack(player)
                                    && this.func_94530_a(aStack, slot3) && (i2 != 0 || slot3.getStack().stackSize != slot3.getStack().getMaxStackSize())) {
                                int k1 = Math.min(aStack.getMaxStackSize() - aStack.stackSize, slot3.getStack().stackSize);
                                ItemStack itemstack2 = slot3.decrStackSize(k1);
                                aStack.stackSize += k1;

                                if (itemstack2.stackSize <= 0) {
                                    slot3.putStack((ItemStack) null);
                                }
                                slot3.onPickupFromSlot(player, itemstack2);
                            }
                        }
                    }
                }
                this.detectAndSendChanges();
            }
        }
        return rStack;

    }
 */


    @Override
    protected boolean mergeItemStack(ItemStack stack, int slotMin, int slotMax, boolean ascending) {

        boolean slotFound = false;
        int k = ascending ? slotMax - 1 : slotMin;

        Slot slot;
        ItemStack stackInSlot;

        if (stack.isStackable()) {
            while (stack.stackSize > 0 && (!ascending && k < slotMax || ascending && k >= slotMin)) {
                slot = (Slot) this.inventorySlots.get(k);
                stackInSlot = slot.getStack();

                if (slot.isItemValid(stack)) {
                    int l = stackInSlot.stackSize + stack.stackSize;
                    int slotLimit = Math.min(stack.getMaxStackSize(), slot.getSlotStackLimit());

                    if (l <= slotLimit) {
                        stack.stackSize = 0;
                        stackInSlot.stackSize = l;
                        slot.putStack(stackInSlot);
                        slot.onSlotChanged();
                        slotFound = true;
                    } else if (stackInSlot.stackSize < slotLimit) {
                        stack.stackSize -= slotLimit - stackInSlot.stackSize;
                        stackInSlot.stackSize = slotLimit;
                        slot.putStack(stackInSlot);
                        slot.onSlotChanged();
                        slotFound = true;
                    }
                }
                k += ascending ? -1 : 1;
            }
        }
        if (stack.stackSize > 0) {
            k = ascending ? slotMax - 1 : slotMin;

            while (!ascending && k < slotMax || ascending && k >= slotMin) {
                slot = (Slot) this.inventorySlots.get(k);
                stackInSlot = slot.getStack();

                if (slot.isItemValid(stack) && stackInSlot == null) {
                    //slot.putStack(ItemHelper.cloneStack(stack, Math.min(stack.stackSize, slot.getSlotStackLimit())));
                    slot.onSlotChanged();

                    if (slot.getStack() != null) {
                        slotFound = true;
                    }
                    break;
                }
                k += ascending ? -1 : 1;
            }
        }
        return slotFound;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        return null;
    }
    /*
    private void onDrag(int sId,int mode,InventoryPlayer inv){
        int l = this.field_94536_g;
        this.field_94536_g = func_94532_c(mode);

        if ((l != 1 || this.field_94536_g != 2) && l != this.field_94536_g) {
            this.func_94533_d();
        } else if (inv.getItemStack() == null) {
            this.func_94533_d();
        } else if (this.field_94536_g == 0) {
            this.field_94535_f = func_94529_b(mode);

            if (func_94528_d(this.field_94535_f)) {
                this.field_94536_g = 1;
                this.field_94537_h.clear();
            } else {
                this.func_94533_d();
            }
        } else if (this.field_94536_g == 1) {
            Slot slot = (Slot) this.inventorySlots.get(sId);

            if (slot != null && func_94527_a(slot, inv.getItemStack(), true) && slot.isItemValid(inv.getItemStack())
                    && inv.getItemStack().stackSize > this.field_94537_h.size() && this.canDragIntoSlot(slot)) {
                this.field_94537_h.add(slot);
            }
        } else if (this.field_94536_g == 2) {
            if (!this.field_94537_h.isEmpty()) {
                ItemStack aStack = inv.getItemStack().copy();
                int holdStackSize = inv.getItemStack().stackSize;
                Iterator<Slot> iterator = this.field_94537_h.iterator();

                while (iterator.hasNext()) {
                    Slot slot1 = iterator.next();

                    if (slot1 != null && func_94527_a(slot1, inv.getItemStack(), true) && slot1.isItemValid(inv.getItemStack())
                            && inv.getItemStack().stackSize >= this.field_94537_h.size() && this.canDragIntoSlot(slot1)) {
                        ItemStack itemstack1 = aStack.copy();
                        int j1 = slot1.getHasStack() ? slot1.getStack().stackSize : 0;
                        func_94525_a(this.field_94537_h, this.field_94535_f, itemstack1, j1);

                        if (itemstack1.stackSize > itemstack1.getMaxStackSize()) {
                            itemstack1.stackSize = itemstack1.getMaxStackSize();
                        }
                        if (itemstack1.stackSize > slot1.getSlotStackLimit()) {
                            itemstack1.stackSize = slot1.getSlotStackLimit();
                        }
                        holdStackSize -= itemstack1.stackSize - j1;
                        slot1.putStack(itemstack1);
                    }
                }

                aStack.stackSize = holdStackSize;

                if (aStack.stackSize <= 0) {
                    aStack = null;
                }
                inv.setItemStack(aStack);
            }
            this.func_94533_d();
        } else {
            this.func_94533_d();
        }

    }
    */
    private void dropItem(int v,InventoryPlayer inv){
        if (inv.getItemStack() != null) {
            if (v == 0) {
                player.dropPlayerItemWithRandomChoice(inv.getItemStack(), true);
                inv.setItemStack(null);
            }

            if (v == 1) {
                player.dropPlayerItemWithRandomChoice(inv.getItemStack().splitStack(1), true);

                if (inv.getItemStack().stackSize == 0) {
                    inv.setItemStack(null);
                }
            }
        }
    }

    private ItemStack setCopy(Slot slot,ItemStack itemStack){
        ItemStack aStack = itemStack.copy();
        aStack.stackSize=1;
        slot.putStack(aStack);
        return itemStack;
    }
}
