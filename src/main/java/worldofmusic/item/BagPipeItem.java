package worldofmusic.item;

public class BagPipeItem extends Instrument{
    public BagPipeItem(Settings settings) {
        super(settings);
        this.instrument = "bagpipes";

        register("bonnie_dundee", PlayCondition.RAID);
        register("blue_bonnets_over_the_border", PlayCondition.RAID);
        register("amazing_grace", PlayCondition.NONE);
        register("ballad_of_glencoe", PlayCondition.NONE);
        register("balmoral", PlayCondition.NONE);
        register("battle_of_the_somme", PlayCondition.NONE);
        register("battle_of_waterloo", PlayCondition.NONE);
        register("cock_o_the_north", PlayCondition.NONE);
        register("lilliburlero", PlayCondition.NONE);
        register("scotland_the_brave", PlayCondition.NONE);
        register("the_black_bear", PlayCondition.NONE);
    }
}
