package worldofmusic.item;

public class DrumItem extends Instrument{
    public DrumItem(Settings settings) {
        super(settings);
        this.instrument = "drum";
        
        register("men_of_harlech", PlayCondition.RAID);
        register("la_diane", PlayCondition.RAID);
        register("la_charge", PlayCondition.RAID);
        register("bonnie_dundee", PlayCondition.RAID);
        register("blue_bonnets_over_the_border", PlayCondition.RAID);
        register("british_grenadiers", PlayCondition.PATROL);
        register("the_girl_i_left_behind_me", PlayCondition.PATROL);
        register("la_grenadiere", PlayCondition.PATROL);
        register("lilliburlero", PlayCondition.OUTPOST);
        register("le_pas_cadence", PlayCondition.OUTPOST);
        register("aux_champs", PlayCondition.OUTPOST);
    }
}
