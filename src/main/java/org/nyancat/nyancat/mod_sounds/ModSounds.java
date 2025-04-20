package org.nyancat.nyancat.mod_sounds;

import org.nyancat.nyancat.Nyancat;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent NYANCAT_MUSIC =
            SoundEvent.of(Identifier.of(Nyancat.MOD_ID, "nyancat_music"));
    public static final SoundEvent TACNAYN_MUSIC =
            SoundEvent.of(Identifier.of(Nyancat.MOD_ID, "tacnayn_music"));

    public static final RegistryKey<JukeboxSong> NYANCAT_M = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(Nyancat.MOD_ID, "nyancat_music"));

    public static final RegistryKey<JukeboxSong> TACNAYN_M = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(Nyancat.MOD_ID, "tacnayn_music"));

    public static void initialize() {
        Registry.register(
                Registries.SOUND_EVENT,
                Identifier.of(Nyancat.MOD_ID, "nyancat_music"),
                NYANCAT_MUSIC
        );
        Registry.register(
                Registries.SOUND_EVENT,
                Identifier.of(Nyancat.MOD_ID, "tacnayn_music"),
                TACNAYN_MUSIC
        );
    }
}
