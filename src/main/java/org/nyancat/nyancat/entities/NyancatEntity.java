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

public class NyancatEntity extends AbstractCatEntity {

    private static final int[] RAINBOW_COLORS = new int[] {
        0xff0000,   // Red
        0xff7f00,   // Orange
        0xffff00,   // Yellow
        0x00ff00,   // Green
        0x007fff,   // Cyan/Blue
        0x4a0080, // Indigo
        0x9400d4  // Violet
    };

    @Override
    protected void emitParticles() {
        for (int i = 0; i < 3; i++) {
            int color = RAINBOW_COLORS[this.random.nextInt(RAINBOW_COLORS.length)];
            float scale = 0.5f + this.random.nextFloat() * 0.3f;

            this.getWorld().addParticleClient(
                new DustParticleEffect(color, scale),
                this.getX() + (this.random.nextDouble() - 0.5) * this.getWidth(),
                this.getY() + 0.5 + this.random.nextDouble() * 0.5,
                this.getZ() + (this.random.nextDouble() - 0.5) * this.getWidth(),
                0, 0.01, 0
            );
        }
    }

	protected NyancatEntity(EntityType<? extends AbstractCatEntity> entityType, World world) 
    {
		super(entityType, world);
	}

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) 
    {
        super.interactMob(player, hand);
        super.onInteract(player, ModItems.WAFFLE, ModItems.NYANCAT_TREAT, "Nyancat");
        return ActionResult.SUCCESS;
    }

    @Override
    public Block getSpawnedBlock()
    {
        return ModBlocks.POPTART_PILE;
    }
}