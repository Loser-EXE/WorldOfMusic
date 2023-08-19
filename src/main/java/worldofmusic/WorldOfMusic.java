package worldofmusic;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import worldofmusic.block.ModBlocks;
import worldofmusic.entity.ModEntities;
import worldofmusic.item.ModItems;
import worldofmusic.mixin.PoiTypesInvoker;
import worldofmusic.mixin.VillagerProfessionInvoker;
import worldofmusic.networking.ModPackets;

public class WorldOfMusic implements ModInitializer {
    public static final String MOD_ID = "worldofmusic";
    public static final Logger LOGGER = LoggerFactory.getLogger("WorldOfMusic");
    public static final PointOfInterestType workstation = PoiTypesInvoker.registerInvoker("musician", PoiTypesInvoker.getAllStatesOfInvoker(ModBlocks.MUSICIAN_WORKSTATION), 1, 1);
    public static final VillagerProfession MUSICIAN = VillagerProfessionInvoker.initInvoker("musician", WorldOfMusic.workstation, ImmutableSet.of(), ImmutableSet.of(), null);

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        ModEntities.registerEntities();
        ModPackets.registerC2SPackets();

        Registry.register(Registry.VILLAGER_PROFESSION, new Identifier(WorldOfMusic.MOD_ID, "musician"), MUSICIAN);
    }
}
