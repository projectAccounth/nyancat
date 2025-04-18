package org.nyancat.nyancat.models;

import org.nyancat.nyancat.Nyancat;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers 
{
    public static final EntityModelLayer WEIRD_LOOKING_CAT_MODEL_LAYER =
            new EntityModelLayer(Identifier.of(Nyancat.MOD_ID, "weird_looking_cat"), "main");
    public static final EntityModelLayer NYANCAT_MODEL_LAYER =
            new EntityModelLayer(Identifier.of(Nyancat.MOD_ID, "nyancat"), "main");
    public static final EntityModelLayer TACNAYN_MODEL_LAYER =
            new EntityModelLayer(Identifier.of(Nyancat.MOD_ID, "tacnayn"), "main");

    public static void initialize() 
    {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.WEIRD_LOOKING_CAT_MODEL_LAYER, WeirdLookingCatModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.NYANCAT_MODEL_LAYER, NyancatModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.TACNAYN_MODEL_LAYER, NyancatModel::getTexturedModelData);
    }
}