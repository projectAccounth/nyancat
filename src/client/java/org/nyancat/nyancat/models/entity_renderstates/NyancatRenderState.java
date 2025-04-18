package org.nyancat.nyancat.models.entity_renderstates;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;

public class NyancatRenderState extends LivingEntityRenderState {
    public double health;
    public double maxHealth;
    public double height;
    public boolean shouldRenderHealthBar;

    public boolean shouldRenderHealthBar()
    {
        return shouldRenderHealthBar;
    }

    public double getHeight()
    {
        return height;
    }

    public NyancatRenderState() 
    {
        super();
        health = 0;
        maxHealth = 1;
        shouldRenderHealthBar = false;
        height = 0;
    }
}
