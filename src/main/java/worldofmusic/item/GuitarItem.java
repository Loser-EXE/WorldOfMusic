package worldofmusic.item;

public class GuitarItem extends Instrument {
    public GuitarItem(Settings settings) {
        super(settings);
        instrument = "guitar";

        registerSong("since_us");
        registerSong("godzilla_theme");
    }
}
