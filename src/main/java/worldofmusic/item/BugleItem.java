package worldofmusic.item;

public class BugleItem extends Instrument {
    public BugleItem(Settings settings) {
        super(settings);
        this.instrument = "bugle";

        registerSong("british_boots");
        registerSong("british_light_infantry");
        registerSong("charge_orders");
        registerSong("dans_le_hussards");
        registerSong("la_marche");
        registerSong("reveille");
        registerSong("to_the_color");
    }
}
