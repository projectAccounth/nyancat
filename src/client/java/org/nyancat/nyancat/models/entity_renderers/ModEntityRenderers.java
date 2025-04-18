package org.nyancat.nyancat.models.entity_renderers;

import org.nyancat.nyancat.entities.ModEntities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class ModEntityRenderers {
    public static void registerRenderers() {
        EntityRendererRegistry.register(ModEntities.CAT_TYPE, WeirdLookingCatRenderer::new);
        EntityRendererRegistry.register(ModEntities.NYANCAT_TYPE, NyancatRenderer::new);
        EntityRendererRegistry.register(ModEntities.TACNAYN_TYPE, TACNAYNRenderer::new);
    }
}