package worldofmusic.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.PatrolEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.spawner.PatrolSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import worldofmusic.entity.ModEntities;

import java.util.Random;

@Mixin(PatrolSpawner.class)
public abstract class PatrolSpawnerMixin {
    @Inject(method = "spawn", at = @At(value = "RETURN", ordinal = 10), locals = LocalCapture.CAPTURE_FAILHARD)
    public void spawn(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals, CallbackInfoReturnable<Integer> cir, Random random, long l, int i, PlayerEntity playerEntity, int j, int k, BlockPos.Mutable mutable, int m, RegistryEntry registryEntry, Biome.Category category, int n) {
        spawnMusician(world, mutable, random, ModEntities.FIFER_PILLAGER_ENTITY);
        spawnMusician(world, mutable, random, ModEntities.DRUMMER_PILLAGER_ENTITY);
    }

    @Unique
    private void spawnMusician(ServerWorld world, BlockPos.Mutable pos, Random random, EntityType musician) {
        pos.setX(pos.getX() + random.nextInt(5) - random.nextInt(5));
        pos.setZ(pos.getZ() + random.nextInt(5) - random.nextInt(5));

        PatrolEntity patrolEntity = (PatrolEntity) musician.create(world);
        if (patrolEntity != null) {
            patrolEntity.setPosition(pos.getX(), pos.getY(), pos.getZ());
            patrolEntity.initialize(world, world.getLocalDifficulty(pos), SpawnReason.PATROL, null, null);
            world.spawnEntityAndPassengers(patrolEntity);
        }
    }
}
