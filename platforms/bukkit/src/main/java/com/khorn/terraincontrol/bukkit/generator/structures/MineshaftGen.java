package com.khorn.terraincontrol.bukkit.generator.structures;

import com.khorn.terraincontrol.LocalBiome;
import com.khorn.terraincontrol.LocalWorld;
import com.khorn.terraincontrol.bukkit.util.WorldHelper;
import com.khorn.terraincontrol.util.minecraftTypes.StructureNames;
import net.minecraft.server.v1_8_R3.StructureGenerator;
import net.minecraft.server.v1_8_R3.StructureStart;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldGenMineshaftStart;

import java.util.Random;

public class MineshaftGen extends StructureGenerator
{
    // canSpawnStructureAtCoords
    @Override
    protected boolean a(int chunkX, int chunkZ)
    {
        Random rand = b;
        World worldMC = c;
        if (rand.nextInt(80) < Math.max(Math.abs(chunkX), Math.abs(chunkZ)))
        {
            LocalWorld world = WorldHelper.toLocalWorld(worldMC);
            LocalBiome biome = world.getBiome(chunkX * 16 + 8, chunkZ * 16 + 8);
            if (rand.nextDouble() * 100.0 < biome.getBiomeConfig().mineshaftsRarity)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    protected StructureStart b(int i, int j)
    {
        return new WorldGenMineshaftStart(this.c, this.b, i, j);
    }

    @Override
    public String a()
    {
        return StructureNames.MINESHAFT;
    }

}