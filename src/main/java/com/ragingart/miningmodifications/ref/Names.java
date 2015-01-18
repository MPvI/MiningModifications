package com.ragingart.miningmodifications.ref;


public class Names {
    public static final String MOD_PREFIX = Reference.MOD_ID.toLowerCase() + ":";
    public static final String INFO_PREFIX = "description.";


    public static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

}
