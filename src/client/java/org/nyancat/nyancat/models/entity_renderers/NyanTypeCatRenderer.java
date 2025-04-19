package org.nyancat.nyancat.models.entity_renderers;

import org.joml.Matrix4f;
import org.nyancat.nyancat.Nyancat;
import org.nyancat.nyancat.entities.AbstractCatEntity;
import org.nyancat.nyancat.models.NyancatModel;
import org.nyancat.nyancat.models.entity_renderstates.NyancatRenderState;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public abstract class NyanTypeCatRenderer<T extends AbstractCatEntity> extends LivingEntityRenderer<T, NyancatRenderState, EntityModel<NyancatRenderState>> {

    public NyanTypeCatRenderer(EntityRendererFactory.Context context, EntityModelLayer layer) 
    {
        super(context, new NyancatModel(context.getPart(layer)), 0.75f);
    }

    @Override
    public void updateRenderState(T entity, NyancatRenderState state, float f) 
    {
        super.updateRenderState(entity, state, f);

        state.health = entity.getHealth();
        state.maxHealth = entity.getMaxHealth();
        state.shouldRenderHealthBar = entity.isTamed() && entity.getWorld().getClosestPlayer(entity, 15) != null;
        state.height = entity.getHeight();
    }

    public void renderHealthBar(NyancatRenderState state, float healthPercent, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        // Position it just above the name tag
        matrices.translate(0, state.getHeight() + 1f, 0);

        matrices.multiply(this.dispatcher.getRotation());
        
        matrices.scale(-1f, -1f, 1);

        matrices.multiply(RotationAxis.NEGATIVE_Z.rotationDegrees(180));

        MatrixStack.Entry entry = matrices.peek();
        Matrix4f positionMatrix = entry.getPositionMatrix();

        float barWidth = 1f;
        float barHeight = .15f;

        // Texture
        VertexConsumer buffer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(Identifier.of(Nyancat.MOD_ID, "textures/entities/health_bar.png")));

        drawTexturedQuad(buffer, positionMatrix, -barWidth / 2, 0, barWidth, barHeight, 0f, 0.5f, 1f, 1f, 0);

        // Foreground (colored health part)
        drawTexturedQuad(buffer, positionMatrix, -barWidth / 2, 0, barWidth * healthPercent, barHeight, 0f, 0f, healthPercent, 0.5f, 0.01f);  

        matrices.pop();
    }

    private void drawTexturedQuad(VertexConsumer buffer, Matrix4f matrix, float x, float y, float w, float h,
                              float u1, float v1, float u2, float v2, float offsetZ) {
        float z = offsetZ;
        int light = 0xF000F0; // full brightness
        int overlay = OverlayTexture.DEFAULT_UV;

        buffer.vertex(matrix, x,     y,     z).color(255, 255, 255, 255).texture(u1, v1).overlay(overlay).light(light).normal(0, 0, -1);
        buffer.vertex(matrix, x,     y + h, z).color(255, 255, 255, 255).texture(u1, v2).overlay(overlay).light(light).normal(0, 0, -1);
        buffer.vertex(matrix, x + w, y + h, z).color(255, 255, 255, 255).texture(u2, v2).overlay(overlay).light(light).normal(0, 0, -1);
        buffer.vertex(matrix, x + w, y,     z).color(255, 255, 255, 255).texture(u2, v1).overlay(overlay).light(light).normal(0, 0, -1);
    }

    @Override
    public void render(NyancatRenderState state,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.scale(.6f, .6f, .6f);
        super.render(state, matrices, vertexConsumers, light);

        double health = state.health;
        double maxHealth = state.maxHealth;
        if (health < maxHealth) {
            double healthPercent = health / maxHealth;
            renderHealthBar(state, (float) healthPercent, matrices, vertexConsumers, light);
        }
    }

    @Override
    public abstract Identifier getTexture(NyancatRenderState state);
}
