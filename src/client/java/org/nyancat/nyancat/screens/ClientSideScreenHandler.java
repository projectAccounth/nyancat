package org.nyancat.nyancat.screens;

import org.nyancat.nyancat.custom_payloads.s2c.LevelingScreenPayloadS2C;
import org.nyancat.nyancat.custom_payloads.s2c.TamingScreenPayloadS2C;
import org.nyancat.nyancat.entities.AbstractCatEntity;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.entity.Entity;

public class ClientSideScreenHandler {
    public static void initialize()
    {
        PayloadTypeRegistry.playS2C().register(
            LevelingScreenPayloadS2C.ID,
            LevelingScreenPayloadS2C.CODEC
        );
        ClientPlayNetworking.registerGlobalReceiver(LevelingScreenPayloadS2C.ID, (payload, context) -> {
            context.client().execute(() -> {
                Entity entity = context.client().world.getEntityById(payload.entityId());
                if (entity != null) {
                    context.client().setScreen(new LevelGuiScreen((AbstractCatEntity) entity, payload.name()));
                }
            });
        });

        PayloadTypeRegistry.playS2C().register(
            TamingScreenPayloadS2C.ID,
            TamingScreenPayloadS2C.CODEC
        );
        ClientPlayNetworking.registerGlobalReceiver(TamingScreenPayloadS2C.ID, (payload, context) -> {
            context.client().execute(() -> {
                Entity entity = context.client().world.getEntityById(payload.entityId());
                if (entity != null) {
                    context.client().setScreen(new TameEntityScreen((AbstractCatEntity) entity, payload.name()));
                }
            });
        });
    }
}
