package worldofmusic.entity;

import net.minecraft.entity.ai.brain.task.VillagerWorkTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import worldofmusic.item.ModItems;

public class MusicianWorkTask extends VillagerWorkTask {
    @Override
    protected void run(ServerWorld serverWorld, VillagerEntity villagerEntity, long l) {
        super.run(serverWorld, villagerEntity, l);
        System.out.println(villagerEntity.getVillagerData().getType().toString());
    }
}
