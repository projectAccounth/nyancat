package org.nyancat.nyancat.structures;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class RainbowGiftChestStructure extends Structure {
    public static final MapCodec<RainbowGiftChestStructure> CODEC = createCodec(RainbowGiftChestStructure::new);

    public RainbowGiftChestStructure(Structure.Config config) {
        super(config);
    }

    @Override
    public Optional<Structure.StructurePosition> getStructurePosition(
            Structure.Context context
    ) {
        int surfaceY = context.chunkGenerator().getHeight(context.chunkPos().x, context.chunkPos().z, Heightmap.Type.WORLD_SURFACE_WG, context.world(), context.noiseConfig());
        return getStructurePosition(context, Heightmap.Type.WORLD_SURFACE_WG, (collector) -> {
            collector.addPiece(new RainbowChestStructurePiece(
                context.chunkPos()
                    .getBlockPos(0, surfaceY, 0)
                )
            );
        });
    }

    @Override
    public StructureType<?> getType() {
        return ModStructures.RAINBOW_GIFT_CHEST;
    }
}
