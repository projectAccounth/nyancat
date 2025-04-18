package org.nyancat.nyancat.models.entity_renderers;

import org.nyancat.nyancat.entities.TACNAYNEntity;
import org.nyancat.nyancat.models.ModModelLayers;
import org.nyancat.nyancat.models.entity_renderstates.NyancatRenderState;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class TACNAYNRenderer extends NyanTypeCatRenderer<TACNAYNEntity> {
    private static final Identifier NYANCAT = Identifier.of("nyancat", "textures/entities/tacnayn.png");

    public TACNAYNRenderer(EntityRendererFactory.Context context) 
    {
        super(context, ModModelLayers.TACNAYN_MODEL_LAYER);
    }

    @Override
    public Identifier getTexture(NyancatRenderState state) 
    {
        return NYANCAT;
    }

    @Override
    public NyancatRenderState createRenderState() 
    {
        return new NyancatRenderState();
    }
}