package com.ragingart.miningmodifications.client.renderer.item;

//import com.ragingart.miningmodifications.client.renderer.model.ModelVoidpack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * Created by MaaT on 14.11.2014.
 */
public class ItemRendererVoidpack implements IItemRenderer {

    //public ModelVoidpack model = new ModelVoidpack();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        switch (type){
            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch (type){
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
            case INVENTORY:
            case ENTITY:/*
                RenderHelper.bindTexture(new ResourceLocation(Reference.MOD_ID.toLowerCase(),"textures/models/voidpack.png"));
                model.render(0.0625F);
                break;*/
            default:
                break;
        }
    }
}
