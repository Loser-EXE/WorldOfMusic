package worldofmusic;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import worldofmusic.entity.ModEntities;
import worldofmusic.item.ModItems;
import worldofmusic.networking.ModPackets;

public class WorldOfMusic implements ModInitializer {
    public static final String MOD_ID = "worldofmusic";
    public static final Logger LOGGER = LoggerFactory.getLogger("WorldOfMusic");

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModEntities.registerEntities();
        ModPackets.registerC2SPackets();
    }
}
