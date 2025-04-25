package org.nyancat.nyancat.entities;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.entity.SpawnGroup;

public class ModEntitySpawns {
    public static void initialize() {
        BiomeModifications.addSpawn(
                context -> { return true; },
                SpawnGroup.CREATURE,
                ModEntities.NYANCAT_TYPE,
                5, 1, 1
        );

        BiomeModifications.addSpawn(
                context -> { return true; },
                SpawnGroup.CREATURE,
                ModEntities.TACNAYN_TYPE,
                5, 1, 1
        );

        BiomeModifications.addSpawn(
                context -> { return true; },
                SpawnGroup.CREATURE,
                ModEntities.CAT_TYPE,
                10, 1, 3
        );
    }
}
