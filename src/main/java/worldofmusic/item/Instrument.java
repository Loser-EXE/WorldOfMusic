package worldofmusic.item;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import worldofmusic.WorldOfMusic;
import worldofmusic.networking.ModPackets;
import worldofmusic.sound.SongManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Instrument extends Item {
    protected String instrument;
    private final Map<PlayCondition, List<String>> songs = new HashMap<>();

    public Instrument(Settings settings) {
        super(settings);

        songs.put(PlayCondition.NONE, new ArrayList<>());
        songs.put(PlayCondition.RAID, new ArrayList<>());
        songs.put(PlayCondition.OUTPOST, new ArrayList<>());
        songs.put(PlayCondition.PATROL, new ArrayList<>());
        songs.put(PlayCondition.VILLAGES, new ArrayList<>());

    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient()) return TypedActionResult.success(user.getStackInHand(hand));

        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(Item.getRawId(this));

        ServerPlayNetworking.send(
                (ServerPlayerEntity) user,
                ModPackets.OPEN_MUSIC_SELECT_SCREEN, buf);

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return false;
    }

    public String getInstrumentName() {
        return this.instrument;
    }

    public List<String> getSongs(PlayCondition condition) {
        return songs.get(condition);
    }

    protected void registerSong(String song, PlayCondition ... conditions) {
        Identifier identifier = new Identifier(WorldOfMusic.MOD_ID, song + "_" + this.instrument);
        Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
        SongManager.addSongInstrument(song, getInstrumentName());
        songs.get(PlayCondition.NONE).add(song);

        for(PlayCondition condition : conditions) {
            songs.get(condition).add(song);
        }
    }

    public enum PlayCondition {
        RAID,
        OUTPOST,
        PATROL,
        VILLAGES,
        NONE
    }
}
