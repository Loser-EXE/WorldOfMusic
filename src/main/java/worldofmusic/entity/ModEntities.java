package worldofmusic.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import worldofmusic.WorldOfMusic;

public class ModEntities {
    public static final EntityType<DrummerPillagerEntity> DRUMMER_PILLAGER_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(WorldOfMusic.MOD_ID, "drummer_pillager"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, DrummerPillagerEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());

    public static final EntityType<FiferPillagerEntity> FIFER_PILLAGER_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(WorldOfMusic.MOD_ID, "fifer_pillager"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, FiferPillagerEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());

    public static final EntityType<BagPiperPillagerEntity> BAGPIPER_PILLAGER_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(WorldOfMusic.MOD_ID, "bagpiper_pillager"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, BagPiperPillagerEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build());

    public static void registerEntities() {
        WorldOfMusic.LOGGER.info("Registering entities");

        FabricDefaultAttributeRegistry.register(DRUMMER_PILLAGER_ENTITY, MusicianPillagerEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(FIFER_PILLAGER_ENTITY, MusicianPillagerEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(BAGPIPER_PILLAGER_ENTITY, MusicianPillagerEntity.createAttributes());
    }
}
