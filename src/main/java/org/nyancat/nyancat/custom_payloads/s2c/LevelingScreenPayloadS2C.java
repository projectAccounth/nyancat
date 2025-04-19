package org.nyancat.nyancat.custom_payloads.s2c;

import org.nyancat.nyancat.Nyancat;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

// "name" here is the Entity's class name used for displaying things like "Nyancat Taming Screen", etc.
public record LevelingScreenPayloadS2C(int entityId, String name) implements CustomPayload {
    public static final Id<LevelingScreenPayloadS2C> ID = new Id<>(Identifier.of(Nyancat.MOD_ID, "open_leveling_screen"));
    public static final PacketCodec<RegistryByteBuf, LevelingScreenPayloadS2C> CODEC =
        PacketCodec.tuple(PacketCodecs.INTEGER, LevelingScreenPayloadS2C::entityId,
            PacketCodecs.STRING, LevelingScreenPayloadS2C::name, LevelingScreenPayloadS2C::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}