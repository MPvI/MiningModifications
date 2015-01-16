package com.ragingart.miningmodifications.init;

import com.ragingart.miningmodifications.handler.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class ModWorldgen implements IWorldGenerator {

    public ModWorldgen(){
        GameRegistry.registerWorldGenerator(this, 5);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        int x = chunkX*16 + random.nextInt(16);
        int y = random.nextInt(ConfigHandler.oreGenMax-ConfigHandler.oreGenMin)+ ConfigHandler.oreGenMin;
        int z = chunkZ*16 + random.nextInt(16);
        setBlock(world,x,y,z,ModBlocks.Ore);
        setBlock(world,x, y + 1, z, ModBlocks.Ore);

        if(random.nextBoolean()) {
            setBlock(world,x + 1, y, z, ModBlocks.Ore);
        }else{
            setBlock(world,x - 1, y, z, ModBlocks.Ore);
        }

        if(random.nextBoolean()) {
            setBlock(world,x, y, z + 1, ModBlocks.Ore);
        }else {
            setBlock(world,x, y, z - 1, ModBlocks.Ore);
        }
    }

    public void setBlock(World world,int x,int y,int z,Block block){
        if(world.getBlockState(new BlockPos(x, y, z)).getBlock()==Blocks.stone){
            world.setBlockState(new BlockPos(x, y, z),block.getDefaultState());
        }
    }

}
