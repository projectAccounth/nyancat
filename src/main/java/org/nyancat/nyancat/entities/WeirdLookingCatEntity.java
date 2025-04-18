package org.nyancat.nyancat.entities;

import org.jetbrains.annotations.Nullable;
import org.nyancat.nyancat.items.ModItems;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class WeirdLookingCatEntity extends MobEntity {

    public static enum CAT_VARIANT 
    {
        GREY,
        BLACK,
        GINGER
    };

    public static final TrackedData<Integer> variant = DataTracker.registerData(WeirdLookingCatEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public WeirdLookingCatEntity(EntityType<WeirdLookingCatEntity> entityType, World world) 
    {
        super(entityType, world);
    }

	public CAT_VARIANT getVariant() 
    {
        return CAT_VARIANT.values()[this.dataTracker.get(variant)];
    }

    public void setVariant(CAT_VARIANT var) 
    {
        this.dataTracker.set(variant, var.ordinal());
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) 
    {
        super.initDataTracker(builder);
        builder.add(variant, CAT_VARIANT.GINGER.ordinal());
    }

    @Override
    public EntityData initialize(
        ServerWorldAccess world,
        LocalDifficulty difficulty,
        SpawnReason spawnReason,
        @Nullable EntityData entityData) 
    {
        this.dataTracker.set(variant, this.getRandom().nextInt(CAT_VARIANT.values().length));
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() 
    {
        return LivingEntity.createLivingAttributes()
                .add(EntityAttributes.MAX_HEALTH, 10.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.FOLLOW_RANGE, 100.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, .5)
                .add(EntityAttributes.ATTACK_KNOCKBACK, .5);
    }
    
	@Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) 
    {
        if (this.getWorld().isClient()) return super.interactMob(player, hand);

        ItemStack itemStack = player.getMainHandStack();

        if (itemStack.isOf(ModItems.POP_TART) && getVariant() == CAT_VARIANT.GREY) {
            if (this.getRandom().nextFloat() <= 0.25f) {
                spawnNyancat();
            }
            if (!player.isCreative()) itemStack.decrement(1);
            return ActionResult.SUCCESS;
        }

        if (itemStack.isOf(ModItems.WAFFLE) && getVariant() == CAT_VARIANT.BLACK) {
            if (this.getRandom().nextFloat() <= 0.25f) {
                spawnTACNAYN();
            }
            if (!player.isCreative()) itemStack.decrement(1);
            return ActionResult.SUCCESS;
        }

        return super.interactMob(player, hand);
    }

    private void spawnNyancat() 
    {
        NyancatEntity newEntity = ModEntities.NYANCAT_TYPE.create(this.getWorld(), null);
        if (newEntity != null) {
            newEntity.teleport(this.getX(), this.getY(), this.getZ(), true);
            newEntity.setPersistent();

            this.getWorld().spawnEntity(newEntity);
            this.discard();
        }
    }

    private void spawnTACNAYN() 
    {
        TACNAYNEntity newEntity = ModEntities.TACNAYN_TYPE.create(this.getWorld(), null);
        if (newEntity != null) {
            newEntity.teleport(this.getX(), this.getY(), this.getZ(), true);
            newEntity.setPersistent();

            this.getWorld().spawnEntity(newEntity);
            this.discard();
        }
    }

    @Override
    public boolean saveNbt(NbtCompound tag) {
        tag.putString("Variant", getVariant().name());
        return super.saveNbt(tag);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        if (tag.contains("Variant")) {
            try {
                setVariant(CAT_VARIANT.valueOf(tag.getString("Variant").get()));
            } catch (Exception e) {
                setVariant(CAT_VARIANT.GINGER); // fallback
            }
        }
    }
}
