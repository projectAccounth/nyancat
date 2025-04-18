package org.nyancat.nyancat.models.entity_renderers;

import org.nyancat.nyancat.entities.NyancatEntity;
import org.nyancat.nyancat.models.ModModelLayers;
import org.nyancat.nyancat.models.entity_renderstates.NyancatRenderState;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class NyancatRenderer extends NyanTypeCatRenderer<NyancatEntity> {
    private static final Identifier NYANCAT = Identifier.of("nyancat", "textures/entities/nyancat.png");

    public NyancatRenderer(EntityRendererFactory.Context context) {
        super(context, ModModelLayers.NYANCAT_MODEL_LAYER);
    }

    @Override
    public Identifier getTexture(NyancatRenderState state) {
        return NYANCAT;
    }

    @Override
    public NyancatRenderState createRenderState() {
        return new NyancatRenderState();
    }
}