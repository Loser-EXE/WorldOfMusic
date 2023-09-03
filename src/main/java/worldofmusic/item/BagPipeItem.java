package worldofmusic.item;

public class BagPipeItem extends Instrument{
    public BagPipeItem(Settings settings) {
        super(settings);
        this.instrument = "bagpipes";

        registerSong("bonnie_dundee", PlayCondition.RAID);
        registerSong("blue_bonnets_over_the_border", PlayCondition.RAID);
        registerSong("amazing_grace");
        registerSong("ballad_of_glencoe");
        registerSong("balmoral");
        registerSong("battle_of_the_somme");
        registerSong("battle_of_waterloo");
        registerSong("cock_o_the_north");
        registerSong("lilliburlero");
        registerSong("scotland_the_brave");
        registerSong("the_black_bear", PlayCondition.VILLAGES);
        registerSong("highlandcathedral");
        registerSong("myhome");
        registerSong("skyeboatsong");
    }
}
