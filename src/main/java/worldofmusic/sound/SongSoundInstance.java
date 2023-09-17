package worldofmusic.sound;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.village.VillagerProfession;
import worldofmusic.entity.MusicianVillager;
import worldofmusic.item.Instrument;
import worldofmusic.networking.ModPackets;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Environment(EnvType.CLIENT)
public class SongSoundInstance extends MovingSoundInstance {
    private LivingEntity entity;
    private Instrument instrument;
    private static final SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();
    private final String song;
    private boolean isMusicianVillager;
    private boolean isInitialized = false;
    private boolean shouldStop = false;

    public SongSoundInstance(LivingEntity entity, SoundEvent event, String song) {
        super(event, SoundCategory.RECORDS);

        this.song = song;
        this.entity = entity;
        initialize(entity);
    }

    public void initialize(LivingEntity entity) {
        if(entity != null) {
            this.instrument = (Instrument) entity.getMainHandStack().getItem();
            this.entity = entity;
            this.volume = 5;
            this.isInitialized = true;
        } else {
            this.volume = 0.0001f; //If it is set at zero it will be inaudible when its volume is changed
        }
    }

    public boolean isInitialized() {
        return this.isInitialized;
    }

    public LivingEntity getEntity() {
        return this.entity;
    }

    private void stop() {
        this.setDone();
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(this.entity.getId());
        buf.writeInt(3);

        ClientPlayNetworking.send(ModPackets.SONG_STATUS, buf);

        Map<String, SongSoundInstance> songInstrumentSoundInstances = SongManager.songInstances.get(entity.getType()).get(song);
        AtomicInteger numberComplete = new AtomicInteger();
        if(songInstrumentSoundInstances != null) {
            songInstrumentSoundInstances.forEach((key, value) -> {
                if(!value.isInitialized) {
                    numberComplete.getAndIncrement();
                    return;
                }
                if(!soundManager.isPlaying(value)) {
                    numberComplete.getAndIncrement();
                }
            });

            if(numberComplete.get() == songInstrumentSoundInstances.size()-1) SongManager.songInstances.get(this.entity.getType()).remove(song);
        }
    }

    public void setToStop() {
        this.shouldStop = true; //Needed to keep a ConcurrentModificationException from being thrown.
    }

    @Override
    public void tick() {
        if(entity == null) {
            return;
        }

        if(this.shouldStop) {
            this.stop();
        }

        this.x = entity.getX();
        this.y = entity.getY();
        this.z = entity.getZ();

        if(!entity.isHolding(instrument) || !entity.isAlive()) {
            this.stop();
        }

        if(entity instanceof VillagerEntity villagerEntity) {
            VillagerProfession profession = villagerEntity.getVillagerData().getProfession();

            if(profession != MusicianVillager.MUSICIAN_PROFESSION && isMusicianVillager) this.stop();

            if(profession == MusicianVillager.MUSICIAN_PROFESSION) isMusicianVillager = true;
        }

        if(!soundManager.isPlaying(this)) {
            this.stop();
        }
    }
}
