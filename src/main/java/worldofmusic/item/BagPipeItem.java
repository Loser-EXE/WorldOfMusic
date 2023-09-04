package worldofmusic.item;

public class BagPipeItem extends Instrument{
    public BagPipeItem(Settings settings) {
        super(settings);
        this.instrument = "bagpipes";

        registerSong("bonnie_dundee", PlayCondition.RAID);
        registerSong("blue_bonnets_over_the_border", PlayCondition.RAID);
        registerSong("amazing_grace", PlayCondition.VILLAGES);
        registerSong("ballad_of_glencoe", PlayCondition.VILLAGES);
        registerSong("balmoral", PlayCondition.VILLAGES);
        registerSong("battle_of_the_somme", PlayCondition.VILLAGES);
        registerSong("battle_of_waterloo", PlayCondition.VILLAGES);
        registerSong("cock_o_the_north", PlayCondition.VILLAGES);
        registerSong("lilliburlero", PlayCondition.VILLAGES, PlayCondition.RAID);
        registerSong("scotland_the_brave", PlayCondition.VILLAGES);
        registerSong("the_black_bear", PlayCondition.VILLAGES);
        registerSong("highlandcathedral", PlayCondition.VILLAGES);
        registerSong("myhome", PlayCondition.VILLAGES);
        registerSong("skyeboatsong", PlayCondition.VILLAGES);
    }
}
