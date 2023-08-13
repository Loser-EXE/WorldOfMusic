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
import worldofmusic.entity.MusicianPillagerEntity;
import worldofmusic.item.ModItems;

@Mixin(Raid.class)
public abstract class RaidWaveSpawnMixin {
    @Shadow @Final private ServerWorld world;

    @Shadow public abstract void addRaider(int wave, RaiderEntity raider, @Nullable BlockPos pos, boolean existing);

    @Shadow private int wavesSpawned;

    @Inject(method = "spawnNextWave", at = @At("HEAD"))
    public void spawnNextWave(BlockPos pos, CallbackInfo info) {
        if(Math.random() < 0.3) {
            MusicianPillagerEntity.setRaidSong(MusicianPillagerEntity.genRandomSong(ModItems.BAGPIPES.getSongs()));
            registerMusicianToRaid(ModEntities.BAGPIPER_PILLAGER_ENTITY, pos);
        } else {
            MusicianPillagerEntity.setRaidSong(MusicianPillagerEntity.genRandomSong(ModItems.FIFE.getSongs()));
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
