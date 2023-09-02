package worldofmusic.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.village.VillagerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import worldofmusic.WorldOfMusic;
import worldofmusic.entity.MusicianVillager;
import worldofmusic.item.Instrument;
import worldofmusic.item.ModItems;
import worldofmusic.sound.SongHelper;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin {
    @Unique
    boolean isPlaying = false;

    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo info) {
        VillagerEntity $this = (VillagerEntity) (Object) this;
        VillagerData villagerData = $this.getVillagerData();

        if(villagerData.getProfession() != MusicianVillager.MUSICIAN_PROFESSION) return;
        if(!($this.getMainHandStack().isEmpty())) return;
        Instrument instrument;

        switch(villagerData.getType().toString()) {
            case("taiga") -> instrument = ModItems.BAGPIPES;
            case("plains") -> instrument = ModItems.GUITAR;
            case("snow") -> instrument = ModItems.BALALAIKA; // lower sound
            case("desert") -> instrument = ModItems.REED_PIPE; // lower sound
            case("jungle") -> instrument = ModItems.BONGOS;
            case("swamp") -> instrument = ModItems.BANJO;
            default -> {
                WorldOfMusic.LOGGER.warn("Failed to get instrument for villager type: " + villagerData.getType());
                instrument = ModItems.BUGLE;
            }
        }

        $this.setStackInHand(Hand.MAIN_HAND, new ItemStack(instrument));

        if(!isPlaying && !$this.getWorld().isClient) {
            SongHelper.playSong($this, SongHelper.genRandomSong(instrument.getSongs(Instrument.PlayCondition.VILLAGES)), instrument.getInstrumentName());
            isPlaying = true;
        }
    }
}
