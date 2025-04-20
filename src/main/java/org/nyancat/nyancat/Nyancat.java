package org.nyancat.nyancat;

import org.nyancat.nyancat.blocks.ModBlocks;
import org.nyancat.nyancat.custom_payloads.ServerHandler;
import org.nyancat.nyancat.entities.ModEntities;
import org.nyancat.nyancat.items.ModItems;
import org.nyancat.nyancat.mod_sounds.ModSounds;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Nyancat implements ModInitializer {
    public static final String MOD_ID = "nyancat";

    public static final Identifier TAMING_SCREEN_PACKET = Identifier.of(Nyancat.MOD_ID, "open_taming_screen");
    public static final Identifier LEVELING_SCREEN_PACKET = Identifier.of(Nyancat.MOD_ID, "open_leveling_screen");

    @Override
    public void onInitialize() 
    {
        ModItems.initialize();
        ModEntities.initialize();
        ModBlocks.initialize();
        ServerHandler.initialize();
        ModSounds.initialize();
    }
}