package worldofmusic.sound;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import worldofmusic.networking.ModPackets;

public class SongManager {
    public static void playSong(LivingEntity entity, String song, String instrument) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(entity.getId());
        buf.writeString(song);
        buf.writeString(instrument);

        for(ServerPlayerEntity player : ((ServerWorld) entity.getWorld()).getPlayers()) {
            if(player.isInRange(entity, 128)) {
                ServerPlayNetworking.send(player, ModPackets.PLAY_SONG_ID, buf);
            }
        }
    }
}
