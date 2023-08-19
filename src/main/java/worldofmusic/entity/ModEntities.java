package worldofmusic.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import worldofmusic.WorldOfMusic;
import worldofmusic.entity.pillager.BagPiperPillagerEntity;
import worldofmusic.entity.pillager.DrummerPillagerEntity;
import worldofmusic.entity.pillager.FiferPillagerEntity;
import worldofmusic.entity.pillager.MusicianPillagerEntity;

public class ModEntities {
    public static final EntityType<DrummerPillagerEntity> DRUMMER_PILLAGER_ENTITY = registerEntity("drummer_pillager", DrummerPillagerEntity::new);
    public static final EntityType<BagPiperPillagerEntity> BAGPIPER_PILLAGER_ENTITY = registerEntity("bagpiper_pillager", BagPiperPillagerEntity::new);
    public static final EntityType<FiferPillagerEntity> FIFER_PILLAGER_ENTITY = registerEntity("fifer_pillager", FiferPillagerEntity::new);

    private static <T extends PathAwareEntity> EntityType<T> registerEntity(String name, EntityType.EntityFactory<T> entity) {
        return Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(WorldOfMusic.MOD_ID, name),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, entity).dimensions(EntityDimensions.fixed(0.6f, 1.95f)).build()
        );
    }

    public static void registerEntities() {
        WorldOfMusic.LOGGER.info("Registering entities");

        FabricDefaultAttributeRegistry.register(DRUMMER_PILLAGER_ENTITY, MusicianPillagerEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(FIFER_PILLAGER_ENTITY, MusicianPillagerEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(BAGPIPER_PILLAGER_ENTITY, MusicianPillagerEntity.createAttributes());
    }
}
