package org.nyancat.nyancat.custom_payloads;
import org.nyancat.nyancat.custom_payloads.c2s.ActionUpdatePayloadC2S;
import org.nyancat.nyancat.custom_payloads.c2s.LevelUpdatePayloadC2S;
import org.nyancat.nyancat.custom_payloads.c2s.NameUpdatePayloadC2S;
import org.nyancat.nyancat.entities.AbstractCatEntity;
import org.nyancat.nyancat.entities.AbstractCatEntity.CatAction;
import org.nyancat.nyancat.entities.AbstractCatEntity.LevelType;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class ServerHandler 
{
    public static void initialize()
    {
        PayloadTypeRegistry.playC2S().register(
            ActionUpdatePayloadC2S.ID,
            ActionUpdatePayloadC2S.CODEC
        );

        PayloadTypeRegistry.playC2S().register(
            LevelUpdatePayloadC2S.ID,
            LevelUpdatePayloadC2S.CODEC
        );

        PayloadTypeRegistry.playC2S().register(
            NameUpdatePayloadC2S.ID,
            NameUpdatePayloadC2S.CODEC
        );

        ServerPlayNetworking.registerGlobalReceiver(ActionUpdatePayloadC2S.ID, (payload, context) -> {
            context.server().execute(() -> {
                ServerWorld world = context.player().getServerWorld();
                AbstractCatEntity entity = (AbstractCatEntity) world.getEntityById(payload.entityId());

                entity.setAction(CatAction.values()[payload.actionOrdinal()]);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(LevelUpdatePayloadC2S.ID, (payload, context) -> {
            context.server().execute(() -> {
                ServerWorld world = context.player().getServerWorld();
                AbstractCatEntity entity = (AbstractCatEntity) world.getEntityById(payload.entityId());

                if (payload.isLevelUp()) {
                    entity.levelUp(payload.value(), payload.value(), payload.value());
                }
                else if (payload.value() > 0) {
                    entity.addLevelPoint(LevelType.values()[payload.valueOrdinal()]);
                }
                else if (payload.value() < 0) {
                    entity.decreaseLevelPoint(LevelType.values()[payload.valueOrdinal()]);
                }
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(NameUpdatePayloadC2S.ID, (payload, context) -> {
            context.server().execute(() -> {
                ServerWorld world = context.player().getServerWorld();
                AbstractCatEntity entity = (AbstractCatEntity) world.getEntityById(payload.entityId());

                entity.setCustomName(Text.literal(payload.name()));
            });
        });
    }
}
