package worldofmusic.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.world.World;
import worldofmusic.item.ModItems;

public class FiferPillagerEntity extends MusicianPillagerEntity {
    public FiferPillagerEntity(EntityType<? extends IllagerEntity> entityType, World world) {
        super(entityType, world);
        instrument = ModItems.FIFE;
    }
}
