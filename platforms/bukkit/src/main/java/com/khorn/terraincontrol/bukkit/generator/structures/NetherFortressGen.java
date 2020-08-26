package com.khorn.terraincontrol.bukkit.generator.structures;

import com.khorn.terraincontrol.LocalBiome;
import com.khorn.terraincontrol.LocalWorld;
import com.khorn.terraincontrol.bukkit.util.WorldHelper;
import com.khorn.terraincontrol.util.minecraftTypes.StructureNames;
import net.minecraft.server.v1_8_R3.BiomeBase.BiomeMeta;
import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.WorldGenNether.WorldGenNetherStart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NetherFortressGen extends StructureGenerator
{

    public List<BiomeMeta> spawnList = new ArrayList<BiomeMeta>();

    public NetherFortressGen()
    {
        this.spawnList.add(new BiomeMeta(EntityBlaze.class, 10, 2, 3));
        this.spawnList.add(new BiomeMeta(EntityPigZombie.class, 5, 4, 4));
        this.spawnList.add(new BiomeMeta(EntitySkeleton.class, 10, 4, 4));
        this.spawnList.add(new BiomeMeta(EntityMagmaCube.class, 3, 4, 4));
    }

    @SuppressWarnings("rawtypes")
    public List b()
    {
        return this.spawnList;
    }

    // canSpawnAtChunkCoords
    @Override
    protected boolean a(int chunkX, int chunkZ)
    {
        Random rand = this.b;
        World worldObj = this.c;

        int var3 = chunkX >> 4;
        int var4 = chunkZ >> 4;
        rand.setSeed(var3 ^ var4 << 4 ^ worldObj.getSeed());
        rand.nextInt();

        if (rand.nextInt(3) != 0)
        {
            return false;
        } else
        {
            if (chunkX != (var3 << 4) + 4 + rand.nextInt(8))
            {
                return false;
            } else
            {
                LocalWorld world = WorldHelper.toLocalWorld(worldObj);
                LocalBiome biome = world.getBiome(chunkX * 16 + 8, chunkZ * 16 + 8);
                if (!biome.getBiomeConfig().netherFortressesEnabled)
                {
                    return false;
                }
                return (chunkZ == (var4 << 4) + 4 + rand.nextInt(8));
            }
        }
    }

    @Override
    protected StructureStart b(int i, int j)
    {
        return new WorldGenNetherStart(this.c, this.b, i, j);
    }

    @Override
    public String a()
    {
        return StructureNames.NETHER_FORTRESS;
    }
}
