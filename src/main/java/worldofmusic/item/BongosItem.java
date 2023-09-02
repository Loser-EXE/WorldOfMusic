package worldofmusic.item;

public class BongosItem extends Instrument {
    public BongosItem(Settings settings) {
        super(settings);
        instrument = "bongos";

        registerSong("solo", PlayCondition.VILLAGES);
    }
}
