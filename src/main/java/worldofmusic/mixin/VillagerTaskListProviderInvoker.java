package worldofmusic.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.brain.task.VillagerTaskListProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(VillagerTaskListProvider.class)
public interface VillagerTaskListProviderInvoker {
    @Invoker("createBusyFollowTask")
    static Pair<Integer, Task<LivingEntity>> createBusyFollowTask() {
        throw new AssertionError();
    }
}
