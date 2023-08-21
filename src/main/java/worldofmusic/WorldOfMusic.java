package worldofmusic;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import worldofmusic.block.ModBlocks;
import worldofmusic.entity.ModEntities;
import worldofmusic.entity.MusicianVillager;
import worldofmusic.item.ModItems;
import worldofmusic.networking.ModPackets;

public class WorldOfMusic implements ModInitializer {
    public static final String MOD_ID = "worldofmusic";
    public static final Logger LOGGER = LoggerFactory.getLogger("WorldOfMusic");

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        ModEntities.registerEntities();
        ModPackets.registerC2SPackets();
        MusicianVillager.registerMusicianVillager();
    }
}
