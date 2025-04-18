package org.nyancat.nyancat.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import org.nyancat.nyancat.Nyancat;

import java.util.function.Function;

public class ModBlocks {
    @SuppressWarnings("unused")
    private static void registerInstance(String name, Block block) 
    {
        Registry.register(Registries.BLOCK, Identifier.of(Nyancat.MOD_ID, name), block);
    }
    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) 
    {
        final Identifier identifier = Identifier.of(Nyancat.MOD_ID, path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        final Block block = Blocks.register(registryKey, factory, settings);
        Items.register(block);
        return block;
    }

    public static final Block WAFFLE_PILE = register("waffle_pile",
            WaffleBlock::new,
            AbstractBlock.Settings.create()
                    .strength(1.0f)
                    .sounds(BlockSoundGroup.METAL)
    );

    public static final Block POPTART_PILE = register("poptart_pile",
            PopTartBlock::new,
            AbstractBlock.Settings.create()
                    .strength(1.0f)
                    .sounds(BlockSoundGroup.METAL)
    );

    public static void initialize() {

    }
}
