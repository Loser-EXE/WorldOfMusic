package worldofmusic.sound;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import worldofmusic.networking.ModPackets;

@Environment(EnvType.CLIENT)
public class WarSongSoundInstance extends MovingSoundInstance {
    private final LivingEntity entity;
    private final Item instrument;

    public WarSongSoundInstance(LivingEntity entity, SoundEvent event, ItemStack stack) {
        super(event, SoundCategory.RECORDS);

        this.entity = entity;
        this.instrument = stack.getItem();
        this.volume = 5;
    }

    public void stop() {
        this.setDone();
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(this.entity.getId());
        buf.writeInt(2);

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
    }
}
