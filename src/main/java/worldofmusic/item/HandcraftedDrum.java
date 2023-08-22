package worldofmusic.item;

public class HandcraftedDrum extends Instrument {
    public HandcraftedDrum(Settings settings) {
        super(settings);
        instrument = "handcrafted_drum";

        registerSong("march_01");
        registerSong("march_02");
        registerSong("march_03");
        registerSong("march_04");
        registerSong("march_05");
    }
}
