package worldofmusic.item;

public class BugleItem extends Instrument {
    public BugleItem(Settings settings) {
        super(settings);
        this.instrument = "bugle";

        register("british_boots", PlayCondition.NONE);
        register("british_light_infantry", PlayCondition.NONE);
        register("charge_orders", PlayCondition.NONE);
        register("dans_le_hussards", PlayCondition.NONE);
        register("la_marche", PlayCondition.NONE);
        register("reveille", PlayCondition.NONE);
        register("to_the_color", PlayCondition.NONE);
    }
}
