package worldofmusic.sound;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.village.VillagerProfession;
import worldofmusic.entity.MusicianVillager;
import worldofmusic.networking.ModPackets;

@Environment(EnvType.CLIENT)
public class SongSoundInstance extends MovingSoundInstance {
    private final LivingEntity entity;
    private final Item instrument;
    private boolean isMusician;

    public SongSoundInstance(LivingEntity entity, SoundEvent event, ItemStack stack) {
        super(event, SoundCategory.RECORDS);

        this.entity = entity;
        this.instrument = stack.getItem();
        this.volume = 5;
    }

    public void stop() {
        this.setDone();
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(this.entity.getId());
        buf.writeInt(3);

        ClientPlayNetworking.send(ModPackets.PLAY_SONG_STATUS_ID, buf);
    }

    @Override
    public void tick() {
        this.x = entity.getX();
        this.y = entity.getY();
        this.z = entity.getZ();

        if(!entity.isHolding(instrument) || !entity.isAlive()) {
            this.stop();
        }

        if(entity instanceof VillagerEntity villagerEntity) {
            VillagerProfession profession = villagerEntity.getVillagerData().getProfession();

            if(profession != MusicianVillager.MUSICIAN_PROFESSION && isMusician) this.stop();

            if(profession == MusicianVillager.MUSICIAN_PROFESSION) isMusician = true;
        }
    }
}
