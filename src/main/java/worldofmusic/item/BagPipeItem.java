package worldofmusic.item;

public class BagPipeItem extends Instrument{
    public BagPipeItem(Settings settings) {
        super(settings);
        this.instrument = "bagpipes";

        register("bonnie_dundee", PlayCondition.RAID);
        register("blue_bonnets_over_the_border", PlayCondition.RAID);
    }
}
