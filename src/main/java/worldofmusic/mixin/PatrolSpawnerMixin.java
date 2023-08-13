package worldofmusic.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.PatrolEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.spawner.PatrolSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import worldofmusic.entity.ModEntities;

import java.util.Random;

@Mixin(PatrolSpawner.class)
public abstract class PatrolSpawnerMixin {
    @Unique
    private BlockPos pos = null;

    @Inject(method = "spawn", at = @At(value = "RETURN", ordinal = 10))
    public void spawn(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals, CallbackInfoReturnable<Integer> cir) {
        if(pos != null) {
            spawnMusician(world, pos, ModEntities.FIFER_PILLAGER_ENTITY);
            spawnMusician(world, pos, ModEntities.DRUMMER_PILLAGER_ENTITY);
        }

    }

    @Inject(method = "spawnPillager", at = @At("HEAD"))
    private void spawnPillager(ServerWorld world, BlockPos pos, Random random, boolean captain, CallbackInfoReturnable<Boolean> cir) {
        this.pos = pos;
    }

    private void spawnMusician(ServerWorld world, BlockPos pos, EntityType musician) {
        PatrolEntity patrolEntity = (PatrolEntity) musician.create(world);
        if (patrolEntity != null) {
            patrolEntity.setPosition(pos.getX(), pos.getY(), pos.getZ());
            patrolEntity.initialize(world, world.getLocalDifficulty(pos), SpawnReason.PATROL, null, null);
            world.spawnEntityAndPassengers(patrolEntity);
        }
    }
}
