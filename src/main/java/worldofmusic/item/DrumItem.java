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
        registerSong("british_grenadiers", PlayCondition.PATROL, PlayCondition.RAID);
        registerSong("the_girl_i_left_behind_me", PlayCondition.PATROL, PlayCondition.RAID);
        registerSong("la_grenadiere", PlayCondition.PATROL, PlayCondition.RAID);
        registerSong("lilliburlero", PlayCondition.OUTPOST, PlayCondition.RAID);
        registerSong("le_pas_cadence", PlayCondition.OUTPOST, PlayCondition.RAID);
        registerSong("aux_champs", PlayCondition.OUTPOST, PlayCondition.RAID);
        registerSong("hohenfriedeberger", PlayCondition.RAID);
        registerSong("grenadiersmarsch", PlayCondition.RAID);
        registerSong("coburger_marsch", PlayCondition.RAID);
        registerSong("pappenheimer", PlayCondition.RAID);
        registerSong("prinz_von_eugen", PlayCondition.RAID);
        registerSong("pariser_einzugsmarsch", PlayCondition.RAID);
        registerSong("marsch_der_spielleute", PlayCondition.RAID);
        registerSong("rule_britannia", PlayCondition.RAID);
        registerSong("lockmarsch");
        registerSong("march_of_attacking", PlayCondition.RAID);
        registerSong("semenovsky", PlayCondition.RAID);
        registerSong("praesentiermarsch", PlayCondition.RAID);
        registerSong("izmailovsky", PlayCondition.RAID);
        registerSong("preobrazhensky", PlayCondition.RAID);
        registerSong("grenadiers", PlayCondition.RAID);
        registerSong("yorckscher", PlayCondition.RAID);
        registerSong("signal_drummers_call");
        registerSong("signal_cease_fire");
        registerSong("signal_camp_taps");
    }
}
