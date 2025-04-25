package org.nyancat.nyancat.items;

import org.nyancat.nyancat.Nyancat;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup TEST_GROUP = new ItemGroup.Builder(ItemGroup.Row.TOP, 5)
            .icon(() -> new ItemStack(ModItems.POP_TART))
            .displayName(Text.translatable("itemGroup.nyancat.mod_group"))
            .entries((context, entries) -> {
                entries.add(ModItems.POP_TART);
                entries.add(ModItems.WAFFLE);
                entries.add(ModItems.NYANCAT_DISK);
                entries.add(ModItems.NYANCAT_TREAT);
                entries.add(ModItems.TACNAYN_DISK);
                entries.add(ModItems.TACNAYN_TREAT);
            })
            .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, Identifier.of(Nyancat.MOD_ID, "mod_group"), TEST_GROUP);
    }
}
