package worldofmusic.mixin;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import worldofmusic.WorldOfMusic;
import worldofmusic.entity.MusicianWorkTask;

@Mixin(VillagerTaskListProvider.class)
public abstract class VillagerTaskListProviderMixin {
    /**
     * @author LoserEXE
     * @reason Create Villager Professions for the Musicians
     */
    @Overwrite
    public static ImmutableList<Pair<Integer, ? extends Task<? super VillagerEntity>>> createWorkTasks(VillagerProfession profession, float speed) {
        VillagerWorkTask villagerWorkTask;
        if (profession.equals(VillagerProfession.FARMER)) {
            villagerWorkTask = new FarmerWorkTask();
        } else if (profession.equals(WorldOfMusic.MUSICIAN)) {
            villagerWorkTask = new MusicianWorkTask();
        } else {
            villagerWorkTask = new VillagerWorkTask();
        }
        return ImmutableList.of(VillagerTaskListProviderInvoker.createBusyFollowTask(), Pair.of(5, new RandomTask(ImmutableList.of(Pair.of(villagerWorkTask, 7), Pair.of(new GoToIfNearbyTask(MemoryModuleType.JOB_SITE, 0.4f, 4), 2), Pair.of(new GoToNearbyPositionTask(MemoryModuleType.JOB_SITE, 0.4f, 1, 10), 5), Pair.of(new GoToSecondaryPositionTask(MemoryModuleType.SECONDARY_JOB_SITE, speed, 1, 6, MemoryModuleType.JOB_SITE), 5), Pair.of(new FarmerVillagerTask(), profession == VillagerProfession.FARMER ? 2 : 5), Pair.of(new BoneMealTask(), profession == VillagerProfession.FARMER ? 4 : 7)))), Pair.of(10, new HoldTradeOffersTask(400, 1600)), Pair.of(10, new FindInteractionTargetTask(EntityType.PLAYER, 4)), Pair.of(2, new VillagerWalkTowardsTask(MemoryModuleType.JOB_SITE, speed, 9, 100, 1200)), Pair.of(3, new GiveGiftsToHeroTask(100)), Pair.of(99, new ScheduleActivityTask()));
    }
}
