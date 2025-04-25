package org.nyancat.nyancat;

import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RainbowParticle {

    // Rainbow colors in hex (standard ROYGBIV)
    private static final int[] RAINBOW_HEX = new int[]{
        0xFF0000, // Red
        0xFF7F00, // Orange
        0xFFFF00, // Yellow
        0x00FF00, // Green
        0x0000FF, // Blue
        0x4B0082, // Indigo
        0x8B00FF  // Violet
    };

    public static void spawnRainbowArc(World world, BlockPos origin) {
        double radius = 2.0;
        int steps = 40;

        for (int i = 0; i < steps; i++) {
            double t = (Math.PI * i) / steps;

            for (int band = 0; band < RAINBOW_HEX.length; band++) {
                int color = RAINBOW_HEX[band];

                double x = radius * Math.cos(t);
                double y = radius * Math.sin(t) + 0.15 * band;
                double z = 0;

                Vec3d particlePos = new Vec3d(
                    origin.getX() + 0.5 + x,
                    origin.getY() + 1.5 + y,
                    origin.getZ() + 0.5 + z
                );
                DustParticleEffect particle = new DustParticleEffect(color, 1.0f);
                if (world instanceof ServerWorld serverWorld) {
                    // Server-side particle spawning
                    serverWorld.spawnParticles(particle, particlePos.x, particlePos.y, particlePos.z, 1, 0, 0, 0, 0);
                } else {
                    // Client-side particle spawning
                    world.addParticleClient(particle, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
                }
            }
        }
    }
}