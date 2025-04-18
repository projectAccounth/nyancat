package org.nyancat.nyancat.items;

import net.minecraft.item.*;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import org.nyancat.nyancat.Nyancat;

public class ModItems {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Nyancat.MOD_ID, name));

        Item item = itemFactory.apply(settings.registryKey(itemKey));

        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static Item POP_TART = register("pop_tart", Item::new, new Item.Settings());

    public static Item POISONED_POP_TART = register("poisoned_pop_tart", Item::new, new Item.Settings());

    public static Item WAFFLE = register("waffle", Item::new, new Item.Settings());

    public static Item NYANCAT_TREAT = register("nyancat_treat", Item::new, new Item.Settings());
    public static Item TACNAYN_TREAT = register("tacnayn_treat", Item::new, new Item.Settings());

    public static Item NYANCAT_DISK = register("nyancat_disk", Item::new, new Item.Settings());
    public static Item TACNAYN_DISK = register("tacnayn_disk", Item::new, new Item.Settings());

    public static void initialize() {}
}