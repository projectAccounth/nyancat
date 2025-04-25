package org.nyancat.nyancat.blocks;

import org.jetbrains.annotations.Nullable;
import org.nyancat.nyancat.RainbowParticle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;

public class RainbowEffectBlock extends Block {
    public static final BooleanProperty SHOULD_RENDER = BooleanProperty.of("should_render");

    public RainbowEffectBlock(Settings settings) 
    {
        super(settings.dropsNothing());
        this.setDefaultState(this.stateManager.getDefaultState().with(SHOULD_RENDER, true));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) 
    {
        builder.add(SHOULD_RENDER);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) 
    {
        if (!world.isClient && !state.get(SHOULD_RENDER)) {
            world.breakBlock(pos, false); // break without dropping
        } else if (!world.isClient) {
            RainbowParticle.spawnRainbowArc((ServerWorld) world, pos);
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) 
    {
        return true;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) 
    {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) 
    {
        if (!state.get(SHOULD_RENDER)) {
            world.breakBlock(pos, false); // clean up if somehow still here
        }
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) 
    {
        if (state.get(SHOULD_RENDER))
            RainbowParticle.spawnRainbowArc(world, pos);
    }

    @Override
    public boolean isTransparent(BlockState state) 
    {
        return true;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) 
    {
        return VoxelShapes.empty();
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) 
    {
        return VoxelShapes.empty();
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        // Any changes self-destruct the block
        if (!world.isClient) {
            world.setBlockState(pos, state.with(SHOULD_RENDER, false), Block.NOTIFY_ALL);
        }
    }
}