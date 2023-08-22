package worldofmusic.item;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import worldofmusic.WorldOfMusic;
import worldofmusic.networking.ModPackets;

import java.util.ArrayList;
import java.util.List;

public abstract class Instrument extends Item {
    protected String instrument;
    private final List<String> songs = new ArrayList<>();
    private final List<String> raidSongs = new ArrayList<>();
    private final List<String> outpostSongs = new ArrayList<>();
    private final List<String> patrolSongs = new ArrayList<>();
    private final List<String> villageSongs = new ArrayList<>();

    public Instrument(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient()) return super.use(world, user, hand);

        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(Item.getRawId(this));

        ServerPlayNetworking.send(
                (ServerPlayerEntity) user,
                ModPackets.OPEN_MUSIC_SELECT_SCREEN_ID, buf);

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    public String getInstrumentName() {
        return this.instrument;
    }

    public List<String> getSongs(PlayCondition condition) {
        switch (condition) {
            case RAID -> {
                return this.raidSongs;
            }
            case OUTPOST -> {
                return this.outpostSongs;
            }
            case PATROL -> {
                return this.patrolSongs;
            }
            case VILLAGES -> {
                return this.villageSongs;
            }
            default -> {
                return this.songs;
            }
        }
    }

    protected void registerSong(String song, PlayCondition ... conditions) {
        Identifier identifier = new Identifier(WorldOfMusic.MOD_ID, song + "_" + this.instrument);
        Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
        songs.add(song);

        for(PlayCondition condition : conditions) {
            switch (condition) {
                case RAID -> raidSongs.add(song);
                case PATROL -> patrolSongs.add(song);
                case OUTPOST -> outpostSongs.add(song);
                case VILLAGES -> villageSongs.add(song);
            }
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
