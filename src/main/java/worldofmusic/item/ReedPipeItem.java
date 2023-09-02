package worldofmusic.item;

public class ReedPipeItem extends Instrument {
    public ReedPipeItem(Settings settings) {
        super(settings);
        instrument = "reed_pipe";

        registerSong("on_the_farm", PlayCondition.VILLAGES);
    }
}
