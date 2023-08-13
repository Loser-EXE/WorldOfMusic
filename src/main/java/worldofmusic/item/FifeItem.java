package worldofmusic.item;

public class FifeItem extends Instrument {
    public FifeItem(Settings settings) {
        super(settings);
        this.instrument = "fife";

        register("men_of_harlech", PlayCondition.RAID);
        register("la_diane", PlayCondition.RAID);
        register("la_charge", PlayCondition.RAID);
        register("british_grenadiers", PlayCondition.PATROL);
        register("the_girl_i_left_behind_me", PlayCondition.PATROL);
        register("la_grenadiere", PlayCondition.PATROL);
        register("lilliburlero", PlayCondition.OUTPOST);
        register("le_pas_cadence", PlayCondition.OUTPOST);
        register("aux_champs", PlayCondition.OUTPOST);
    }
}
