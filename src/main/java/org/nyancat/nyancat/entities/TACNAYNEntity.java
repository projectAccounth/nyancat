package org.nyancat.nyancat.entities;

import org.nyancat.nyancat.blocks.ModBlocks;
import org.nyancat.nyancat.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TACNAYNEntity extends AbstractCatEntity {
    @Override
    protected void emitParticles() {
        for (int i = 0; i < 2; i++) {
            this.getWorld().addParticleClient(
                new DustParticleEffect(0x4c4c4c, 0.7f),
                this.getX() + (this.random.nextDouble() - 0.5) * this.getWidth(),
                this.getY() + 0.5 + this.random.nextDouble() * 0.2,
                this.getZ() + (this.random.nextDouble() - 0.5) * this.getWidth(),
                0, 0.02, 0
            );
        }
    }
    
	protected TACNAYNEntity(EntityType<? extends AbstractCatEntity> entityType, World world) 
    {
		super(entityType, world);
        
	}

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) 
    {
        super.interactMob(player, hand);
        super.onInteract(player, ModItems.POP_TART, ModItems.TACNAYN_TREAT, "TACNAYN");
        return ActionResult.SUCCESS;
    }

    @Override
    public Block getSpawnedBlock()
    {
        return ModBlocks.WAFFLE_PILE;
    }
}