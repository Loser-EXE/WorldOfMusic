package worldofmusic.networking.packet;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import worldofmusic.WorldOfMusic;
import worldofmusic.networking.ModPackets;
import worldofmusic.sound.WarSongSoundInstance;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class PlaySongS2CPacket {
    private static final SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();
    private static final Map<Integer, SoundInstance> soundInstances = new HashMap<>();

    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        int id = buf.readInt();
        PacketByteBuf responseBuf = PacketByteBufs.create();
        responseBuf.writeInt(id);

        LivingEntity entity = (LivingEntity) handler.getWorld().getEntityById(id);
        if (entity != null) {
            ItemStack stack = entity.getStackInHand(Hand.MAIN_HAND);
            SoundEvent song = Registry.SOUND_EVENT.get(new Identifier(
                    WorldOfMusic.MOD_ID,
                    buf.readString() + "_" + buf.readString()));

            SoundInstance soundInstance = soundInstances.get(id);
            SoundInstance newSoundInstance = new WarSongSoundInstance(entity, song, stack);
            soundInstances.put(id, newSoundInstance);
            if (soundInstance != null && soundManager.isPlaying(soundInstance)) soundManager.stop(soundInstance);
            soundManager.play(newSoundInstance);

            responseBuf.writeInt(0);
        } else {
            responseBuf.writeInt(1);
        }

        responseSender.sendPacket(ModPackets.PLAY_SONG_STATUS_ID, responseBuf);
    }
}
