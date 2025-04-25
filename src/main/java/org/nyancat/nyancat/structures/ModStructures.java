package org.nyancat.nyancat.structures;

import java.util.Locale;

import org.nyancat.nyancat.Nyancat;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public class ModStructures {
    public static final StructureType<RainbowGiftChestStructure> RAINBOW_GIFT_CHEST =
        Registry.register(Registries.STRUCTURE_TYPE, Identifier.of(Nyancat.MOD_ID, "rainbow_gift_chest"),
            () -> RainbowGiftChestStructure.CODEC);

    public static final StructurePieceType RAINBOW_GIFT_CHEST_TYPE = register(RainbowChestStructurePiece::new, "rcsp");

    public static final RegistryKey<Structure> RAINBOW_GIFT_CHEST_KEY = RegistryKey.of(RegistryKeys.STRUCTURE, Identifier.of(Nyancat.MOD_ID, "rainbow_gift_chest"));

    private static StructurePieceType register(StructurePieceType type, String id) {
        return (StructurePieceType)Registry.register(Registries.STRUCTURE_PIECE, id.toLowerCase(Locale.ROOT), type);
    }

    public static void initialize() 
    {
        
    }
}
