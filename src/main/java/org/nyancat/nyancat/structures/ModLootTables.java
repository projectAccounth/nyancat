package org.nyancat.nyancat.structures;

import org.nyancat.nyancat.Nyancat;

import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModLootTables {
    public static final RegistryKey<LootTable> RAINBOW_CHEST_LOOT_TABLE = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(Nyancat.MOD_ID, "chests/rainbow_gift"));
    public static void initialize() 
    {
    }
}
