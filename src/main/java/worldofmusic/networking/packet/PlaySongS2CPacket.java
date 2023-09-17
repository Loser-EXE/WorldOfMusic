package worldofmusic.networking.packet;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import worldofmusic.WorldOfMusic;
import worldofmusic.networking.ModPackets;
import worldofmusic.sound.SongManager;
import worldofmusic.sound.SongSoundInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class PlaySongS2CPacket {
    private static final SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        int id = buf.readInt();
        LivingEntity entity = (LivingEntity) handler.getWorld().getEntityById(id);

        if (entity == null) {
            sendResponsePacket(id, false, responseSender);
        }

        String song = buf.readString();
        String instrument = buf.readString();

        Map<String, Map<String, SongSoundInstance>> activeSongs =
                SongManager.songInstances.computeIfAbsent(entity.getType(), key -> new HashMap<>());

        Map<String, SongSoundInstance> activeSong = activeSongs.computeIfAbsent(song, key -> {
            Map<String, SongSoundInstance> map = new HashMap<>();
            List<String> songInstruments = SongManager.getSongInstuments(song);
            for (String songInstrument : songInstruments) {
                SoundEvent event = Registry.SOUND_EVENT.get(new Identifier(WorldOfMusic.MOD_ID, song + "_" + songInstrument));
                SongSoundInstance soundInstance = new SongSoundInstance(null, event, song);
                if(instrument.equals(songInstrument)) soundInstance.initialize(entity);
                soundManager.play(soundInstance);
                map.put(songInstrument, soundInstance);
            }
            return map;
        });

        activeSong.compute(instrument, (instrumentName, soundInstance) -> {
            if(soundInstance == null) return null;

            if(!soundInstance.isInitialized()) {
                soundInstance.initialize(entity);
            }

            return soundInstance;
        });

        for (String songName : activeSongs.keySet()) {
            if (!songName.equals(song)) {
                SongSoundInstance songInstance = activeSongs.get(songName).get(instrument);

                if (songInstance != null && songInstance.isInitialized() && songInstance.getEntity().equals(entity)) {
                    songInstance.setToStop();
                }
            }
        }

        sendResponsePacket(id, true, responseSender);
    }

    private static void sendResponsePacket(int id, boolean success, PacketSender sender) {
        PacketByteBuf responseBuf = PacketByteBufs.create();
        responseBuf.writeInt(id);
        responseBuf.writeInt(success ? 0 : 1);
        sender.sendPacket(ModPackets.SONG_STATUS, responseBuf);
    }
}
