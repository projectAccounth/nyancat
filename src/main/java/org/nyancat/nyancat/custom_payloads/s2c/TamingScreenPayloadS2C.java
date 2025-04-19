package org.nyancat.nyancat.custom_payloads.s2c;

import org.nyancat.nyancat.Nyancat;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record TamingScreenPayloadS2C(int entityId, String name) implements CustomPayload {
    public static final Id<TamingScreenPayloadS2C> ID = new Id<>(Identifier.of(Nyancat.MOD_ID, "open_taming_screen"));
    public static final PacketCodec<RegistryByteBuf, TamingScreenPayloadS2C> CODEC =
        PacketCodec.tuple(PacketCodecs.INTEGER, TamingScreenPayloadS2C::entityId,
            PacketCodecs.STRING, TamingScreenPayloadS2C::name, TamingScreenPayloadS2C::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
