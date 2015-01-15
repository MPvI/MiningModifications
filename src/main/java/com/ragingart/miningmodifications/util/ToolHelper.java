package com.ragingart.miningmodifications.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

/**
 * Created by MaaT on 09.09.2014.
 */
public class ToolHelper {
    public static HFE[] getHarvestField(int x,int y,int z,int side,int r){
        int d = (2*r+1);
        HFE[] result = new HFE[d*d];

        for (int i = 0; i < result.length; i++) {
            result[i]=new HFE();
        }

        return init(x,y,z,result,r,side);
    }


    private static HFE[] init(int x,int y,int z,HFE[] result,int r,int side){
        for (int i = -r; i <= r; i++) {
            for (int j = -r; j <= r; j++) {
                switch (side){
                    case 0:
                    case 1:
                        result[f(i,j,r)].x=x+i;
                        result[f(i,j,r)].y=y;
                        result[f(i,j,r)].z=z+j;
                        break;
                    case 2:
                    case 3:
                        result[f(i,j,r)].x=x+i;
                        result[f(i,j,r)].y=y+j;
                        result[f(i,j,r)].z=z;
                        break;
                    case 4:
                    case 5:
                        result[f(i,j,r)].x=x;
                        result[f(i,j,r)].y=y+i;
                        result[f(i,j,r)].z=z+j;
                        break;
                    default:
                        break;
                }
            }
        }
        return result;
    }

    private static int f(int a,int b,int r){
        return (a+r)*(2*r+1)+b+r;
    }

    public static class HFE {
        public int x;
        public int y;
        public int z;
        public Block mBlock;

        public HFE(){
            x=0;
            y=0;
            z=0;
            mBlock=Blocks.air;
        }
    }
}

   /*
    sides
    0 bot
    1 top
    2 right
    3 left
    4 back
    5 front
     */