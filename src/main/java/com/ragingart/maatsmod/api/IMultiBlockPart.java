package com.ragingart.maatsmod.api;

/**
 * Created by MaaT on 22.10.2014.
 */
public interface IMultiBlockPart {

    /**
     * The base block checking for the structure should always have ID 1.
     * The 0 is reserved for non Multiblockparts.
     * @return Return ID for the Multiblockpart
     */

    public int getID();
}
