package org.nyancat.nyancat.custom_payloads.c2s;

import org.nyancat.nyancat.Nyancat;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

// Note: actionOrdinal is the ordinal of the action, which is CatAction[i].
public record ActionUpdatePayloadC2S(int entityId, int actionOrdinal) implements CustomPayload {
    public static final Id<ActionUpdatePayloadC2S> ID = new Id<>(Identifier.of(Nyancat.MOD_ID, "action_update_signal"));
    public static final PacketCodec<RegistryByteBuf, ActionUpdatePayloadC2S> CODEC =
        PacketCodec.tuple(PacketCodecs.INTEGER, ActionUpdatePayloadC2S::entityId,
                    PacketCodecs.INTEGER, ActionUpdatePayloadC2S::actionOrdinal, ActionUpdatePayloadC2S::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}