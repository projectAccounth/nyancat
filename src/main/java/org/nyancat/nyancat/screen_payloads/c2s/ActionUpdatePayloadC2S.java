package org.nyancat.nyancat.screen_payloads.c2s;

import org.nyancat.nyancat.Nyancat;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ActionUpdatePayloadS2C(int entityId, String name) implements CustomPayload {
    public static final Id<ActionUpdatePayloadS2C> ID = new Id<>(Identifier.of(Nyancat.MOD_ID, "open_leveling_screen"));
    public static final PacketCodec<RegistryByteBuf, ActionUpdatePayloadS2C> CODEC =
        PacketCodec.tuple(PacketCodecs.INTEGER, ActionUpdatePayloadS2C::entityId,
            PacketCodecs.STRING, ActionUpdatePayloadS2C::name, ActionUpdatePayloadS2C::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}