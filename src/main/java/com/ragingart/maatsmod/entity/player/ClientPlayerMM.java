package com.ragingart.maatsmod.entity.player;

import api.player.client.ClientPlayerAPI;
import api.player.client.ClientPlayerBase;
import com.ragingart.maatsmod.util.LogHelper;

/**
 * Created by MaaT on 23.09.2014.
 */
public class ClientPlayerMM extends ClientPlayerBase{
    public ClientPlayerMM(ClientPlayerAPI playerAPI) {
        super(playerAPI);
    }

    @Override
    public void swingItem() {
        LogHelper.info("Hello");

        //super.swingItem();
    }


}
