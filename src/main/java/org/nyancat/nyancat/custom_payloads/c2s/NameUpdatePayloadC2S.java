package org.nyancat.nyancat.custom_payloads.c2s;

import org.nyancat.nyancat.Nyancat;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

// Note: valueOrdinal is LevelType[i] (which is the ordinal), and value is the increment.
public record NameUpdatePayloadC2S(int entityId, String name) implements CustomPayload {
    public static final Id<NameUpdatePayloadC2S> ID = new Id<>(Identifier.of(Nyancat.MOD_ID, "name_update_signal"));
    public static final PacketCodec<RegistryByteBuf, NameUpdatePayloadC2S> CODEC =
        PacketCodec.tuple(PacketCodecs.INTEGER, NameUpdatePayloadC2S::entityId,
                    PacketCodecs.STRING, NameUpdatePayloadC2S::name, NameUpdatePayloadC2S::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}