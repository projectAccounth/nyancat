package org.nyancat.nyancat.custom_payloads.c2s;

import org.nyancat.nyancat.Nyancat;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

// Note: valueOrdinal is LevelType[i] (which is the ordinal), and value is the increment.
public record LevelUpdatePayloadC2S(int entityId, int valueOrdinal, int value, boolean isLevelUp) implements CustomPayload {
    public static final Id<LevelUpdatePayloadC2S> ID = new Id<>(Identifier.of(Nyancat.MOD_ID, "level_update_signal"));
    public static final PacketCodec<RegistryByteBuf, LevelUpdatePayloadC2S> CODEC =
        PacketCodec.tuple(PacketCodecs.INTEGER, LevelUpdatePayloadC2S::entityId,
                    PacketCodecs.INTEGER, LevelUpdatePayloadC2S::valueOrdinal,
                    PacketCodecs.INTEGER, LevelUpdatePayloadC2S::value,
                    PacketCodecs.BOOLEAN, LevelUpdatePayloadC2S::isLevelUp, LevelUpdatePayloadC2S::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}