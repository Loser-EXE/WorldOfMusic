package worldofmusic.entity.pillager;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import worldofmusic.WorldOfMusic;
import worldofmusic.item.Instrument;
import worldofmusic.sound.SongManager;

import java.util.List;

public abstract class MusicianPillagerEntity extends IllagerEntity {
    protected Instrument instrument;
    private SongStatus songStatus = SongStatus.STOPPED;
    private SpawnReason spawnReason;
    private boolean isOutpostSpawned = false;
    private List<String> songs;
    private static String raidSong;
    private static String song;

    public MusicianPillagerEntity(EntityType<? extends IllagerEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, PlayerEntity.class, 8.0F, 0.6, 1.0));
        this.goalSelector.add(2, new RaiderEntity.PatrolApproachGoal(this, 10.0F));
        this.goalSelector.add(3, new WanderAroundGoal(this, 0.6));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 15.0F, 1.0F));
        this.goalSelector.add(5, new LookAtEntityGoal(this, MobEntity.class, 15.0F));
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.initEquipment(difficulty);
        if (spawnReason == SpawnReason.PATROL) {
            songs = instrument.getSongs(Instrument.PlayCondition.PATROL);
        } else {
            songs = instrument.getSongs(Instrument.PlayCondition.NONE);
        }
        this.spawnReason = spawnReason;
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    protected void initEquipment(LocalDifficulty difficulty) {
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(instrument));
    }

    @Override
    public void tick() {
        super.tick();

         switch (this.songStatus) {
            case STOPPED -> {
                if(isOutpostSpawned && songs == null) {
                    songs = instrument.getSongs(Instrument.PlayCondition.OUTPOST);
                }

                if(songs != null) {
                    song = SongManager.genRandomSong(songs);
                    playSong();
                }
            }

            case FAILED -> {
                WorldOfMusic.LOGGER.warn("Failed to play song retrying...");
                playSong();
            }
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.isOutpostSpawned = nbt.getBoolean("isOutpostSpawned");
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("isOutpostSpawned", this.isOutpostSpawned);
    }

    private void playSong() {
        if (this.world.isClient) return;
        this.songStatus = SongStatus.PENDING;
        SongManager.playSong(this, (this.spawnReason == SpawnReason.EVENT) ? raidSong : song, instrument.getInstrumentName());
    }

    public Instrument getInstrument() {
        return this.instrument;
    }

    public static void setRaidSong(String song) {
        raidSong = song;
    }

    public void setSongStatus(int status) {
        switch (status) {
            case 0 -> this.songStatus = SongStatus.PLAYING;
            case 1 -> this.songStatus = SongStatus.FAILED;
            case 2 -> this.songStatus = SongStatus.STOPPED;
            case 3 -> this.songStatus = SongStatus.ENDED;
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3499999940395355).add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0);
    }


    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PILLAGER_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PILLAGER_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_PILLAGER_HURT;
    }

    @Override
    public SoundEvent getCelebratingSound() {
        return SoundEvents.ENTITY_PILLAGER_CELEBRATE;
    }

    @Override
    public void addBonusForWave(int wave, boolean unused) { }

    public enum SongStatus {
        PENDING,
        PLAYING,
        STOPPED,
        FAILED,
        ENDED
    }
}
