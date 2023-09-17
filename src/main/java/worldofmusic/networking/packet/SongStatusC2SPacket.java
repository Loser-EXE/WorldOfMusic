package worldofmusic.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import worldofmusic.entity.pillager.MusicianPillagerEntity;

public class SongStatusC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        Entity entity = player.getWorld().getEntityById(buf.readInt());
        if(entity instanceof MusicianPillagerEntity musicianPillager) {
            musicianPillager.setSongStatus(buf.readInt());
        }
    }
}
