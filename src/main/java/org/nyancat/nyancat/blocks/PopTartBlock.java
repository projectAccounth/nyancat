package org.nyancat.nyancat.blocks;

import org.nyancat.nyancat.items.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PopTartBlock extends AbstractResourceBlock {

    public PopTartBlock(Settings settings) 
    {
        super(settings);
    }

    @Override
    public void dropItems(World world, BlockPos pos) 
    {
        if (world.isClient) return;
        ItemScatterer.spawn(world, pos, DefaultedList.ofSize(2, new ItemStack(ModItems.POP_TART, 2)));
    }
}
