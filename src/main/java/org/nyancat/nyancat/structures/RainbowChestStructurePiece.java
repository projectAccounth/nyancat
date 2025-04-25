package org.nyancat.nyancat.structures;

import org.nyancat.nyancat.blocks.ModBlocks;
import org.nyancat.nyancat.blocks.RainbowEffectBlock;

import net.minecraft.block.Block;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.StructureContext;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class RainbowChestStructurePiece extends StructurePiece {

    protected BlockPos pos;

    public RainbowChestStructurePiece(BlockPos pos) {
        super(ModStructures.RAINBOW_GIFT_CHEST_TYPE, 0, new BlockBox(pos));
        this.pos = pos;
        System.out.println("Pos:" + pos);
    }


    public RainbowChestStructurePiece(StructureTemplateManager structureTemplateManager, BlockPos pos, BlockRotation rotation, BlockBox boundingBox) {
        super(ModStructures.RAINBOW_GIFT_CHEST_TYPE, 0, boundingBox);
        this.pos = pos;
    }

    public RainbowChestStructurePiece(StructureContext context, NbtCompound nbt)
    {
        super(ModStructures.RAINBOW_GIFT_CHEST_TYPE, nbt);
        this.pos = new BlockPos(nbt.getInt("PosX", 0), nbt.getInt("PosY", 0), nbt.getInt("PosZ", 0));
    }

    @Override
    public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator,
                        Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
        
        // Set up rotation and placement data
        StructurePlacementData placementData = createPlacementData(getRotation(), chunkBox);
        BlockPos offsetPos = this.pos; // the origin of this piece

        // Place the chests
        this.addChest(world, chunkBox, random, offsetPos.getX(), offsetPos.getY(), offsetPos.getZ() + 1, ModLootTables.RAINBOW_CHEST_LOOT_TABLE);
        this.addChest(world, chunkBox, random, offsetPos.getX(), offsetPos.getY(), offsetPos.getZ() - 2, ModLootTables.RAINBOW_CHEST_LOOT_TABLE);

        // Place the RainbowEffectBlock above each chest
        placeRainbowEffect(world, chunkBox, offsetPos);
    }

    // Helper to place the RainbowEffectBlock if within bounds
    private void placeRainbowEffect(StructureWorldAccess world, BlockBox chunkBox, BlockPos pos) {
        if (chunkBox.contains(pos)) {
            world.setBlockState(pos, ModBlocks.RAINBOW_EFFECT_BLOCK
                .getDefaultState()
                .with(RainbowEffectBlock.SHOULD_RENDER, true), Block.NOTIFY_ALL);
        }
    }

    @SuppressWarnings("unused")
    private BlockPos offsetPos(BlockPos origin, int dx, int dy, int dz) 
    {
        Direction dir = getRotation().rotate(Direction.NORTH);
        return origin.add(
            dx * dir.getOffsetX() + dz * dir.rotateYClockwise().getOffsetX(),
            dy,
            dx * dir.getOffsetZ() + dz * dir.rotateYClockwise().getOffsetZ()
        );
    }


    @Override
    protected void writeNbt(StructureContext context, NbtCompound nbt) 
    {
        nbt.putInt("PosX", pos.getX());
        nbt.putInt("PosY", pos.getY());
        nbt.putInt("PosZ", pos.getZ());
    }

    protected StructurePlacementData createPlacementData(BlockRotation rotation, BlockBox box) 
    {
        StructurePlacementData structurePlacementData = new StructurePlacementData();
        structurePlacementData.setBoundingBox(box);
        structurePlacementData.setRotation(rotation);
        structurePlacementData.setUpdateNeighbors(true);
        structurePlacementData.setIgnoreEntities(false);
        structurePlacementData.addProcessor(BlockIgnoreStructureProcessor.IGNORE_AIR);
        structurePlacementData.setInitializeMobs(true);

        return structurePlacementData;
   }
}
