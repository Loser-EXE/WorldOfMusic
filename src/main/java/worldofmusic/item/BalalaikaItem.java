package worldofmusic.item;

public class BalalaikaItem extends Instrument {
    public BalalaikaItem(Settings settings) {
        super(settings);
        instrument = "balalaika";

        registerSong("kalinka", PlayCondition.VILLAGES);
    }
}
