package worldofmusic.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.raid.Raid;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import worldofmusic.entity.ModEntities;
import worldofmusic.entity.pillager.MusicianPillagerEntity;
import worldofmusic.item.Instrument;
import worldofmusic.item.ModItems;
import worldofmusic.sound.SongManager;

@Mixin(Raid.class)
public abstract class RaidWaveSpawnMixin {
    @Shadow @Final private ServerWorld world;

    @Shadow public abstract void addRaider(int wave, RaiderEntity raider, @Nullable BlockPos pos, boolean existing);

    @Shadow private int wavesSpawned;

    @Inject(method = "spawnNextWave", at = @At("HEAD"))
    public void spawnNextWave(BlockPos pos, CallbackInfo info) {
        if(Math.random() < 0.3) {
            MusicianPillagerEntity.setRaidSong(SongManager.genRandomSong(ModItems.BAGPIPES.getSongs(Instrument.PlayCondition.RAID)));
            registerMusicianToRaid(ModEntities.BAGPIPER_PILLAGER_ENTITY, pos);
        } else {
            MusicianPillagerEntity.setRaidSong(SongManager.genRandomSong(ModItems.FIFE.getSongs(Instrument.PlayCondition.RAID)));
            registerMusicianToRaid(ModEntities.FIFER_PILLAGER_ENTITY, pos);
        }
        registerMusicianToRaid(ModEntities.DRUMMER_PILLAGER_ENTITY, pos);
    }

    @Unique
    private void registerMusicianToRaid(EntityType<? extends MusicianPillagerEntity> entityType, BlockPos pos) {
        MusicianPillagerEntity entity = entityType.create(world);
        this.addRaider(this.wavesSpawned + 1, entity, pos, false);
    }
}
