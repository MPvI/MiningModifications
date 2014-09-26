package com.ragingart.maatsmod.init;

import com.ragingart.maatsmod.handler.ConfigHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

/**
 * Created by MaaT on 25.09.2014.
 */

public class ModWorldgen implements IWorldGenerator {

    public ModWorldgen(){
        GameRegistry.registerWorldGenerator(this,5);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        int x = chunkX*16 + random.nextInt(16);
        int y = random.nextInt(ConfigHandler.oreGenMax-ConfigHandler.oreGenMin)+ ConfigHandler.oreGenMin;
        int z = chunkZ*16 + random.nextInt(16);
        world.setBlock(x,y,z,ModBlocks.Ore);
        world.setBlock(x, y + 1, z, ModBlocks.Ore);

        if(random.nextBoolean()) {
            world.setBlock(x + 1, y, z, ModBlocks.Ore);
        }else{
            world.setBlock(x - 1, y, z, ModBlocks.Ore);
        }

        if(random.nextBoolean()) {
            world.setBlock(x, y, z + 1, ModBlocks.Ore);
        }else {
            world.setBlock(x, y, z - 1, ModBlocks.Ore);
        }
    }

}
