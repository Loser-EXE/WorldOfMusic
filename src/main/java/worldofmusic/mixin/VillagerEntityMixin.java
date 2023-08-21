package worldofmusic.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import worldofmusic.entity.MusicianVillager;
import worldofmusic.item.ModItems;
import worldofmusic.sound.SongManager;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin {
    @Unique
    boolean isPlaying = false;
    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo info) {
        VillagerEntity $this = (VillagerEntity) (Object) this;
        if(!$this.getVillagerData().getProfession().equals(MusicianVillager.MUSICIAN_PROFESSION)) return;
        if(!($this.getMainHandStack() == null || $this.getMainHandStack().isEmpty())) return;
        $this.setStackInHand(Hand.MAIN_HAND, new ItemStack(ModItems.DRUM));

        if(!isPlaying && !$this.getWorld().isClient) {
            SongManager.playSong($this, "men_of_harlech", "fife");
            isPlaying = true;
        }
    }
}
