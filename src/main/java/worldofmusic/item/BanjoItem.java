package worldofmusic.item;

public class BanjoItem extends Instrument {
    public BanjoItem(Settings settings) {
        super(settings);
        instrument = "banjo";

        registerSong("arkansas_traveler", PlayCondition.VILLAGES);
    }
}
