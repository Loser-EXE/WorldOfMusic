package worldofmusic.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.world.World;
import worldofmusic.item.ModItems;

public class BagPiperPillagerEntity extends MusicianPillagerEntity {
    public BagPiperPillagerEntity(EntityType<? extends IllagerEntity> entityType, World world) {
        super(entityType, world);
        instrument = ModItems.BAGPIPES;
    }
}
