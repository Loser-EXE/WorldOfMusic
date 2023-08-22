package worldofmusic.item;

public class FifeItem extends Instrument {
    public FifeItem(Settings settings) {
        super(settings);
        this.instrument = "fife";

        registerSong("men_of_harlech", PlayCondition.RAID);
        registerSong("la_diane", PlayCondition.RAID);
        registerSong("la_charge", PlayCondition.RAID);
        registerSong("british_grenadiers", PlayCondition.PATROL);
        registerSong("the_girl_i_left_behind_me", PlayCondition.PATROL);
        registerSong("la_grenadiere", PlayCondition.PATROL);
        registerSong("lilliburlero", PlayCondition.OUTPOST);
        registerSong("le_pas_cadence", PlayCondition.OUTPOST);
        registerSong("aux_champs", PlayCondition.OUTPOST);
        registerSong("hohenfriedeberger");
    }
}
