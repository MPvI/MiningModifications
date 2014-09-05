package com.ragingart.maatsmod.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderLivingEvent;

/**
 * Created by MaaT on 04.09.2014.
 */
public class EventHandler {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void doPlayerRender(RenderLivingEvent.Pre event) {

    }
}
