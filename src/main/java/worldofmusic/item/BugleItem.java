package worldofmusic.item;

public class BugleItem extends Instrument {
    public BugleItem(Settings settings) {
        super(settings);
        this.instrument = "bugle";

        registerSong("british_boots");
        registerSong("british_light_infantry");
        registerSong("danslehussards");
        registerSong("althessischer");
        registerSong("fehrbell");
        registerSong("freiwilligen");
        registerSong("dans_le_hussards");
        registerSong("la_marche");
        registerSong("jaegers");
        registerSong("marchartillery");
        registerSong("musketeers");
        registerSong("reveille");
        registerSong("to_the_color");
        registerSong("leban");
        registerSong("strauch");
        registerSong("charge_orders");
        registerSong("assamble_call");
        registerSong("closeranks_call");
        registerSong("extend_call");
        registerSong("fire_call");
        registerSong("ontheenemy_call");
    }
}
