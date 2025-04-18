package org.nyancat.nyancat.blocks;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractResourceBlock extends Block 
{
    public AbstractResourceBlock(Settings settings) 
    {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, BlockHitResult hit) 
    {
        if (!world.isClient) {
            dropItems(world, pos);
            world.removeBlock(pos, false); // destroy the block
        }
        return ActionResult.SUCCESS;
    }

    /**
     * Checks whether the given item stack has a specific enchantment, by RegistryKey.
     * Fuck it, the API does not provide a reliable way to check for enchantment, so a messy method must be implemented.
     * 
     * @param stack the item stack to check
     * @param enchantmentKey the registry key of the enchantment (e.g., Enchantments.SILK_TOUCH)
     * @return true if the enchantment is present
     */
    public static boolean hasEnchantment(ItemStack stack, RegistryKey<Enchantment> enchantmentKey) 
    {
        Set<RegistryEntry<Enchantment>> enchantments = EnchantmentHelper.getEnchantments(stack).getEnchantments();

        return enchantments.stream()
            .anyMatch(entry -> entry.matches(key -> key.equals(enchantmentKey)));
    }

    public static boolean hasSilkTouch(ItemStack stack) 
    {
        return hasEnchantment(stack, net.minecraft.enchantment.Enchantments.SILK_TOUCH);
    }
    
    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) 
    {
        if (!world.isClient) {
            if (!player.isCreative()) {
                boolean silkTouch = hasSilkTouch(player.getMainHandStack());
                if (silkTouch) {
                    ItemStack stack = new ItemStack(this);
                    Block.dropStack(world, pos, stack);
                } else {
                    dropItems(world, pos);
                }
            }
        }
        return super.onBreak(world, pos, state, player);
    }

    public abstract void dropItems(World world, BlockPos pos);
}
