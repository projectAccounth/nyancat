package org.nyancat.nyancat.models.entity_renderstates;

import org.nyancat.nyancat.entities.WeirdLookingCatEntity.CAT_VARIANT;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;

public class WeirdLookingCatRenderState extends LivingEntityRenderState {
    public CAT_VARIANT variant;

    public WeirdLookingCatRenderState() 
    {
        super();
        variant = CAT_VARIANT.GINGER;
    }
}
