package worldofmusic.item;

public class TinWhistleItem extends Instrument {
    public TinWhistleItem(Settings settings) {
        super(settings);
        instrument = "tin_whistle";

        registerSong("amazing_grace", PlayCondition.VILLAGES);
    }
}
