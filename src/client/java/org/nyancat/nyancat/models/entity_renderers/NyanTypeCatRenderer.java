package org.nyancat.nyancat.models.entity_renderers;

import org.joml.Matrix4f;
import org.nyancat.nyancat.entities.AbstractCatEntity;
import org.nyancat.nyancat.models.NyancatModel;
import org.nyancat.nyancat.models.entity_renderstates.NyancatRenderState;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;

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
        state.shouldRenderHealthBar = entity.isTamed();
        state.height = entity.getHeight();
    }

    public void renderHealthBar(NyancatRenderState state, float healthPercent, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        // Position it just above the name tag
        matrices.translate(0, state.getHeight() + 0.5f, 0);
        matrices.multiply(MinecraftClient.getInstance().gameRenderer.getCamera().getRotation());
        matrices.scale(-0.025f, -0.025f, 0.025f); // Scale to look nice in world space

        MatrixStack.Entry entry = matrices.peek();
        Matrix4f positionMatrix = entry.getPositionMatrix();

        float barWidth = 40f;
        float barHeight = 5f;
        float full = barWidth * healthPercent;

        VertexConsumer buffer = vertexConsumers.getBuffer(RenderLayer.getLines());

        // Background (dark gray)
        drawQuad(buffer, positionMatrix, 0, 0, barWidth, barHeight, 0.1f, 0.1f, 0.1f, 1f);

        // Foreground (green health)
        drawQuad(buffer, positionMatrix, 0, 0, full, barHeight, 0f, 1f, 0f, 1f);

        matrices.pop();
    }

    private void drawQuad(VertexConsumer buffer, Matrix4f matrix, float x, float y, float w, float h, float r, float g, float b, float a) {
        buffer.vertex(matrix, x, y, 0).color(r, g, b, a).light(0xF000F0).overlay(OverlayTexture.DEFAULT_UV).normal(0, 0, -1);
        buffer.vertex(matrix, x, y + h, 0).color(r, g, b, a).light(0xF000F0).overlay(OverlayTexture.DEFAULT_UV).normal(0, 0, -1);
        buffer.vertex(matrix, x + w, y + h, 0).color(r, g, b, a).light(0xF000F0).overlay(OverlayTexture.DEFAULT_UV).normal(0, 0, -1);
        buffer.vertex(matrix, x + w, y, 0).color(r, g, b, a).light(0xF000F0).overlay(OverlayTexture.DEFAULT_UV).normal(0, 0, -1);
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
}
