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
        registerSong("grenadiersmarsch");
        registerSong("coburger_marsch");
        registerSong("pappenheimer");
        registerSong("prinz_von_eugen");
        registerSong("pariser_einzugsmarsch");
        registerSong("marsch_der_spielleute");
        registerSong("rule_britannia");
        registerSong("lockmarsch");
        registerSong("march_of_attacking");
        registerSong("semenovsky");
        registerSong("praesentiermarsch");
        registerSong("izmailovsky");
        registerSong("preobrazhensky");
        registerSong("grenadiers");
        registerSong("yorckscher");
    }
}
