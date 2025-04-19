package org.nyancat.nyancat.entities;


import org.jetbrains.annotations.Nullable;
import org.nyancat.nyancat.custom_payloads.s2c.LevelingScreenPayloadS2C;
import org.nyancat.nyancat.custom_payloads.s2c.TamingScreenPayloadS2C;
import org.nyancat.nyancat.entities.mob_routines.ConditionalGoal;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public abstract class AbstractCatEntity extends TameableEntity 
{
    protected static final TrackedData<Integer> level = DataTracker.registerData(AbstractCatEntity.class, TrackedDataHandlerRegistry.INTEGER);
    protected static final TrackedData<Integer> healthLevel = DataTracker.registerData(AbstractCatEntity.class, TrackedDataHandlerRegistry.INTEGER);
    protected static final TrackedData<Integer> speedLevel = DataTracker.registerData(AbstractCatEntity.class, TrackedDataHandlerRegistry.INTEGER);
    protected static final TrackedData<Integer> strengthLevel = DataTracker.registerData(AbstractCatEntity.class, TrackedDataHandlerRegistry.INTEGER);
    protected static final TrackedData<Integer> skillPoints = DataTracker.registerData(AbstractCatEntity.class, TrackedDataHandlerRegistry.INTEGER);
    protected static final TrackedData<Integer> catAction = DataTracker.registerData(AbstractCatEntity.class, TrackedDataHandlerRegistry.INTEGER);
    protected static final TrackedData<Integer> overallHealth = DataTracker.registerData(AbstractCatEntity.class, TrackedDataHandlerRegistry.INTEGER);
    protected static final TrackedData<Integer> overallSpeed = DataTracker.registerData(AbstractCatEntity.class, TrackedDataHandlerRegistry.INTEGER);
    protected static final TrackedData<Integer> overallStrength = DataTracker.registerData(AbstractCatEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private int spawnBlockTimer = 0;
    
    protected static final String[] KEYS = {
        "Level",
        "HealthLevel",
        "SpeedLevel",
        "StrengthLevel",
        "SkillPoint",
        "OverallSpeed",
        "OverallStrength"
    };

    abstract protected void emitParticles();

    public static enum CatAction
    {
        DEFAULT,
        FOLLOW,
        STAY,
        FIGHT
    };

    public static enum LevelType
    {
        HEALTH,
        SPEED,
        STRENGTH
    }

    public void setRoutine(CatAction newAction) 
    {
        if (this.getWorld().isClient) return;

        System.out.println("Action set!");
        if (getAction() != newAction) {
            this.setAction(newAction);

            this.goalSelector.getGoals().clear();
            this.targetSelector.getGoals().clear();

            this.initGoals(); // Rebuild based on new routine
        }
    }

    protected boolean isValidSameTypeTarget(LivingEntity other, World world)
    {
        return !world.isClient && other instanceof AbstractCatEntity target && !target.isTamed();
    }

    protected boolean isTamedValidTarget(LivingEntity other, World world)
    {
        ;
        try {
            TameableEntity et = ((TameableEntity) other);
            return !world.isClient && !et.isTamed();
        }
        catch (Exception e) {
            return !world.isClient && !(other instanceof PlayerEntity);
        }
    }

    @Override
    protected void initGoals() 
    {
        goalSelector.add(1, new SwimGoal(this));

        this.targetSelector.add(2, new ConditionalGoal<>(
            new ActiveTargetGoal<>(this, AbstractCatEntity.class, 10, true, true, this::isValidSameTypeTarget),
            false, this::isTamed // Only active when this mob is untamed
        ));

        this.targetSelector.add(2, new ConditionalGoal<>(
            new ActiveTargetGoal<>(this, WeirdLookingCatEntity.class, true),
            false, this::isTamed
        ));

        this.targetSelector.add(2, new ConditionalGoal<>(
            new EscapeDangerGoal(this, 1.2),
            false, this::isTamed
        ));

        goalSelector.add(2, new ConditionalGoal<>(new WanderAroundFarGoal(this, 1.0), CatAction.DEFAULT, this::getAction));
        goalSelector.add(2, new ConditionalGoal<>(new LookAtEntityGoal(this, PlayerEntity.class, 5.0f), CatAction.DEFAULT, this::getAction));

        goalSelector.add(2, new ConditionalGoal<>(new FollowOwnerGoal(this, 1.0, 5f, 50f), CatAction.FOLLOW, this::getAction));
        goalSelector.add(2, new ConditionalGoal<>(new SitGoal(this), CatAction.STAY, this::getAction));

        goalSelector.add(2, new ConditionalGoal<>(new MeleeAttackGoal(this, 1.2, true), CatAction.FIGHT, this::getAction));

        targetSelector.add(1, new ConditionalGoal<>(new ActiveTargetGoal<>(this, LivingEntity.class, 10, true, true, this::isTamedValidTarget), CatAction.FIGHT, this::getAction));
    }

    @Override
    public boolean shouldRenderName() {
        return this.isTamed();
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) 
    {
        super.initDataTracker(builder);
        builder.add(catAction, CatAction.DEFAULT.ordinal());

        builder.add(level, 1);
        builder.add(healthLevel, 10);
        builder.add(overallHealth, 7);
        builder.add(speedLevel, 4);
        builder.add(strengthLevel, 4);
        builder.add(skillPoints, 0);
        builder.add(overallSpeed, 1);
        builder.add(overallStrength, 1);
    }

    @Override
    public boolean onKilledOther(ServerWorld world, LivingEntity other) {
        boolean onKilled = super.onKilledOther(world, other);

        // Do something when this entity kills another
        if (!world.isClient) {
            this.dataTracker.set(skillPoints, getSkillPoints() + 1);
        }

        return onKilled;
    }

    public int getHealthLevel()
    {
        return this.dataTracker.get(healthLevel);
    }

    public int getSpeedLevel()
    {
        return this.dataTracker.get(speedLevel);
    }

    public int getStrengthLevel()
    {
        return this.dataTracker.get(strengthLevel);
    }

    public int getOverallHealth()
    {
        return this.dataTracker.get(overallHealth);
    }

    public void addLevelPoint(LevelType type) 
    {
        if (getSkillPoints() <= 0) return;
        switch (type) {
            case HEALTH:
                incHealth(1);
                this.dataTracker.set(skillPoints, getSkillPoints() - 1);
                break;
            case SPEED:
                incSpeed(1);
                this.dataTracker.set(skillPoints, getSkillPoints() - 1);
                break;
            case STRENGTH:
                incStrength(1);
                this.dataTracker.set(skillPoints, getSkillPoints() - 1);
                break;
        }
    }

    public void decreaseLevelPoint(LevelType type) 
    {
        switch (type) {
            case HEALTH:
                incHealth(-1);
                this.dataTracker.set(skillPoints, getSkillPoints() + 1);
                break;
            case SPEED:
                incSpeed(-1);
                this.dataTracker.set(skillPoints, getSkillPoints() + 1);
                break;
            case STRENGTH:
                this.incStrength(-1);
                this.dataTracker.set(skillPoints, getSkillPoints() + 1);
                break;
        }
    }

    public int getSkillPoints()
    {
        return this.dataTracker.get(skillPoints);
    }

	public CatAction getAction() 
    {
        return CatAction.values()[this.dataTracker.get(catAction)];
    }

    public int getSpeed()
    {
        return this.dataTracker.get(overallSpeed);
    }

    public void setAction(CatAction var) 
    {
        this.dataTracker.set(catAction, var.ordinal());
        this.setRoutine(var);
    }

    public int getStrength()
    {
        return this.dataTracker.get(overallStrength);
    }

    @Override
    public EntityData initialize(
        ServerWorldAccess world,
        LocalDifficulty difficulty,
        SpawnReason spawnReason,
        @Nullable EntityData entityData) 
    {
        this.dataTracker.set(catAction, CatAction.DEFAULT.ordinal());

        this.dataTracker.set(level, 1);
        this.dataTracker.set(healthLevel, 10);
        this.dataTracker.set(speedLevel, 4);
        this.dataTracker.set(strengthLevel, 4);
        this.dataTracker.set(skillPoints, 0);
        this.dataTracker.set(overallSpeed, 1);
        this.dataTracker.set(overallStrength, 1);
        this.setRoutine(CatAction.DEFAULT);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }


    public int getLevel() 
    {
        return this.dataTracker.get(level);
    }

    public abstract Block getSpawnedBlock();

    @Override
    public void tick() 
    {
        super.tick();

        if (!this.getWorld().isClient()) return;

        emitParticles();

        spawnBlockTimer++;

        if (spawnBlockTimer >= 200) { // e.g., after 10 seconds (20 ticks * 10)
            BlockPos pos = this.getBlockPos().down(); // or another nearby pos

            if (this.getWorld().getBlockState(pos).isAir()) {
                this.getWorld().setBlockState(pos, getSpawnedBlock().getDefaultState());
            }

            spawnBlockTimer = 0; // Reset timer
        }
    }

	protected AbstractCatEntity(EntityType<? extends AbstractCatEntity> entityType, World world) 
    {
		super(entityType, world);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) 
    {
        return stack.isOf(null);
	}

	@Override
	public PassiveEntity createChild(ServerWorld arg0, PassiveEntity arg1) 
    {
		return null;
	}

    public void levelUp(int healthInc, int speedInc, int strengthInc) 
    {
        this.dataTracker.set(level, getLevel() + 1);

        this.dataTracker.set(healthLevel, getHealthLevel() + healthInc);
        this.dataTracker.set(speedLevel, getSpeedLevel() + speedInc);
        this.dataTracker.set(strengthLevel, getStrengthLevel() + strengthInc);
    }

    public void incSpeed(int val)
    {
        // this.dataTracker.set(speedLevel, getSpeedLevel() + val);
        this.dataTracker.set(overallSpeed, getSpeed() + val);
        this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(this.getAttributeBaseValue(EntityAttributes.MOVEMENT_SPEED) + val * 0.02);
    }

    public void incHealth(int val)
    {
        // this.dataTracker.set(healthLevel, getHealthLevel() + val);
        this.dataTracker.set(overallHealth, getOverallHealth() + val);
        this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(this.getMaxHealth() + val);
    }

    public void incStrength(int val)
    {
        // this.dataTracker.set(strengthLevel, getStrengthLevel() + val);
        this.dataTracker.set(overallStrength, getStrength() + val);
        this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(this.getAttributeBaseValue(EntityAttributes.ATTACK_DAMAGE) + val * 0.3);
    }
    
    public static DefaultAttributeContainer.Builder createMobAttributes() 
    {
        return LivingEntity.createLivingAttributes()
                .add(EntityAttributes.MAX_HEALTH, 7.0)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.FOLLOW_RANGE, 70.0)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, .5)
                .add(EntityAttributes.ATTACK_KNOCKBACK, .5);
    }

    public void onInteract(PlayerEntity player, Item tamingItem, Item healingItem, String name) 
    {
        if (this.getWorld().isClient) return;

        ItemStack itemStack = player.getMainHandStack();
        

        if (!this.isTamed() && itemStack.isOf(tamingItem)) {
            if (this.getRandom().nextFloat() <= 0.4f) {
                this.setTamedBy(player);
                this.getServer().execute(() -> openTamingGui(player, name));
            }
            if (!player.isCreative()) itemStack.decrement(1);
        }
        else if (this.isTamed()) {
            if (!this.isOwner(player)) return;
            if (!itemStack.isOf(healingItem)) {
                this.getServer().execute(() -> openLevelingGui(player, name));
            }
            else {
                // Healing mechanics
            }
        }
    }

    protected void openLevelingGui(PlayerEntity player, String name)
    {
        if (!this.getWorld().isClient && this.isTamed()) {
            LevelingScreenPayloadS2C payload = new LevelingScreenPayloadS2C(this.getId(), name);

            ServerPlayNetworking.send((ServerPlayerEntity) player, payload);
        }
    }

    protected void openTamingGui(PlayerEntity player, String name)
    {
        if (!this.getWorld().isClient && this.isTamed()) {
            TamingScreenPayloadS2C payload = new TamingScreenPayloadS2C(this.getId(), name);

            ServerPlayNetworking.send((ServerPlayerEntity) player, payload);
        }
    }

    @Override
    public boolean saveNbt(NbtCompound tag) 
    {
        tag.putInt("Level", getLevel());
        tag.putInt("SkillPoints", getSkillPoints());
        tag.putInt("HealthLevel", getHealthLevel());
        tag.putInt("SpeedLevel", getSpeedLevel());
        tag.putInt("StrengthLevel", getStrengthLevel());
        tag.putInt("Action", getAction().ordinal());
        tag.putInt("OverallSpeed", getSpeed());
        tag.putInt("OverallStrength", getStrength());
        tag.putInt("OverallHealth", getOverallHealth());

        return super.saveNbt(tag);
    }

    @Override
    public void readNbt(NbtCompound tag) 
    {
        super.readNbt(tag);
        for (String key : KEYS) if (!tag.contains(key)) return;
        try {
            this.dataTracker.set(level, tag.getInt("Level").get());
            this.dataTracker.set(skillPoints, tag.getInt("SkillPoints").get());
            this.dataTracker.set(healthLevel, tag.getInt("HealthLevel").get());
            this.dataTracker.set(speedLevel, tag.getInt("SpeedLevel").get());
            this.dataTracker.set(strengthLevel, tag.getInt("StrengthLevel").get());
            this.dataTracker.set(catAction, tag.getInt("Action").get());
            this.dataTracker.set(overallSpeed, tag.getInt("OverallSpeed").get());
            this.dataTracker.set(overallStrength, tag.getInt("OverallStrength").get());
            this.dataTracker.set(overallHealth, tag.getInt("OverallHealth").get());
        } catch (Exception e) {
            this.dataTracker.set(level, 1);
            this.dataTracker.set(skillPoints, 0);
            this.dataTracker.set(healthLevel, 10);
            this.dataTracker.set(speedLevel, 4);
            this.dataTracker.set(strengthLevel, 4);
            this.dataTracker.set(catAction, CatAction.DEFAULT.ordinal());
            this.dataTracker.set(overallSpeed, 1);
            this.dataTracker.set(overallStrength, 1);
            this.dataTracker.set(overallHealth, 7);
        }
    }
}