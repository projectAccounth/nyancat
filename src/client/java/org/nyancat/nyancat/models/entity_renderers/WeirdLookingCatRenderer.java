package org.nyancat.nyancat.models.entity_renderers;

import org.jetbrains.annotations.NotNull;
import org.nyancat.nyancat.entities.WeirdLookingCatEntity;
import org.nyancat.nyancat.models.ModModelLayers;
import org.nyancat.nyancat.models.WeirdLookingCatModel;
import org.nyancat.nyancat.models.entity_renderstates.WeirdLookingCatRenderState;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class WeirdLookingCatRenderer extends LivingEntityRenderer<WeirdLookingCatEntity, WeirdLookingCatRenderState, EntityModel<WeirdLookingCatRenderState>> {
    private static final Identifier CAT_GRAY = Identifier.of("nyancat", "textures/entities/cat1.png");
    private static final Identifier CAT_BLACK = Identifier.of("nyancat", "textures/entities/cat2.png");
    private static final Identifier CAT_GINGER = Identifier.of("nyancat", "textures/entities/cat3.png");

    public WeirdLookingCatRenderer(EntityRendererFactory.Context context) {
        super(context, new WeirdLookingCatModel(context.getPart(ModModelLayers.WEIRD_LOOKING_CAT_MODEL_LAYER)), 0.75f);
    }

    @Override
    public Identifier getTexture(WeirdLookingCatRenderState state) {
        switch (state.variant) {
            case GINGER: return CAT_GINGER;
            case GREY: return CAT_GRAY;
            case BLACK: return CAT_BLACK;
            default : break;
        }
        return CAT_GINGER; // Fallback
    }

    @Override
    public WeirdLookingCatRenderState createRenderState() {
        return new WeirdLookingCatRenderState();
    }

    @Override
    public void updateRenderState(WeirdLookingCatEntity entity, WeirdLookingCatRenderState state, float f) {
        super.updateRenderState(entity, state, f);
        state.variant = entity.getVariant();
    }

    @Override
    public void render(@NotNull WeirdLookingCatRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(state, matrices, vertexConsumers, light); // What am I supposed to do?
    }
}
