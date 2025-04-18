package org.nyancat.nyancat.entities;

import org.nyancat.nyancat.Nyancat;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final RegistryKey<EntityType<?>> CAT_KEY = RegistryKey.of(
            Registries.ENTITY_TYPE.getKey(),
            Identifier.of(Nyancat.MOD_ID, "weird_looking_cat")
    );

    public static final EntityType<WeirdLookingCatEntity> CAT_TYPE = EntityType.Builder.create(WeirdLookingCatEntity::new, SpawnGroup.CREATURE)
            .dimensions(1f, 1f)
            .build(CAT_KEY);

    public static final RegistryKey<EntityType<?>> NYANCAT_KEY = RegistryKey.of(
            Registries.ENTITY_TYPE.getKey(),
            Identifier.of(Nyancat.MOD_ID, "nyancat")
    );

    public static final EntityType<NyancatEntity> NYANCAT_TYPE = EntityType.Builder.create(NyancatEntity::new, SpawnGroup.CREATURE)
            .dimensions(1f, 1f)
            .build(NYANCAT_KEY);

    public static final RegistryKey<EntityType<?>> TACNAYN_KEY = RegistryKey.of(
            Registries.ENTITY_TYPE.getKey(),
            Identifier.of(Nyancat.MOD_ID, "tacnayn")
    );

    public static final EntityType<TACNAYNEntity> TACNAYN_TYPE = EntityType.Builder.create(TACNAYNEntity::new, SpawnGroup.CREATURE)
            .dimensions(1f, 1f)
            .build(TACNAYN_KEY);

    public static void initialize() 
    {
        Registry.register(Registries.ENTITY_TYPE, NYANCAT_KEY, NYANCAT_TYPE);
        FabricDefaultAttributeRegistry.register(NYANCAT_TYPE, NyancatEntity.createMobAttributes());
        Registry.register(Registries.ENTITY_TYPE, TACNAYN_KEY, TACNAYN_TYPE);
        FabricDefaultAttributeRegistry.register(TACNAYN_TYPE, NyancatEntity.createMobAttributes());
        Registry.register(Registries.ENTITY_TYPE, CAT_KEY, CAT_TYPE);
        FabricDefaultAttributeRegistry.register(CAT_TYPE, WeirdLookingCatEntity.createMobAttributes());
    }
}
