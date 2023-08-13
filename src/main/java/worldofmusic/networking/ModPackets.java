package worldofmusic.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import worldofmusic.WorldOfMusic;
import worldofmusic.networking.packet.OpenMusicSelectScreenS2CPacket;
import worldofmusic.networking.packet.PlaySelectedSongC2SPacket;
import worldofmusic.networking.packet.PlaySongS2CPacket;
import worldofmusic.networking.packet.PlaySongStatusC2SPacket;

public class ModPackets {
    public static final Identifier PLAY_SONG_ID = new Identifier(WorldOfMusic.MOD_ID, "play_song_packet");
    public static final Identifier OPEN_MUSIC_SELECT_SCREEN_ID = new Identifier(WorldOfMusic.MOD_ID, "open_music_select_packet");

    public static final Identifier PLAY_SELECTED_SONG_ID = new Identifier(WorldOfMusic.MOD_ID, "play_selected_song");
    public static final Identifier PLAY_SONG_STATUS_ID = new Identifier(WorldOfMusic.MOD_ID, "test");

    public static void registerS2CPackets() {
        WorldOfMusic.LOGGER.info("Registering receivers for S2C packets");

        ClientPlayNetworking.registerGlobalReceiver(PLAY_SONG_ID, PlaySongS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(OPEN_MUSIC_SELECT_SCREEN_ID, OpenMusicSelectScreenS2CPacket::receive);
    }

    public static void registerC2SPackets() {
        WorldOfMusic.LOGGER.info("Registering receivers for C2S packets");

        ServerPlayNetworking.registerGlobalReceiver(PLAY_SELECTED_SONG_ID, PlaySelectedSongC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(PLAY_SONG_STATUS_ID, PlaySongStatusC2SPacket::receive);
    }
}
