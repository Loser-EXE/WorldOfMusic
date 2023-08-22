package worldofmusic.item;

public class HandcraftedFife extends Instrument{
    public HandcraftedFife(Settings settings) {
        super(settings);
        this.instrument = "handcrafted_fife";

        registerSong("march_01");
        registerSong("march_02");
        registerSong("march_03");
        registerSong("march_04");
        registerSong("march_05");
    }
}
