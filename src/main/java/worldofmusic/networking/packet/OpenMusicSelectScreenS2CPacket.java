package worldofmusic.networking.packet;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import worldofmusic.gui.MusicSelectScreen;

import java.util.UUID;

@Environment(EnvType.CLIENT)
public class OpenMusicSelectScreenS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        int itemId = buf.readInt();
        client.execute(() -> client.setScreen(new MusicSelectScreen(itemId)));
    }
}
