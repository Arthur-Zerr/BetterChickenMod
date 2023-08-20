package de.pkz.betterchicken.entities.chicken;

import de.pkz.betterchicken.BetterChicken;
import de.pkz.betterchicken.registers.EntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class ChickenEntity extends Animal {

    public static final String ENTITY_ID = "chicken_entity";
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS, Items.TORCHFLOWER_SEEDS, Items.PITCHER_POD);

    private static final EntityDataAccessor<Integer> DATA_VARIANT = SynchedEntityData.defineId(ChickenEntity.class, EntityDataSerializers.INT);


    public ChickenEntity(EntityType<ChickenEntity> type, Level level) {
        super(type, level);
    }

    public ChickenEntity(Level level, double x, double y, double z) {
        this(EntityRegister.CHICKEN_ENTITY.get(), level);
        setPos(x, y, z);
    }

    public ChickenEntity(Level level, BlockPos pos) {

        this(level, pos.getX(), pos.getY(), pos.getZ());
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return new ChickenEntity(level, this.blockPosition());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    public static boolean canSpawn(EntityType<ChickenEntity> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource randomSource) {
        return Animal.checkAnimalSpawnRules(entityType, level, spawnType, pos, randomSource);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance p_146747_, MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_, @Nullable CompoundTag p_146750_) {
        int randomValue = level.getRandom().nextInt(100);
        EChickenVariant variant = EChickenVariant.BROWN;
        if (randomValue < 15)
            variant = EChickenVariant.WHITE_BROWN;
        else if (randomValue < 50)
            variant = EChickenVariant.WHITE;

        this.entityData.set(DATA_VARIANT, variant.ordinal());
        return super.finalizeSpawn(level, p_146747_, p_146748_, p_146749_, p_146750_);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        CompoundTag betterChickenData = new CompoundTag();
        betterChickenData.putInt("variant", this.getVariant().ordinal());
        nbt.put(BetterChicken.MODID, betterChickenData);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        CompoundTag betterChickenData = nbt.getCompound(BetterChicken.MODID);
        int variant = betterChickenData.getInt("variant");
        this.entityData.set(DATA_VARIANT, variant);
    }

    public EChickenVariant getVariant() {
        return EChickenVariant.values()[this.entityData.get(DATA_VARIANT)];
    }

}
