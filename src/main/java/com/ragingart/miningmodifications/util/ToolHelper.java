package com.ragingart.miningmodifications.util;

/**
 * Created by MaaT on 09.09.2014.
 */
public class ToolHelper {
    public static int[][] getHarvestField(int x,int y,int z,int side){
        int[][] result = new int[9][3];

        switch(side){
            case 0:
                return initTopBot(x,y,z,result);

            case 1:
                return initTopBot(x,y,z,result);

            case 2:
                return initNoSo(x,y,z,result);

            case 3:
                return initNoSo(x,y,z,result);

            case 4:
                return initWeEa(x,y,z,result);

            case 5:
                return initWeEa(x,y,z,result);

        }
        return result;
    }

    private static int[][] initTopBot(int x,int y,int z,int[][] result){
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                result[(i+1)*3+j+1][0]=x+i;
                result[(i+1)*3+j+1][1]=y;
                result[(i+1)*3+j+1][2]=z+j;
            }
        }
        return result;
    }

    //side 5 4
    private static int[][] initWeEa(int x,int y,int z,int[][] result){
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                result[(i+1)*3+j+1][0]=x;
                result[(i+1)*3+j+1][1]=y+i;
                result[(i+1)*3+j+1][2]=z+j;
            }
        }
        return result;
    }

       //side 2 3
    private static int[][] initNoSo(int x,int y,int z,int[][] result){
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                result[(i+1)*3+j+1][0]=x+i;
                result[(i+1)*3+j+1][1]=y+j;
                result[(i+1)*3+j+1][2]=z;
            }
        }
        return result;
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