package org.nyancat.nyancat.screens;

import org.nyancat.nyancat.entities.AbstractCatEntity;
import org.nyancat.nyancat.screen_payloads.LevelingScreenPayload;
import org.nyancat.nyancat.screen_payloads.TamingScreenPayload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.entity.Entity;

public class ClientSideScreenHandler {
    public static void initialize()
    {
        PayloadTypeRegistry.playS2C().register(
            LevelingScreenPayload.ID,
            LevelingScreenPayload.CODEC
        );
        ClientPlayNetworking.registerReceiver(LevelingScreenPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                Entity entity = context.client().world.getEntityById(payload.entityId());
                if (entity != null) {
                    context.client().setScreen(new LevelGuiScreen((AbstractCatEntity) entity, payload.name()));
                }
            });
        });

        PayloadTypeRegistry.playS2C().register(
            TamingScreenPayload.ID,
            TamingScreenPayload.CODEC
        );
        ClientPlayNetworking.registerReceiver(TamingScreenPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                Entity entity = context.client().world.getEntityById(payload.entityId());
                if (entity != null) {
                    context.client().setScreen(new TameEntityScreen((AbstractCatEntity) entity, payload.name()));
                }
            });
        });
    }
}
