package worldofmusic.item;

public class DrumItem extends Instrument{
    public DrumItem(Settings settings) {
        super(settings);
        this.instrument = "drum";
        
        registerSong("men_of_harlech", PlayCondition.RAID);
        registerSong("la_diane", PlayCondition.RAID);
        registerSong("la_charge", PlayCondition.RAID);
        registerSong("bonnie_dundee", PlayCondition.RAID);
        registerSong("blue_bonnets_over_the_border", PlayCondition.RAID);
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
        registerSong("signal_drummers_call");
        registerSong("signal_cease_fire");
        registerSong("signal_camp_taps");
    }
}
